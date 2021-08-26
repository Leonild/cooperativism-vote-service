package com.cooperativismvoteservice.core.services;

import com.cooperativismvoteservice.api.CPFValidator;
import com.cooperativismvoteservice.api.Vote;
import com.cooperativismvoteservice.api.VotingSession;
import com.cooperativismvoteservice.core.dao.repositoy.VoteRepository;
import com.cooperativismvoteservice.core.dao.repositoy.VotingSessionRepository;
import com.cooperativismvoteservice.exceptions.CPFException;
import com.cooperativismvoteservice.exceptions.SessionException;
import com.cooperativismvoteservice.exceptions.VoteException;
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

    public Vote vote(String sessionId, String cpf, String choice) throws VoteException {
        checkVote(sessionId, cpf);
        VotingSessionRepository votingSessionRepository = new VotingSessionRepository(voteRepository.getJdbi());
        VotingSession votingSession = votingSessionRepository.findById(Long.valueOf(sessionId));
        Vote vote = new Vote(votingSession.getVotingSessionId(), cpf, choice);
        vote.setVoteId(Long.valueOf(voteRepository.insert(vote)));
        return vote;
    }

    public boolean isSessionOpen(String sessionId) throws SessionException {
        VotingSessionRepository votingSessionRepository = new VotingSessionRepository(voteRepository.getJdbi());
        VotingSession votingSession = votingSessionRepository.findById(Long.valueOf(sessionId));
        if(votingSession == null){
            throw new SessionException("Session not found!");
        }
        return votingSession.isOpen();
    }

    public boolean checkVote(String sessionId, String cpf) throws VoteException {
        Vote vote = voteRepository.findByCPF(Long.valueOf(sessionId), cpf);
        if(vote == null) return false;
        throw new VoteException("Vote already registered!");
    }

    /**
     * Integrating with cpf validation
     * @return CPFValidator object
     */
    public CPFValidator verifyCPF(String cpf) throws CPFException {
        try{
            Response response = client.target(target)
                    .path(cpf)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get();
            if (response.getStatusInfo().getFamily() != Response.Status.Family.SUCCESSFUL) {
                throw new CPFException("Invalid CPF");
            }

            String body = response.readEntity(String.class);
            return objectMapper.readValue(body, CPFValidator.class);
        } catch (Exception e) {
            logger.error("Erro to read herokuapp response");
            throw new CPFException("Error in the herokuapp response");
        }
    }
}
