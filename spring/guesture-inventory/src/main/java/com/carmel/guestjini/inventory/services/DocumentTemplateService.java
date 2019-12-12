package com.carmel.guestjini.inventory.services;

import com.carmel.guestjini.inventory.model.DocumentTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface DocumentTemplateService {

    DocumentTemplate save(DocumentTemplate documentTemplate);

    Optional<DocumentTemplate> findById(String id);

    List<DocumentTemplate> findAllByIsDeletedAndClientId(int isDeleted, String clientId);

    Page<DocumentTemplate> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable);

    Page<DocumentTemplate> findAll(Specification<DocumentTemplate> textInAllColumns, Pageable pageable);

    List<DocumentTemplate> findAllByClientIdAndTitle(String clientId, String title);

    List<DocumentTemplate> findAllByClientIdAndTitleAndIdIsNot(String clientId, String title, String id);
}
