package AutoClicker;


import java.awt.*;
import java.awt.event.InputEvent;

public class Fights { // Zobaczyć zamiane z public na protected czy zadziałą wywołanie

    private Robot robot;

    private Main main;

    public Fights(Main main) {
        this.main = main; // teraz main != null
        try {
            this.robot = new Robot(); // ✅ tutaj tworzysz robota
        } catch (AWTException ignored) {}
    }

    public void wyprawyGildyjne (){
        startProgramu();
  //Pierwsze 8 sektorów sprawdzone
        ustawienieKamery(200, 310, 1800, 900); // 1 Etap
        wgFights(305, 480, 410, 555); // Klik na 1 sektor
        ustawienieKamery(200, 310, 1800, 900);
        odebranieNagrod(485, 490);

        ustawienieKamery(200, 310, 1800, 900);
        wgFights(700, 480, 810, 570); // Klik na 2 sektor
        ustawienieKamery(200, 310, 1800, 900);
        odebranieNagrod(865, 420);

        ustawienieKamery(200, 310, 1800, 900);
        wgFights(995, 370, 1130, 450); // Klik na 3 sektor
        ustawienieKamery(200, 310, 1800, 900);
        odebranieNagrod(1160, 320);

        ustawienieKamery(200, 310, 1800, 900);
        wgFights(1370, 380, 990, 410); // Klik na 4 sektor
        ustawienieKamery(200, 310, 1800, 900);
        odebranieNagrod(1380, 550);

        ustawienieKamery(200, 310, 1800, 900);
        wgFights(1270, 660, 1380, 760); //Klik na 5 sektor
        ustawienieKamery(200, 310, 1800, 900);
        odebranieNagrod(1150, 750);

        //Klik na 6 sektor
        ustawienieKamery(200, 310, 1800, 900);
        wgFights(970, 750, 1060, 870);
        ustawienieKamery(200, 310, 1800, 900);
        odebranieNagrod(770, 780);

        //Klik na 7 sektor
        wgFights(630, 755, 720, 860);
        odebranieNagrod(630, 750);

        //Klik na 8 sektor
        wgFights(820, 810, 930, 870);
        odebranieNagrod(1000, 850);

        //Klik na 9 sektor
        wgFights(1010, 800, 1120, 890);
        odebranieNagrod(1000, 760);

        //Klik na 10 sektor
        wgFights(1000, 760, 1100, 860);
        odebranieNagrod(1010, 760);

        //Klik na 11 sektor - To najlepiej wyszło
        wgFights(1010, 720, 1120, 830);
        odebranieNagrod(1010, 740);

        //Klik na 12 sektor
        wgFights(1100, 760, 1210, 790);
        odebranieNagrod(1270, 750);

        //Klik na 13 sektor
        wgFights(1450, 760, 1080, 860);
        odebranieNagrod(1610, 750);

        //Klik na 14 sektor
        wgFights(1650, 750, 1260, 860);
        odebranieNagrod(1610, 580);

        //Klik na 15 sektor
        wgFights(1500, 470, 1100, 550);
        odebranieNagrod(1320, 410);

        //Klik na 16 sektor
        wgFights(1110, 400, 1200, 400);
        odebranieNagrod(1010, 370);
    }

