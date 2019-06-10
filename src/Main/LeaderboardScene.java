package Main;

import javafx.scene.Scene;

/**
 * This class is the scene for the leaderboard.
 *
 * @author Josh Friedman
 * @version 1 - June 2 - Josh Friedman - 10 mins - created entire class
 */
public class LeaderboardScene extends Scene {
    public static final LeaderboardLayout leaderboardLayout = new LeaderboardLayout();

    /**
     * This is the class constructor that creates a scene with the
     * leaderBoardLayout as root.
     *
     * @param windowWidth The width of the window
     * @param windowHeight The height of the window
     * @param app The Main class
     */
    public LeaderboardScene(int windowWidth, int windowHeight, Main app) {
        super(leaderboardLayout, windowWidth, windowHeight);
        leaderboardLayout.setApp(app);
    }

    /**
     * This method calss the readLeaderboardFile methodd of the
     * leaderboardLayout class.
     */
    public void readLeaderboard() {
        leaderboardLayout.readLeaderboardFile();
    }
}
