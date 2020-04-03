package com.carmel.guestjini.service.service.Inventory;

import com.carmel.guestjini.service.model.Inventory.DocumentTemplate;
import com.carmel.guestjini.service.repository.Inventory.DocumentTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentTemplateServiceImpl implements DocumentTemplateService{
    @Autowired
    DocumentTemplateRepository documentTemplateRepository;


    @Override
    public DocumentTemplate save(DocumentTemplate documentTemplate) {
        return documentTemplateRepository.save(documentTemplate);
    }

    @Override
    public Optional<DocumentTemplate> findById(String id) {
        return documentTemplateRepository.findById(id);
    }

    @Override
    public List<DocumentTemplate> findAllByIsDeletedAndClientId(int isDeleted, String clientId) {
        return documentTemplateRepository.findAllByIsDeletedAndClientId(isDeleted, clientId);
    }

    @Override
    public Page<DocumentTemplate> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable) {
        return documentTemplateRepository.findAllByClientIdAndIsDeleted(clientId, isDeleted, pageable);
    }

    @Override
    public Page<DocumentTemplate> findAll(Specification<DocumentTemplate> textInAllColumns, Pageable pageable) {
        return documentTemplateRepository.findAll(textInAllColumns, pageable);
    }

    @Override
    public List<DocumentTemplate> findAllByClientIdAndTitle(String clientId, String title) {
        return documentTemplateRepository.findAllByClientIdAndTitle(clientId, title);
    }

    @Override
    public List<DocumentTemplate> findAllByClientIdAndTitleAndIdIsNot(String clientId, String title, String id) {
        return documentTemplateRepository.findAllByClientIdAndTitleAndIdIsNot(clientId, title, id);
    }
}
