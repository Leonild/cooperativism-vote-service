package com.cooperativismvoteservice.core;

import com.cooperativismvoteservice.core.configuration.CooperativismVoteServiceConfig;
import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import javax.ws.rs.client.Client;

/**
 *
 * @author Leonildo
 */
public class CooperativismVoteServiceApp extends Application<CooperativismVoteServiceConfig> {

    public static void main(String[] args) throws Exception {
        new CooperativismVoteServiceApp().run(args);
    }

    @Override
    public String getName() {
        return "cooperativism-vote-service";
    }

    @Override
    public void initialize(Bootstrap<CooperativismVoteServiceConfig> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(CooperativismVoteServiceConfig cooperativismVoteServiceConfig, Environment environment) throws Exception {
        Client client = new JerseyClientBuilder(environment).using(cooperativismVoteServiceConfig.getHttpClient()).build(getName());

    }

}