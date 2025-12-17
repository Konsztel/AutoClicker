package View;

import AutoClicker.AppState;
import AutoClicker.Collector.Collector;
import AutoClicker.Main;
import Logic.ClickService;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class KwantyView {
    private final ClickService clickService;
    private final Scene scene;
    private final TextField quantField;
    private final TextField quantGoodsField;

    public KwantyView (ClickService clickService, Collector collector, Main main, AppState appState) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(10);
        grid.setHgap(10);

        this.clickService = clickService;



        quantField = new TextField("5");
        grid.add(new Label("Podaj ile wykonać walk kwantowych"), 0, 0);
        grid.add(quantField, 1, 0);

        Button quantumStartButton = new Button("F7 - Start walk kwantowych");
        grid.add(quantumStartButton, 2, 0);
        quantumStartButton.setOnAction(e -> this.startQuantumClicking());

        quantGoodsField = new TextField("0");
        grid.add(new Label("Podaj ile razy wytworzyć produkcje x20"), 0, 1);
        grid.add(quantGoodsField, 1, 1);

        Button quantGoodsProduction = new Button("Produkcja towarów kwantowych");
        grid.add(quantGoodsProduction, 2, 1);
        quantGoodsProduction.setOnAction(e -> {
            try {
                int quantGoods = Integer.parseInt(quantGoodsField.getText());

                collector.setQuantProductionNumber(quantGoods);

                new Thread(() -> {
                    appState.running = true;
                    collector.quantProduction();
                    appState.running = false;
                }).start();

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        Button wyjscieKwanty = new Button("Wyjście");
        grid.add(wyjscieKwanty, 0, 2);
        wyjscieKwanty.setOnAction(e -> main.showMainScene());

        this.scene = new Scene(grid, 500, 400);
    }

    public Scene getScene() {
        return scene;
    }

    public void startQuantumClicking () {
        try {
            int quantFights = Integer.parseInt(quantField.getText());
            clickService.setClickQuant(quantFights);
            clickService.clickKwanty();
        } catch (NumberFormatException e) {
            System.err.println("Nieprawidłowa liczba walk kwantowych.");
        }
    }
}