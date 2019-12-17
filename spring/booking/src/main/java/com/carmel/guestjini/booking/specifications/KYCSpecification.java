package com.carmel.guestjini.booking.specifications;

import com.carmel.guestjini.booking.model.Booking;
import com.carmel.guestjini.booking.model.KYC;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;

public class KYCSpecification {
    public static Specification<KYC> textInAllColumns(String searchText, String clientId, Booking booking) {
        if (!searchText.contains("%")) {
            searchText = "%" + searchText + "%";
        }
        String finalText = searchText;
        return Specification
                .where(textSearch(finalText,clientId))
                .or(bookingSearch(booking));
    }

    public static Specification<KYC> textSearch(String finalText, String clientId) {
        return (Specification<KYC>) (root, query, builder) -> builder.and(builder.or(
                builder.like(root.get("identityDocumentOther"), finalText),
                builder.like(root.get("arrivedFrom"), finalText),
                builder.like(root.get("permanentAddress"), finalText),
                builder.like(root.get("designation"), finalText),
                builder.like(root.get("organization"), finalText),
                builder.like(root.get("visitPurposeOther"), finalText),
                builder.like(root.get("emergencyName1"), finalText),
                builder.like(root.get("emergencyPhone1"), finalText),
                builder.like(root.get("emergencyName2"), finalText),
                builder.like(root.get("emergencyPhone2"), finalText),
                builder.like(root.get("nationality"), finalText),
                builder.like(root.get("foreignerDrivingLicenseNumber"), finalText),
                builder.like(root.get("foreignerPassportNumber"), finalText),
                builder.like(root.get("foreignerPassportNumber"), finalText),
                builder.like(root.get("foreignerEmployerDetails"), finalText)
                ),
                builder.equal(root.get("isDeleted"), 0),
                builder.equal(root.get("clientId"), clientId)
        );
    }
    public static Specification<KYC> bookingSearch(Booking booking) {
        return (root, query, builder) -> {
            Join<KYC, Booking> bookingJoin = root.join("booking");
            return builder.and(
                    builder.equal(bookingJoin.get("id"), booking.getId())
            );
        };
    }


}
