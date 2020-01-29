package com.carmel.common.dbservice.specifications;

import com.carmel.common.dbservice.model.Group;
import org.springframework.data.jpa.domain.Specification;

public class GroupSpecification {
    public static Specification<Group> textInAllColumns(String searchText, String client) {
        if (!searchText.contains("%")) {
            searchText = "%" + searchText + "%";
        }
        String finalText = searchText;
        return (root, query, builder) -> builder.and(builder.or(
                builder.like(root.get("name"), finalText),
                builder.like(root.get("description"), finalText)
                ),
                builder.equal(root.get("isDeleted"), 0),
                builder.equal(root.get("client"),client )
        );
    }
}
