package com.idealista.infrastructure.service.impl;

import com.idealista.infrastructure.api.PublicAd;
import com.idealista.infrastructure.api.QualityAd;
import com.idealista.infrastructure.persistence.InMemoryPersistence;
import com.idealista.infrastructure.service.ListingService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ListingServiceImplTest {

    @Test
    void getQualityAds() {
        InMemoryPersistence inMemoryPersistence = new InMemoryPersistence();
        inMemoryPersistence.findAllAds().get(0).setScore(41);
        inMemoryPersistence.findAllAds().get(1).setScore(20);
        inMemoryPersistence.findAllAds().get(2).setScore(100);
        inMemoryPersistence.findAllAds().get(3).setScore(0);
        inMemoryPersistence.findAllAds().get(4).setScore(0);
        inMemoryPersistence.findAllAds().get(5).setScore(0);
        inMemoryPersistence.findAllAds().get(6).setScore(89);
        inMemoryPersistence.findAllAds().get(7).setScore(61);

        ListingService listingService = new ListingServiceImpl(inMemoryPersistence);
        List<QualityAd> qualityAds = listingService.getQualityAds();

        List<QualityAd> ads = new ArrayList<>();
        ads.add(new QualityAd(3, "CHALET", "", Collections.singletonList("http://www.idealista.com/pictures/2"), 300, null, null, null));
        ads.add(new QualityAd(7, "GARAGE", "Garaje en el centro de Albacete", Collections.emptyList(), 300, null, null, null));
        ads.add(new QualityAd(8, "CHALET", "Maravilloso chalet situado en lAs afueras de un pequeño pueblo rural. El entorno es espectacular, las vistas magníficas. ¡Cómprelo ahora!", Arrays.asList("http://www.idealista.com/pictures/1", "http://www.idealista.com/pictures/7"), 300, null, null, null));
        ads.add(new QualityAd(1, "CHALET", "Este piso es una ganga, compra, compra, COMPRA!!!!!", Collections.emptyList(), 300, null, null, null));

        assertArrayEquals(ads.toArray(), qualityAds.toArray());

    }

    @Test
    void getPublicAds() {

        InMemoryPersistence inMemoryPersistence = new InMemoryPersistence();
        inMemoryPersistence.findAllAds().get(0).setScore(20);
        inMemoryPersistence.findAllAds().get(0).setIrrelevantSince(new Date());
        inMemoryPersistence.findAllAds().get(1).setScore(20);
        inMemoryPersistence.findAllAds().get(1).setIrrelevantSince(new Date());
        inMemoryPersistence.findAllAds().get(2).setScore(100);
        inMemoryPersistence.findAllAds().get(3).setScore(80);
        inMemoryPersistence.findAllAds().get(4).setScore(90);
        inMemoryPersistence.findAllAds().get(5).setScore(61);
        inMemoryPersistence.findAllAds().get(6).setScore(89);
        inMemoryPersistence.findAllAds().get(7).setScore(61);

        ListingService listingService = new ListingServiceImpl(inMemoryPersistence);
        List<PublicAd> irrelevantAds = listingService.getIrrelevantAds();

        List<PublicAd> ads = new ArrayList<>();
        ads.add(new PublicAd(1, "CHALET", "Este piso es una ganga, compra, compra, COMPRA!!!!!", Collections.emptyList(), 300, null,  null));
        ads.add(new PublicAd(2, "FLAT", "Nuevo ático céntrico recién reformado. No deje pasar la oportunidad y adquiera este ático de lujo", Arrays.asList("http://www.idealista.com/pictures/4"), 300, null,  null));

        assertArrayEquals(ads.toArray(), irrelevantAds.toArray());
    }
}
