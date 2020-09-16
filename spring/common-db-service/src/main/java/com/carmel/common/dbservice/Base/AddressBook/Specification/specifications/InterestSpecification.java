package com.carmel.common.dbservice.Base.AddressBook.Specification.specifications;

import com.carmel.common.dbservice.Base.Interest.Model.Interest;
import org.springframework.data.jpa.domain.Specification;

public class InterestSpecification {

    public static Specification<Interest> textInAllColumns(String searchText, String clientId) {
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
