package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.InterestCategory;
import com.carmel.common.dbservice.repository.InterestCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InterestCategoryServiceImpl implements InterestCategoryService {
    @Autowired
    InterestCategoryRepository interestCategoryRepository;

    @Override
    public InterestCategory save(InterestCategory interestCategory) {
        return interestCategoryRepository.save(interestCategory);
    }

    @Override
    public Optional<InterestCategory> findById(String id) {
        return interestCategoryRepository.findById(id);
    }

    @Override
    public List<InterestCategory> findAllByClientIdAndIsDeleted(String clientId, int isDeleted) {
        return interestCategoryRepository.findAllByClientIdAndIsDeleted(clientId, isDeleted);
    }

    @Override
    public Page<InterestCategory> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable) {
        return interestCategoryRepository.findAllByClientIdAndIsDeleted(clientId, isDeleted, pageable);
    }

    @Override
    public Page<InterestCategory> findAll(Specification<InterestCategory> textInAllColumns, Pageable pageable) {
        return interestCategoryRepository.findAll(textInAllColumns, pageable);
    }

    @Override
    public List<InterestCategory> findAllByClientIdAndIsDeletedAndName(String clientId, int isDeleted, String name) {
        return interestCategoryRepository.findAllByClientIdAndIsDeletedAndName(clientId, isDeleted, name);
    }

    @Override
    public List<InterestCategory> findAllByClientIdAndIsDeletedAndNameAndIdIsNot(String clientId, int isDeleted, String name, String id) {
        return interestCategoryRepository.findAllByClientIdAndIsDeletedAndNameAndIdIsNot(clientId, isDeleted,name, id);
    }
}
