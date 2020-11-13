package com.carmel.guestjini.service.service.HelpDesk;

import com.carmel.guestjini.service.model.HelpDesk.TaskTicketCategories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface TaskTicketCategoriesService {
    TaskTicketCategories save(TaskTicketCategories taskTicketCategories);

    Optional<TaskTicketCategories> findById(String id);

    List<TaskTicketCategories> findAllByDeletionStatus(int isDeleted, String clientId);

    Page<TaskTicketCategories> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable);

    Page<TaskTicketCategories> findAll(Specification<TaskTicketCategories> textInAllColumns, Pageable pageable);

    List<TaskTicketCategories> findAllByCategoryDescriptionAndClientIdAndParentId(String categoryDescription, String clientId, String parentId);

    List<TaskTicketCategories> findAllByCategoryDescriptionAndClientIdAndIdAndParentId(String categoryDescription, String clientId, String id, String parentId);

    List<TaskTicketCategories> getTaskCategoriesByParentId(String parentId, String clientId);

    List<TaskTicketCategories> getAllParents(String ticketCategoryId);


}
