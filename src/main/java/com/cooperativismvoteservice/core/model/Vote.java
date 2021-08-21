package com.cooperativismvoteservice.core.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vote")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteId;

    @OneToOne()
    @JoinColumn(name = "voting_agenda_id")
    private VotingAgenda votingAgenda;

    private Boolean vote;

    public Vote(VotingAgenda votingAgenda, Boolean vote) {
        this.votingAgenda = votingAgenda;
        this.vote = vote;
    }

    public Long getVoteId() {
        return voteId;
    }

    public void setVoteId(Long voteId) {
        this.voteId = voteId;
    }

    public VotingAgenda getVotingAgenda() {
        return votingAgenda;
    }

    public void setVotingAgenda(VotingAgenda votingAgenda) {
        this.votingAgenda = votingAgenda;
    }

    public Boolean getVote() {
        return vote;
    }

    public void setVote(Boolean vote) {
        this.vote = vote;
    }

}