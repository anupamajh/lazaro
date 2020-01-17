package com.carmel.guestjini.helpdesk.service;

import com.carmel.guestjini.helpdesk.model.TaskRunner;
import com.carmel.guestjini.helpdesk.repository.TaskRunnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskRunnerServiceImpl implements TaskRunnerService {

    @Autowired
    TaskRunnerRepository taskRunnerRepository;

    @Override
    public TaskRunner save(TaskRunner taskRunner) {
        return taskRunnerRepository.save(taskRunner);
    }

    @Override
    public Optional<TaskRunner> findById(String id) {
        return taskRunnerRepository.findById(id);
    }

    @Override
    public List<TaskRunner> findAllByClientIdAndIsDeleted(String clientId, int isDeleted) {
        return taskRunnerRepository.findAllByClientIdAndIsDeleted(clientId, isDeleted);
    }

    @Override
    public Page<TaskRunner> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable) {
        return taskRunnerRepository.findAllByClientIdAndIsDeleted(clientId, isDeleted, pageable);
    }

    @Override
    public Page<TaskRunner> findAll(Specification<TaskRunner> textInAllColumns, Pageable pageable) {
        return taskRunnerRepository.findAll(textInAllColumns, pageable);
    }
}
