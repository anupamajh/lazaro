package com.carmel.common.dbservice.Base.AddressBook.Specification.specifications;

import com.carmel.common.dbservice.Base.Album.Model.Album;
import com.carmel.common.dbservice.Base.Photo.Model.Photo;
import org.springframework.data.jpa.domain.Specification;

public class PhotoSpecification {
    public static Specification<Photo> textInAllColumns(String searchText, String clientId, Album album) {
        if (!searchText.contains("%")) {
            searchText = "%" + searchText + "%";
        }
        String finalText = searchText;
        return (Specification<Photo>) (root, query, builder) -> builder.and(builder.or(
                builder.like(root.get("type"), finalText),
                builder.like(root.get("path"), finalText),
                builder.like(root.get("name"), finalText)
                ),
                builder.equal(root.get("isDeleted"), 0),
                builder.equal(root.get("clientId"), clientId),
                builder.equal(root.get("album"), album)
        );
    }
}
