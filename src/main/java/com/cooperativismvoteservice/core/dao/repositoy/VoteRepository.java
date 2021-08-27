package com.cooperativismvoteservice.core.dao.repositoy;

import com.cooperativismvoteservice.core.dao.VoteDAO;
import com.cooperativismvoteservice.api.Vote;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class VoteRepository implements VoteDAO {
    private final Jdbi jdbi;

    public VoteRepository(Jdbi jdbi) {
        this.jdbi = jdbi;
        this.jdbi.useHandle(handle -> handle.execute("CREATE TABLE IF NOT EXISTS vote " +
                "(vote_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, " +
                "voting_session_id BIGINT, cpf varchar(20) NOT NULL, choice varchar(5) NOT NULL," +
                "CONSTRAINT voting_session_id FOREIGN KEY(voting_session_id) REFERENCES voting_session(voting_session_id))"));
    }

    @Override
    public Vote findById(Long id) {
        return jdbi.withExtension(VoteDAO.class, dao -> dao.findById(id));
    }

    @Override
    public Vote findByCPF(Long sessionId, String cpf) {
        return jdbi.withExtension(VoteDAO.class, dao -> dao.findByCPF(sessionId, cpf));
    }


    @Override
    public Long deleteById(Long vote_id) {
        return 0L;
    }

    @Override
    public Long insert(Vote vote) {
        return jdbi.withExtension(VoteDAO.class, dao -> dao.insert(vote));
    }

    @Override
    public List<Vote> getAll(Long sessionId) {
        return jdbi.withExtension(VoteDAO.class, dao -> dao.getAll(sessionId));
    }

    @Override
    public List<Vote> getAll() {
        return jdbi.withExtension(VoteDAO.class, VoteDAO::getAll);
    }

    public Jdbi getJdbi() {
         return jdbi;
    }
}