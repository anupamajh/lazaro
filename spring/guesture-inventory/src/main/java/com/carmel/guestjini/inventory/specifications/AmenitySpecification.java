package com.carmel.guestjini.inventory.specifications;

import com.carmel.guestjini.inventory.model.Amenity;
import org.springframework.data.jpa.domain.Specification;

public class AmenitySpecification {
    public static Specification<Amenity> textInAllColumns(String searchText, String clientId) {
        if (!searchText.contains("%")) {
            searchText = "%" + searchText + "%";
        }
        String finalText = searchText;
        return (Specification<Amenity>) (root, query, builder) -> builder.and(builder.or(
                builder.like(root.get("title"), finalText),
                builder.like(root.get("narration"), finalText)
                ),
                builder.equal(root.get("isDeleted"), 0),
                builder.equal(root.get("clientId"), clientId)
        );
    }
}