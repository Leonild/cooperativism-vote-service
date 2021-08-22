package com.cooperativismvoteservice.core.model;

import javax.persistence.*;

@Entity
@Table(name = "voting_agenda")
public class VotingAgenda implements Comparable<VotingAgenda>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voting_agenda_id")
    private Long votingAgendaId;

    @Column(name = "description")
    private String description;

    @OneToOne(mappedBy = "votingAgenda")
    private VotingSession votingSession;

    public VotingAgenda(String description) {
        this.description = description;
    }

    public VotingAgenda() {
    }

    public Long getVotingAgendaId() {
        return votingAgendaId;
    }

    public void setVotingAgendaId(Long votingAgendaId) {
        this.votingAgendaId = votingAgendaId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int compareTo(VotingAgenda o) {
        return description.compareTo(o.description);
    }
}