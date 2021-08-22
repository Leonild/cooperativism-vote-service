package com.cooperativismvoteservice.core.model;


import javax.persistence.*;

@Entity
@Table(name = "voting_session")
public class VotingSession implements Comparable<VotingSession>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voting_session_id")
    private Long votingSessionId;

    @OneToOne()
    @JoinColumn(name = "voting_agenda_id")
    private VotingAgenda votingAgenda;

    public VotingSession() {
    }

    public Long getVotingSessionId() {
        return votingSessionId;
    }

    public void setVotingSessionId(Long votingSessionId) {
        this.votingSessionId = votingSessionId;
    }

    public VotingAgenda getVotingAgenda() {
        return votingAgenda;
    }

    public void setVotingAgenda(VotingAgenda votingAgenda) {
        this.votingAgenda = votingAgenda;
    }

    @Override
    public int compareTo(VotingSession o) {
        return votingAgenda.compareTo(o.votingAgenda);
    }

}