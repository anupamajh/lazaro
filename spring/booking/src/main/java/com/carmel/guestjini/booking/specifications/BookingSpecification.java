package com.carmel.guestjini.booking.specifications;

import com.carmel.guestjini.booking.model.Booking;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookingSpecification {

    public static Specification<Booking> checkInventoryAvailability(List<String> inventoryId, Date checkInDate, Date checkoutDate, String bookingReference) {
        return Specification
                .where(checkInventoryAvailabilityOnInventory(inventoryId))
                .and(checkInventoryAvailabilityOnDate(checkInDate, checkoutDate))
                .and(checkInventoryAvailabilityOnReferenceNumber(bookingReference));
    }

    public static Specification<Booking> checkInventoryAvailabilityOnDate(Date checkInDate, Date checkoutDate) {
        return (root, query, builder) -> builder.or(
                builder.greaterThanOrEqualTo(root.get("checkInTime"), checkInDate),
                builder.greaterThanOrEqualTo(root.get("checkOutTime"), checkoutDate)
        );

    }

    public static Specification<Booking> checkInventoryAvailabilityOnReferenceNumber(String bookingReference) {
        return (root, query, builder) -> builder.and(
                builder.equal(root.get("bookingStatus"), 1),
                builder.notEqual(root.get("referenceNo"), bookingReference)
        );
    }

    public static Specification<Booking> checkInventoryAvailabilityOnInventory(List<String> inventoryId) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (inventoryId != null && !inventoryId.isEmpty()) {
                predicates.add(root.get("inventoryId").in(inventoryId));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public static Specification<Booking> textInAllColumns(String searchText, String clientId) {
        if (!searchText.contains("%")) {
            searchText = "%" + searchText + "%";
        }
        String finalText = searchText;
        return (root, query, builder) -> builder.and(builder.or(
                builder.like(root.get("referenceNo"), finalText),
                builder.like(root.get("phone"), finalText),
                builder.like(root.get("email"), finalText),
                builder.like(root.get("fullName"), finalText)
                ),
                builder.equal(root.get("isDeleted"), 0),
                builder.equal(root.get("clientId"), clientId)
        );
    }

}
