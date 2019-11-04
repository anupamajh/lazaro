package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.AppFeatures;
import com.carmel.common.dbservice.repository.AppFeaturesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppFeaturesServiceImpl implements AppFeaturesService{

    @Autowired
    AppFeaturesRepository appFeaturesRepository;

    @Override
    public AppFeatures save(AppFeatures appFeatures) {
        return appFeaturesRepository.save(appFeatures);
    }

    @Override
    public Optional<AppFeatures> findById(String id) {
        return appFeaturesRepository.findById(id);
    }

    @Override
    public List<AppFeatures> findAllByFeatureNameAndParentIdIs(String featureName, String parentId) {
        return appFeaturesRepository.findAllByFeatureNameAndParentIdIs(featureName, parentId);
    }

    @Override
    public List<AppFeatures> findAllByFeatureNameAndParentIdIsAndIdIsNot(String featureName, String parentId, String id) {
        return appFeaturesRepository.findAllByFeatureNameAndParentIdIsAndIdIsNot(featureName, parentId, id);
    }

    @Override
    public List<AppFeatures> findAllByIsDeleted(int isDeleted) {
        return appFeaturesRepository.findAllByIsDeleted(isDeleted);
    }

    @Override
    public Page<AppFeatures> findAll(Pageable pageable) {
        return appFeaturesRepository.findAllByIsDeleted(0,pageable);
    }

    @Override
    public Page<AppFeatures> findAllByFeatureNameContaining(String featureName, Pageable pageable) {
        return appFeaturesRepository.findAllByFeatureNameContainingAndIsDeletedIs(featureName, 0, pageable);
    }

    @Override
    public Page<AppFeatures> findAllByIsDeleted(int isDeleted, Pageable pageable) {
        return appFeaturesRepository.findAllByIsDeleted(isDeleted,pageable);
    }
}
