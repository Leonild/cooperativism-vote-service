package com.cooperativismvoteservice.core.mapper;

import com.cooperativismvoteservice.model.VotingSession;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VotingSessionMapper implements RowMapper<VotingSession> {
    @Override
    public VotingSession map(ResultSet rs, StatementContext ctx) throws SQLException {
        VotingSession votingSession = new VotingSession(rs.getLong("voting_agenda_id"));
        votingSession.setVotingSessionId(rs.getLong("voting_session_id"));;
        return votingSession;
    }
}