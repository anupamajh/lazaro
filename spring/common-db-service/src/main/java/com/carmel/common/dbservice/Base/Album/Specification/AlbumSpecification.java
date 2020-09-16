package com.carmel.common.dbservice.Base.Album.Specification;

import com.carmel.common.dbservice.Base.Album.Model.Album;
import org.springframework.data.jpa.domain.Specification;

public class AlbumSpecification {
    public static Specification<Album> textInAllColumns(String searchText, String clientId) {
        if (!searchText.contains("%")) {
            searchText = "%" + searchText + "%";
        }
        String finalText = searchText;
        return (Specification<Album>) (root, query, builder) -> builder.and(builder.or(
                builder.like(root.get("title"), finalText),
                builder.like(root.get("narration"), finalText)
                ),
                builder.equal(root.get("isDeleted"), 0),
                builder.equal(root.get("clientId"), clientId)
        );
    }
}
