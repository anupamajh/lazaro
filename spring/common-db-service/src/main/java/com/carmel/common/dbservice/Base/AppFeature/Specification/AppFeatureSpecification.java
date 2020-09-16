package com.carmel.common.dbservice.Base.AppFeature.Specification;

import com.carmel.common.dbservice.Base.AppFeature.Model.AppFeatures;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class AppFeatureSpecification {
    public static Specification<AppFeatures> textInAllColumns(String searchText) {
        if (!searchText.contains("%")) {
            searchText = "%" + searchText + "%";
        }
        String finalText = searchText;
        return new Specification<AppFeatures>() {
            public Predicate toPredicate(Root<AppFeatures> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                return builder.and(builder.or(
                        builder.like(root.get("featureName"), finalText),
                        builder.like(root.get("toolTip"), finalText)
                        ),
                        builder.equal(root.get("isDeleted"), 0)
                );
            }
        };
    }
}
