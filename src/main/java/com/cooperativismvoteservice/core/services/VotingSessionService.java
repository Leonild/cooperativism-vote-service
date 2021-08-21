package com.cooperativismvoteservice.core.services;

import com.cooperativismvoteservice.core.model.VotingAgenda;
import com.cooperativismvoteservice.core.model.VotingSession;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;

/**
 * @author Leonildo Azevedo
 */
public class VotingSessionService {

    //estruturando logs para o serviço
    private static final Logger logger = LoggerFactory.getLogger(VotingSessionService.class);

    private final String target;
    private final Client client;
    private final ObjectMapper objectMapper;

    public VotingSessionService(String target, Client client, ObjectMapper objectMapper) {
        this.target = target;
        this.client = client;
        this.objectMapper = objectMapper;
    }

    public VotingSession getVotingSession(String sessionId) {
        VotingSession votingSession = new VotingSession();
        votingSession.setVotingSessionId(Long.valueOf(sessionId));
        votingSession.setVotingAgenda(new VotingAgenda("Agenda de teste!"));
        return votingSession;
    }
}
