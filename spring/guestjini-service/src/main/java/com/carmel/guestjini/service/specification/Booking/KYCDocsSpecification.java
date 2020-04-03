package com.carmel.guestjini.service.specification.Booking;

import com.carmel.guestjini.service.model.Booking.Booking;
import com.carmel.guestjini.service.model.Booking.KYC;
import com.carmel.guestjini.service.model.Booking.KYCDocs;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;

public class KYCDocsSpecification {
    public static Specification<KYCDocs> textInAllColumns(String searchText, String clientId, Booking booking) {
        if (!searchText.contains("%")) {
            searchText = "%" + searchText + "%";
        }
        String finalText = searchText;
        return Specification
                .where(textSearch(finalText,clientId))
                .or(bookingSearch(booking));
    }

    public static Specification<KYCDocs> textSearch(String finalText, String clientId) {
        return (Specification<KYCDocs>) (root, query, builder) -> builder.and(builder.or(
                builder.like(root.get("type"), finalText),
                builder.like(root.get("doc_title"), finalText),
                builder.like(root.get("name"), finalText)
                ),
                builder.equal(root.get("isDeleted"), 0),
                builder.equal(root.get("clientId"), clientId)
        );
    }
    public static Specification<KYCDocs> bookingSearch(Booking booking) {
        return (root, query, builder) -> {
            Join<KYC, Booking> bookingJoin = root.join("booking");
            return builder.and(
                    builder.equal(bookingJoin.get("id"), booking.getId())
            );
        };
    }
}
