package Main;

import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * This class is the layout for the IntroScene class
 *
 * @author Josh Friedman
 * @version 1 - May 11 - Josh Friedman - 1 hour - create entire class with fade animations
 */
public class IntroLayout extends StackPane {
    private ImageView companyLogo;
    private ImageView gameLogo;
    private Main app;

    /**
     * This is the class constructor that creates all of the Nodes
     * and adds them to the layout.
     */
    public IntroLayout() {
        loadResources();

        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        companyLogo.setPreserveRatio(true);
        companyLogo.setFitWidth(400);
        gameLogo.setPreserveRatio(true);
        gameLogo.setFitWidth(750);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(companyLogo, gameLogo);
        companyLogo.setOpacity(0);
        gameLogo.setOpacity(0);

        FadeTransition fade = new FadeTransition(Duration.millis(2000), companyLogo);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.setAutoReverse(true);
        fade.setCycleCount(2);
        FadeTransition fade2 = new FadeTransition(Duration.millis(2000), gameLogo);
        fade2.setFromValue(0);
        fade2.setToValue(1);
        fade2.setAutoReverse(true);
        fade2.setCycleCount(2);

        fade.playFromStart();
        fade.setOnFinished(e -> fade2.playFromStart());
        fade2.setOnFinished(e -> app.setMainMenuScene());
    }

    /**
     * This method sets the app instance variable.
     *
     * @param app The app to set it to
     */
    public void setApp(Main app) {
        this.app = app;
    }

    /**
     * Loads the various resources needed for the intro
     */
    private void loadResources() {
        companyLogo = new ImageView(new Image(getClass().getClassLoader().getResource("res/company_logo.png").toString()));
        gameLogo = new ImageView(new Image(getClass().getClassLoader().getResource("res/game_logo.png").toString()));
    }
}
