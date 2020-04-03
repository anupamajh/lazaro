package com.carmel.guestjini.service.specification.Accounts;

import com.carmel.guestjini.service.model.Accounts.AccountTicket;
import org.springframework.data.jpa.domain.Specification;

public class AccountTicketSpecification {
    public static Specification<AccountTicket> textInAllColumns(String searchText, String bookingId) {
        if (!searchText.contains("%")) {
            searchText = "%" + searchText + "%";
        }
        String finalText = searchText;
        return (Specification<AccountTicket>) (root, query, builder) -> builder.and(builder.or(
                builder.like(root.get("ticketNumber"), finalText),
                builder.like(root.get("ticketNarration"), finalText)
                ),
                builder.equal(root.get("isDeleted"), 0),
                builder.equal(root.get("bookingId"), bookingId)
        );
    }
}
