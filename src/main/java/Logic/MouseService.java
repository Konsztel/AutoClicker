package Logic;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.mouse.NativeMouseAdapter;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;


public class MouseService {

    int kliknieciaZlicz1 = 0;
    final List<Point> klikniecia = new ArrayList<>();
    private final Robot robot;
    private int howManyBuildingsBuild = 0;

    public MouseService(){
        try {
            this.robot = new Robot();
        } catch (AWTException e){
            throw new RuntimeException("Nie udało sie utowrzyć Robota");
        }
    }
    public void setHowManyBuildingsBuild (int liczba){
        this.howManyBuildingsBuild = liczba;
    }

    public void mouseMove(int x, int y){
        robot.mouseMove(x, y);
    }

    public void mouseClick(int delay){
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        sleep(delay);
    }

    public void pressKey (int keyCode){
        robot.keyPress(keyCode);
        robot.keyRelease(keyCode);
        //robot.keyRelease(KeyEvent.CTRL_MASK);
    }

    public void sleep(int ms){
        try {
            Thread.sleep(ms);
        }catch (InterruptedException ignored){}
    }

    public void mouseMoveToStart(int x, int y){
        mouseMove(x, y);
        mouseClick(100);
    }

    public void cameraToPRCollector(){
        // Ustawienie kamery do zbioru PR

        for (int i = 0; i < 5; i++) {
            sleep(100);
            robot.mouseWheel(1);
        }

        mouseMove(1850, 1010);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        sleep(100);
        mouseMove(1360, 1010);
        sleep(100);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        mouseMove(1810, 200);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        sleep(100);
        mouseMove(1810, 900);
        sleep(100);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public void cameraToWGFights (int x1, int y1, int x2, int y2){
        mouseMove(x1, y1);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        mouseMove(x2, y2);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public void buildingEventX (List<Point> klikniecia){
        // Pobranie pierwszego zapisanego kliku
        Point p = klikniecia.get(0);
        int x = p.x;
        int y = p.y;

        // Odpalenie menu budowy
        mouseMove(25, 925);
        mouseClick(1200);

        // Wybranie menu zadowolenia
        mouseMove(240, 710);
        mouseClick(100);

        for (int i = 0; i < howManyBuildingsBuild; i++){
            // Wybranie budynku do budowy
            mouseMove(60, 630);
            mouseClick(100);

            // Wybranie miejsca do budowy
            mouseMove(x, y);
            mouseClick(7000);

            // Wybranie sprzedaj budynek
            mouseMove(860, 190);
            mouseClick(100);

            // Wybranie budynku do sprzedania
            mouseMove(x, y);
            mouseClick(100);

            // Potwierdzenie sprzedania budynku
            mouseMove(1080, 740);
            mouseClick(700);
        }

        // Zamknięcie menu budowy
        mouseMove(200, 530);
        mouseClick(700);
        System.out.println("Koniec programu");
    }

    public void  happyMissionBuilding(){
        klikniecia.clear();
        System.out.println("Kliknij gdzie budynek ma być stawiany");
        GlobalScreen.addNativeMouseListener(new NativeMouseAdapter() {
            public void nativeMouseClicked(NativeMouseEvent e) {
                klikniecia.add(new Point(e.getX(), e.getY()));

                System.out.println("Klik " + klikniecia.size() + ": " + klikniecia);

                if (klikniecia.size() >= 1) {
                    GlobalScreen.removeNativeMouseListener(this);
                    System.out.println("Koniec pobierania kliknięć");

                    buildingEventX(klikniecia);
                }
            }
        });
    }

    // Do pobrania dwóch koordynatów
    public void  nasluchiwanieKlikniec2(){
        kliknieciaZlicz1 = 0;
        GlobalScreen.addNativeMouseListener(new NativeMouseAdapter() {
            @Override
            public void nativeMouseClicked(NativeMouseEvent e) {
                kliknieciaZlicz1++;
                System.out.println("Klik " + kliknieciaZlicz1 + ": X=" + e.getX() + " Y=" + e.getY());

                if (kliknieciaZlicz1 >= 2) {
                    GlobalScreen.removeNativeMouseListener(this);
                }
            }
        });
    }

    public void mouseWheel (int ileRazyRuszycKolkiem, int JedenDoDoluMinusJedenDoGory){
        for (int i = 0; i < ileRazyRuszycKolkiem; i++){
            robot.mouseWheel(JedenDoDoluMinusJedenDoGory);
            sleep(30);
        }
    }

    public void wklejWartosc () {
        sleep(2000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        sleep(300);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }
}
