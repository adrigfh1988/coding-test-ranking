package com.idealista.infrastructure.service;

import com.idealista.infrastructure.persistence.AdVO;

public interface CompleteAddScoreCalculationService {

    Integer calculateScore(AdVO adVO);
}
