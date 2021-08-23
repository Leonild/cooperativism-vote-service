package com.cooperativismvoteservice.core.repositoy;

import com.cooperativismvoteservice.core.dao.VoteDAO;
import com.cooperativismvoteservice.core.model.Vote;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class VoteRepository implements VoteDAO {
    private final Jdbi jdbi;

    public VoteRepository(Jdbi jdbi) {
        this.jdbi = jdbi;
        this.jdbi.useHandle(handle -> handle.execute("CREATE TABLE IF NOT EXISTS vote " +
                "(vote_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, " +
                "voting_session_id BIGINT, cpf varchar(20) NOT NULL, choice varchar(5) NOT NULL," +
                "vote bool NOT NULL)"));
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

    public Jdbi getJdbi() {
         return jdbi;
    }
}