package com.carmel.guestjini.inventory.specifications;

import com.carmel.guestjini.inventory.model.Inventory;
import com.carmel.guestjini.inventory.model.InventoryAttributes;
import com.carmel.guestjini.inventory.model.Package;
import com.carmel.guestjini.inventory.request.PaymentRequest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class InventorySpecification {
    public static Specification<Inventory> textInAllColumns(String searchText, String clientId) {
        if (!searchText.contains("%")) {
            searchText = "%" + searchText + "%";
        }
        String finalText = searchText;
        return (root, query, builder) -> builder.and(builder.or(
                builder.like(root.get("title"), finalText)
                ),
                builder.equal(root.get("isDeleted"), 0),
                builder.equal(root.get("clientId"), clientId)
        );
    }


    public static Specification<Inventory> filterInventoryByAvailability(
            String packageId,
            int hasBalcony,
            int hasBathRoom
    ){
        return Specification
                .where(filterInventoryByPackage(packageId))
                .and(filterInventoryWithAttributes(hasBalcony, hasBathRoom));
    }

    public static Specification<Inventory> filterInventoryByPackage(
            String packageId) {

        return (root, query, builder) -> {
            Join<Inventory, Package> packageJoin = root.join("packages");
            return builder.and(
                    builder.equal(packageJoin.get("id"), packageId)
            );
        };
    }

    public static Specification<Inventory> filterInventoryWithAttributes(
            int hasBalcony,
            int hasBathRoom
    ) {

        return (root, query, builder) -> {
            Join<Inventory, InventoryAttributes> inventoryInventoryAttributesJoin = root.join("inventoryAttributes");
            return builder.and(
                    builder.equal(inventoryInventoryAttributesJoin.get("hasAttachedBalcony"), hasBalcony),
                    builder.equal(inventoryInventoryAttributesJoin.get("hasAttachedBathroom"), hasBathRoom)
            );
        };
    }


}
