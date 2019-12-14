package com.carmel.guestjini.booking.specifications;

import com.carmel.guestjini.booking.model.Booking;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
        return  (root, query, builder) -> builder.or(
                builder.greaterThanOrEqualTo(root.get("checkInTime"), checkInDate),
                builder.greaterThanOrEqualTo(root.get("checkOutTime"), checkoutDate)
        );

    }

    public static Specification<Booking> checkInventoryAvailabilityOnReferenceNumber(String bookingReference) {
        return  (root, query, builder) -> builder.and(
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

}
