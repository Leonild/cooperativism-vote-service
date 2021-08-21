package com.cooperativismvoteservice.core.resources;

import com.codahale.metrics.annotation.Timed;
import com.cooperativismvoteservice.core.model.Vote;
import com.cooperativismvoteservice.core.services.VoteService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/cooperativism-vote-service/vote")
@Produces("application/json; charset=UTF-8")
public class VoteResource {

    private final VoteService voteService;

    public VoteResource(VoteService voteService) {
        this.voteService = voteService;
    }

    @GET
    @Path("/{agendaId}/{cpf}/{choice}")
    @Timed
    public Response getVote(@PathParam("agendaId") String agendaId,
                            @PathParam("cpf") String cpf,
                            @PathParam("choice") String choice) {
        Vote vote = voteService.vote(agendaId, cpf, choice);
        if (vote == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(vote).build();
    }
}
