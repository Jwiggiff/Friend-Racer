package Main;

import com.sun.javafx.scene.control.skin.ButtonSkin;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DecisionButtonSkin extends ButtonSkin {
    public DecisionButtonSkin(Button btn) {
        super(btn);

        // Styling //
        btn.setTextFill(Color.BLUE);
        btn.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(5), Insets.EMPTY)));
        btn.setBorder(new Border(new BorderStroke(Color.rgb(0,0,139, 0), BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(0,0,5,0))));

        btn.setFont(new Font(35));

        // Hovering //
        btn.setOnMouseEntered(e -> btn.setBorder(new Border(new BorderStroke(Color.rgb(0,0,139), BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(0,0,5,0)))));
        btn.setOnMouseExited(e -> btn.setBorder(new Border(new BorderStroke(Color.rgb(0,0,139, 0), BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(0,0,5,0)))));
    }
}
