package Main;

public class LeaderboardEntry {
    private String name;
    private int numInvites;
    private int numTips;
    private int numDeaths;

    public LeaderboardEntry(String name, int numInvites, int numTips, int numDeaths) {
        this.name = name;
        this.numInvites = numInvites;
        this.numTips = numTips;
        this.numDeaths = numDeaths;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumInvites() {
        return numInvites;
    }

    public void setNumInvites(int numInvites) {
        this.numInvites = numInvites;
    }

    public int getNumTips() {
        return numTips;
    }

    public void setNumTips(int numTips) {
        this.numTips = numTips;
    }

    public int getNumDeaths() {
        return numDeaths;
    }

    public void setNumDeaths(int numDeaths) {
        this.numDeaths = numDeaths;
    }
}
