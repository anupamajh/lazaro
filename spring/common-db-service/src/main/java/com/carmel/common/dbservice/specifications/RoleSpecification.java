package com.carmel.common.dbservice.specifications;

import com.carmel.common.dbservice.model.Client;
import com.carmel.common.dbservice.model.Role;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class RoleSpecification {

    public static Specification<Role> textInAllColumns(String searchText, Client client) {
        if (!searchText.contains("%")) {
            searchText = "%" + searchText + "%";
        }
        String finalText = searchText;
        return new Specification<Role>() {
            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                return builder.and(builder.or(
                        builder.like(root.get("roleName"), finalText),
                        builder.like(root.get("description"), finalText),
                        builder.like(root.get("homePage"), finalText)
                        ),
                        builder.equal(root.get("isDeleted"), 0),
                        builder.equal(root.get("client"), client)
                );
            }
        };
    }
}
