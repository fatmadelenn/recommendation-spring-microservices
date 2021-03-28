package com.fatmadelenn.recommendation.viewproducer.dto;

public class Context {

    private String source;

    public Context(String source) {
        this.source = source;
    }

    public Context() {
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
