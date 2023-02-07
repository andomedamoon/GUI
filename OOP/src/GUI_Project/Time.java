package GUI_Project;

import java.time.LocalDate;

public class Time extends Thread {

    public static LocalDate myObj = LocalDate.now();

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(5000);
                myObj = myObj.plusDays(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
