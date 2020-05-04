package com.carmel.guestjini.service.specification.Inventory;

import com.carmel.guestjini.service.model.Inventory.Inventory;
import com.carmel.guestjini.service.model.Inventory.InventoryDetail;
import com.carmel.guestjini.service.model.Inventory.InventoryType;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;

public class InventoryDetailSpecification {
    public static Specification<InventoryDetail> textInAllColumns(String searchText, String clientId) {
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

    public static Specification<InventoryDetail> filterInventoryDetailByAvailability(
            String packageId,
            String propertyId,
            String allowedInventoryTypes,
            int hasBalcony,
            int hasBathRoom
    ) {
        return Specification
                .where(filterInventoryDetailByType(allowedInventoryTypes))
                .and(filterInventoryByProperty(propertyId));
    }

    public static Specification<InventoryDetail> filterInventoryByProperty
            (String propertyId) {
        return (root, query, builder) ->builder.equal(root.get("propertyId"), propertyId);
    }

    public static Specification<InventoryDetail>
    filterInventoryDetailByType
            (String inventoryTypeId) {
        return (root, query, builder) -> {
            Join<Inventory, InventoryType> inventoryTypeJoin = root.join("inventoryType");
            return builder.equal(inventoryTypeJoin.get("id"), inventoryTypeId);
        };
    }

}
