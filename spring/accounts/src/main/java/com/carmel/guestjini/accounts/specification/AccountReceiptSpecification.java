package com.carmel.guestjini.accounts.specification;

import com.carmel.guestjini.accounts.model.AccountReceipts;
import org.springframework.data.jpa.domain.Specification;

public class AccountReceiptSpecification {
    public static Specification<AccountReceipts> textInAllColumns(String searchText, String bookingId) {
        if (!searchText.contains("%")) {
            searchText = "%" + searchText + "%";
        }
        String finalText = searchText;
        return (Specification<AccountReceipts>) (root, query, builder) -> builder.and(builder.or(
                builder.like(root.get("receiptNarration"), finalText),
                builder.like(root.get("instrumentNarration"), finalText)
                ),
                builder.equal(root.get("isDeleted"), 0),
                builder.equal(root.get("bookingId"), bookingId)
        );
    }
}
