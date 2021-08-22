package com.cooperativismvoteservice.core.dao;

import com.cooperativismvoteservice.core.mapper.VotingSessionMapper;
import com.cooperativismvoteservice.core.model.VotingSession;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@RegisterRowMapper(VotingSessionMapper.class)
public interface VotingSessionDAO {

    @SqlQuery("select * from VOTING_SESSION")
    List<VotingSession> getAll();

    @SqlQuery("select * from VOTING_SESSION where VOTING_SESSION_ID = :voting_session_id")
    VotingSession findById(@Bind("voting_agenda_id") int voting_agenda_id);

    @SqlUpdate("delete from VOTING_SESSION where VOTING_SESSION_ID = :voting_session_id")
    int deleteById(@Bind("voting_agenda_id") Long voting_session_id);

//    @SqlUpdate("update VOTING_AGENDA set DESCRIPTION = :description where VOTING_SESSION_ID = :voting_session_id")
//    int update(@BindBean VotingAgenda votingAgenda);

    @SqlUpdate("insert into VOTING_SESSION (VOTING_SESSION_ID, VOTING_AGENDA_ID) values (:voting_session_id, :voting_agenda_id)")
    int insert(@BindBean VotingSession votingSession);
}