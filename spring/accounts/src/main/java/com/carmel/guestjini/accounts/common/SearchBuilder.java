package com.carmel.guestjini.accounts.common;


import com.carmel.guestjini.accounts.request.SearchCriteria;
import com.carmel.guestjini.accounts.request.SearchRequest;
import com.carmel.guestjini.accounts.request.SearchUnit;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.lang.reflect.Field;
import java.util.*;

public class SearchBuilder {

    public static CriteriaQuery buildSearch(
            EntityManager entityManager,
            CriteriaBuilder criteriaBuilder,
            CriteriaQuery<?> criteriaQuery,
            Root<?> root,
            Class<?> cls,
            SearchRequest searchRequest
    ) {
        HashMap<String, Predicate> predicateHashMap = buildCriteriaQuery(
                entityManager,
                criteriaBuilder,
                criteriaQuery,
                root,
                cls,
                searchRequest
        );
        Predicate andPredicate = predicateHashMap.get("and");
        Predicate orPredicate = predicateHashMap.get("or");

        if (andPredicate != null && orPredicate != null) {
            criteriaQuery.where(andPredicate, orPredicate);
        } else if (andPredicate != null) {
            criteriaQuery.where(andPredicate);
        } else if (orPredicate != null) {
            criteriaQuery.where(orPredicate);
        }
        if (searchRequest.getOrderUnits() != null) {
            if (!searchRequest.getOrderUnits().isEmpty()) {
                List<Order> orders = new ArrayList<>();
                searchRequest.getOrderUnits().forEach(orderUnit -> {
                    if (orderUnit.getDirection().toLowerCase().trim().equals("asc")) {
                        orders.add(criteriaBuilder.asc(root.get(orderUnit.getOrderBy().trim())));
                    } else {
                        orders.add(criteriaBuilder.desc(root.get(orderUnit.getOrderBy().trim())));

                    }
                });
                criteriaQuery.orderBy(orders);
            }
        }

        return criteriaQuery;
    }


    private static HashMap<String, Predicate> buildCriteriaQuery(
            EntityManager entityManager,
            CriteriaBuilder criteriaBuilder,
            CriteriaQuery<?> criteriaQuery,
            Root<?> root,
            Class<?> cls,
            SearchRequest searchRequest
    ) {
        List<Predicate> andPredicates = new ArrayList<>();
        List<Predicate> orPredicates = new ArrayList<>();
        for (SearchCriteria searchCriteria : searchRequest.getSearchCriteria()) {
            Predicate predicate = processSearchUnits(
                    entityManager,
                    criteriaBuilder,
                    criteriaQuery,
                    root,
                    cls,
                    searchCriteria.getSearchUnitCondition(),
                    searchCriteria.getSearchUnits()
            );

            if (searchCriteria.getCondition().trim().toLowerCase().equals("and")) {
                andPredicates.add(predicate);
            } else {
                orPredicates.add(predicate);
            }
        }
        Predicate andPredicate = null;
        Predicate orPredicate = null;

        if (!andPredicates.isEmpty()) {
            andPredicate = criteriaBuilder.and(andPredicates.stream().toArray(Predicate[]::new));
        }
        if (!orPredicates.isEmpty()) {
            orPredicate = criteriaBuilder.or(orPredicates.stream().toArray(Predicate[]::new));
        }
        HashMap<String, Predicate> retValue = new HashMap<>();
        retValue.put("and", andPredicate);
        retValue.put("or", orPredicate);
        return retValue;
    }

