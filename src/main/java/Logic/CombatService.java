package Logic;

import AutoClicker.AppState;
import View.WgView;

public class CombatService {
    private AppState appState;
    MouseService mouseService = new MouseService();

    public interface ArmyExchange {
        void wymianaWojska();
    }

    public CombatService(AppState appState) {
        this.appState = appState;
    }
    public void wymianaWojska(){

        // Wywalenie całego wojska
        mouseService.mouseMove(662, 480);
        for (int i = 0; i < 8; i++) {
            mouseService.mouseClick(5);
        }

        // Odpalenie kategori ciężkozbrojne
        mouseService.mouseMove(811, 630);
        mouseService.mouseClick(200);

        // Dodanie 2sztuk ciężkozbrojnych
        mouseService.mouseMove(807, 766);
        mouseService.mouseClick(5);
        mouseService.mouseClick(5);

        // Odpalenie kategori lekkozbrojne
        mouseService.mouseMove(858, 630);
        mouseService.mouseClick(10);

        // Dodanie 6sztuk łotrów
        mouseService.mouseMove(880, 770);
        for (int i = 0; i < 6; i++) {
            mouseService.mouseClick(0);
        }}

    public void quantumFights(){

        // Pierwszy etap - Wejście do menu wyboru armi
        mouseService.mouseMove(980, 730);
        mouseService.mouseClick(300);

        // Wywalenie całego wojska
        mouseService.mouseMove(660, 440);
        for (int i = 0; i < 8; i++) {
            mouseService.mouseClick(5);
        }
        mouseService.sleep(300);

        // Odpalenie kategori Artyleria
        mouseService.mouseMove(900, 590);
        mouseService.mouseClick(200);

        // Dodanie 8 pierwszej w kolejności artyleri
        mouseService.mouseMove(740, 650);
        for (int i = 0; i < 8; i++) {
            mouseService.mouseClick(5);
        }

        // Rozpoczęcie walki
        mouseService.mouseMove(1070, 800);
        mouseService.mouseClick(300);

        // Rozpoczęcie drugiej fali walki
        mouseService.mouseMove(1040, 840);
        mouseService.mouseClick(300);

        // Potwierdzenie menu po walce
        mouseService.mouseMove(960, 800);
        mouseService.mouseClick(300);

        // Potwierdzenie nagrody
        mouseService.mouseMove(960, 710);
        mouseService.mouseClick(300);
    }


    public void globalMapFight(){
        //
        mouseService.mouseMove(1060, 775);
        mouseService.mouseClick(300);

        // Wymiana wojska i odpalenie walki
        wymianaWojska();
        mouseService.mouseMove(1070, 840);
        mouseService.mouseClick(400);

        // Odpalenie drugiej walki
        mouseService.mouseMove(970, 832);
        mouseService.mouseClick(400);

        // Potwierdzenie po walce
        mouseService.mouseMove(970, 850);
        mouseService.mouseClick(100);
    }

    public void rewardFromWGFights(int x, int y){
        mouseService.mouseMove(x, y);
        mouseService.mouseClick(1000);
        mouseService.mouseMove(1400, 970);
        mouseService.mouseClick(1000);
        mouseService.mouseClick(1000);
        mouseService.mouseClick(1000);
        mouseService.sleep(3000);
    }

    public void wgFights(int lokalizacjaSektoraX, int lokalizacjaSektoraY, int lokalizacjaMenuX, int lokalizacjaMenY){
//Dodać jeszcze obsługe dwóch walk
        mouseService.mouseMove(lokalizacjaSektoraX, lokalizacjaSektoraY);
        mouseService.mouseClick(700); // Wejście do sektora

        mouseService.mouseMove(lokalizacjaMenuX, lokalizacjaMenY);
        mouseService.mouseClick(700); // Wejście do sektora

        wymianaWojska();

        // Rozpoczęcie pierwszej walki
        mouseService.mouseMove(995, 840);
        mouseService.mouseClick(400);

        // Potwierdzenie po pierwszej walce
        mouseService.mouseMove(965, 797);
        mouseService.mouseClick(800);

        // Druga walka
        mouseService.mouseMove(963, 832);
        mouseService.mouseClick(600);

        // Potwierdzenie po drugiej walce
        mouseService.mouseMove(963, 789);
        mouseService.mouseClick(1000);
    }

