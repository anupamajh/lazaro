package com.carmel.common.dbservice.specifications;

import com.carmel.common.dbservice.model.Client;
import com.carmel.common.dbservice.model.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    public static Specification<User> textInAllColumns(String searchText, Client client) {
        if (!searchText.contains("%")) {
            searchText = "%" + searchText + "%";
        }
        String finalText = searchText;
        return (root, query, builder) -> builder.and(builder.or(
                builder.like(root.get("fullName"), finalText),
                builder.like(root.get("userName"), finalText)
                ),
                builder.equal(root.get("isDeleted"), 0),
                builder.equal(root.get("client"), client)
        );
    }
}
