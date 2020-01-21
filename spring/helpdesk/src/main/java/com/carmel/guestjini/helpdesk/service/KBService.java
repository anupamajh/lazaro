package com.carmel.guestjini.helpdesk.service;

import com.carmel.guestjini.helpdesk.model.KB;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface KBService {
    KB save(KB kb);

    List<KB> findAllByClientIdAndIsDeletedAndTopicTitle(String clientId, int isDeleted, String topicTitle);

    List<KB> findAllByClientIdAndIsDeletedAndTopicTitleAndIdIsNot(String clientId, int isDeleted, String topicTitle, String id);

    Optional<KB> findById(String id);

    List<KB> findAllByClientIdAndIsDeleted(String clientId, int isDeleted);

    Page<KB> findAllByClientIdAndIsDeleted(String clientId, int i, Pageable pageable);

    Page<KB> findAll(Specification<KB> textInAllColumns, Pageable pageable);
}
