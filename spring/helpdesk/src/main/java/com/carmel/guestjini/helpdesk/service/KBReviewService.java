package com.carmel.guestjini.helpdesk.service;

import com.carmel.guestjini.helpdesk.model.KB;
import com.carmel.guestjini.helpdesk.model.KBReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface KBReviewService {
    KBReview save(KBReview kbReview);

    Optional<KBReview> findById(String id);

    List<KBReview> findAllByKbIdAndIsDeleted(String kbId, int isDeleted);

    Page<KBReview> findAllByKbIdAndIsDeleted(String kbId, int isDeleted, Pageable pageable);
}
