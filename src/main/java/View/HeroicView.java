package View;

import AutoClicker.Main;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import Logic.HeroicService;

public class HeroicView {

    private Scene scene;
    private TextField howManyLvlUp;
    private int howManyLvl = 0;

    public HeroicView(HeroicService heroicService, Main main) {
        GridPane gridZwoje = new GridPane();
        gridZwoje.setPadding(new Insets(10));
        gridZwoje.setVgap(10);
        gridZwoje.setHgap(10);

        howManyLvlUp = new TextField("0");
        gridZwoje.add(howManyLvlUp, 1, 0);
        gridZwoje.add(new Label("O ile poziomów ulepszyć?"), 0, 0);

        Button heroesLvlUp = new Button("Levelowanie herosów");
        gridZwoje.add(heroesLvlUp, 2, 0);
        heroesLvlUp.setOnAction(e -> {
            howManyLvl = Integer.parseInt(howManyLvlUp.getText());
            heroicService.setHowManyLvlUp(howManyLvl);
            heroicService.heroesLvlUp();
        });

        Button heroicFights = new Button("Walki - zwoje");
        gridZwoje.add(heroicFights, 0, 2);
        heroicFights.setOnAction(e -> heroicService.heroicFightsTasks());

        Button wyjscieZwoje = new Button("Wyjście");
        gridZwoje.add(wyjscieZwoje, 0, 16);
        wyjscieZwoje.setOnAction(e -> main.showMainScene());
        this.scene = new Scene(gridZwoje, 460, 480);
    }
    public Scene getScene() {
        return scene;
    }
}
