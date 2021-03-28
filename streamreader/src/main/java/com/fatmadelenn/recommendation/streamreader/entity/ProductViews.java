package com.fatmadelenn.recommendation.streamreader.entity;

import com.fatmadelenn.recommendation.streamreader.dto.Context;
import com.fatmadelenn.recommendation.streamreader.dto.Properties;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
public class ProductViews {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String userid;

    @NotNull
    @Embedded
    private Properties properties;

    @NotNull
    @Embedded
    private Context context;

    private Timestamp timestamp;

    public ProductViews() {
    }

    public ProductViews(String userid, Properties properties, Context context) {
        this.userid = userid;
        this.properties = properties;
        this.context = context;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ProductViews{" +
                "userid='" + userid + '\'' +
                ", properties=" + properties +
                ", context=" + context +
                ", timestamp=" + timestamp +
                '}';
    }
}
