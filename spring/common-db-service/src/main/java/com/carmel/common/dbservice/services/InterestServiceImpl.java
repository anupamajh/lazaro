package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.Interest;
import com.carmel.common.dbservice.repository.InterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InterestServiceImpl implements InterestService {

    @Autowired
    InterestRepository interestRepository;

    @Override
    public Interest save(Interest interest) {
        return interestRepository.save(interest);
    }

    @Override
    public Optional<Interest> findById(String id) {
        return interestRepository.findById(id);
    }

    @Override
    public List<Interest> findAllByClientIdAndIsDeleted(String clientId, int isDeleted) {
        return interestRepository.findAllByClientIdAndIsDeleted(clientId, isDeleted);
    }

    @Override
    public Page<Interest> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable) {
        return interestRepository.findAllByClientIdAndIsDeleted(clientId, isDeleted, pageable);
    }

    @Override
    public Page<Interest> findAll(Specification<Interest> textInAllColumns, Pageable pageable) {
        return interestRepository.findAll(textInAllColumns, pageable);
    }

    @Override
    public List<Interest> findAllByClientIdAndIsDeletedAndName(String clientId, int isDeleted, String name) {
        return interestRepository.findAllByClientIdAndIsDeletedAndName(clientId, isDeleted, name);
    }

    @Override
    public List<Interest> findAllByClientIdAndIsDeletedAndNameAndIdIsNot(String clientId, int isDeleted, String name, String id) {
        return interestRepository.findAllByClientIdAndIsDeletedAndNameAndIdIsNot(clientId, isDeleted, name, id);
    }
}
