package com.carmel.common.dbservice.Base.AccountHead.Specification;

import com.carmel.common.dbservice.Base.AccountHead.Model.AccountHead;
import org.springframework.data.jpa.domain.Specification;

public class AccountHeadSpecification {
    public static Specification<AccountHead> textInAllColumns(String searchText, String clientId) {
        if (!searchText.contains("%")) {
            searchText = "%" + searchText + "%";
        }
        String finalText = searchText;
        return (Specification<AccountHead>) (root, query, builder) -> builder.and(builder.or(
                builder.like(root.get("title"), finalText),
                builder.like(root.get("narration"), finalText)
                ),
                builder.equal(root.get("isDeleted"), 0),
                builder.equal(root.get("clientId"), clientId)
        );
    }
}
