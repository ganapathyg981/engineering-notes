package stackoverflow.models;

public interface Votable {
    Integer getVotes();
    void vote(VoteType voteType);
    String getUserId();
}


