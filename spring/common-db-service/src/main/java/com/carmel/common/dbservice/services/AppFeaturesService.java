package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.AppFeatures;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AppFeaturesService {

    AppFeatures save(AppFeatures appFeatures);

    Optional<AppFeatures> findById(String id);

    List<AppFeatures> findAllByFeatureNameAndParentIdIs(String featureName, String parentId);

    List<AppFeatures> findAllByFeatureNameAndParentIdIsAndIdIsNot(String featureName, String parentId, String id);

    List<AppFeatures> findAllByIsDeleted(int isDeleted);

    Page<AppFeatures> findAll(Pageable pageable);

    Page<AppFeatures> findAllByFeatureNameContaining(String featureName, Pageable pageable);

    Page<AppFeatures> findAllByIsDeleted(int isDeleted, Pageable pageable);

}
