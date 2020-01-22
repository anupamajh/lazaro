package com.carmel.guestjini.helpdesk.service;

import com.carmel.guestjini.helpdesk.model.KBRating;
import com.carmel.guestjini.helpdesk.response.KBRatingPercentResponse;

import java.util.Optional;

public interface KBRatingService {
    Optional<KBRating> findByKbIdAndRatingBy(String kbId, String ratingBy);

    KBRating save(KBRating kbRating);

    KBRatingPercentResponse getPercentageData(String kbId);
}
