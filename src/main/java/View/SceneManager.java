package View;
//
//public class SceneManager {
//    private Stage primaryStage;
//    private Scene mainScene;
//    private Scene kwantyScene;
//
//    private MainView mainView;
//    private KwantyView kwantyView;
//
//    private final ClickService clickService;
//    private final AppState appState;
//
//    public SceneManager(Stage primaryStage, ClickService clickService, AppState appState) {
//        this.primaryStage = primaryStage;
//        this.clickService = clickService;
//        this.appState = appState;
//    }
//
//    public void initScenes() {
//        mainView = new MainView(this, clickService, appState);
//        mainScene = mainView.getScene();
//
//        kwantyView = new KwantyView(this, clickService, appState);
//        kwantyScene = kwantyView.getScene();
//
//        showMainScene();
//    }
//
//    public void showMainScene() {
//        primaryStage.setScene(mainScene);
//    }
//
//    public void showKwantyScene() {
//        primaryStage.setScene(kwantyScene);
//    }
//
//    // Można dodać np. reload, cleanup, przekazywanie danych itd.
//}
