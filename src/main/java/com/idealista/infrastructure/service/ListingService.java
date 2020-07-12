package com.idealista.infrastructure.service;

import com.idealista.infrastructure.api.PublicAd;
import com.idealista.infrastructure.api.QualityAd;

import java.util.List;

public interface ListingService {

    List<QualityAd> getQualityAds();
    List<PublicAd> getIrrelevantAds();
}
