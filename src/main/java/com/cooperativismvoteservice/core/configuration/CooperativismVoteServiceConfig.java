package com.cooperativismvoteservice.core.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Class to define the application config
 * @author Leonildo Azevedo
 */
public class CooperativismVoteServiceConfig extends Configuration {

    @NotEmpty
    private String target;

    @Valid
    @NotNull
    private JerseyClientConfiguration httpClient = new JerseyClientConfiguration();

    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    @JsonProperty
    public String getTarget() {
        return target;
    }

    @JsonProperty
    public void setTarget(String target) {
        this.target = target;
    }

    @JsonProperty
    public JerseyClientConfiguration getHttpClient() {
        return httpClient;
    }

    @JsonProperty
    public void setHttpClient(JerseyClientConfiguration httpClient) {
        this.httpClient = httpClient;
    }

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory factory) {
        this.database = factory;
    }

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }
}