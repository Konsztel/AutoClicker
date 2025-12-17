package View;

import AutoClicker.Main;
import AutoClicker.AppState;
import Logic.CombatService;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import java.util.HashMap;
import java.util.Map;

public class WgView {
    private final Scene scene;
    private Thread clickThread;

    private final Map<String, CheckBox> checkBoxMap = new HashMap<>();
    private final Map<String, CheckBox> checkBoxLvl = new HashMap<>();

    public WgView(AppState appState, CombatService combatService, Main main) {

        GridPane gridWG = new GridPane();
        gridWG.setVgap(10);
        gridWG.setHgap(10);
        scene = new Scene(gridWG, 480, 480);

        Button startWalk = new Button("Start");
        gridWG.add(startWalk, 3, 0);

        startWalk.setOnAction(e -> {
            if (!appState.running) {
                try {
                    clickThread = new Thread(() -> {
                        appState.running = true;
                        combatService.wyprawyGildyjne(this);
                        appState.running = false;
                    });
                    clickThread.start();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                System.out.println("Poczekaj na zakończenie aktualnego wątku");
            }
        });

        Button wyjscieWG = new Button("Wyjście");
        gridWG.add(wyjscieWG, 0, 16);
        wyjscieWG.setOnAction(e -> main.showMainScene());

        // Checkboxy sektorów 1–16
        for (int i = 1; i <= 16; i++) {
            CheckBox cb = new CheckBox("Sektor: " + i);
            gridWG.add(cb, 0, i - 1);
            cb.setSelected(false);
            checkBoxMap.put("cb" + i, cb);
        }

        // Checkboxy poziomów 1–5
        for (int i = 1; i <= 5; i++) {
            CheckBox cbLvl = new CheckBox("Poziom: " + i);
            gridWG.add(cbLvl, 1, i - 1);
            cbLvl.setSelected(false);
            checkBoxLvl.put("cbLvl" + i, cbLvl);
        }
    }

    public Scene getScene() {
        return scene;
    }

    public Map<String, CheckBox> getCheckBoxMap() {
        return checkBoxMap;
    }

    public Map<String, CheckBox> getCheckBoxLvl() {
        return checkBoxLvl;
    }

}
