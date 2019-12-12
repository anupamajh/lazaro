package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.AccountHead;
import com.carmel.common.dbservice.repository.AccountHeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AccountHeadServiceImpl implements AccountHeadService {
    @Autowired
    AccountHeadRepository accountHeadRepository;

    @Override
    public AccountHead save(AccountHead accountHead) {
        return accountHeadRepository.save(accountHead);
    }

    @Override
    public List<AccountHead> findAllByClientIdAndTitle(String clientId, String title) {
        return accountHeadRepository.findAllByClientIdAndTitle(clientId, title);
    }

    @Override
    public List<AccountHead> findAllByClientIdAndTitleAndIdIsNot(String clientId, String title, String id) {
        return accountHeadRepository.findAllByClientIdAndTitleAndIdIsNot(clientId, title, id);
    }

    @Override
    public Optional<AccountHead> findById(String id) {
        return accountHeadRepository.findById(id);
    }

    @Override
    public List<AccountHead> findAllByIsDeletedAndClientId(int isDeleted, String clientId) {
        return accountHeadRepository.findAllByIsDeletedAndClientId(isDeleted, clientId);
    }

    @Override
    public Page<AccountHead> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable) {
        return accountHeadRepository.findAllByClientIdAndIsDeleted(clientId, isDeleted,pageable);
    }

    @Override
    public Page<AccountHead> findAll(Specification<AccountHead> textInAllColumns, Pageable pageable) {
        return accountHeadRepository.findAll(textInAllColumns, pageable);
    }
}
