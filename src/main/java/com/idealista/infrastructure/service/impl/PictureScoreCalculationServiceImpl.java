package com.idealista.infrastructure.service.impl;

import com.idealista.infrastructure.persistence.InMemoryPersistence;
import com.idealista.infrastructure.service.PictureScoreCalculationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureScoreCalculationServiceImpl implements PictureScoreCalculationService {

    private final InMemoryPersistence inMemoryPersistence;

    public PictureScoreCalculationServiceImpl(InMemoryPersistence inMemoryPersistence) {
        this.inMemoryPersistence = inMemoryPersistence;
    }

    @Override
    public Integer calculateScore(List<Integer> pictures) {

        int scoreOfPictures;
        if (pictures.isEmpty()) {
            scoreOfPictures = -10;
        } else {
            scoreOfPictures = inMemoryPersistence
                    .findByIdIn(pictures)
                    .stream()
                    .map(pictureVO -> {
                        if (pictureVO.getQuality().equals("HD")) {
                            return 20;
                        } else
                            return 10;
                    }).reduce(Integer::sum)
                    .orElseThrow(() -> new IllegalArgumentException("Not able tu add the values of the pictures"));

        }

        return scoreOfPictures;
    }
}
