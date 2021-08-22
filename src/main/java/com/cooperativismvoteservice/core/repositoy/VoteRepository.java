package com.cooperativismvoteservice.core.repositoy;

import com.cooperativismvoteservice.core.dao.VoteDAO;
import com.cooperativismvoteservice.core.model.Vote;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class VoteRepository implements VoteDAO {
    private final Jdbi jdbi;

    public VoteRepository(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public Vote findById(int id) {
        return jdbi.withExtension(VoteDAO.class, dao -> dao.findById(id));
    }

    @Override
    public int deleteById(Long vote_id) {
        return 0;
    }

    @Override
    public int insert(Vote vote) {
        return jdbi.withExtension(VoteDAO.class, dao -> dao.insert(vote));
    }

    @Override
    public List<Vote> getAll() {
        return jdbi.withExtension(VoteDAO.class, VoteDAO::getAll);
    }

}