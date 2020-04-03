package com.carmel.guestjini.service.service.HelpDesk;


import com.carmel.guestjini.service.model.HelpDesk.TaskTicketCategories;
import com.carmel.guestjini.service.repository.HelpDesk.TaskTicketCategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskTicketCategoriesServiceImpl implements  TaskTicketCategoriesService{
    @Autowired
    TaskTicketCategoriesRepository taskTicketCategoriesRepository;

    @Override
    public TaskTicketCategories save(TaskTicketCategories taskTicketCategories) {
        return taskTicketCategoriesRepository.save(taskTicketCategories);
    }

    @Override
    public List<TaskTicketCategories> findAllByTitleAndClientId(String title, String clientId) {
        return taskTicketCategoriesRepository.findAllByTitleAndClientIdAndIsDeleted(title, clientId, 0);
    }

    @Override
    public List<TaskTicketCategories> findAllByTitleAndClientIdAndId(String title, String clientId, String id) {
        return taskTicketCategoriesRepository.findAllByTitleAndClientIdAndIdAndIsDeleted(title, clientId, id, 0);
    }

    @Override
    public Optional<TaskTicketCategories> findById(String id) {
        return taskTicketCategoriesRepository.findById(id);
    }

    @Override
    public List<TaskTicketCategories> findAllByDeletionStatus(int isDeleted, String clientId) {
        return taskTicketCategoriesRepository.findAllByClientIdAndIsDeleted(clientId, isDeleted);
    }

    @Override
    public Page<TaskTicketCategories> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable) {
        return taskTicketCategoriesRepository.findAllByClientIdAndIsDeleted(clientId, isDeleted, pageable);
    }

    @Override
    public Page<TaskTicketCategories> findAll(Specification<TaskTicketCategories> textInAllColumns, Pageable pageable) {
        return taskTicketCategoriesRepository.findAll(textInAllColumns, pageable);
    }
}
