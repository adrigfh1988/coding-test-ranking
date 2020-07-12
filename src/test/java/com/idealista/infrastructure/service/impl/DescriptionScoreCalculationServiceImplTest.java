package com.idealista.infrastructure.service.impl;

import com.idealista.infrastructure.service.DescriptionScoreCalculationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DescriptionScoreCalculationServiceImplTest {

    final DescriptionScoreCalculationService descriptionScoreCalculationService = new DescriptionScoreCalculationServiceImpl();

    @Test
    @DisplayName("Test with A good descriptive text")
    void scoreForDescriptiveTextOkTest() {
        assertEquals(5, descriptionScoreCalculationService.scoreForDescriptiveText("This is a descriptive text"));
    }

    @Test
    @DisplayName("Test with not a good descriptive text")
    void scoreForDescriptiveTextNotGoodTest() {
        assertEquals(0, descriptionScoreCalculationService.scoreForDescriptiveText(""));
    }


    @Test
    @DisplayName("Test For the description Size, chalet with over 50 words")
    void scoreForDescriptionSizeChaletAndLongDescTest() {
        Integer score = descriptionScoreCalculationService.scoreForDescriptionSize("CHALET", "1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 79 1 2 3 4 5 6 7 8 9 0 1 2 3 5 6 7 8 9 0 1 2 3 4 6 7 8 9 9 1 2 43 5 6 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5");
        assertEquals(20, score);
    }

    @Test
    @DisplayName("Test For the description Size, chalet with less than 50 words")
    void scoreForDescriptionSizeChaletAndShortDescTest() {
        Integer score = descriptionScoreCalculationService.scoreForDescriptionSize("CHALET", "1 2 3 4 5 6 7 8 1 2 43 5 6 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5");
        assertEquals(0, score);
    }

    @Test
    @DisplayName("Test For the description Size, flat with words between 20 and 49")
    void scoreForDescriptionSizeFlatAndMidDescTest() {
        Integer score = descriptionScoreCalculationService.scoreForDescriptionSize("FLAT", "1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3");
        assertEquals(10, score);
    }

    @Test
    @DisplayName("Test For the description Size, flat with over 50 words")
    void scoreForDescriptionSizeFlatAndLongDescTest() {
        Integer score = descriptionScoreCalculationService.scoreForDescriptionSize("FLAT", "1 2 3 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 65 6");
        assertEquals(30, score);
    }

    @Test
    @DisplayName("Test For Keywords in description blank")
    void scoreForKeyWordsInDescriptionBlankTest() {
        Integer score = descriptionScoreCalculationService.scoreForKeyWordsInDescription("");
        assertEquals(0, score);
    }

    @Test
    @DisplayName("Test For Keywords in description with all the keywords")
    void scoreForKeyWordsInDescriptionAllKeywordsTest() {
        Integer score = descriptionScoreCalculationService.scoreForKeyWordsInDescription("\"Luminoso\", \"Nuevo\", \"Céntrico\", \"Reformado\", \"Ático\"");
        assertEquals(25, score);
    }

    @Test
    @DisplayName("Test For Keywords in description with 3 keywords")
    void scoreForKeyWordsInDescription3KeywordsTest() {
        Integer score = descriptionScoreCalculationService.scoreForKeyWordsInDescription("\"Luminoso\", \"Nuevo\", \"Céntrico\"");
        assertEquals(15, score);
    }
}
