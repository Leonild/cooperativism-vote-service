package com.cooperativismvoteservice.resources;

import com.codahale.metrics.annotation.Timed;
import com.cooperativismvoteservice.api.VotingSession;
import com.cooperativismvoteservice.core.services.VotingSessionService;
import com.cooperativismvoteservice.exceptions.AgendaException;
import org.hibernate.validator.constraints.Length;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/cooperativism-vote-service/voting-session")
@Produces("application/json; charset=UTF-8")
public class VotingSessionResource {

    private final VotingSessionService votingSessionService;

    public VotingSessionResource(VotingSessionService votingSessionService) {
        this.votingSessionService = votingSessionService;
    }

    @GET
    @Path("/open/{agendaId}")
    @Timed
    public Response openVotingSession(@Length(min = 1, max = 19, message = "Invalid session ID!")
                                 @PathParam("agendaId") String agendaId) {
        try {
            VotingSession votingSession = votingSessionService.openVotingSession(agendaId);
            return Response.ok(votingSession).build();
        } catch (AgendaException e) {
            return Response.ok("Agenda not found!").build();
        }
    }

    @GET
    @Path("/open/{agendaId}/{time}")
    @Timed
    public Response openVotingSessionWithTime(@Length(min = 1, max = 19, message = "Invalid session ID!")
                                      @PathParam("agendaId") String agendaId,
                                              @PathParam("time") String time) {
        try {
            VotingSession votingSession = votingSessionService.openVotingSession(agendaId, time);
            return Response.ok(votingSession).build();
        } catch (AgendaException e) {
            return Response.ok("Agenda not found!").build();
        }
    }
}