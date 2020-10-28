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

    @Override
    public List<TaskTicketCategories> findAllByCategoryDescriptionAndClientIdAndParentId(String categoryDescription, String clientId, String parentId) {
        return taskTicketCategoriesRepository.findAllByCategoryDescriptionAndClientIdAndParentId(categoryDescription, clientId,parentId);
    }

    @Override
    public List<TaskTicketCategories> findAllByCategoryDescriptionAndClientIdAndIdAndParentId(String categoryDescription, String clientId, String id, String parentId) {
        return taskTicketCategoriesRepository.findAllByCategoryDescriptionAndClientIdAndIdAndParentId(categoryDescription, clientId,id,parentId);
    }

    @Override
    public List<TaskTicketCategories> getTaskCategoriesByParentId(String parentId, String clientId) {
        return taskTicketCategoriesRepository.findAllByClientIdAndIsDeletedAndParentId(clientId, 0, parentId);
    }
}
