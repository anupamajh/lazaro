package com.carmel.guestjini.service.specification.HelpDesk;


import com.carmel.guestjini.service.model.HelpDesk.TaskTicketCategories;
import org.springframework.data.jpa.domain.Specification;

public class TaskTicketCategoriesSpecification {
    public static Specification<TaskTicketCategories> textInAllColumns(String searchText, String clientId) {
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
