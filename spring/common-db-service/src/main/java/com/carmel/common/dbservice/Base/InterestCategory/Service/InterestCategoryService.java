package com.carmel.common.dbservice.Base.InterestCategory.Service;

import com.carmel.common.dbservice.Base.InterestCategory.Model.InterestCategory;
import com.carmel.common.dbservice.Base.InterestCategory.Responce.InterestCategoryResponse;
import com.carmel.common.dbservice.common.Search.SearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface InterestCategoryService {
    InterestCategory save(InterestCategory interestCategory) throws Exception;

    Optional<InterestCategory> findById(String id)throws Exception;

    InterestCategoryResponse saveInterestcategory(InterestCategory interestCategory) throws Exception;

    InterestCategoryResponse moveToTrash(Map<String, String> formData) throws Exception;

    InterestCategoryResponse get(Map<String, String> formData) throws Exception;

    InterestCategoryResponse getDeleted() throws Exception;

    InterestCategoryResponse getAll() throws Exception;

    InterestCategoryResponse getPaginated(Map<String, String> formData) throws Exception;

    InterestCategoryResponse searchPaginated(Map<String, String> formData) throws Exception;

    InterestCategoryResponse search(SearchRequest searchRequest) throws Exception;

    InterestCategoryResponse findAllByClientIdAndIsDeleted(String clientId, int isDeleted)throws Exception;

    InterestCategoryResponse findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable)throws Exception;

    InterestCategoryResponse findAll(Specification<InterestCategory> textInAllColumns, Pageable pageable)throws Exception;

    InterestCategoryResponse findAllByClientIdAndIsDeletedAndName(String clientId, int isDeleted, String name)throws Exception;

    InterestCategoryResponse findAllByClientIdAndIsDeletedAndNameAndIdIsNot(String clientId, int isDeleted, String name, String id)throws Exception;
}
