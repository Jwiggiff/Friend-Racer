package Main;

import javafx.scene.Scene;

public class LeaderboardScene extends Scene {
    public static final LeaderboardLayout leaderboardLayout = new LeaderboardLayout();

    public LeaderboardScene(int windowWidth, int windowHeight, Main app) {
        super(leaderboardLayout, windowWidth, windowHeight);
        leaderboardLayout.setApp(app);
    }

    public void readLeaderboard() {
        leaderboardLayout.readLeaderboardFile();
    }
}
