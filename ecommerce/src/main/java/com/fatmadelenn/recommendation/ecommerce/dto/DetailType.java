package com.fatmadelenn.recommendation.ecommerce.dto;


public enum DetailType {

    NON_PERSONALIZED("non-personalized"),

    PERSONALIZED("personalized");

    public final String value;

    DetailType(String value) {
        this.value = value;
    }
}
