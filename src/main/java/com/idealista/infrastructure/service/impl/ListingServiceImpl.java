package com.idealista.infrastructure.service.impl;

import com.idealista.infrastructure.api.PublicAd;
import com.idealista.infrastructure.api.QualityAd;
import com.idealista.infrastructure.persistence.InMemoryPersistence;
import com.idealista.infrastructure.persistence.PictureVO;
import com.idealista.infrastructure.service.ListingService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListingServiceImpl implements ListingService {

    private final InMemoryPersistence inMemoryPersistence;

    public ListingServiceImpl(InMemoryPersistence inMemoryPersistence) {
        this.inMemoryPersistence = inMemoryPersistence;
    }

    @Override
    public List<QualityAd> getQualityAds() {

        return inMemoryPersistence
                .findAllAds()
                .stream()
                .filter(adVO -> adVO.getScore() >= 40)
                .map(adVO -> {
                    List<String> urls = inMemoryPersistence.findByIdIn(adVO.getPictures())
                            .stream()
                            .map(PictureVO::getUrl)
                            .collect(Collectors.toList());

                    return new QualityAd(adVO.getId(),
                            adVO.getTypology(),
                            adVO.getDescription(),
                            urls,
                            adVO.getHouseSize(),
                            adVO.getGardenSize(),
                            adVO.getScore(),
                            adVO.getIrrelevantSince());

                })
                .sorted(Comparator.comparing(QualityAd::getScore,Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    @Override
    public List<PublicAd> getIrrelevantAds() {

        return inMemoryPersistence
                .findAllAds()
                .stream()
                .filter(adVO -> adVO.getScore() < 40)
                .map(adVO -> {
                    List<String> urls = inMemoryPersistence.findByIdIn(adVO.getPictures())
                            .stream()
                            .map(PictureVO::getUrl)
                            .collect(Collectors.toList());

                    return new PublicAd(adVO.getId(),
                            adVO.getTypology(),
                            adVO.getDescription(),
                            urls,
                            adVO.getHouseSize(),
                            adVO.getGardenSize(),
                            adVO.getIrrelevantSince());

                })
                .collect(Collectors.toList());
    }
}
