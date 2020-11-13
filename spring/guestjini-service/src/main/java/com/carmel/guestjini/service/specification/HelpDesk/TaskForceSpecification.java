package com.carmel.guestjini.service.specification.HelpDesk;

import com.carmel.guestjini.service.model.HelpDesk.TaskForce;
import org.springframework.data.jpa.domain.Specification;

public class TaskForceSpecification {
    public static Specification<TaskForce> textInAllColumns(String clientId, String searchText) {
        if (!searchText.contains("%")) {
            searchText = "%" + searchText + "%";
        }
        String finalText = searchText;
        return (root, query, builder) -> builder.and(builder.or(
                builder.like(root.get("phone"), finalText)
                ),
                builder.equal(root.get("isDeleted"), 0),
                builder.equal(root.get("clientId"), clientId)
        );
    }
}
