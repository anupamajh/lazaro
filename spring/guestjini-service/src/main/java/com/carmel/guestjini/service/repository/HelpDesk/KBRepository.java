package com.carmel.guestjini.service.repository.HelpDesk;


import com.carmel.guestjini.service.model.HelpDesk.KB;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KBRepository extends JpaRepository<KB, String> {
    List<KB> findAllByClientIdAndIsDeletedAndTopicTitle(String clientId, int isDeleted, String topicTitle);

    List<KB> findAllByClientIdAndIsDeletedAndTopicTitleAndIdIsNot(String clientId, int isDeleted, String topicTitle, String id);

    List<KB> findAllByClientIdAndIsDeleted(String clientId, int isDeleted);

    Page<KB> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable);

    Page<KB> findAll(Specification<KB> textInAllColumns, Pageable pageable);
}
