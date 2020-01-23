package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.InterestCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface InterestCategoryService {
    InterestCategory save(InterestCategory interestCategory);

    Optional<InterestCategory> findById(String id);

    List<InterestCategory> findAllByClientIdAndIsDeleted(String clientId, int isDeleted);

    Page<InterestCategory> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable);

    Page<InterestCategory> findAll(Specification<InterestCategory> textInAllColumns, Pageable pageable);

    List<InterestCategory> findAllByClientIdAndIsDeletedAndName(String clientId, int isDeleted, String name);

    List<InterestCategory> findAllByClientIdAndIsDeletedAndNameAndIdIsNot(String clientId, int isDeleted, String name, String id);
}
