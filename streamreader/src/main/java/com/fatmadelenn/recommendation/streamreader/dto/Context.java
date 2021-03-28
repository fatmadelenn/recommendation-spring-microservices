package com.fatmadelenn.recommendation.streamreader.dto;

import javax.persistence.Embeddable;

@Embeddable
public class Context {

    private Source source;

    public Context() {
    }

    public Context(Source source) {
        this.source = source;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "Context{" +
                "source=" + source +
                '}';
    }
}
