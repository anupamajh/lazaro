package com.carmel.guestjini.service.service.HelpDesk;


import com.carmel.guestjini.service.model.DTO.HelpDesk.TaskTicketCategoriesDTO;
import com.carmel.guestjini.service.model.HelpDesk.TaskTicketCategories;
import com.carmel.guestjini.service.repository.HelpDesk.TaskTicketCategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskTicketCategoriesServiceImpl implements TaskTicketCategoriesService {
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
        return taskTicketCategoriesRepository.findAllByCategoryDescriptionAndClientIdAndParentId(categoryDescription, clientId, parentId);
    }

    @Override
    public List<TaskTicketCategories> findAllByCategoryDescriptionAndClientIdAndIdAndParentId(String categoryDescription, String clientId, String id, String parentId) {
        return taskTicketCategoriesRepository.findAllByCategoryDescriptionAndClientIdAndIdAndParentId(categoryDescription, clientId, id, parentId);
    }

    @Override
    public List<TaskTicketCategories> getTaskCategoriesByParentId(String parentId, String clientId) {
        return taskTicketCategoriesRepository.findAllByClientIdAndIsDeletedAndParentId(clientId, 0, parentId);
    }

    @Override
    public List<TaskTicketCategories> getAllParents(String ticketCategoryId) {
        List<TaskTicketCategories> ticketCategoriesList = new ArrayList<>();
        TaskTicketCategories tempTicketCategories;
        Optional<TaskTicketCategories> optionalTaskTicketCategories = findById(ticketCategoryId);
        if (optionalTaskTicketCategories.isPresent()) {
            tempTicketCategories = optionalTaskTicketCategories.get();
            ticketCategoriesList.add(optionalTaskTicketCategories.get());
            if (tempTicketCategories.getParentId() == null) {
                tempTicketCategories.setParentId("");
            }
            while (!tempTicketCategories.getParentId().equals("")) {
                optionalTaskTicketCategories = findById(tempTicketCategories.getParentId());
                if (optionalTaskTicketCategories.isPresent()) {
                    tempTicketCategories = optionalTaskTicketCategories.get();
                    ticketCategoriesList.add(optionalTaskTicketCategories.get());
                    if (tempTicketCategories.getParentId() == null) {
                        tempTicketCategories.setParentId("");
                    }
                }else{
                    break;
                }
            }
        }
        Collections.reverse(ticketCategoriesList);
        return ticketCategoriesList;
    }
}
