package View;

import AutoClicker.Collector.Collector;
import AutoClicker.Main;
import AutoClicker.Model.BuildingCoordinates;
import Logic.MouseService;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;

public class PrCollectorView {
    private final Scene scene;
    public PrCollectorView(MouseService mouseService, Collector collector, BuildingCoordinates buildingCoordinates, Main main) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(16));
        grid.setVgap(10);
        grid.setHgap(10);


        Button zbiorPrButton = new Button("Zbiór PR");
        grid.add(zbiorPrButton, 1, 1);
        zbiorPrButton.setOnAction(e -> {
            mouseService.cameraToPRCollector();
            mouseService.sleep(100);
            collector.zbiorPR();
        });

        // Dodanie nazw budynków do tabeli
        for (int i = 0; i < 14; i++) {
            CheckBox budynek = new CheckBox(buildingCoordinates.nazwyBudynkow.get(i));
            grid.add(budynek, 0, i);
            budynek.setSelected(true);
            collector.budynki.add(budynek);
        }

        Button wyjscie = new Button("Wyjście");
        grid.add(wyjscie, 0, 15);
        wyjscie.setOnAction(e -> main.showMainScene());

        scene = new Scene(grid, 460, 440);
    }

    public Scene getScene() {
        return scene;
    }
}

