package com.cooperativismvoteservice.core.services;

import com.cooperativismvoteservice.core.model.Vote;
import com.cooperativismvoteservice.core.model.VotingAgenda;
import com.cooperativismvoteservice.core.repositoy.VoteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;

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

    public Vote vote(String agendaId, String cpf, String choice) {
        Vote vote = new Vote(new VotingAgenda(agendaId), cpf, choice, true);
        return vote;
    }
}
