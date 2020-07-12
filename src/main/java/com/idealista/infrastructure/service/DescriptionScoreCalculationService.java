package com.idealista.infrastructure.service;

public interface DescriptionScoreCalculationService {

    Integer scoreForDescriptiveText(String description);
    Integer scoreForDescriptionSize(String typology,String description);
    Integer scoreForKeyWordsInDescription(String description);
}