    public static long getTotalRecordCount(
            EntityManager entityManager,
            CriteriaBuilder criteriaBuilder,
            CriteriaQuery<?> criteriaQuery,
            Root<?> root
    ) {
        final CriteriaQuery<Long> countCriteria = criteriaBuilder.createQuery(Long.class);
        countCriteria.select(criteriaBuilder.count(root));
        for (Root<?> fromRoot : criteriaQuery.getRoots()) {
            countCriteria.getRoots().add(fromRoot);
        }
        Predicate andPredicate = criteriaQuery.getRestriction();
        Predicate groupPredicate = criteriaQuery.getGroupRestriction();

        if (andPredicate != null) {
            countCriteria.where(andPredicate);
        }
        if (groupPredicate != null) {
            countCriteria.having(groupPredicate);
        }
        countCriteria.groupBy(criteriaQuery.getGroupList());
        countCriteria.distinct(criteriaQuery.isDistinct());
        return entityManager.createQuery(countCriteria).getSingleResult();
    }

    private static Predicate processSearchUnits(
            EntityManager entityManager,
            CriteriaBuilder criteriaBuilder,
            CriteriaQuery<?> criteriaQuery,
            Root<?> root,
            Class<?> cls,
            String searchUnitCondition,
            List<SearchUnit> searchUnits
    ) {
        List<Predicate> predicates = new ArrayList<>();
        List<Field> fields = Arrays.asList(cls.getDeclaredFields());
        for (SearchUnit searchUnit : searchUnits) {
            Optional<Field> optionalField = fields.stream().filter(
                    f -> f.getName()
                            .toLowerCase()
                            .trim()
                            .equals(
                                    searchUnit.getField()
                                            .toLowerCase()
                                            .trim()
                            )
            )
                    .findFirst();
            if (optionalField.isPresent()) {
                Field field = optionalField.get();
                Predicate predicate = null;
                if (String.class.equals(field.getType())) {
                    predicate = processStringPredicate(
                            criteriaBuilder,
                            root,
                            searchUnit
                    );
                } else if (int.class.equals(field.getType())) {
                    predicate = processIntPredicate(
                            criteriaBuilder,
                            root,
                            searchUnit
                    );
                } else if (Date.class.equals(field.getType())) {
                    predicate = processDatePredicate(
                            criteriaBuilder,
                            root,
                            searchUnit
                    );
                }
                if (predicate != null) {
                    predicates.add(predicate);
                }
            }
        }
        predicates.add(criteriaBuilder
                .equal(
                        root.get("isDeleted"),
                        0
                ));
        if (searchUnitCondition.trim().toLowerCase().equals("and")) {
            return criteriaBuilder.and(predicates.stream().toArray(Predicate[]::new));
        } else {
            return criteriaBuilder.or(predicates.stream().toArray(Predicate[]::new));
        }
    }

    private static Predicate processDatePredicate(CriteriaBuilder criteriaBuilder, Root<?> root, SearchUnit searchUnit) {
        Predicate predicate = null;
        switch (searchUnit.getOperator().trim().toLowerCase()) {
            case "lessthan": {
                predicate =
                        criteriaBuilder.lessThan(
                                root.get(searchUnit.getField()),
                                DateUtil.convertToDate(searchUnit.getValue())
                        );
            }
            break;
            case "lessthanorequal": {
                predicate =
                        criteriaBuilder
                                .lessThanOrEqualTo(
                                        root.get(searchUnit.getField()),
                                        DateUtil.convertToDate(searchUnit.getValue())
                                );
            }
            break;
            case "greaterthan": {
                predicate =
                        criteriaBuilder
                                .greaterThan(
                                        root.get(searchUnit.getField()),
                                        DateUtil.convertToDate(searchUnit.getValue())
                                );
            }
            break;
            case "greaterthanorequal": {
                predicate =
                        criteriaBuilder
                                .greaterThanOrEqualTo(
                                        root.get(searchUnit.getField()),
                                        DateUtil.convertToDate(searchUnit.getValue())
                                );
            }
            break;
            case "notequal": {
                predicate =
                        criteriaBuilder
                                .notEqual(
                                        root.get(searchUnit.getField()),
                                        DateUtil.convertToDate(searchUnit.getValue())
                                );
            }
            break;
            case "between": {
                predicate =
                        criteriaBuilder
                                .between(
                                        root.get(searchUnit.getField()),
                                        DateUtil.convertToDate(searchUnit.getValue()),
                                        DateUtil.convertToDate(searchUnit.getValue1())
                                );
            }
            break;

            default: {
                predicate =
                        criteriaBuilder
                                .equal(
                                        root.get(searchUnit.getField()),
                                        DateUtil.convertToDate(searchUnit.getValue())
                                );
            }

        }
        return predicate;

    }

