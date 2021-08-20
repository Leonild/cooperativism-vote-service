package com.cooperativismvoteservice.core.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Class to define the application config
 * @author Leonildo Azevedo
 */
public class CooperativismVoteServiceConfig extends Configuration {

    @NotBlank
    private String target;

    @Valid
    @NotNull
    private JerseyClientConfiguration httpClient = new JerseyClientConfiguration();

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
}