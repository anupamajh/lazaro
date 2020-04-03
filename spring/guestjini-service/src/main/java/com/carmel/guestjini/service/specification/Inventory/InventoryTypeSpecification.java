package com.carmel.guestjini.service.specification.Inventory;

import com.carmel.guestjini.service.model.Inventory.InventoryType;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class InventoryTypeSpecification {

    public static Specification<InventoryType> textInAllColumns(String searchText, String clientId) {
        if (!searchText.contains("%")) {
            searchText = "%" + searchText + "%";
        }
        String finalText = searchText;
        return new Specification<InventoryType>() {
            public Predicate toPredicate(Root<InventoryType> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                return builder.and(builder.or(
                        builder.like(root.get("title"), finalText),
                        builder.like(root.get("narration"), finalText)
                        ),
                        builder.equal(root.get("isDeleted"), 0),
                        builder.equal(root.get("clientId"),clientId )
                );
            }
        };
    }

}
