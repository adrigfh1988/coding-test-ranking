package com.idealista.infrastructure.service.impl;

import com.idealista.infrastructure.persistence.AdVO;
import com.idealista.infrastructure.service.CompleteAddScoreCalculationService;
import org.springframework.stereotype.Service;

@Service
public class CompleteAddScoreCalculationServiceImpl implements CompleteAddScoreCalculationService {

    @Override
    public Integer calculateScore(AdVO adVO) {

        boolean isAdComplete;
        isAdComplete = !adVO.getPictures().isEmpty();

        if (adVO.getTypology().equals("FLAT")) {

            isAdComplete = isAdComplete && !adVO.getDescription().isEmpty();
            isAdComplete = isAdComplete && adVO.getHouseSize() != null;

        }

        if (adVO.getTypology().equals("CHALET")) {
            isAdComplete = isAdComplete && !adVO.getDescription().isEmpty();
            isAdComplete = isAdComplete && adVO.getHouseSize() != null;
            isAdComplete = isAdComplete && adVO.getGardenSize() != null;
        }

        if (isAdComplete) {
            return 40;
        }
        return 0;
    }
}
