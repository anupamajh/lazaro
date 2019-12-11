package com.carmel.guesture.inventory.specifications;

import com.carmel.guesture.inventory.model.Inventory;
import com.carmel.guesture.inventory.model.InventoryType;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class InventorySpecification {
    public static Specification<Inventory> textInAllColumns(String searchText, String clientId) {
        if (!searchText.contains("%")) {
            searchText = "%" + searchText + "%";
        }
        String finalText = searchText;
        return new Specification<Inventory>() {
            public Predicate toPredicate(Root<Inventory> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                return builder.and(builder.or(
                        builder.like(root.get("title"), finalText)
                        ),
                        builder.equal(root.get("isDeleted"), 0),
                        builder.equal(root.get("clientId"),clientId )
                );
            }
        };
    }
}
