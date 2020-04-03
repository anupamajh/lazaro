package com.carmel.guestjini.service.specification.Inventory;

import com.carmel.guestjini.service.model.Inventory.DocumentTemplate;
import org.springframework.data.jpa.domain.Specification;

public class DocumentTemplateSpecification {
    public static Specification<DocumentTemplate> textInAllColumns(String searchText, String clientId) {
        if (!searchText.contains("%")) {
            searchText = "%" + searchText + "%";
        }
        String finalText = searchText;
        return (Specification<DocumentTemplate>) (root, query, builder) -> builder.and(builder.or(
                builder.like(root.get("title"), finalText),
                builder.like(root.get("narration"), finalText)
                ),
                builder.equal(root.get("isDeleted"), 0),
                builder.equal(root.get("clientId"), clientId)
        );
    }
}
