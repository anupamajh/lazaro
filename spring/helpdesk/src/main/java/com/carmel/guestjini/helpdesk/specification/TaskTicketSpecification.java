package com.carmel.guestjini.helpdesk.specification;

import com.carmel.guestjini.helpdesk.model.TaskTicket;
import org.springframework.data.jpa.domain.Specification;

public class TaskTicketSpecification {
    public static Specification<TaskTicket> textInAllColumns(String searchText, String requesterId) {
        if (!searchText.contains("%")) {
            searchText = "%" + searchText + "%";
        }
        String finalText = searchText;
        return (Specification<TaskTicket>) (root, query, builder) -> builder.and(builder.or(
                builder.like(root.get("ticketNumber"), finalText),
                builder.like(root.get("ticketNarration"), finalText),
                builder.like(root.get("ticketTitle"), finalText)
                ),
                builder.equal(root.get("isDeleted"), 0),
                builder.equal(root.get("requesterId"), requesterId)
        );
    }
}
