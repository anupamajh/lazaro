package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.AccountHead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface AccountHeadService {
    AccountHead save(AccountHead accountHead);

    List<AccountHead> findAllByClientIdAndTitle(String clientId, String title);

    List<AccountHead> findAllByClientIdAndTitleAndIdIsNot(String clientId, String title, String id);

    Optional<AccountHead> findById(String id);

    List<AccountHead> findAllByIsDeletedAndClientId(int isDeleted, String clientId);

    Page<AccountHead> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable);

    Page<AccountHead> findAll(Specification<AccountHead> textInAllColumns, Pageable pageable);
}
