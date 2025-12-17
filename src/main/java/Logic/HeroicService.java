package Logic;

public class HeroicService implements CombatService.ArmyExchange {
    MouseService mouse = new MouseService();
    private int howManyLvlUp = 0;

    private CombatService.ArmyExchange armyExchange;

    public HeroicService( CombatService.ArmyExchange armyExchange) {
        this.armyExchange = armyExchange;
    }

    public void setHowManyLvlUp (int liczba){
        this.howManyLvlUp = liczba;
    }

    public void heroicFightsTasks(){
        mouse.sleep(2000);
        mouse.mouseMoveToStart(1200, 400);

        // Walki Atak
        for (int i = 0; i < 19; i++) {
                mouse.mouseMove(933, 706);
                mouse.mouseClick(700);
                armyExchange.wymianaWojska();
                mouse.mouseMove(990, 845);
                mouse.mouseClick(1000);
                mouse.mouseClick(1600);
            }

        // Waki Obrona
        for (int i = 0; i < 19; i++) {
                mouse.mouseMove(930, 865);
                mouse.mouseClick(700);
                armyExchange.wymianaWojska();
                mouse.mouseMove(990, 845);
                mouse.mouseClick(1000);
                mouse.mouseClick(1600);
            }

        // Przesunięcie planszy na sam dół
        mouse.mouseWheel(9, 1);


        // Wpłata medali
        for (int i = 0; i <15; i++){
                mouse.mouseMove(930, 680);
                mouse.mouseClick(2500);
            }

        // Przesunięcie planszy na samą góre
        mouse.mouseWheel(9, -1);

        mouse.sleep(400);

        // Zebranie nagród
        for (int i = 0; i <10 ; i++){
                mouse.mouseMove(930, 545);
                mouse.mouseClick(1300);
                mouse.mouseMove(950, 690);
                mouse.mouseClick(400);
                mouse.mouseClick(400);
            }
        System.out.println("Koniec");
        }

    public void heroesLvlUp (){
        for (int i = 0; i < howManyLvlUp; i++) {
            mouse.mouseMove(1310, 840);
            mouse.mouseClick(600);
        }
        System.out.println("Koniec");
    }

    @Override
    public void wymianaWojska() {
        armyExchange.wymianaWojska();
    }
}
