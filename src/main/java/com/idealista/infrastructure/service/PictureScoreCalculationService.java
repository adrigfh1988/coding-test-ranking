package com.idealista.infrastructure.service;

import java.util.List;

public interface PictureScoreCalculationService {

    Integer calculateScore(List<Integer> pictures);
}
