package com.cooperativismvoteservice.core;

import com.cooperativismvoteservice.core.configuration.CooperativismVoteServiceConfig;
import com.cooperativismvoteservice.core.resources.VoteResource;
import com.cooperativismvoteservice.core.resources.VotingAgendaResource;
import com.cooperativismvoteservice.core.resources.VotingSessionResource;
import com.cooperativismvoteservice.core.services.VoteService;
import com.cooperativismvoteservice.core.services.VotingAgendaService;
import com.cooperativismvoteservice.core.services.VotingSessionService;
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
    public void run(CooperativismVoteServiceConfig cooperativismVoteServiceConfig, Environment environment) {
        Client client = new JerseyClientBuilder(environment).using(cooperativismVoteServiceConfig.getHttpClient()).build(getName());

        VotingSessionService votingSessionService = new VotingSessionService(cooperativismVoteServiceConfig.getTarget(), client, environment.getObjectMapper());
        VotingSessionResource votingSessionResource = new VotingSessionResource(votingSessionService);
        environment.jersey().register(votingSessionResource);

        VoteService voteService = new VoteService(cooperativismVoteServiceConfig.getTarget(), client, environment.getObjectMapper());
        VoteResource voteResource = new VoteResource(voteService);
        environment.jersey().register(voteResource);

        VotingAgendaService votingAgendaService = new VotingAgendaService(cooperativismVoteServiceConfig.getTarget(), client, environment.getObjectMapper());
        VotingAgendaResource votingAgenda = new VotingAgendaResource(votingAgendaService);
        environment.jersey().register(votingAgenda);

    }

}