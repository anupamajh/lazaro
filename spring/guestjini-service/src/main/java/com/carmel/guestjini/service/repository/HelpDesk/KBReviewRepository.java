package com.carmel.guestjini.service.repository.HelpDesk;


import com.carmel.guestjini.service.model.HelpDesk.KBReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KBReviewRepository extends JpaRepository<KBReview, String> {
    List<KBReview> findAllByKbIdAndIsDeleted(String kbId, int isDeleted);

    Page<KBReview> findAllByKbIdAndIsDeleted(String kbId, int isDeleted, Pageable pageable);
}
