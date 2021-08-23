package com.cooperativismvoteservice.core.model;


import javax.persistence.*;

@Entity
@Table(name = "voting_session")
public class VotingSession implements Comparable<VotingSession>{

    @Id
    @Column(name = "voting_session_id")
    private Long votingSessionId;

    @JoinColumn(name = "voting_agenda_id")
    private Long votingAgendaID;

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