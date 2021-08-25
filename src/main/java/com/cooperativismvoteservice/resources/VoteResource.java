package com.cooperativismvoteservice.resources;

import com.codahale.metrics.annotation.Timed;
import com.cooperativismvoteservice.api.CPFValidator;
import com.cooperativismvoteservice.api.Vote;
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
    @Path("/{sessionId}/{cpf}/{choice}")
    @Timed
    public Response getVote(@PathParam("sessionId") String sessionId,
                            @PathParam("cpf") String cpf,
                            @PathParam("choice") String choice) {
        CPFValidator validator = voteService.verifyCPF(cpf);
        if(validator == null) return Response.ok("Invalid CPF").build();
        if(validator.getStatus().equals("UNABLE_TO_VOTE")) return Response.ok(validator).build();
        Vote vote = voteService.vote(sessionId, cpf, choice);
        if (vote == null) {
            return Response.ok("Session not found!").build();
        }
        return Response.ok(vote).build();
    }
}
