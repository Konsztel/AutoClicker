package AutoClicker;
// Version 3.0 x.x.2025

import AutoClicker.Collector.Collector;
import AutoClicker.Model.BuildingCoordinates;
import Logic.ClickService;
import Logic.CombatService;
import Logic.HeroicService;
import Logic.MouseService;
import View.*;
import View.HeroicView;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application implements NativeKeyListener {

    private Scene Scene, scenePrCollector,scenaWg, scenaKwanty, mainScene;
    private Scene scenaZwoje;
    private Scene scenaPrCollector;
    private Stage primaryStage;
    MouseService mouseService = new MouseService();
    HeroicService heroicService;

    private HeroicView heroicView;

    private AppState appState;
    private ClickService clickService;
    private CombatService combatService;
    private KwantyView kwantyView;
    private WgView wgView;
    private PrCollectorView prCollectorView;
    private BuildingCoordinates buildingCoordinates;


    Collector collector = new Collector();

    private MainView mainView;

    public static void main(String[] args) {launch(args);}

    @Override
    public void start(Stage primaryStage) {

        appState = new AppState();
        clickService = new ClickService(appState);
        combatService = new CombatService(appState);
        buildingCoordinates = new BuildingCoordinates();

        primaryStage.setTitle("Autoclicker");
        this.primaryStage = primaryStage;

        // Wyłącz logowanie JNativeHook
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);
        logger.setUseParentHandlers(false);

        try {
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(this);
        } catch (NativeHookException e) {
            throw new RuntimeException(e);
        }

        this.mainView = new MainView(mouseService, appState, clickService, this, collector, combatService);
        mainScene = mainView.getScene();

        this.wgView = new WgView(appState, combatService, this);
        scenaWg = wgView.getScene();

        this.kwantyView = new KwantyView(clickService, collector, this, appState);
        scenaKwanty = kwantyView.getScene();

        this.heroicView = new HeroicView(heroicService, this);
        scenaZwoje = heroicView.getScene();

        this.prCollectorView = new PrCollectorView(mouseService, collector, buildingCoordinates, this);
        scenaPrCollector = prCollectorView.getScene();


// To nie wiem czy tak ma być
        primaryStage.setScene(mainScene);
        primaryStage.setOnCloseRequest(e -> {
            appState.stopClicker();

            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException ex) {
                throw new RuntimeException(ex);
            }
        });
        primaryStage.show();
    }


    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {

        if (primaryStage.getScene() == mainScene) {
            if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_F6) {
                appState.stopClicker();
            }
            if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_F7) {
                if (!appState.running) {
                    clickService.setAutoClickerValue(
                            mainView.getDelay(),
                            mainView.getRKeyDelay(),
                            mainView.getClicks());
                }
            }
        }
        if (primaryStage.getScene() == scenaKwanty) {
            if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_F7) {
                kwantyView.startQuantumClicking();
            }

            if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_F6) {
                clickService.stopClickKwanty();
            }
        }



        if (primaryStage.getScene() == scenaWg) {
            if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_F6) {
                clickService.stopClickingWG();
            }
        }

        if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_F8) {
            combatService.wymianaWojska();
        }

        if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_F2) {
            combatService.globalMapFight();
        }
    }

//    public void showAlert (String message) {
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }


    public void showMainScene () {
        primaryStage.setScene(mainScene);
    }

    public void showKwantyScene () {
        primaryStage.setScene(scenaKwanty);
    }

    public void showWgScene () {
        primaryStage.setScene(scenaWg);
    }

    public void showPrCollectorScene () {
        primaryStage.setScene(scenaPrCollector);
    }

    public void showHeroicScene () {
        primaryStage.setScene(scenaZwoje);
    }

}