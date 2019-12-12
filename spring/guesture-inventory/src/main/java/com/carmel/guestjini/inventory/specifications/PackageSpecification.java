package com.carmel.guestjini.inventory.specifications;

import com.carmel.guestjini.inventory.model.Package;
import org.springframework.data.jpa.domain.Specification;

public class PackageSpecification {
    public static Specification<Package> textInAllColumns(String searchText, String clientId) {
        if (!searchText.contains("%")) {
            searchText = "%" + searchText + "%";
        }
        String finalText = searchText;
        return (Specification<Package>) (root, query, builder) -> builder.and(builder.or(
                builder.like(root.get("packageTitle"), finalText),
                builder.like(root.get("packageNarration"), finalText)
                ),
                builder.equal(root.get("isDeleted"), 0),
                builder.equal(root.get("clientId"), clientId)
        );
    }
}
