package com.cooperativismvoteservice.core.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "voting_session")
public class VotingSession implements Comparable<VotingSession>{

    @Id
    @JsonProperty
    @Column(name = "voting_session_id")
    private Long votingSessionId;

    @JsonProperty
    @Column(name = "voting_agenda_id")
    private Long votingAgendaID;

    public VotingSession(Long votingAgendaID) {
        this.votingAgendaID = votingAgendaID;
    }

    public VotingSession() {
    }

    public Long getVotingSessionId() {
        return votingSessionId;
    }

    public void setVotingSessionId(Long votingSessionId) {
        this.votingSessionId = votingSessionId;
    }

    public Long getVotingAgendaID() {
        return votingAgendaID;
    }

    public void setVotingAgendaID(Long votingAgendaID) {
        this.votingAgendaID = votingAgendaID;
    }

    @Override
    public int compareTo(VotingSession o) {
        return votingAgendaID.compareTo(o.votingAgendaID);
    }

}