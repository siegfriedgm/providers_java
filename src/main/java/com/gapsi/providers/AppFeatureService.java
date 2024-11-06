package com.gapsi.providers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AppFeatureService {

    private final AppFeature appFeature;

    public AppFeatureService(AppFeature appFeature) {
        this.appFeature = appFeature;
    }

    public ResponseEntity<AppFeature> getAppFeature() {
        return ResponseEntity.ok(appFeature);
    }

}
