package Main;

/**
 * This is a class used to represent each individual entry in the leaderboard.
 *
 * @author Josh Friedman
 * @version 1 - June 1 - Josh Friedman - 20 mins - created entire class
 */
public class LeaderboardEntry {
    private String name;
    private int numInvites;
    private int numTips;
    private int numDeaths;

    /**
     * This is the class constructor that sets all of the isntance variables.
     *
     * @param name The name of the leaderboard entry
     * @param numInvites The number of invites for the leaderboard entry
     * @param numTips The number of tips for the leaderboard entry
     * @param numDeaths The number of deaths for the leaderboard entry
     */
    public LeaderboardEntry(String name, int numInvites, int numTips, int numDeaths) {
        this.name = name;
        this.numInvites = numInvites;
        this.numTips = numTips;
        this.numDeaths = numDeaths;
    }
}
