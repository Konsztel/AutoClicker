package View;

import AutoClicker.AppState;
import AutoClicker.Collector.Collector;
import Logic.ClickService;
import Logic.CombatService;
import Logic.MouseService;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class MainView {
    private final Scene scene;
    private final TextField delayField;
    private final TextField rKeyDelayField;
    private final TextField clicksField;
    private final TextField HappyMissionField;
    private final TextField buyDiamonds;
    public MainView (MouseService mouseService, AppState appState, ClickService clickService, AutoClicker.Main main, Collector collector, CombatService combatService) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(10);
        grid.setHgap(10);

        delayField = new TextField("220");
        grid.add(new Label("Opóźnienie kliknięcia (ms):"), 0, 1);
        grid.add(delayField, 1, 1);

        rKeyDelayField = new TextField("230");
        grid.add(new Label("Opóźnienie klawisza R (ms):"), 0, 2);
        grid.add(rKeyDelayField, 1, 2);

        clicksField = new TextField("600");
        grid.add(new Label("Kliknięć do wykonania:"), 0, 3);
        grid.add(clicksField, 1, 3);

        grid.add(new Label("Ile budynków postawić?"), 0, 4);
        HappyMissionField = new TextField("0");
        grid.add(HappyMissionField, 1, 4);
        Button happyMission = new Button("Start 'zdobądź x zadowolenia'");
        grid.add(happyMission, 2, 4);
        happyMission.setOnAction(e -> {
            try {
                int howManyTimesBuild = Integer.parseInt(HappyMissionField.getText());
                mouseService.setHowManyBuildingsBuild(howManyTimesBuild);
                mouseService.happyMissionBuilding();
            } catch (NumberFormatException f) {
                System.err.println("Nieprawidłowa wartość.");
            }
        });

        Button startButton = new Button("Start - F7");
        grid.add(startButton, 2, 2);
        startButton.setOnAction(e -> {
            if (!appState.running) {
                clickService.setAutoClickerValue(
                        Integer.parseInt(delayField.getText()),
                        Integer.parseInt(rKeyDelayField.getText()),
                        Integer.parseInt(clicksField.getText())
                );
            }
        });

        Button stopButton = new Button("Stop - F6");
        grid.add(stopButton, 2, 1);
        stopButton.setOnAction(e -> appState.stopClicker());

        buyDiamonds = new TextField("0");
        grid.add(new Label("Ile razy kupić diamenty"), 0, 5);
        grid.add(buyDiamonds, 1, 5);
        Button buttonBuyDiamonds = new Button("Start - Kupno diamentów");
        grid.add(buttonBuyDiamonds, 2, 5);
        buttonBuyDiamonds.setOnAction(e -> {
            int intBuyDiamonds = Integer.parseInt(buyDiamonds.getText());
            collector.setDiamondsBuing(intBuyDiamonds);
            collector.diamondShop();
        });

        Button wymianawojska = new Button("F8 - Wymiana Wojska");
        grid.add(wymianawojska, 2, 6);
        wymianawojska.setOnAction(e -> combatService.wymianaWojska());

        Button globalFight = new Button("F2 - Walka - Globalna mapa");
        grid.add(globalFight, 1, 6);
        globalFight.setOnAction(e -> combatService.globalMapFight());

        Button podajeWspolrzedne = new Button("Pobieranie dwóch kliknięć");
        grid.add(podajeWspolrzedne, 0, 6);
        podajeWspolrzedne.setOnAction(e -> mouseService.nasluchiwanieKlikniec2());

        Button autoClickerTrade = new Button("Zdejmowanie ofert handlowych");
        grid.add(autoClickerTrade, 0, 7);
        autoClickerTrade.setOnAction(e -> {
            for (int i = 0; i < 1; i++) {
                mouseService.sleep(800);
                mouseService.mouseMove(1150, 760);
                mouseService.mouseClick(100);
                mouseService.mouseMove(1080, 730);
                mouseService.mouseClick(100);
            }
            });



        Button wejscieWG = new Button("Wejście do menu WG");
        grid.add(wejscieWG, 0, 10);
        wejscieWG.setOnAction(e -> main.showWgScene());

        Button wejscieKwanty = new Button ("Wejście do kwantów");
        grid.add(wejscieKwanty, 1, 10);
        wejscieKwanty.setOnAction (e -> main.showKwantyScene());

        Button wejscie = new Button("Wejście do menu zbiorów PR");
        grid.add(wejscie, 2, 10);
        wejscie.setOnAction(e -> main.showPrCollectorScene());

        Button wejscieZwoje = new Button ("Wejście do menu zwoi");
        grid.add(wejscieZwoje, 0, 11);
        wejscieZwoje.setOnAction (e -> main.showHeroicScene());

//        Button Test = new Button("Test");
//        grid.add(Test, 0, 12);
//        Test.setOnAction (e -> collector.szukaniePerlyDoWrzuceniaPrNaZarobek());
//        Test.setOnAction (e -> collector.zbieranieSmieci());
//        Test.setOnAction (e -> mouseService.wklejWartosc());
//        Test.setOnAction (e -> clickService.howMuchClicksToProducesHammers(22));

        this.scene = new Scene(grid, 560, 400);
    }

    public Scene getScene () {
        return scene;
    }

    public int getDelay() {
        try {
            return Integer.parseInt(delayField.getText());
        } catch (NumberFormatException e) {
            System.err.println("Błąd: delay nie jest liczbą całkowitą.");
            return 220;
        }
    }

    public int getRKeyDelay() {
        try {
            return Integer.parseInt(rKeyDelayField.getText());
        } catch (NumberFormatException e) {
            System.err.println("Błąd: R delay nie jest liczbą całkowitą.");
            return 230;
        }
    }

    public int getClicks() {
        try {
            return Integer.parseInt(clicksField.getText());
        } catch (NumberFormatException e) {
            System.err.println("Błąd: ilość kliknięć nie jest liczbą całkowitą.");
            return 5;
        }
    }

}
