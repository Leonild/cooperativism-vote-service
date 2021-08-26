package com.cooperativismvoteservice.resources;

import com.codahale.metrics.annotation.Timed;
import com.cooperativismvoteservice.api.CPFValidator;
import com.cooperativismvoteservice.api.Vote;
import com.cooperativismvoteservice.core.services.VoteService;
import com.cooperativismvoteservice.exceptions.CPFException;
import com.cooperativismvoteservice.exceptions.SessionException;
import com.cooperativismvoteservice.exceptions.VoteException;
import org.hibernate.validator.constraints.Length;

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
                            @Length(min = 3, max = 3, message = "Only Sim or Não in parameter choice!")
                            @PathParam("choice") String choice) {
        try {
            CPFValidator validator = voteService.verifyCPF(cpf);
            if (validator.getStatus().equals("UNABLE_TO_VOTE")) return Response.ok(validator).build();
            if (!voteService.isSessionOpen(sessionId)) return Response.ok("This session is already close!").build();
            Vote vote = voteService.vote(sessionId, cpf, choice);
            return Response.ok(vote).build();
        } catch (CPFException e){
            return Response.ok("Invalid CPF").build();
        } catch (SessionException e){
            return Response.ok("Session not found!").build();
        } catch (VoteException e) {
            return Response.ok("You have already voted in this session!").build();
        }
    }
}
