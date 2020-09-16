package com.carmel.common.dbservice.Base.AppFeature.Service;

import com.carmel.common.dbservice.Base.AppFeature.Model.AppFeatures;
import com.carmel.common.dbservice.Base.AppFeature.DTO.AppFeatureTreeDTO;
import com.carmel.common.dbservice.Base.AppFeature.Response.AppFeaturesResponse;
import com.carmel.common.dbservice.common.Search.SearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AppFeaturesService {

    AppFeatures save(AppFeatures appFeatures) throws Exception;

    Optional<AppFeatures> findById(String id) throws Exception;

    AppFeaturesResponse saveAppFeature(AppFeatures appFeatures) throws Exception;

    AppFeaturesResponse moveToTrash(Map<String, String> formData) throws Exception;

    AppFeaturesResponse get(Map<String, String> formData) throws Exception;

    AppFeaturesResponse getAll() throws Exception;

    AppFeaturesResponse getDeleted() throws Exception;

    AppFeaturesResponse getPaginated(Map<String, String> formData) throws Exception;

    AppFeaturesResponse searchPaginated(Map<String, String> formData) throws Exception;

    AppFeaturesResponse getDeepAppFeatures(Map<String, String> formData) throws Exception;

    AppFeaturesResponse getUserAppFeatures() throws Exception;

    AppFeaturesResponse search(SearchRequest searchRequest) throws Exception;

    AppFeaturesResponse findAllByFeatureNameAndParentIdIs(String featureName, String parentId) throws Exception;

    AppFeaturesResponse findAllByFeatureNameAndParentIdIsAndIdIsNot(String featureName, String parentId, String id) throws Exception;

    AppFeaturesResponse findAllByIsDeleted(int isDeleted) throws Exception;

    AppFeaturesResponse findAll(Pageable pageable) throws Exception;

    AppFeaturesResponse findAllByFeatureNameContaining(String featureName, Pageable pageable) throws Exception;

    AppFeaturesResponse findAllByIsDeleted(int isDeleted, Pageable pageable) throws Exception;

    AppFeaturesResponse findAll(Specification<AppFeatures> textInAllColumns, Pageable pageable) throws Exception;

    AppFeaturesResponse getTreeData(String parentId) throws Exception;

    AppFeaturesResponse findAllByIdInAndIsDeleted(List<String> ids, int isDeleted) throws Exception;
}
