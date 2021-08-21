package com.cooperativismvoteservice.core.resources;

import com.codahale.metrics.annotation.Timed;
import com.cooperativismvoteservice.core.model.VotingSession;
import com.cooperativismvoteservice.core.services.VotingSessionService;
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
    @Path("/{sessionId}")
    @Timed
    public Response getVotingSession(@Length(min = 1, max = 19, message = "Identificador de sessão inválido")
                                 @PathParam("sessionId") String sessionId) {
        VotingSession votingSession = votingSessionService.getVotingSession(sessionId);
        if (votingSession == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(votingSession).build();
    }
}