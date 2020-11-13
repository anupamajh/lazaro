package com.carmel.guestjini.service.specification.HelpDesk;

import com.carmel.guestjini.service.model.HelpDesk.TaskForceGroup;
import org.springframework.data.jpa.domain.Specification;

public class TaskForceGroupSpecification {
    public static Specification<TaskForceGroup> textInAllColumns(String clientId, String searchText) {
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
