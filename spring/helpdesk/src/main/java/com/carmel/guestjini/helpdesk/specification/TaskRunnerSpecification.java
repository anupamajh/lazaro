package com.carmel.guestjini.helpdesk.specification;

import com.carmel.guestjini.helpdesk.model.TaskRunner;
import org.springframework.data.jpa.domain.Specification;

public class TaskRunnerSpecification {
    public static Specification<TaskRunner> textInAllColumns(String searchText, String clientId) {
        if (!searchText.contains("%")) {
            searchText = "%" + searchText + "%";
        }
        String finalText = searchText;
        return  (root, query, builder) -> builder.and(builder.or(
                builder.like(root.get("title"), finalText),
                builder.like(root.get("description"), finalText)
                ),
                builder.equal(root.get("isDeleted"), 0),
                builder.equal(root.get("clientId"), clientId)
        );
    }
}