    public void wyprawyGildyjne (WgView wgView){
        mouseService.mouseMoveToStart(1750, 300);
        // Klik na 1 sektor
        appState.running = true;
        if (appState.running && wgView.getCheckBoxMap().get("cb1").isSelected()) {
            mouseService.cameraToWGFights(200, 310, 1800, 900); // 1 Etap

            if (wgView.getCheckBoxLvl().get("cbLvl1").isSelected()) {
                wgFights(305, 480, 410, 570);
            } else if (wgView.getCheckBoxLvl().get("cbLvl2").isSelected()) {
                wgFights(305, 480, 410, 560);
            } else {
                wgFights(305, 480, 410, 590);
            }

            mouseService.cameraToWGFights(200, 310, 1800, 900);
            rewardFromWGFights(485, 490);
        }

        // Klik na 2 sektor
        if (appState.running && wgView.getCheckBoxMap().get("cb2").isSelected()) {
            mouseService.cameraToWGFights(200, 310, 1800, 900);
            if (wgView.getCheckBoxLvl().get("cbLvl1").isSelected()) {
                wgFights(700, 480, 800, 600);
            } else {
                wgFights(700, 480, 800, 580);
            }
            mouseService.cameraToWGFights(200, 310, 1800, 900);
            rewardFromWGFights(865, 420);
        }

        // Klik na 3 sektor
        if (appState.running && wgView.getCheckBoxMap().get("cb3").isSelected()) {
            mouseService.cameraToWGFights(200, 310, 1800, 900);
            wgFights(995, 370, 1130, 450);
            mouseService.cameraToWGFights(200, 310, 1800, 900);
            rewardFromWGFights(1160, 320);
        }

        // Klik na 4 sektor
        if (appState.running && wgView.getCheckBoxMap().get("cb4").isSelected()) {
            mouseService.cameraToWGFights(200, 310, 1800, 900);
            wgFights(1370, 380, 990, 410);
            mouseService.cameraToWGFights(200, 310, 1800, 900);
            rewardFromWGFights(1380, 550);
        }

        // Klik na 5 sektor
        if (appState.running && wgView.getCheckBoxMap().get("cb5").isSelected()) {
            mouseService.cameraToWGFights(200, 310, 1800, 900);
            if (wgView.getCheckBoxLvl().get("cbLvl2").isSelected()) {
                wgFights(1270, 660, 1380, 760);
            } else {
                wgFights(1270, 660, 1380, 780);
            }
            mouseService.cameraToWGFights(200, 310, 1800, 900);
            rewardFromWGFights(1150, 750);
        }

        // Klik na 6 sektor
        if (appState.running && wgView.getCheckBoxMap().get("cb6").isSelected()) {
            mouseService.cameraToWGFights(200, 310, 1800, 900);
            if (wgView.getCheckBoxLvl().get("cbLvl1").isSelected() || (wgView.getCheckBoxLvl().get("cbLvl2").isSelected())) {
                wgFights(970, 750, 1060, 860);
            } else {
                wgFights(970, 750, 1060, 840);
            }
            mouseService.cameraToWGFights(200, 310, 1800, 900);
            rewardFromWGFights(770, 780);
        }

        // Klik na 7 sektor
        if (appState.running && wgView.getCheckBoxMap().get("cb7").isSelected()) {
            mouseService.cameraToWGFights(200, 310, 1800, 900);
            wgFights(630, 880, 720, 990);
            mouseService.cameraToWGFights(200, 310, 1800, 900);
            rewardFromWGFights(630, 990);
        }

        // Klik na 8 sektor
        if (appState.running && wgView.getCheckBoxMap().get("cb8").isSelected()) {
            mouseService.cameraToWGFights(330, 950, 1800, 240); // 2 Etap
            wgFights(830, 810, 930, 870);
            mouseService.cameraToWGFights(330, 950, 1800, 240);
            rewardFromWGFights(1050, 850);
        }

        // Klik na 9 sektor
        if (appState.running && wgView.getCheckBoxMap().get("cb9").isSelected()) {
            mouseService.cameraToWGFights(310, 475, 1000, 200);
            if (wgView.getCheckBoxLvl().get("cbLvl1").isSelected()) {
                wgFights(1230, 810, 1330, 940);
            } else {
                wgFights(1230, 810, 1330, 900);
            }
            mouseService.cameraToWGFights(310, 475, 1000, 200);
            rewardFromWGFights(1365, 690);
        }

        // Klik na 10 sektor
        if (appState.running && wgView.getCheckBoxMap().get("cb10").isSelected()) {
            mouseService.cameraToWGFights(310, 475, 1000, 200);
            wgFights(1530, 590, 1150, 690);
            mouseService.cameraToWGFights(310, 475, 1000, 200);
            rewardFromWGFights(1650, 480);
        }

        // Klik na 11 sektor
        if (appState.running && wgView.getCheckBoxMap().get("cb11").isSelected()) {
            mouseService.cameraToWGFights(900, 900, 120, 260); // 3 Etap
            if (wgView.getCheckBoxLvl().get("cbLvl1").isSelected()) {
                wgFights(730, 440, 830, 520);
            } else {
                wgFights(730, 440, 830, 550);
            }
            mouseService.cameraToWGFights(900, 900, 120, 260);
            rewardFromWGFights(915, 460);
        }

        // Klik na 12 sektor
        if (appState.running && wgView.getCheckBoxMap().get("cb12").isSelected()) {
            mouseService.cameraToWGFights(900, 900, 120, 260);
            if (wgView.getCheckBoxLvl().get("cbLvl2").isSelected()) {
                wgFights(1110, 585, 1200, 580);
            } else {
                wgFights(1110, 585, 1200, 610);
            }
            mouseService.cameraToWGFights(900, 900, 120, 260);
            rewardFromWGFights(1266, 690);
        }

        // Klik na 13 sektor
        if (appState.running && wgView.getCheckBoxMap().get("cb13").isSelected()) {
            mouseService.cameraToWGFights(900, 900, 120, 260);
            if (wgView.getCheckBoxLvl().get("cbLvl2").isSelected()) {
                wgFights(1460, 730, 1070, 860);
            } else {
                wgFights(1460, 730, 1070, 830);
            }
            mouseService.cameraToWGFights(900, 900, 120, 260);
            rewardFromWGFights(1600, 626);
        }

        // Klik na 14 sektor
        if (appState.running && wgView.getCheckBoxMap().get("cb14").isSelected()) {
            mouseService.cameraToWGFights(1800, 230, 280, 880);
            wgFights(1650, 755, 1250, 860);
            mouseService.cameraToWGFights(1800, 230, 280, 880);
            rewardFromWGFights(1610, 580);
        }

        // Klik na 15 sektor
        if (appState.running && wgView.getCheckBoxMap().get("cb15").isSelected()) {
            mouseService.cameraToWGFights(1800, 230, 280, 880);
            if (wgView.getCheckBoxLvl().get("cbLvl1").isSelected()) {
                wgFights(1500, 470, 1100, 570);
            } else {
                wgFights(1500, 470, 1100, 550);
            }
            mouseService.cameraToWGFights(1800, 230, 280, 880);
            rewardFromWGFights(1320, 410);
        }

        // Klik na 16 sektor
        if (appState.running && wgView.getCheckBoxMap().get("cb16").isSelected()) {
            mouseService.cameraToWGFights(1800, 230, 280, 880);
            wgFights(1110, 400, 1200, 410);
            mouseService.cameraToWGFights(1800, 230, 280, 880);
            rewardFromWGFights(850, 370);
        }

        System.out.println("Koniec programu");
    }
}