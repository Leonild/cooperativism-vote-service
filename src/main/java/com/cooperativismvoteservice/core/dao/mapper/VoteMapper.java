package com.cooperativismvoteservice.core.dao.mapper;

import com.cooperativismvoteservice.api.Vote;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VoteMapper implements RowMapper<Vote> {
    @Override
    public Vote map(ResultSet rs, StatementContext ctx) throws SQLException {
        Vote vote = new Vote(rs.getLong("voting_session_id"), rs.getString("cpf"),
                rs.getString("choice"));
        vote.setVoteId(rs.getLong("vote_id"));
        return vote;
    }
}