package com.idealista.infrastructure.api;

import com.idealista.infrastructure.service.GlobalScoreCalculationService;
import com.idealista.infrastructure.service.ListingService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ads/v1")
public class AdsController {

    private final GlobalScoreCalculationService globalScoreCalculationService;
    private final ListingService listingService;

    public AdsController(GlobalScoreCalculationService globalScoreCalculationService, ListingService listingService) {
        this.globalScoreCalculationService = globalScoreCalculationService;
        this.listingService = listingService;
    }

    @GetMapping(value = "/quality-listing", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<QualityAd>> qualityListing() {
        List<QualityAd> qualityAds = listingService.getQualityAds();

        if (qualityAds.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(qualityAds);
    }

    @GetMapping(value = "/public-listing", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PublicAd>> publicListing() {
        List<PublicAd> qualityAds = listingService.getIrrelevantAds();

        if (qualityAds.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(qualityAds);
    }

    @PostMapping(value = "/score",consumes = MediaType.ALL_VALUE)
    public ResponseEntity<Void> calculateScore() {
        globalScoreCalculationService.calculateScore();
        return ResponseEntity.ok().build();
    }
}
