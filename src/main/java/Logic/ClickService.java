package Logic;

import AutoClicker.AppState;

import java.awt.event.KeyEvent;

public class ClickService {
    MouseService mouse = new MouseService();
    private int ileWalkWykonac;
    private final AppState appState;
    private final CombatService combatService;

    public ClickService (AppState appState) {
        this.appState = appState;
        this.combatService = new CombatService(appState);
    }

    public void setClickQuant (int ileWalkWykonac) {
        this.ileWalkWykonac = ileWalkWykonac;
    }

    public void stopClickingWG () {
        appState.running = false;
        if (appState.clickThread != null && appState.clickThread.isAlive()) {
            appState.clickThread.interrupt();
        }
    }

    public void stopClickKwanty () {
        appState.running = false;
        if (appState.clickThread != null && appState.clickThread.isAlive()) {
            appState.clickThread.interrupt();
        }
    }

    public void startClicking(int delayClick, int delayR, int maxClicks) {
            if (appState.running) return;
            appState.running = true;

        Thread clickThread = new Thread(() -> {
            int clickCount = 0;

            while (appState.running && clickCount < maxClicks) {
                mouse.mouseMove(970, 850);
                mouse.mouseClick(0);
                mouse.sleep(delayR);

                mouse.pressKey(KeyEvent.VK_R);

                clickCount++;
                mouse.sleep(delayClick);
            }
            appState.running = false;
        });
            clickThread.start();
    }

    public void clickKwanty () {
        if (!appState.running) {
            try {
                int quant = ileWalkWykonac;

                if (quant <= 0) {
                    System.out.println("Za mało punktów");
                    return;
                }

                appState.clickThread = new Thread(() -> {
                    appState.running = true;
                    mouse.mouseMoveToStart(1280, 760);
                    for (int i = 0; i < quant; i++) {
                        if (!appState.running) {
                            System.out.println("Klikanie przerwane");
                            break;
                        }
                        combatService.quantumFights();
                    }

                    System.out.println("Walki zakończone");
                    appState.running = false;
                });
                appState.clickThread.start();

            } catch (NumberFormatException ex) {
                //main.showAlert("Upewnij się, że pole zawiera poprawną liczba całkowitą.");
            }
        }
    }

    public void howMuchClicksToProducesHammers(int ilePowtorzen) {
        mouse.sleep(5000);
        for (int i = 0; i < ilePowtorzen; i++) {
            mouse.mouseClick(1000);
        }
    }

    public void setAutoClickerValue (int delay, int rDelay, int clicks) {
        if (!appState.running) {
            try {
                startClicking(delay, rDelay, clicks);
            } catch (NumberFormatException ex) {
                System.err.println("Upewnij się, że wszystkie pola zawierają poprawne liczby całkowite.");
            }
        }
    }
}




