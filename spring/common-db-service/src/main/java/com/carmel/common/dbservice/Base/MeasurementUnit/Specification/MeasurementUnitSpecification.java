package com.carmel.common.dbservice.Base.MeasurementUnit.Specification;

import com.carmel.common.dbservice.Base.MeasurementUnit.Model.MeasurementUnit;
import org.springframework.data.jpa.domain.Specification;

public class MeasurementUnitSpecification {
    public static Specification<MeasurementUnit> textInAllColumns(String searchText, String clientId) {
        if (!searchText.contains("%")) {
            searchText = "%" + searchText + "%";
        }
        String finalText = searchText;
        return (Specification<MeasurementUnit>) (root, query, builder) -> builder.and(builder.or(
                builder.like(root.get("title"), finalText),
                builder.like(root.get("narration"), finalText)
                ),
                builder.equal(root.get("isDeleted"), 0),
                builder.equal(root.get("clientId"), clientId)
        );
    }
}
