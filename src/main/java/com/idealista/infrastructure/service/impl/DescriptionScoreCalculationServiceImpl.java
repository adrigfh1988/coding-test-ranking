package com.idealista.infrastructure.service.impl;

import com.idealista.infrastructure.service.DescriptionScoreCalculationService;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class DescriptionScoreCalculationServiceImpl implements DescriptionScoreCalculationService {

    @Override
    public Integer scoreForDescriptiveText(String description) {

        int scoreIfHasDecription = 0;

        if (description != null && !description.trim().isEmpty()) {
            scoreIfHasDecription = 5;
        }

        return scoreIfHasDecription;
    }

    @Override
    public Integer scoreForDescriptionSize(String typology, String description) {

        int scoreOfTypologyDescription = 0;
        int numberOfWordsInDescription = countWords(description);
        if (typology.equals("CHALET")) {

            if (numberOfWordsInDescription > 50) {
                scoreOfTypologyDescription = 20;
            }

        } else if (typology.equals("FLAT")) {

            if (numberOfWordsInDescription >= 20 && numberOfWordsInDescription < 49) {
                scoreOfTypologyDescription = 10;
            }
            if (numberOfWordsInDescription > 50) {
                scoreOfTypologyDescription = 30;
            }
        }

        return scoreOfTypologyDescription;

    }

    @Override
    public Integer scoreForKeyWordsInDescription(String description) {

        return Stream.of("Luminoso", "Nuevo", "Céntrico", "Reformado", "Ático")
                .map(keyWord -> {

                    if (description.toLowerCase().contains(keyWord.toLowerCase())) {
                        return 5;
                    }
                    return 0;

                }).reduce(Integer::sum)
                .orElseThrow(() -> new IllegalArgumentException("Not able tu add the values of the pictures"));
    }

    private int countWords(String str) {
        int count = 0;

        if (str.isEmpty())
            return count;

        if (!(" ".equals(str.substring(0, 1))) || !(" ".equals(str.substring(str.length() - 1)))) {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == ' ') {
                    count++;
                }
            }
            count = count + 1;
        }
        return count; // returns 0 if string starts or ends with space " ".
    }
}
