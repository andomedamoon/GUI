public class Infections extends Thread {

    public static int infections = 0;
    private final int AMOUNT = 13;
    private int multiple = 1;

    @Override
    public void run() {
        while (infections <= 82_000_000) {
            try {
                Thread.sleep(1000);
                infections += AMOUNT * multiple * 500000;
                if (infections % 10 == 0) {
                    multiple++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (infections > 82_000_000) {
                infections = 82_000_000;
                return;
            }
        }
    }

}
