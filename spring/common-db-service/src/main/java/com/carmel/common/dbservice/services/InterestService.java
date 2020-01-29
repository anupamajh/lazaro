package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.Interest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface InterestService {
    Interest save(Interest interest);

    Optional<Interest> findById(String id);

    List<Interest> findAllByClientIdAndIsDeleted(String clientId, int isDeleted);

    Page<Interest> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable);

    Page<Interest> findAll(Specification<Interest> textInAllColumns, Pageable pageable);

    List<Interest> findAllByClientIdAndIsDeletedAndName(String clientId, int isDeleted, String name);

    List<Interest> findAllByClientIdAndIsDeletedAndNameAndIdIsNot(String clientId, int isDeleted, String name, String id);

    int countByIsDeleted(int i);
}
