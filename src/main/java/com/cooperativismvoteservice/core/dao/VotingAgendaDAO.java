package com.cooperativismvoteservice.core.dao;

import com.cooperativismvoteservice.core.dao.mapper.VotingAgendaMapper;
import com.cooperativismvoteservice.api.VotingAgenda;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@RegisterRowMapper(VotingAgendaMapper.class)
public interface VotingAgendaDAO {

    @SqlQuery("select * from VOTING_AGENDA")
    List<VotingAgenda> getAll();

    @SqlQuery("select * from VOTING_AGENDA where VOTING_AGENDA_ID = :voting_agenda_id")
    VotingAgenda findById(@Bind("voting_agenda_id") Long voting_agenda_id);

    @SqlUpdate("delete from VOTING_AGENDA where VOTING_AGENDA_ID = :voting_agenda_id")
    Long deleteById(@Bind("voting_agenda_id") Long voting_agenda_id);

//    @SqlUpdate("update VOTING_AGENDA set DESCRIPTION = :description where VOTING_AGENDA_ID = :voting_agenda_id")
//    int update(@BindBean VotingAgenda votingAgenda);

    @SqlUpdate("insert into VOTING_AGENDA (DESCRIPTION) values (:description)")
    @GetGeneratedKeys
    Long insert(@BindBean VotingAgenda votingAgenda);
}