    private void ustawienieKamery (int x1, int y1, int x2, int y2){
        robot.mouseMove(x1, y1);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseMove(x2, y2);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    private void wgFights(int lokalizacjaSektoraX, int lokalizacjaSektoraY, int lokalizacjaMenuX, int lokalizacjaMenY){ //Dodać jeszcze obsługe dwóch walk
        main.mouseMove(lokalizacjaSektoraX, lokalizacjaSektoraY);
        main.mouseClick(200); // Wejście do sektora

        main.mouseMove(lokalizacjaMenuX, lokalizacjaMenY);
        main.mouseClick(250); // Wejście do sektora

        main.wymianawojska();

        //Rozpoczęcie pierwszej walki
        main.mouseMove(995, 840);
        main.mouseClick(100);
        //Potwierdzenie po pierwszej walce
        main.mouseMove(965, 797);
        main.mouseClick(400);

        //Druga walka
        main.mouseMove(963, 832);
        main.mouseClick(200);
        //Potwierdzenie po drugiej walce
        main.mouseMove(963, 789);
        main.mouseClick(600);
    }
    private void startProgramu(){
        main.mouseMove(200, 300);
        main.mouseClick(100);
    }

    private void odebranieNagrod(int x, int y){
        main.mouseMove(x, y);
        main.mouseClick(1000);
        main.mouseMove(1400, 970);
        main.mouseClick(1000);
        main.mouseClick(1000);
    }
}

/*  //Pierwsze 8 sektorów sprawdzone
        ustawienieKamery(200, 310, 1800, 900); // 1 Etap
        wgFights(305, 480, 410, 555); // Klik na 1 sektor
        ustawienieKamery(200, 310, 1800, 900);
        odebranieNagrod(485, 490);

        ustawienieKamery(200, 310, 1800, 900);
        wgFights(700, 480, 810, 570); // Klik na 2 sektor
        ustawienieKamery(200, 310, 1800, 900);
        odebranieNagrod(865, 420);

        ustawienieKamery(200, 310, 1800, 900);
        wgFights(995, 370, 1130, 450); // Klik na 3 sektor
        ustawienieKamery(200, 310, 1800, 900);
        odebranieNagrod(1160, 320);

        ustawienieKamery(200, 310, 1800, 900);
        wgFights(1370, 380, 990, 410); // Klik na 4 sektor
        ustawienieKamery(200, 310, 1800, 900);
        odebranieNagrod(1380, 550);

        ustawienieKamery(200, 310, 1800, 900);
        wgFights(1270, 660, 1380, 760); //Klik na 5 sektor
        ustawienieKamery(200, 310, 1800, 900);
        odebranieNagrod(1150, 750);

        //Klik na 6 sektor
        ustawienieKamery(200, 310, 1800, 900);
        wgFights(970, 750, 1060, 870);
        ustawienieKamery(200, 310, 1800, 900);
        odebranieNagrod(770, 780);

        //Klik na 7 sektor
        ustawienieKamery(200, 310, 1800, 900);
        wgFights(630, 880, 720, 990);
        ustawienieKamery(200, 310, 1800, 900);
        odebranieNagrod(630, 990);

        //Klik na 8 sektor
        ustawienieKamery(330, 950, 1800, 240); // 2 Etap
        wgFights(830, 810, 930, 870);
        ustawienieKamery(330, 950, 1800, 240);
        odebranieNagrod(1050, 850);
*/
/* Dotąd przerobiłem
//Klik na 9 sektor
ustawienieKamery(310, 475, 1000, 100);
wgFights(1230, 810, 1330, 930);
ustawienieKamery(310, 475, 1000, 100);
odebranieNagrod(1365, 690);
 */
/*
        //Klik na 10 sektor
        ustawienieKamery(310, 475, 1000, 100);
        wgFights(1530, 590, 1150, 690);
        ustawienieKamery(310, 475, 1000, 100);
        odebranieNagrod(1650, 480);

        //Klik na 11 sektor - To najlepiej wyszło
        ustawienieKamery(900, 900, 120, 260); // 3 Etap
        wgFights(730, 440, 830, 520);
        ustawienieKamery(900, 900, 120, 260);
        odebranieNagrod(915, 460);

        //Klik na 12 sektor
        ustawienieKamery(900, 900, 120, 260);
        wgFights(1110, 585, 1220, 624);
        ustawienieKamery(900, 900, 120, 260);
        odebranieNagrod(1266, 690);

        //Klik na 13 sektor
        ustawienieKamery(900, 900, 120, 260);
        wgFights(1460, 730, 1070, 820);
        ustawienieKamery(900, 900, 120, 260);
        odebranieNagrod(1600, 626);

        //Klik na 14 sektor
        ustawienieKamery(1800, 230, 280, 880);
        wgFights(1650, 755, 1250, 860);
        ustawienieKamery(1800, 230, 280, 880);
        odebranieNagrod(1610, 580);

        //Klik na 15 sektor
        ustawienieKamery(1800, 230, 280, 880);
        wgFights(1500, 470, 1100, 570);
        ustawienieKamery(1800, 230, 280, 880);
        odebranieNagrod(1320, 410);

        //Klik na 16 sektor
        ustawienieKamery(1800, 230, 280, 880);
        wgFights(1110, 400, 1200, 400);
        ustawienieKamery(1800, 230, 280, 880);
        odebranieNagrod(850, 370);
*/