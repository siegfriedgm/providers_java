package com.gapsi.providers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class AppFeature {

    @Value(value = "${app.feature.version}")
    private String appVersion;

    @Value(value = "${app.feature.candidate.name}")
    private String appCandidateName;

    @JsonIgnore
    @Value(value = "${app.db.location}")
    private String appDbLocation;

}
