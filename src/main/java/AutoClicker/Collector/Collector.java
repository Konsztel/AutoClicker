package AutoClicker.Collector;

import AutoClicker.Model.BuildingCoordinates;
import Logic.MouseService;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.mouse.NativeMouseAdapter;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import javafx.scene.control.CheckBox;
import org.sikuli.script.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Collector {
    List<Point> mousePoints = new ArrayList<>();
    public List<CheckBox> budynki = new ArrayList<>();
    final List<Point> klikniecia3 = new ArrayList<>();
    MouseService mouse = new MouseService();
    BuildingCoordinates buildingCoordinates = new BuildingCoordinates();

    private int liczbaProdukcji = 1;
    private int diamondsBuying = 0;

    public void setQuantProductionNumber(int liczba) {
        this.liczbaProdukcji = liczba;
    }
    public void setDiamondsBuing (int liczbaDiamentow){
        this.diamondsBuying = liczbaDiamentow;
    }

    public void  quantProduction (){
            // Produkcja towarów w kwantach
            klikniecia3.clear();
            System.out.println("Czekam na klik");
            GlobalScreen.addNativeMouseListener(new NativeMouseAdapter() {
                public void nativeMouseClicked(NativeMouseEvent e) {
                    klikniecia3.add(new Point(e.getX(), e.getY()));

                    System.out.println("Klik " + klikniecia3.size() + ": " + klikniecia3);

                    if (klikniecia3.size() >= 1) {
                        int x0 = klikniecia3.get(0).x;
                        int y0 = klikniecia3.get(0).y;
                        GlobalScreen.removeNativeMouseListener(this);

                        // Wybranie czwartej produkcji
                        mouse.mouseMove(1270, 660);
                        mouse.mouseClick(4000);

                        // Zbiór produkcji
                        mouse.mouseMove(x0, y0);
                        mouse.mouseClick(200);

                        for (int i = 1; i < liczbaProdukcji; i++){
                            // Klik w budynek
                            mouse.mouseMove(e.getX(), e.getY());
                            mouse.mouseClick(200);

                            // Wybranie czwartej produkcji
                            mouse.mouseMove(1270, 660);
                            mouse.mouseClick(4000);

                            // Zbiór produkcji
                            mouse.mouseMove(e.getX(), e.getY());
                            mouse.mouseClick(200);
                        }
                        System.out.println("Koniec programu");
                    }
                }
            });
        }

    public void zbiorPR() {
        // Zbiór PR z bonusem BG
        mousePoints.clear();

        // Zminimalizowanie paska sąsiadów
        mouse.mouseMove(280, 890);
        mouse.mouseClick(10);

        // Zebranie bonusu BG
        mouse.mouseMove(293, 511);
        mouse.mouseClick(4000);

        for (String wsp : buildingCoordinates.wspolrzedne) {
            String[] xy = wsp.split(",\\s*"); // rozdziel po przecinku i ewentualnej spacji
            int x = Integer.parseInt(xy[0]);
            int y = Integer.parseInt(xy[1]);
            mousePoints.add(new Point(x, y));
        }

        for (int i = 0; i < budynki.size(); i++) {
            if (budynki.get(i).isSelected()) {
                Point p = mousePoints.get(i);
                mouse.mouseMove(p.x, p.y);
                mouse.mouseClick(10);
                System.out.println("Kliknięto Budynek " + (i + 1) + " (" + p.x + ", " + p.y + ")");
            }
        }
        // Ruch aby niczego nie zasłaniało
        mouse.mouseMove(1755, 384);

    }

    public void diamondShop() {
        // Otwarcie sklepu
        mouse.mouseMove(1888, 306);
        mouse.mouseClick(1000);

        // Otwarcie Sezonu pól chwały
        mouse.mouseMove(544, 559);
        mouse.mouseClick(1000);

        mouse.mouseMove(1091, 588);
        // Przewinięcie do samego dołu
        mouse.mouseWheel(40, 1);

        // Kupno diamentów
        for (int i = 0; i <diamondsBuying; i++) {
        mouse.mouseMove(1310, 820);
        mouse.mouseClick(1000);
        mouse.mouseMove(950, 690);
        mouse.mouseClick(1000);
        }

        // Zamknięcie menu
        mouse.mouseMove(1400, 300);
        mouse.mouseClick(0);

        System.out.println("Koniec");
    }

    public void szukaniePerlyDoWrzuceniaPrNaZarobek () {
        int count = 0;
        // Region – np. pasek na ekranie (x, y, szerokość, wysokość)
        Region searchRegion = new Region(810, 480, 90, 320);

        // Załaduj wzorce (np. pattern33.png, pattern34.png, itd.)
        List<Pattern> patterns = new ArrayList<>();
        for (int i = 30; i <= 79; i++) {
            patterns.add(new Pattern("C:/Users/Paweł/Desktop/Śmieci/Poziom" + i + ".png").similar(0.8f));
        }



        while (true) {
            boolean foundSomething = false;


            for (Pattern p : patterns) {
                Match match = searchRegion.exists(p, 0); // 0 = szybkie sprawdzenie


                if (match != null) {
                    foundSomething = true;
                    count = 0;

                    // Kliknij w przesunięte miejsce
                    int offsetX = 400; // np. przesunięcie o 50 px w prawo
                    Location clickPoint = new Location (match.getTarget().getX() + offsetX, match.getTarget().getY());

                    try {
                        searchRegion.click(clickPoint);
                    } catch (FindFailed e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println("Kliknięto na " + clickPoint);

                    // Uruchom swoją logikę (np. czekanie na zakończenie programu)
                    wrzuceniePR();

                    // Po zakończeniu akcji przerywamy sprawdzanie reszty wzorców
                    break;
                }
            }


            if (!foundSomething) {
                count++;
                System.out.println("Nic nie znaleziono. Próba nr: " + count);
                mouse.sleep(1000);
                if (count > 5) {
                    System.out.println("Liczba prób: " + count);
                    return;
                }
            }
        }
    }

    private void wrzuceniePR() {
        // Odpalenie wejścia do perły
        mouse.mouseClick(2000);

        // Odpalenie pola do wrzucenia PR
        mouse.mouseMove(1210, 490);
        mouse.mouseClick(100);

        mouse.wklejWartosc();

        mouse.mouseMove(1260, 490);
        mouse.mouseClick(100);

        mouse.sleep(2000);
        System.out.println("Akcja zakończona");
    }

    public void zbieranieSmieci () {
        Screen searchRegion = new Screen();

        List<Pattern> patterns = new ArrayList<>();
        patterns.add(new Pattern("C:/Users/Paweł/Desktop/Śmieci/IMG2.png").similar(0.93f));
    //        patterns.add(new Pattern("C:/Users/Paweł/Desktop/Śmieci/IMG3.png").similar(0.7f));
    //    patterns.add(new Pattern("C:/Users/Paweł/Desktop/Śmieci/IMG5.png").similar(0.7f));
    //    patterns.add(new Pattern("C:/Users/Paweł/Desktop/Śmieci/IMG12.png").similar(0.80f));
    //    patterns.add(new Pattern("C:/Users/Paweł/Desktop/Śmieci/IMG9.png").similar(0.85f));
    //    patterns.add(new Pattern("C:/Users/Paweł/Desktop/Śmieci/IMG10.png").similar(0.85f));
    //    patterns.add(new Pattern("C:/Users/Paweł/Desktop/Śmieci/IMG14.png").similar(0.95f));
    //    patterns.add(new Pattern("C:/Users/Paweł/Desktop/Śmieci/IMG15.png").similar(0.95f));
    //    patterns.add(new Pattern("C:/Users/Paweł/Desktop/Śmieci/IMG16.png").similar(0.95f));
    //    for (int i = 1; i < 16; i++) {
    //        patterns.add(new Pattern("C:/Users/Paweł/Desktop/Śmieci/IMG" + i + ".png").similar(0.9f));
    //    }
    //    patterns.add(new Pattern("C:/Users/Paweł/Desktop/Śmieci/IMG7.png").similar(0.95f));

        int count = 0;
        while (true) {
            boolean foundSomething = false;

            for (Pattern p : patterns) {
                Match match = searchRegion.exists(p, 0);


                if (match != null) {
                    foundSomething = true;
                    count = 0;

                    Location clickPoint = new Location (match.getTarget().getX(), match.getTarget().getY());

                    try {
                        searchRegion.click(clickPoint);
                    } catch (FindFailed e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println("Kliknięto na " + clickPoint);
                    mouse.sleep(4000);

                    break;
                }
            }

            if (!foundSomething) {
                count++;
                System.out.println("Nic nie znaleziono. Czekam...");
                mouse.sleep(1000);
                if (count > 5) {
                    System.out.println("Koniec czekania");
                    return;
                }
            }
        }
    }
}