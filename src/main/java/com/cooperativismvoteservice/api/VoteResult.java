package com.cooperativismvoteservice.api;

public class VoteResult {

    private Long votingSessionId;
    private int totalYes;
    private int totalNo;
    private int totalNullVotes;

    public VoteResult(Long votingSessionId, int totalYes, int totalNo, int totalNullVotes) {
        this.votingSessionId = votingSessionId;
        this.totalYes = totalYes;
        this.totalNo = totalNo;
        this.totalNullVotes = totalNullVotes;
    }

    public VoteResult(){

    }

    public Long getVotingSessionId() {
        return votingSessionId;
    }

    public void setVotingSessionId(Long votingSessionId) {
        this.votingSessionId = votingSessionId;
    }

    public int getTotalYes() {
        return totalYes;
    }

    public void setTotalYes(int totalYes) {
        this.totalYes = totalYes;
    }

    public int getTotalNo() {
        return totalNo;
    }

    public void setTotalNo(int totalNo) {
        this.totalNo = totalNo;
    }

    public int getTotalNullVotes() {
        return totalNullVotes;
    }

    public void setTotalNullVotes(int totalNullVotes) {
        this.totalNullVotes = totalNullVotes;
    }

}
