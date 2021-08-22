package com.cooperativismvoteservice.core.repositoy;

import com.cooperativismvoteservice.core.dao.VotingSessionDAO;
import com.cooperativismvoteservice.core.model.VotingSession;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class VotingSessionRepository implements VotingSessionDAO {
    private final Jdbi jdbi;

    public VotingSessionRepository(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public VotingSession findById(Long id) {
        return jdbi.withExtension(VotingSessionDAO.class, dao -> dao.findById(id));
    }

    @Override
    public int deleteById(Long voting_agenda_id) {
        return 0;
    }

    @Override
    public int insert(VotingSession votingSession) {
        return jdbi.withExtension(VotingSessionDAO.class, dao -> dao.insert(votingSession));
    }


    @Override
    public List<VotingSession> getAll() {
        return jdbi.withExtension(VotingSessionDAO.class, VotingSessionDAO::getAll);
    }

}