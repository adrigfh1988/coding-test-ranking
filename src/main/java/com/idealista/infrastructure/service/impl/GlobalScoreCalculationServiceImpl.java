package com.idealista.infrastructure.service.impl;

import com.idealista.infrastructure.persistence.AdVO;
import com.idealista.infrastructure.persistence.InMemoryPersistence;
import com.idealista.infrastructure.service.CompleteAddScoreCalculationService;
import com.idealista.infrastructure.service.DescriptionScoreCalculationService;
import com.idealista.infrastructure.service.GlobalScoreCalculationService;
import com.idealista.infrastructure.service.PictureScoreCalculationService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GlobalScoreCalculationServiceImpl implements GlobalScoreCalculationService {

    private final InMemoryPersistence inMemoryPersistence;
    private final PictureScoreCalculationService pictureScoreCalculationService;
    private final DescriptionScoreCalculationService descriptionScoreCalculationService;
    private final CompleteAddScoreCalculationService completeAddScoreCalculationService;

    public GlobalScoreCalculationServiceImpl(InMemoryPersistence inMemoryPersistence,
                                             PictureScoreCalculationService pictureScoreCalculationService,
                                             DescriptionScoreCalculationService descriptionScoreCalculationService,
                                             CompleteAddScoreCalculationService completeAddScoreCalculationService) {
        this.inMemoryPersistence = inMemoryPersistence;
        this.pictureScoreCalculationService = pictureScoreCalculationService;
        this.descriptionScoreCalculationService = descriptionScoreCalculationService;
        this.completeAddScoreCalculationService = completeAddScoreCalculationService;
    }

    @Override
    public void calculateScore() {

        List<AdVO> newAdsWithScore = inMemoryPersistence.findAllAds().stream().peek(adVO -> {

            int score = 0;

            score = score + pictureScoreCalculationService.calculateScore(adVO.getPictures());
            score = score + descriptionScoreCalculationService.scoreForDescriptiveText(adVO.getDescription());
            score = score + descriptionScoreCalculationService.scoreForDescriptionSize(adVO.getTypology(), adVO.getDescription());
            score = score + descriptionScoreCalculationService.scoreForKeyWordsInDescription(adVO.getDescription());
            score = score + completeAddScoreCalculationService.calculateScore(adVO);


            if (score < 0) {
                score = 0;
            }

            if (score > 100) {
                score = 100;
            }

            adVO.setIrrelevantSince(isScoreIrrelevant(score) ? new Date() : null);
            adVO.setScore(score);

        }).collect(Collectors.toList());

        inMemoryPersistence.bulkSave(newAdsWithScore);
    }

    private boolean isScoreIrrelevant(Integer score) {
        return score < 40;
    }


}

