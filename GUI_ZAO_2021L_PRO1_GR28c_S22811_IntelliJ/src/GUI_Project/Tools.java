package GUI_Project;

import java.util.Scanner;

public class Tools {

    private static int thingsTemp = 0;
    private static int warehouseTemp = 0;
    private static int parkingTemp = 0;

    public static void endOfTheCommandMessage() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("PRESS ENTER TO CONTINUE!");
        keyboard.nextLine();
    }

    public static String thingsCounter() {
        String tempString = "PRO-" + thingsTemp;
        thingsTemp++;
        return tempString;
    }

    public static String warehousesCounter() {
        String tempString = "CM-" + warehouseTemp;
        warehouseTemp++;
        return tempString;
    }

    public static String parkingSpaceCounter() {
        String tempString = "PS-" + parkingTemp;
        parkingTemp++;
        return tempString;
    }



}
