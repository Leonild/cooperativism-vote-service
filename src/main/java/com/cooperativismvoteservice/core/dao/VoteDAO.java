package com.cooperativismvoteservice.core.dao;

import com.cooperativismvoteservice.core.mapper.VoteMapper;
import com.cooperativismvoteservice.core.model.Vote;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@RegisterRowMapper(VoteMapper.class)
public interface VoteDAO {

    @SqlQuery("select * from VOTE")
    List<Vote> getAll();

    @SqlQuery("select * from VOTE where VOTE_ID = :vote_id")
    Vote findById(@Bind("vote_id") int vote_id);

    @SqlUpdate("delete from VOTE where VOTE_ID = :vote_id")
    int deleteById(@Bind("vote_id") Long vote_id);

//    @SqlUpdate("update VOTING_AGENDA set DESCRIPTION = :description where VOTING_SESSION_ID = :voting_session_id")
//    int update(@BindBean VotingAgenda votingAgenda);

    @SqlUpdate("insert into VOTE (VOTING_SESSION_ID, CPF, CHOICE, VOTE) values (:votingSession, :cpf, :choice, :vote)")
    int insert(@BindBean Vote vote);
}