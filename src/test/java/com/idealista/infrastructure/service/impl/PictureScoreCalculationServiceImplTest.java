package com.idealista.infrastructure.service.impl;

import com.idealista.infrastructure.persistence.InMemoryPersistence;
import com.idealista.infrastructure.service.PictureScoreCalculationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PictureScoreCalculationServiceImplTest {

    @Test
    @DisplayName("Score calculation with 1 HD and 2 SD pictures")
    void calculateScoreOkTest() {
        InMemoryPersistence inMemoryPersistence = new InMemoryPersistence();
        PictureScoreCalculationService pictureScoreCalculationService = new PictureScoreCalculationServiceImpl(inMemoryPersistence);
        Integer score = pictureScoreCalculationService.calculateScore(Arrays.asList(1, 2, 3));
        assertEquals(40, score);
    }

    @Test
    @DisplayName("Score calculation with all the pictures")
    void calculateScoreAllPicturesTest() {
        InMemoryPersistence inMemoryPersistence = new InMemoryPersistence();
        PictureScoreCalculationService pictureScoreCalculationService = new PictureScoreCalculationServiceImpl(inMemoryPersistence);
        Integer score = pictureScoreCalculationService.calculateScore(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        assertEquals(110, score);
    }
}
