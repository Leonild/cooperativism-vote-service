package com.cooperativismvoteservice.resources;

import com.codahale.metrics.annotation.Timed;
import com.cooperativismvoteservice.api.VotingAgenda;
import com.cooperativismvoteservice.core.services.VotingAgendaService;
import com.cooperativismvoteservice.exceptions.AgendaException;

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

    @GET()
    @Path("/{description}")
    @Timed
    public Response createAgenda(@PathParam("description") String description) {
        VotingAgenda votingAgenda = votingAgendaService.createAgenda(description);
        if (votingAgenda == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(votingAgenda).build();
    }

    @GET
    @Path("/get-agenda/{id}")
    @Timed
    public Response getAgenda(@PathParam("id") String agendaID) {
        try {
            VotingAgenda votingAgenda = votingAgendaService.getAgenda(agendaID);
            return Response.ok(votingAgenda).build();
        } catch (AgendaException e) {
            return Response.ok("Agenda not found!").build();
        }

    }
}
