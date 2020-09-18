package com.carmel.common.dbservice.Base.InterestCategory.Specification;

import com.carmel.common.dbservice.Base.InterestCategory.Model.InterestCategory;
import org.springframework.data.jpa.domain.Specification;

public class InterestCategorySpecification {
    public static Specification<InterestCategory> textInAllColumns(String searchText, String clientId) {
        if (!searchText.contains("%")) {
            searchText = "%" + searchText + "%";
        }
        String finalText = searchText;
        return (root, query, builder) -> builder.and(builder.or(
                builder.like(root.get("name"), finalText),
                builder.like(root.get("description"), finalText)
                ),
                builder.equal(root.get("isDeleted"), 0),
                builder.equal(root.get("clientId"), clientId)
        );
    }
}
