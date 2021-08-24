package com.cooperativismvoteservice.core.services;

import com.cooperativismvoteservice.api.CPFValidator;
import com.cooperativismvoteservice.api.Vote;
import com.cooperativismvoteservice.api.VotingSession;
import com.cooperativismvoteservice.core.repositoy.VoteRepository;
import com.cooperativismvoteservice.core.repositoy.VotingSessionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * @author Leonildo Azevedo
 */
public class VoteService {

    //estruturando logs para o serviço
    private static final Logger logger = LoggerFactory.getLogger(VoteService.class);

    private final String target;
    private final Client client;
    private final ObjectMapper objectMapper;
    private final VoteRepository voteRepository;

    public VoteService(String target, Client client, ObjectMapper objectMapper, VoteRepository voteRepository) {
        this.target = target;
        this.client = client;
        this.objectMapper = objectMapper;
        this.voteRepository = voteRepository;
    }

    public Vote vote(String sessionId, String cpf, String choice)  {
        VotingSessionRepository votingSessionRepository = new VotingSessionRepository(voteRepository.getJdbi());
        VotingSession votingSession = votingSessionRepository.findById(Long.valueOf(sessionId));
        if(votingSession==null) return null;
        Vote vote = new Vote(votingSession.getVotingSessionId(), cpf, choice, true);
        voteRepository.insert(vote);
        return vote;
    }

    /**
     * Integrating with cpf validation
     * @return CPFValidator object
     */
    public CPFValidator verifyCPF(String cpf) {
        Response response = client.target(target)
                .path(cpf)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();
        if (response.getStatusInfo().getFamily() != Response.Status.Family.SUCCESSFUL) {
            return null;
        }

        String body = response.readEntity(String.class);
        try{
            return objectMapper.readValue(body, CPFValidator.class);
        } catch (IOException e) {
            logger.error("Erro to read herokuapp response");
            throw new RuntimeException(e);
        }
    }
}
