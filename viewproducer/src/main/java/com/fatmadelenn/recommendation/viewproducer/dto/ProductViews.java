package com.fatmadelenn.recommendation.viewproducer.dto;

import java.util.UUID;

public class ProductViews {
    private String event;
    private UUID messageid;
    private String userid;
    private Properties properties;
    private Context context;

    public ProductViews() {
    }

    public ProductViews(String event, UUID messageid, String userid, Properties properties, Context context) {
        this.event = event;
        this.messageid = messageid;
        this.userid = userid;
        this.properties = properties;
        this.context = context;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public UUID getMessageid() {
        return messageid;
    }

    public void setMessageid(UUID messageid) {
        this.messageid = messageid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "KafkaProductMessage{" +
                "event='" + event + '\'' +
                ", messageid=" + messageid +
                ", userid='" + userid + '\'' +
                ", properties=" + properties +
                ", context=" + context +
                '}';
    }
}
