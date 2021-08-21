package com.cooperativismvoteservice.core.resources;

import com.codahale.metrics.annotation.Timed;
import com.cooperativismvoteservice.core.model.VotingAgenda;
import com.cooperativismvoteservice.core.services.VotingAgendaService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/cooperativism-vote-service/voting-agenda")
@Produces("application/json; charset=UTF-8")
public class VotingAgendaResource {

    private final VotingAgendaService votingAgendaService;

    public VotingAgendaResource(VotingAgendaService votingAgendaService) {
        this.votingAgendaService = votingAgendaService;
    }

    @GET
    @Path("/{description}")
    @Timed
    public Response getVote(@PathParam("description") String description) {
        VotingAgenda votingAgenda = votingAgendaService.createAgenda(description);
        if (votingAgenda == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(votingAgenda).build();
    }
}
