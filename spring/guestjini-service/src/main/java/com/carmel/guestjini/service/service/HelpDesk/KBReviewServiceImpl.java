package com.carmel.guestjini.service.service.HelpDesk;


import com.carmel.guestjini.service.model.HelpDesk.KBReview;
import com.carmel.guestjini.service.repository.HelpDesk.KBReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KBReviewServiceImpl implements KBReviewService {
    @Autowired
    KBReviewRepository kbReviewRepository;

    @Override
    public KBReview save(KBReview kbReview) {
        return kbReviewRepository.save(kbReview);
    }

    @Override
    public Optional<KBReview> findById(String id) {
        return kbReviewRepository.findById(id);
    }

    @Override
    public List<KBReview> findAllByKbIdAndIsDeleted(String kbId, int isDeleted) {
        return kbReviewRepository.findAllByKbIdAndIsDeleted(kbId, isDeleted);
    }

    @Override
    public Page<KBReview> findAllByKbIdAndIsDeleted(String kbId, int isDeleted, Pageable pageable) {
        return kbReviewRepository.findAllByKbIdAndIsDeleted(kbId, isDeleted, pageable);
    }
}
