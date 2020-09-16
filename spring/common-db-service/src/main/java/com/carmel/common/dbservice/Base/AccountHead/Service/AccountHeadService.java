package com.carmel.common.dbservice.Base.AccountHead.Service;

import com.carmel.common.dbservice.Base.AccountHead.Model.AccountHead;
import com.carmel.common.dbservice.Base.AccountHead.Responce.AccountHeadResponse;
import com.carmel.common.dbservice.common.Search.SearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AccountHeadService {
    AccountHead save(AccountHead accountHead) throws Exception;

    Optional<AccountHead> findById(String id)  throws Exception;

    AccountHeadResponse findAllByClientIdAndTitle(String clientId, String title) throws Exception;

    AccountHeadResponse findAllByClientIdAndTitleAndIdIsNot(String clientId, String title, String id) throws Exception;

    AccountHeadResponse findAllByIsDeletedAndClientId(int isDeleted, String clientId) throws Exception;

    AccountHeadResponse findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable) throws Exception;

    AccountHeadResponse findAll(Specification<AccountHead> textInAllColumns, Pageable pageable) throws Exception;

    AccountHeadResponse saveAccountHead(AccountHead accountHead) throws Exception;

    AccountHeadResponse moveToTrash(Map<String, String> formData) throws Exception;

    AccountHeadResponse get(Map<String, String> formData) throws Exception;

    AccountHeadResponse getDeleted() throws Exception;

    AccountHeadResponse getAll() throws Exception;

    AccountHeadResponse getPaginated(Map<String, String> formData) throws Exception;

    AccountHeadResponse searchPaginated(Map<String, String> formData) throws Exception;

    AccountHeadResponse search(SearchRequest searchRequest) throws Exception;

}
