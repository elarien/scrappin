import java.util.Timer;

public class Collector {
    public static void main(String[] args){
        Timer t = new Timer();
        Scrapper mTask = new Scrapper();
        // This task is scheduled to run every 30 seconds

        t.scheduleAtFixedRate(mTask, 0, Integer.parseInt(args[0]));
    }
}
