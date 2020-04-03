package com.carmel.guestjini.service.service.HelpDesk;



import com.carmel.guestjini.service.model.HelpDesk.KBRating;
import com.carmel.guestjini.service.response.HelpDesk.KBRatingPercentResponse;

import java.util.Optional;

public interface KBRatingService {
    Optional<KBRating> findByKbIdAndRatingBy(String kbId, String ratingBy);

    KBRating save(KBRating kbRating);

    KBRatingPercentResponse getPercentageData(String kbId);
}
