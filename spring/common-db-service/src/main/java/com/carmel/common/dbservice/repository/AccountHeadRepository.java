package com.carmel.common.dbservice.repository;

import com.carmel.common.dbservice.model.AccountHead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountHeadRepository extends JpaRepository<AccountHead, String> {
    List<AccountHead> findAllByClientIdAndTitle(String clientId, String title);

    List<AccountHead> findAllByClientIdAndTitleAndIdIsNot(String clientId, String title, String id);

    List<AccountHead> findAllByIsDeletedAndClientId(int isDeleted, String clientId);

    Page<AccountHead> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable);

    Page<AccountHead> findAll(Specification<AccountHead> textInAllColumns, Pageable pageable);
}
