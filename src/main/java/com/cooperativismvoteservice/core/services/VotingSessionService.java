package com.cooperativismvoteservice.core.services;

import com.cooperativismvoteservice.api.VotingAgenda;
import com.cooperativismvoteservice.api.VotingSession;
import com.cooperativismvoteservice.core.dao.repositoy.VotingAgendaRepository;
import com.cooperativismvoteservice.core.dao.repositoy.VotingSessionRepository;
import com.cooperativismvoteservice.exceptions.AgendaException;
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
    private  final VotingSessionRepository votingSessionRepository;

    public VotingSessionService(String target, Client client, ObjectMapper objectMapper, VotingSessionRepository votingSessionRepository) {
        this.target = target;
        this.client = client;
        this.objectMapper = objectMapper;
        this.votingSessionRepository = votingSessionRepository;
    }

    public VotingSession openVotingSession(String agendaId) throws AgendaException {
        VotingAgendaService agendaService = new VotingAgendaService(target, client, objectMapper,
                new VotingAgendaRepository(votingSessionRepository.getJdbi()));
        VotingAgenda votingAgenda = agendaService.getAgenda(agendaId);
        VotingSession votingSession = new VotingSession(votingAgenda.getVotingAgendaId());
        logger.info("AGENDA ID: " + votingAgenda.getVotingAgendaId());
        Long sessionID = votingSessionRepository.insert(votingSession);
        votingSession.setVotingSessionId(sessionID);
        return votingSession;
    }

    public VotingSession openVotingSession(String agendaId, String time) throws AgendaException {
        VotingAgendaService agendaService = new VotingAgendaService(target, client, objectMapper,
                new VotingAgendaRepository(votingSessionRepository.getJdbi()));
        VotingAgenda votingAgenda = agendaService.getAgenda(agendaId);
        VotingSession votingSession = new VotingSession(votingAgenda.getVotingAgendaId(), Long.valueOf(time));
        logger.info("AGENDA ID: " + votingAgenda.getVotingAgendaId());
        Long sessionID = votingSessionRepository.insert(votingSession);
        votingSession.setVotingSessionId(sessionID);
        return votingSession;
    }
}
