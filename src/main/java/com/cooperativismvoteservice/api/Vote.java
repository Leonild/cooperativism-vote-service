package com.cooperativismvoteservice.api;

import javax.persistence.*;

@Entity
@Table(name = "vote")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id")
    private Long voteId;

    @OneToOne()
    @JoinColumn(name = "voting_session_id")
    private Long votingSession;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "choice")
    private String choice;

    @Column(name = "vote")
    private Boolean vote;

    public Vote(Long votingSession, String cpf, String choice, Boolean vote) {
        this.votingSession = votingSession;
        this.cpf = cpf;
        this.choice = choice;
        this.vote = vote;
    }

    public Vote() {
    }

    public Long getVoteId() {
        return voteId;
    }

    public void setVoteId(Long voteId) {
        this.voteId = voteId;
    }

    public Long getVotingSession() {
        return votingSession;
    }

    public void setVotingSession(Long votingSession) {
        this.votingSession = votingSession;
    }

    public Boolean getVote() {
        return vote;
    }

    public void setVote(Boolean vote) {
        this.vote = vote;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vote)) return false;

        Vote that = (Vote) o;

        if (!getVoteId().equals(that.getVoteId())) return false;

        return true;
    }

}