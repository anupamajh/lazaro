package com.carmel.guestjini.service.specification.HelpDesk;


import com.carmel.guestjini.service.model.HelpDesk.KB;
import org.springframework.data.jpa.domain.Specification;

public class KBSpecification {

    public static Specification<KB> textInAllColumns(String searchText, String clientId) {
        if (!searchText.contains("%")) {
            searchText = "%" + searchText + "%";
        }
        String finalText = searchText;
        return (root, query, builder) -> builder.and(builder.or(
                builder.like(root.get("topicTitle"), finalText),
                builder.like(root.get("topicNarration"), finalText)
                ),
                builder.equal(root.get("isDeleted"), 0),
                builder.equal(root.get("clientId"), clientId)
        );
    }
}
