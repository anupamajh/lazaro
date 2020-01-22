package com.carmel.guestjini.helpdesk.service;

import com.carmel.guestjini.helpdesk.model.KBRating;
import com.carmel.guestjini.helpdesk.repository.KBRatingRepository;
import com.carmel.guestjini.helpdesk.response.KBRatingPercentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KBRatingServiceImpl implements KBRatingService {

    @Autowired
    KBRatingRepository kbRatingRepository;

    @Override
    public Optional<KBRating> findByKbIdAndRatingBy(String kbId, String ratingBy) {
        return kbRatingRepository.findByKbIdAndRatingBy(kbId, ratingBy);
    }

    @Override
    public KBRating save(KBRating kbRating) {
        return kbRatingRepository.save(kbRating);
    }

    @Override
    public KBRatingPercentResponse getPercentageData(String kbId) {

        List<KBRating> kbRatings = kbRatingRepository.findAllByKbId(kbId);
        int totalRatings = kbRatings.size();
        int likedRatings = (int) kbRatings
                .stream()
                .filter(kbRating -> kbRating.getIsLiked() == 1).count();

        int disLikedRatings = (int) kbRatings
                .stream()
                .filter(kbRating -> kbRating.getIsLiked() == 0).count();
        KBRatingPercentResponse kbRatingPercentResponse = new KBRatingPercentResponse();
        kbRatingPercentResponse.setLikedCount(likedRatings);
        kbRatingPercentResponse.setDisLikedCount(disLikedRatings);
        if(totalRatings > 0) {
            kbRatingPercentResponse.setDisLikedPercent((disLikedRatings / totalRatings) * 100);
            kbRatingPercentResponse.setLikedPercent((likedRatings / totalRatings) * 100);
        }else{
            kbRatingPercentResponse.setDisLikedPercent(0);
            kbRatingPercentResponse.setLikedPercent(0);
        }
        return kbRatingPercentResponse;
    }
}