    private static Predicate processIntPredicate(CriteriaBuilder criteriaBuilder, Root<?> root, SearchUnit searchUnit) {
        Predicate predicate = null;
        int intValue = Integer.parseInt(searchUnit.getValue());
        switch (searchUnit.getOperator().trim().toLowerCase()) {
            case "lessthan": {
                predicate =
                        criteriaBuilder.lessThan(
                                root.get(searchUnit.getField()),
                                intValue
                        );
            }
            break;
            case "lessthanorequal": {
                predicate =
                        criteriaBuilder
                                .lessThanOrEqualTo(
                                        root.get(searchUnit.getField()),
                                        intValue
                                );
            }
            break;
            case "greaterthan": {
                predicate =
                        criteriaBuilder
                                .greaterThan(
                                        root.get(searchUnit.getField()),
                                        intValue
                                );
            }
            break;
            case "greaterthanorequal": {
                predicate =
                        criteriaBuilder
                                .greaterThanOrEqualTo(
                                        root.get(searchUnit.getField()),
                                        intValue
                                );
            }
            break;
            case "notequal": {
                predicate =
                        criteriaBuilder
                                .notEqual(
                                        root.get(searchUnit.getField()),
                                        intValue
                                );
            }
            break;
            default: {
                predicate =
                        criteriaBuilder
                                .equal(
                                        root.get(searchUnit.getField()),
                                        intValue
                                );
            }

        }
        return predicate;
    }

    private static Predicate processStringPredicate(
            CriteriaBuilder criteriaBuilder,
            Root<?> root,
            SearchUnit searchUnit
    ) {
        Predicate predicate = null;
        switch (searchUnit.getOperator().trim().toLowerCase()) {
            case "like": {
                predicate =
                        criteriaBuilder
                                .like(
                                        root.get(searchUnit.getField()),
                                        "%" + searchUnit.getValue().trim() + "%"
                                );
            }
            break;
            case "notlike": {
                predicate =
                        criteriaBuilder
                                .notLike(
                                        root.get(searchUnit.getField()),
                                        "%" + searchUnit.getValue().trim() + "%"
                                );
            }
            break;
            case "lessthan": {
                predicate =
                        criteriaBuilder.lessThan(
                                root.get(searchUnit.getField()),
                                searchUnit.getValue()
                        );
            }
            break;
            case "lessthanorequal": {
                predicate =
                        criteriaBuilder
                                .lessThanOrEqualTo(
                                        root.get(searchUnit.getField()),
                                        searchUnit.getValue()
                                );
            }
            break;
            case "greaterthan": {
                predicate =
                        criteriaBuilder
                                .greaterThan(
                                        root.get(searchUnit.getField()),
                                        searchUnit.getValue()
                                );
            }
            break;
            case "greaterthanorequal": {
                predicate =
                        criteriaBuilder
                                .greaterThanOrEqualTo(
                                        root.get(searchUnit.getField()),
                                        searchUnit.getValue()
                                );
            }
            break;
            case "notequal": {
                predicate =
                        criteriaBuilder
                                .notEqual(
                                        root.get(searchUnit.getField()),
                                        searchUnit.getValue()
                                );
            }
            break;
            default: {
                predicate =
                        criteriaBuilder
                                .equal(
                                        root.get(searchUnit.getField()),
                                        searchUnit.getValue()
                                );
            }

        }
        return predicate;
    }

    private static Predicate processDateSearchValue() {
        return null;
    }

    private static Object processSearchValue(String searchValue, Field field) {
        if (field.getType().equals(Integer.class)) {
            return Integer.parseInt(searchValue);
        }
        return null;
    }
}
