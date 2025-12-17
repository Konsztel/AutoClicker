package AutoClicker;

public class AppState {
    public volatile boolean running = false;
    public Thread clickThread = null;

    public void stopClicker() {
        running = false;
        if (clickThread != null && clickThread.isAlive()) {
            clickThread.interrupt();
        }
        clickThread = null;
    }
}
