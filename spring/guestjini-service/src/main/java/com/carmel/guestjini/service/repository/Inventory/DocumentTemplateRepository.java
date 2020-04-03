package com.carmel.guestjini.service.repository.Inventory;

import com.carmel.guestjini.service.model.Inventory.DocumentTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentTemplateRepository extends JpaRepository<DocumentTemplate, String> {
    List<DocumentTemplate> findAllByIsDeletedAndClientId(int isDeleted, String clientId);

    Page<DocumentTemplate> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable);

    Page<DocumentTemplate> findAll(Specification<DocumentTemplate> textInAllColumns, Pageable pageable);

    List<DocumentTemplate> findAllByClientIdAndTitle(String clientId, String title);

    List<DocumentTemplate> findAllByClientIdAndTitleAndIdIsNot(String clientId, String title, String id);
}
