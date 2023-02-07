import javax.swing.*;

public class GermanyInfections extends Thread {

    public static int infections = 0;
    public static int recovered = 0;
    public static int amount = LevelDataSetter.amountValue;
    public static double multiple = LevelDataSetter.mulipleValue;

    public GermanyInfections() {
        start();

        JOptionPane.showMessageDialog(null, "THE VIRUS APPEARED IN GERMANY!");
    }


    @Override
    public void run() {
        while (infections <= 82_000_000) {
            try {
                Thread.sleep(1000);
                infections += amount * multiple * 25;
                if (infections % 2000 == 0) {
                    amount += 30;
                }
                if (infections % 100000 == 0) {
                    multiple++;

                }
                recovered = (int) (0.25 * infections);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (infections > 82_000_000) {
                infections = 82_000_000;
                return;
            } else if (infections <= 0) {
                infections = 0;
                return;
            }
        }
    }

    public static void divideMultiple(int multiple) {
        GermanyInfections.multiple = GermanyInfections.multiple / multiple;
    }

    public static void changeAmount(int number) {
        amount -= number;
    }

    public static int getInfections() {
        return infections;
    }
}
