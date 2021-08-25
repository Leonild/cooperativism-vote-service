package com.cooperativismvoteservice.api;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

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

    @JsonProperty
    private boolean open;

    private LocalDateTime startTime;

    private Long totalTime;

    public VotingSession(Long votingAgendaID) {
        this.votingAgendaID = votingAgendaID;
        this.startTime = LocalDateTime.now();
    }

    public VotingSession(Long votingAgendaID, Long totalTime) {
        this.votingAgendaID = votingAgendaID;
        this.totalTime = totalTime;
        this.startTime = LocalDateTime.now();
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

    public boolean getOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    @Override
    public int compareTo(VotingSession o) {
        return votingAgendaID.compareTo(o.votingAgendaID);
    }

}