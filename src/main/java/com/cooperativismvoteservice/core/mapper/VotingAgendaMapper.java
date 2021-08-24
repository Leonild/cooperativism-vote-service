package com.cooperativismvoteservice.core.mapper;

import com.cooperativismvoteservice.api.VotingAgenda;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VotingAgendaMapper implements RowMapper<VotingAgenda> {
    @Override
    public VotingAgenda map(ResultSet rs, StatementContext ctx) throws SQLException {
        VotingAgenda votingAgenda = new VotingAgenda(rs.getString("description"));
        votingAgenda.setVotingAgendaId(rs.getLong("voting_agenda_id"));
        return votingAgenda;
    }
}