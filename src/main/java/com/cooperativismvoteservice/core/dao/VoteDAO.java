package com.cooperativismvoteservice.core.dao;

import com.cooperativismvoteservice.core.dao.mapper.VoteMapper;
import com.cooperativismvoteservice.api.Vote;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.BindMap;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@RegisterRowMapper(VoteMapper.class)
public interface VoteDAO {

    @SqlQuery("select * from VOTE")
    List<Vote> getAll();

    @SqlQuery("select * from VOTE where VOTE_ID = :vote_id")
    Vote findById(@Bind("vote_id") Long vote_id);

    @SqlQuery("select * from VOTE where VOTING_SESSION_ID = :vote_session_id AND CPF = :cpf")
    Vote findByCPF(@Bind("vote_session_id") Long sessionId, @Bind("cpf") String cpf);

    @SqlUpdate("delete from VOTE where VOTE_ID = :vote_id")
    Long deleteById(@Bind("vote_id") Long vote_id);

//    @SqlUpdate("update VOTING_AGENDA set DESCRIPTION = :description where VOTING_SESSION_ID = :voting_session_id")
//    int update(@BindBean VotingAgenda votingAgenda);

    @SqlUpdate("insert into VOTE (VOTING_SESSION_ID, CPF, CHOICE, VOTE) values (:votingSession, :cpf, :choice, :vote)")
    int insert(@BindBean Vote vote);
}