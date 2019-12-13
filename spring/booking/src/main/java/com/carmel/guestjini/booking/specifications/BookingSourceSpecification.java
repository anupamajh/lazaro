package com.carmel.guestjini.booking.specifications;

import com.carmel.guestjini.booking.model.BookingSource;
import org.springframework.data.jpa.domain.Specification;

public class BookingSourceSpecification {
    public static Specification<BookingSource> textInAllColumns(String searchText, String clientId) {
        if (!searchText.contains("%")) {
            searchText = "%" + searchText + "%";
        }
        String finalText = searchText;
        return (Specification<BookingSource>) (root, query, builder) -> builder.and(builder.or(
                builder.like(root.get("title"), finalText),
                builder.like(root.get("narration"), finalText)
                ),
                builder.equal(root.get("isDeleted"), 0),
                builder.equal(root.get("clientId"), clientId)
        );
    }
}
