package com.fatmadelenn.recommendation.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Source {

    @JsonProperty("mobile-web")
    MOBILE_WEB("mobile-web"),

    @JsonProperty("desktop")
    DESKTOP("desktop"),

    @JsonProperty("mobile-app")
    MOBILE_APP("mobile-app");

    public final String value;

    Source(String value) {
        this.value = value;
    }
}
