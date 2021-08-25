package com.cooperativismvoteservice;

import com.cooperativismvoteservice.configuration.CooperativismVoteServiceConfig;
import com.cooperativismvoteservice.core.repositoy.VoteRepository;
import com.cooperativismvoteservice.core.repositoy.VotingAgendaRepository;
import com.cooperativismvoteservice.core.repositoy.VotingSessionRepository;
import com.cooperativismvoteservice.resources.VoteResource;
import com.cooperativismvoteservice.resources.VotingAgendaResource;
import com.cooperativismvoteservice.resources.VotingSessionResource;
import com.cooperativismvoteservice.core.services.VoteService;
import com.cooperativismvoteservice.core.services.VotingAgendaService;
import com.cooperativismvoteservice.core.services.VotingSessionService;
import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.jdbi3.bundles.JdbiExceptionsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.jdbi.v3.core.Jdbi;

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
        bootstrap.addBundle(new ViewBundle<>());
        bootstrap.addBundle(new JdbiExceptionsBundle());
    }

    @Override
    public void run(CooperativismVoteServiceConfig cooperativismVoteServiceConfig, Environment environment) {
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, cooperativismVoteServiceConfig.getDataSourceFactory(), "postgresql");

        Client client = new JerseyClientBuilder(environment).using(cooperativismVoteServiceConfig.getHttpClient()).build(getName());

        VotingSessionService votingSessionService = new VotingSessionService(cooperativismVoteServiceConfig.getTarget(), client, environment.getObjectMapper(), new VotingSessionRepository(jdbi));
        VotingSessionResource votingSessionResource = new VotingSessionResource(votingSessionService);
        environment.jersey().register(votingSessionResource);

        VoteService voteService = new VoteService(cooperativismVoteServiceConfig.getTarget(), client, environment.getObjectMapper(), new VoteRepository(jdbi));
        VoteResource voteResource = new VoteResource(voteService);
        environment.jersey().register(voteResource);

        VotingAgendaService votingAgendaService = new VotingAgendaService(cooperativismVoteServiceConfig.getTarget(), client, environment.getObjectMapper(), new VotingAgendaRepository(jdbi));
        VotingAgendaResource votingAgenda = new VotingAgendaResource(votingAgendaService);
        environment.jersey().register(votingAgenda);

    }

}