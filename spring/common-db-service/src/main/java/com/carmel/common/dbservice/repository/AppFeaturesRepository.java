package com.carmel.common.dbservice.repository;

import com.carmel.common.dbservice.model.AppFeatures;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppFeaturesRepository extends JpaRepository<AppFeatures, String> {

    Optional<AppFeatures> findById(String id);

    List<AppFeatures> findAllByFeatureNameAndParentIdIs(String featureName, String parentId);

    List<AppFeatures> findAllByFeatureNameAndParentIdIsAndIdIsNot(String featureName, String parentId, String id);

    List<AppFeatures> findAllByIsDeleted(int isDeleted);

    Page<AppFeatures> findAllByFeatureNameContainingAndIsDeletedIs(String featureName, int isDeleted, Pageable pageable);

    Page<AppFeatures> findAllByIsDeleted(int isDeleted, Pageable pageable);

    Page<AppFeatures> findAll(Specification<AppFeatures> textInAllColumns, Pageable pageable);
}
