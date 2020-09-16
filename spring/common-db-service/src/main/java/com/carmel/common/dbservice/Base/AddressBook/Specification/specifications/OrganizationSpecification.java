package com.carmel.common.dbservice.Base.AddressBook.Specification.specifications;

import com.carmel.common.dbservice.Base.Client.Model.Client;
import com.carmel.common.dbservice.Base.Organization.Model.Organization;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class OrganizationSpecification {

    public static Specification<Organization> textInAllColumns(String searchText, Client client) {
        if (!searchText.contains("%")) {
            searchText = "%" + searchText + "%";
        }
        String finalText = searchText;
        return new Specification<Organization>() {
            public Predicate toPredicate(Root<Organization> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                return builder.and(builder.or(
                        builder.like(root.get("orgName"), finalText),
                        builder.like(root.get("description"), finalText),
                        builder.like(root.get("orgDomain"), finalText)
                        ),
                        builder.equal(root.get("isDeleted"), 0),
                        builder.equal(root.get("client"), client)
                );
            }
        };
    }
}
