package com.cooperativismvoteservice.core.services;

import com.cooperativismvoteservice.core.model.VotingAgenda;
import com.cooperativismvoteservice.core.repositoy.VotingAgendaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;

/**
 * @author Leonildo Azevedo
 */
public class VotingAgendaService {

    //estruturando logs para o serviço
    private static final Logger logger = LoggerFactory.getLogger(VoteService.class);

    private final String target;
    private final Client client;
    private final ObjectMapper objectMapper;
    private final VotingAgendaRepository votingAgendaRepository;

    public VotingAgendaService(String target, Client client, ObjectMapper objectMapper, VotingAgendaRepository votingAgendaRepository) {
        this.target = target;
        this.client = client;
        this.objectMapper = objectMapper;
        this.votingAgendaRepository = votingAgendaRepository;
    }

    public VotingAgenda createAgenda(String description){
        VotingAgenda votingAgenda = new VotingAgenda(description);
        Long votinAgendaID = votingAgendaRepository.insert(votingAgenda);
        votingAgenda.setVotingAgendaId(votinAgendaID);
        logger.debug("Insertion ID was: " + votinAgendaID);
        return votingAgenda;
    }

    public VotingAgenda getAgenda(String agendaId) {
        VotingAgenda votingAgenda = votingAgendaRepository.findById(Long.valueOf(agendaId));
        return votingAgenda;
    }
}