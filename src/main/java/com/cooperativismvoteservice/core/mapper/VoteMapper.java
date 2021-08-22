package com.cooperativismvoteservice.core.mapper;

import com.cooperativismvoteservice.core.model.Vote;
import com.cooperativismvoteservice.core.model.VotingAgenda;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VoteMapper implements RowMapper<Vote> {
    @Override
    public Vote map(ResultSet rs, StatementContext ctx) throws SQLException {
        Vote vote = new Vote((VotingAgenda) rs.getObject("voting_agenda_id"), rs.getString("cpf"),
                rs.getString("choice"), rs.getBoolean("vote"));
        vote.setVoteId(rs.getLong("vote_id"));
        return vote;
    }
}