package com.carmel.guestjini.booking.specifications;

import com.carmel.guestjini.booking.model.Booking;
import com.carmel.guestjini.booking.model.BookingAdditionalCharge;
import com.carmel.guestjini.booking.model.KYC;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;

public class BookingAdditionalChargeSpecification {

    public static Specification<BookingAdditionalCharge>
    textInAllColumns(String searchText, String clientId, Booking booking) {
        if (!searchText.contains("%")) {
            searchText = "%" + searchText + "%";
        }
        String finalText = searchText;
        return Specification
                .where(textSearch(finalText, clientId))
                .or(bookingSearch(booking));
    }

    public static Specification<BookingAdditionalCharge> textSearch(String finalText, String clientId) {
        return (root, query, builder) -> builder.and(builder.or(
                builder.like(root.get("title"), finalText)
                ),
                builder.equal(root.get("isDeleted"), 0),
                builder.equal(root.get("clientId"), clientId)
        );
    }

    public static Specification<BookingAdditionalCharge> bookingSearch(Booking booking) {
        return (root, query, builder) -> {
            Join<KYC, Booking> bookingJoin = root.join("booking");
            return builder.and(
                    builder.equal(bookingJoin.get("id"), booking.getId())
            );
        };
    }
}
