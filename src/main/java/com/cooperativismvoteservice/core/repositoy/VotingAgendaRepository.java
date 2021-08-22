package com.cooperativismvoteservice.core.repositoy;


import com.cooperativismvoteservice.core.dao.VotingAgendaDAO;
import com.cooperativismvoteservice.core.model.VotingAgenda;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class VotingAgendaRepository implements VotingAgendaDAO {
    private final Jdbi jdbi;

    public VotingAgendaRepository(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public VotingAgenda findById(Long id) {
        return jdbi.withExtension(VotingAgendaDAO.class, dao -> dao.findById(id));
    }

    @Override
    public int deleteById(Long voting_agenda_id) {
        return 0;
    }

    @Override
    public int insert(VotingAgenda votingAgenda) {
        return jdbi.withExtension(VotingAgendaDAO.class, dao -> dao.insert(votingAgenda));
    }

    @Override
    public List<VotingAgenda> getAll() {
        return jdbi.withExtension(VotingAgendaDAO.class, VotingAgendaDAO::getAll);
    }

}