package AutoClicker;
// Version 2.3 26.08.2025

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseAdapter;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application implements NativeKeyListener {

    private volatile boolean running = false;
    private Label counterLabel;
    private Thread clickThread;
    TextField delayField;
    TextField rDelayField;
    TextField clicksField;
    TextField quantField;
    List<Point> mousePoints = new ArrayList<>();
    private Robot robot;
    private int kliknieciaZlicz = 0;

    String[] wspolrzedne = {
            "830, 487", "920, 531", "586, 681", "1020, 890", "846, 932", "492, 858", "441, 583", "514, 639", "525, 723", "438, 696", "715, 742", "351, 616", "303, 653",
            "659, 711"};

    List<CheckBox> budynki = new ArrayList<>();

    List<String> nazwyBudynkow = Arrays.asList(
            "1 - Celtyckie Gospodarstwo", "2 - Celtyckie Gospodarstwo", "3 - Koniczyny", "4 - Fort Imperial", "5 - Cytadela Graniczna", "6 - Port Fjord", "7 - Złotnik",
            "8 - Złotnik", "9 - Złotnik", "10 - Wieczne Targowisko", "11 - Ruiny Twierdzy", "12 - Złotnik", "13 - Złotnik", "14 - Wieża Yukitomo");


    public static void main(String[] args) {
        launch(args);
    }



    @Override
    public void start(Stage primaryStage) {
        try {
            this.robot = new Robot();
        } catch (AWTException e) {
            showAlert("Nie udało się zainicjalizować robota.");
            return;
        }
        primaryStage.setTitle("Autoclicker");

        // Wyłącz logowanie JNativeHook (opcjonalne)
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);
        logger.setUseParentHandlers(false);

        try {
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(this);
        } catch (NativeHookException e) {
            throw new RuntimeException(e);
        }

        // Pola tekstowe
        delayField = new TextField("220");
        rDelayField = new TextField("230");
        clicksField = new TextField("600");
        quantField = new TextField("0");

        Button startButton = new Button("Start - F7");
        Button stopButton = new Button("Stop - F6");
        Button wymianawojska = new Button("Wymiana Wojska - F8");
        Button quantumStartButton = new Button("Start walk kwantowych");
        Button zbiorPRButton = new Button("Zbiór PR");
        Button wejscie = new Button("Wejście do zbioru PR");
        Button wyjscie = new Button("Wyjście z zbioru PR");
        Button heroicFights = new Button("Walki - zwoje");
        Button globalFight = new Button("F2 - Walka - Globalna mapa");
        Button podajeWspolrzedne = new Button("Pobieranie 2 kliknięć");
        Button wgFights = new Button ("Walki WG");
        Button quantGoodsProduction = new Button ("Produkcja towarów kwantowych");

        counterLabel = new Label("Pozostało: 0");

        // Layout main grid
        GridPane grid1 = new GridPane();
        grid1.setPadding(new Insets(10));
        grid1.setVgap(10);
        grid1.setHgap(10);

        grid1.add(new Label("Delay kliknięcia (ms):"), 0, 2);
        grid1.add(delayField, 1, 2);
        grid1.add(new Label("Delay klawisza R (ms):"), 0, 3);
        grid1.add(rDelayField, 1, 3);
        grid1.add(new Label("Clicks:"), 0, 4);
        grid1.add(clicksField, 1, 4);
        grid1.add(new Label("Podaj ile wykonać walk"), 0, 5);
        grid1.add(quantField, 1, 5);
        grid1.add(startButton, 0, 6);
        grid1.add(stopButton, 1, 6);
        grid1.add(counterLabel, 3, 4, 2, 1);
        grid1.add(wymianawojska, 1, 7);
        grid1.add(quantumStartButton, 0, 7);
        grid1.add(wejscie, 0, 9);
        grid1.add(heroicFights, 1, 9);
        grid1.add(wgFights, 2, 9);
        grid1.add(globalFight, 2, 7);
        grid1.add(podajeWspolrzedne, 0, 10);
        grid1.add(quantGoodsProduction, 0, 10);

        Scene scene1 = new Scene(grid1, 560, 400);
        primaryStage.setScene(scene1);
        primaryStage.setOnCloseRequest(e -> {
            stopClicking();
            running = false;
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException ex) {
                throw new RuntimeException(ex);
            }
        });
        primaryStage.show();

        // Layout grid 2
        GridPane grid2 = new GridPane();
        grid2.setPadding(new Insets(16));
        grid2.setVgap(10);
        grid2.setHgap(10);

        grid2.add(zbiorPRButton, 1, 1);
        grid2.add(wyjscie, 0, 15);

        Scene scene2 = new Scene(grid2, 460, 440);

        wejscie.setOnAction(e -> primaryStage.setScene(scene2));
        wyjscie.setOnAction(e -> primaryStage.setScene(scene1));

        startButton.setOnAction(e -> {
            if (!running) {
                try {
                    int delay = Integer.parseInt(delayField.getText());
                    int rDelay = Integer.parseInt(rDelayField.getText());
                    int clicks = Integer.parseInt(clicksField.getText());

                    running = true;
                    startClicking(delay, rDelay, clicks);
                } catch (NumberFormatException ex) {
                    showAlert("Upewnij się, że wszystkie pola zawierają poprawne liczby całkowite.");
                }
            }
        });

        stopButton.setOnAction(e -> stopClicking());

        quantumStartButton.setOnAction(e -> {
            if (!running) {
                try {
                    int quant = Integer.parseInt(quantField.getText());

                    if (quant == 0) {
                        System.out.println("Za mało punktów");
                        return;
                    }

                    new Thread(() -> {
                        running = true;
                        for (int i = 0; i < quant; i++) {
                            quantumClicks();
                        }

                        System.out.println("Walki zakończone");
                        running = false;
                    }).start();

                } catch (NumberFormatException ex) {
                    showAlert("Upewnij się, że pole zawiera poprawną liczba całkowitą.");
                }
            }
        });

        wymianawojska.setOnAction(e -> wymianawojska());

        zbiorPRButton.setOnAction(e -> {
            ustawienieKamery();
            czekaj(100);
            zbior();
        });

        heroicFights.setOnAction(e -> zwoje());

        Fights fights = new Fights(this); //Dodać osobne gui i przycisk do walk WG
        wgFights.setOnAction (e -> fights.wyprawyGildyjne());


        for (int i = 0; i < 14; i++) {
            CheckBox budynek = new CheckBox(nazwyBudynkow.get(i));
            grid2.add(budynek, 0, i);
            budynek.setSelected(true);
            budynki.add(budynek);
        } // Dodanie nazw budynków do tabeli

        globalFight.setOnAction(e -> {
            globalMapFight();
        });

        podajeWspolrzedne.setOnAction(e -> nasluchiwanieKlikniec());

        quantGoodsProduction.setOnAction(e -> produkcjaZasobowKwantowych());
    }

    private void startClicking(int delayClick, int delayR, int maxClicks) {
        clickThread = new Thread(() -> {
                int clickCount = 0;

                while (running && clickCount < maxClicks) {
                    mouseMove(970, 850);

                    mouseClick(0);
                    czekaj(delayR);

                    robot.keyPress(KeyEvent.VK_R);
                    robot.keyRelease(KeyEvent.VK_R);

                    clickCount++;
                    int remaining = maxClicks - clickCount;

                    Platform.runLater(() ->
                            counterLabel.setText("Pozostało: " + remaining)
                    );

                    czekaj(delayClick);
            }

                while (!running){
                    Platform.runLater(() ->
                    counterLabel.setText("Klikanie zakończone.")
                );}

        });
        clickThread.start();
    }

    private void stopClicking() {
        running = false;
        if (clickThread != null && clickThread.isAlive()) {
            clickThread.interrupt();
        }
        Platform.runLater(() ->
                counterLabel.setText("Zatrzymano (przycisk/F6).")
        );
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_F6) {
            stopClicking();
        }

        if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_F8) {
            wymianawojska();
        }

        if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_F7) {
            if (!running) {
                try {
                    int delayClick = Integer.parseInt((delayField).getText());
                    int delayR = Integer.parseInt((rDelayField).getText());
                    int clicks = Integer.parseInt((clicksField).getText());

                    running = true;
                    startClicking(delayClick, delayR, clicks);
                } catch (NumberFormatException ex) {
                    showAlert("Upewnij sie, że wszystkie pola zawierają liczby całkowite");
                }
            }
        }

        if (nativeKeyEvent.getKeyCode() ==NativeKeyEvent.VC_F2){
            globalMapFight();
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    protected void wymianawojska() {

        mouseMove(662, 480);
        for (int i = 0; i < 8; i++) { //Wywalenie całego wojska
            mouseClick(5);
        }

        mouseMove(811, 630); //Odpalenie kategori ciężkozbrojne
        mouseClick(200);

        mouseMove(807, 766); //Dodanie 2sztuk ciężkozbrojnych
        mouseClick(0);
        mouseClick(0);

        mouseMove(858, 630); //Odpalenie kategori lekkozbrojne
        mouseClick(10);

        mouseMove(953, 693);
        for (int i = 0; i < 6; i++) { //Dodanie 6sztuk łotrów
            mouseClick(0);
        }
    } // Wymiana wojska

    private void quantumClicks() {
        mouseMove(980, 730); //Pierwszy etap - Wejście do menu wyboru armi
        mouseClick(300);

        mouseMove(660, 440); //Wywalenie całego wojska
        for (int i = 0; i < 8; i++) {
            mouseClick(0);
        }
        czekaj(300);

        mouseMove(810, 720); // Dodanie 8sztuk balist
        for (int i = 0; i < 8; i++) {
            mouseClick(0);
        }

        mouseMove(1070, 800); //Rozpoczęcie walki
        mouseClick(300);

        mouseMove(1040, 840); //Rozpoczęcie drugiej fali walki
        mouseClick(300);

        mouseMove(960, 800); //Potwierdzenie menu po walce
        mouseClick(300);

        mouseMove(960, 710); //Potwierdzenie nagrody
        mouseClick(300);
    } // Walki kwantowe

    protected void mouseClick(int czasCzekania) {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        czekaj(czasCzekania);
    } //Klik myszą z czasem czekania

    public void mouseMove(int x, int y) {
        robot.mouseMove(x, y);
    } // Ruch myszą do danego piksela

    private void ustawienieKamery() {

        mouseMove(1825, 300);
        czekaj(10);
        for (int i = 0; i < 5; i++) {
            czekaj(100);
            robot.mouseWheel(1);
        }

        mouseMove(1825, 300);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        czekaj(100);
        mouseMove(1825, 965);
        czekaj(100);
        mouseMove(280, 970);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

    } //Ustawienie kamery do zbioru PR

    private void zbior() {
        // Odpalane raz na dobę – nie czyszczę mousePoints, bo niepotrzebne
        //Zebranie bonusu BG
        mouseMove(293, 511);
        mouseClick(4000);


        for (String wsp : wspolrzedne) {
            String[] xy = wsp.split(",\\s*"); // rozdziel po przecinku i ewentualnej spacji
            int x = Integer.parseInt(xy[0]);
            int y = Integer.parseInt(xy[1]);
            mousePoints.add(new Point(x, y));
        }

        for (int i = 0; i < budynki.size(); i++) {
                if (budynki.get(i).isSelected()) {
                    Point p = mousePoints.get(i);
                    robot.mouseMove(p.x, p.y);
                    mouseClick(10);
                    System.out.println("Kliknięto Budynek " + (i + 1) + " (" + p.x + ", " + p.y + ")");
                }
        }
        mouseMove(1755, 384);

    } // Zbiór PR z bonusem BG

    private void zwoje(){
        czekaj(2000);
        for (int i = 0; i < 19; i++) {
            mouseMove(933, 706);
            mouseClick(700);
            wymianawojska();
            mouseMove(990, 845);
            mouseClick(1000);
            mouseClick(1000);
            czekaj(600);
        } // Walki Atak

        for (int i = 0; i < 19; i++) {
            mouseMove(930, 865);
            mouseClick(700);
            wymianawojska();
            mouseMove(990, 845);
            mouseClick(1000);
            mouseClick(1000);
            czekaj(600);
        } // Waki Obrona

        for (int i = 0; i <9; i++) {
            robot.mouseWheel(1);
            czekaj(10);
        } // Przesunięcie planszy na sam dół

        for (int i = 0; i <15; i++){
            mouseMove(930, 680);
            mouseClick(2500);
        } // Wpłata medali

        for (int i = 0; i <9; i++) {
            robot.mouseWheel(-1);
            czekaj(10);
        } // Przesunięcie planszy na samą góre

        czekaj(400);
        for (int i = 0; i <8 ; i++){
            mouseMove(930, 545);
            mouseClick(1300);
            mouseMove(950, 690);
            mouseClick(400);
            mouseClick(400);
        } // Zebranie nagród
    } // Przeklikanie walk i medali z zadaniami dla zwojów

    protected void czekaj(int czasCzekania) {
        try {
            Thread.sleep(czasCzekania);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    } // Poczekaj x czasu

    private void globalMapFight(){
        mouseMove(1060, 775);
        mouseClick(150);
        wymianawojska();
        mouseMove(1070, 840);
        mouseClick(400);
        mouseMove(965, 850);
        mouseClick(100);
    } // Walka na mapie globalnej

    private void  nasluchiwanieKlikniec(){
        kliknieciaZlicz = 0;
        GlobalScreen.addNativeMouseListener(new NativeMouseAdapter() {

            @Override
            public void nativeMouseClicked(NativeMouseEvent e) {
                kliknieciaZlicz++;
                // Tutaj e jest OK, bo to już wewnątrz globalnego listenera
                System.out.println("Klik "+ kliknieciaZlicz + ": X=" + e.getX() + " Y=" + e.getY());

                if (kliknieciaZlicz >= 2) {
                    GlobalScreen.removeNativeMouseListener(this);
                }
            }
        });
    }

    private void produkcjaZasobowKwantowych(){
        for (int i = 0; i < 8; i++ ){
            int x = 860;
            int y = 600;
            mouseMove(1120, 920);
            mouseClick(50);
            mouseMove(x, y);
            mouseClick(200);
            mouseMove(1270, 660);
            mouseClick(4000);
            mouseMove(x, y);
            mouseClick(200);
        }
    } // Przerobić to, aby ustawiało kamere i pobierało najpierw gdzie ma klikać oraz ile razy ma towar wyprodukować
}
