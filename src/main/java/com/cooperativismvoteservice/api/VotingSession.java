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
    @Column(name = "start_time")
    private Long startTime;

    @JsonProperty
    @Column(name = "total_time")
    private Long totalTime;

    public VotingSession(Long votingAgendaID) {
        this.votingAgendaID = votingAgendaID;
        this.startTime = System.currentTimeMillis();
        this.totalTime = 60L;
    }

    public VotingSession(Long votingAgendaID, Long totalTime) {
        this.votingAgendaID = votingAgendaID;
        this.totalTime = totalTime;
        this.startTime = System.currentTimeMillis();
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

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    public boolean isOpen(){
        Long checkTime = System.currentTimeMillis();
        if (checkTime > (getStartTime() + (getTotalTime() * 1000))) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(VotingSession o) {
        return votingAgendaID.compareTo(o.votingAgendaID);
    }

}