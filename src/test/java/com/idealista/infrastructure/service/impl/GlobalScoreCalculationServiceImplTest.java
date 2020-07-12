package com.idealista.infrastructure.service.impl;

import com.idealista.infrastructure.persistence.AdVO;
import com.idealista.infrastructure.persistence.InMemoryPersistence;
import com.idealista.infrastructure.service.CompleteAddScoreCalculationService;
import com.idealista.infrastructure.service.DescriptionScoreCalculationService;
import com.idealista.infrastructure.service.GlobalScoreCalculationService;
import com.idealista.infrastructure.service.PictureScoreCalculationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GlobalScoreCalculationServiceImplTest {


    @Test
    @DisplayName("Global test of score calculation")
    void calculateScoreOkTest() {
        InMemoryPersistence inMemoryPersistence = new InMemoryPersistence();
        PictureScoreCalculationService pictureScoreCalculationService = new PictureScoreCalculationServiceImpl(inMemoryPersistence);
        DescriptionScoreCalculationService descriptionScoreCalculationService = new DescriptionScoreCalculationServiceImpl();
        CompleteAddScoreCalculationService completeAddScoreCalculationService = new CompleteAddScoreCalculationServiceImpl();
        GlobalScoreCalculationService globalScoreCalculationService = new GlobalScoreCalculationServiceImpl(inMemoryPersistence, pictureScoreCalculationService, descriptionScoreCalculationService, completeAddScoreCalculationService);
        globalScoreCalculationService.calculateScore();

        List<AdVO> allAds = inMemoryPersistence.findAllAds();
        assertEquals(0, allAds.get(0).getScore());
        assertEquals(85, allAds.get(1).getScore());
        assertEquals(20, allAds.get(2).getScore());
        assertEquals(80, allAds.get(3).getScore());
        assertEquals(75, allAds.get(4).getScore());
        assertEquals(50, allAds.get(5).getScore());
        assertEquals(0, allAds.get(6).getScore());
        assertEquals(25, allAds.get(7).getScore());

        assertNotNull(allAds.get(0).getIrrelevantSince());
        assertNotNull(allAds.get(2).getIrrelevantSince());
        assertNotNull(allAds.get(6).getIrrelevantSince());
        assertNotNull(allAds.get(7).getIrrelevantSince());

        assertNull(allAds.get(1).getIrrelevantSince());
        assertNull(allAds.get(3).getIrrelevantSince());
        assertNull(allAds.get(4).getIrrelevantSince());
        assertNull(allAds.get(5).getIrrelevantSince());

    }
}
