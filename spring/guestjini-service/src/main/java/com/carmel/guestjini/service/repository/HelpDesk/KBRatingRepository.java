package com.carmel.guestjini.service.repository.HelpDesk;


import com.carmel.guestjini.service.model.HelpDesk.KBRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KBRatingRepository extends JpaRepository<KBRating, String> {
    Optional<KBRating> findByKbIdAndRatingBy(String kbId, String ratingBy);

    List<KBRating> findAllByKbId(String kbId);
}
