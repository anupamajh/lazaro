package com.carmel.common.dbservice.specifications;

import com.carmel.common.dbservice.model.ApplicationConstant;
import org.springframework.data.jpa.domain.Specification;

public class ApplicationConstantSpecification {
    public static Specification<ApplicationConstant> textInAllColumns(String searchText, String clientId) {
        if (!searchText.contains("%")) {
            searchText = "%" + searchText + "%";
        }
        String finalText = searchText;
        return  (root, query, builder) -> builder.and(builder.or(
                builder.like(root.get("key"), finalText)
                ),
                builder.equal(root.get("clientId"), clientId)
        );
    }
}
