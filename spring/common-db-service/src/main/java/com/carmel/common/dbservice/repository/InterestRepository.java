package com.carmel.common.dbservice.repository;

import com.carmel.common.dbservice.model.Interest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterestRepository extends JpaRepository<Interest, String> {
    List<Interest> findAllByClientIdAndIsDeleted(String clientId, int isDeleted);

    Page<Interest> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable);

    Page<Interest> findAll(Specification<Interest> textInAllColumns, Pageable pageable);

    List<Interest> findAllByClientIdAndIsDeletedAndName(String clientId, int isDeleted, String name);

    List<Interest> findAllByClientIdAndIsDeletedAndNameAndIdIsNot(String clientId, int isDeleted, String name, String id);
}
