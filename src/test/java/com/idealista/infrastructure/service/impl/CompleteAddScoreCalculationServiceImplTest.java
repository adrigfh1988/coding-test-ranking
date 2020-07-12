package com.idealista.infrastructure.service.impl;

import com.idealista.infrastructure.persistence.AdVO;
import com.idealista.infrastructure.service.CompleteAddScoreCalculationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompleteAddScoreCalculationServiceImplTest {

    @Test
    @DisplayName("Test With Chalet add with all fields valid")
    void calculateScoreChaletTest() {
        CompleteAddScoreCalculationService completeAddScoreCalculationService = new CompleteAddScoreCalculationServiceImpl();

        AdVO ad = new AdVO(1, "CHALET", "Este piso es una ganga, compra, compra, COMPRA!!!!!", Collections.singletonList(1), 300, 10, null, null);
        Integer score = completeAddScoreCalculationService.calculateScore(ad);
        assertEquals(40, score);

    }

    @Test
    @DisplayName("Test With Chalet add with all fields valid except garden info")
    void calculateScoreChaletNoGardenTest() {
        CompleteAddScoreCalculationService completeAddScoreCalculationService = new CompleteAddScoreCalculationServiceImpl();

        AdVO ad = new AdVO(1, "CHALET", "Este piso es una ganga, compra, compra, COMPRA!!!!!", Collections.singletonList(1), 300, null, null, null);
        Integer score = completeAddScoreCalculationService.calculateScore(ad);
        assertEquals(0, score);

    }

    @Test
    @DisplayName("Test With Chalet add with all fields valid except House size info")
    void calculateScoreChaletNoHouseSizeTest() {
        CompleteAddScoreCalculationService completeAddScoreCalculationService = new CompleteAddScoreCalculationServiceImpl();

        AdVO ad = new AdVO(1, "CHALET", "Este piso es una ganga, compra, compra, COMPRA!!!!!", Collections.singletonList(1), null, 10, null, null);
        Integer score = completeAddScoreCalculationService.calculateScore(ad);
        assertEquals(0, score);

    }

    @Test
    @DisplayName("Test With Flat and all fields valid")
    void calculateScoreFlatTest() {
        CompleteAddScoreCalculationService completeAddScoreCalculationService = new CompleteAddScoreCalculationServiceImpl();

        AdVO ad = new AdVO(1, "FLAT", "Este piso es una ganga, compra, compra, COMPRA!!!!!", Collections.singletonList(1), 110, null, null, null);
        Integer score = completeAddScoreCalculationService.calculateScore(ad);
        assertEquals(40, score);

    }

    @Test
    @DisplayName("Test With Flat and all fields valid except pictures field")
    void calculateScoreFlatNoPicturesTest() {
        CompleteAddScoreCalculationService completeAddScoreCalculationService = new CompleteAddScoreCalculationServiceImpl();

        AdVO ad = new AdVO(1, "FLAT", "Este piso es una ganga, compra, compra, COMPRA!!!!!", Collections.emptyList(), 110, null, null, null);
        Integer score = completeAddScoreCalculationService.calculateScore(ad);
        assertEquals(0, score);

    }

    @Test
    @DisplayName("Test With Flat and all fields valid except House size field")
    void calculateScoreFlatNoHouseSizeTest() {
        CompleteAddScoreCalculationService completeAddScoreCalculationService = new CompleteAddScoreCalculationServiceImpl();

        AdVO ad = new AdVO(1, "FLAT", "Este piso es una ganga, compra, compra, COMPRA!!!!!", Collections.singletonList(1), null, null, null, null);
        Integer score = completeAddScoreCalculationService.calculateScore(ad);
        assertEquals(0, score);

    }

    @Test
    @DisplayName("Test With Chalet and all fields valid except Description")
    void calculateScoreChaletNoDescriptionTest() {
        CompleteAddScoreCalculationService completeAddScoreCalculationService = new CompleteAddScoreCalculationServiceImpl();

        AdVO ad = new AdVO(1, "CHALET", "", Collections.singletonList(1), null, null, null, null);
        Integer score = completeAddScoreCalculationService.calculateScore(ad);
        assertEquals(0, score);

    }

    @Test
    @DisplayName("Test With Flat and all fields valid except Description")
    void calculateScoreFlatNoDescriptionTest() {
        CompleteAddScoreCalculationService completeAddScoreCalculationService = new CompleteAddScoreCalculationServiceImpl();

        AdVO ad = new AdVO(1, "FLAT", "", Collections.singletonList(1), null, null, null, null);
        Integer score = completeAddScoreCalculationService.calculateScore(ad);
        assertEquals(0, score);

    }

}
