package com.carmel.common.dbservice.repository;

import com.carmel.common.dbservice.model.InterestCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterestCategoryRepository extends JpaRepository<InterestCategory, String> {
    List<InterestCategory> findAllByClientIdAndIsDeleted(String clientId, int isDeleted);

    Page<InterestCategory> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable);

    Page<InterestCategory> findAll(Specification<InterestCategory> textInAllColumns, Pageable pageable);

    List<InterestCategory> findAllByClientIdAndIsDeletedAndName(String clientId, int isDeleted, String name);

    List<InterestCategory> findAllByClientIdAndIsDeletedAndNameAndIdIsNot(String clientId, int isDeleted, String name, String id);
}
