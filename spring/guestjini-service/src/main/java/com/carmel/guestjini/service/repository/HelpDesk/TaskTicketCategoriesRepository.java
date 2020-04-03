package com.carmel.guestjini.service.repository.HelpDesk;

import com.carmel.guestjini.service.model.HelpDesk.TaskTicketCategories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskTicketCategoriesRepository extends JpaRepository<TaskTicketCategories, String> {
    List<TaskTicketCategories> findAllByTitleAndClientIdAndIsDeleted(String title, String clientId, int isDeleted);

    List<TaskTicketCategories> findAllByTitleAndClientIdAndIdAndIsDeleted(String title, String clientId, String id, int isDeleted);

    List<TaskTicketCategories> findAllByClientIdAndIsDeleted(String clientId, int isDeleted);

    Page<TaskTicketCategories> findAll(Specification<TaskTicketCategories> textInAllColumns, Pageable pageable);

    Page<TaskTicketCategories> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable);
}
