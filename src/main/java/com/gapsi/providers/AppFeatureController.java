package com.gapsi.providers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appfeature")
public class AppFeatureController {

    private final AppFeatureService appFeatureService;

    public AppFeatureController(AppFeatureService appFeatureService) {
        this.appFeatureService = appFeatureService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AppFeature> welcomeToApp(){
        return appFeatureService.getAppFeature();
    }

}
