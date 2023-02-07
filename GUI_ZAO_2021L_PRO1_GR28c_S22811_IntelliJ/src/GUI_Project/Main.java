package GUI_Project;


import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        //INFORMACJA: Nie wiem czemu ale wywołując metody z klasy MAIN nie ma żadnego efektu. Cały czas budwowałem projekt pod konsolę
        // i niestety nie zdołałem już tego naprawić, ponieważ za późno się zorientowałem. Pomimo umieszczenia referencji obiektów klasy Person
        // do mapy clients w klasie Service konsola nie może się do żadnej z osób zalogować. Konieczna jest rejestracja osoby przez konsolę (polecenie numer 10)
        // ,a wtedy wszystko działa.


        Time time = new Time();
        time.start();

        Service service = new Service(10, 16, 12);

        Check check = new Check(service);
        check.start();

        Control.console(service);

        Person person1 = new Person("KAROLINA", "MAKOWSKA", "22387423498", "WARSAW",
                1986, 4, 21);
        Person person2 = new Person("ADRIAN", "WOJCIECHOWSKI", "67623421188", "OTWOCK",
                1991, 12, 2);
        Person person3 = new Person("SZYMON", "KRAWCZYK", "27497261043", "SOCHACZEW",
                1989, 8, 9);
        Person person4 = new Person("ALICJA", "KUBIAK", "53241111456", "LEGIONOWO",
                1978, 10, 27);
        Person person5 = new Person("DAWID", "KONIECZNY", "57238957543", "PIASECZNO",
                1994, 2, 13);


        service.clients.put(person1.getID(), person1);
        service.clients.put(person2.getID(), person2);
        service.clients.put(person3.getID(), person3);
        service.clients.put(person4.getID(), person4);
        service.clients.put(person5.getID(), person5);

        Motorcycle motorcycle = new Motorcycle(person1, 2, 0.5, 0.7, "RED", "KW7822E",
                "CHOPPER", "FOUR-STROKE", 1100, "MANUAL", true);

        Amphibious amphibious = new Amphibious(person2, 4, 3, 3.5, "BROWN", "AL3468C", true,
                6, 2, 1200, 20.7);

        CityCar cityCar = new CityCar(person3, 3, 2.5, 1.6, "BLACK", "OS42339K", "COMBI",
                4, 190, 1995, "AUTO", "OIL", 144, "FRONT DRIVE", 250);

        OffRoadCar offRoadCar = new OffRoadCar(person4, 3, 2.7, 1.8, "SILVER", "UI2370P",
                "DIESEL", true, 2300, "MANUAL", "4X4", "DIESEL", 300);

        Motorcycle motorcycle1 = new Motorcycle(person5, 2, 0.4, 1, "GREEN", "SC7643E", "CRUISER",
                "FOUR STROKE", 1250, "MANUAL", false);

        HashMap<String, AutomotiveServices> randomOperations1 = new HashMap<>();
        randomOperations1.put("AXA1", AutomotiveServices.AXA1);
        randomOperations1.put("CXA3", AutomotiveServices.CXA3);
        randomOperations1.put("NXA14", AutomotiveServices.NXA14);

        HashMap<String, AutomotiveServices> randomOperations2 = new HashMap<>();
        randomOperations2.put("OXA15", AutomotiveServices.OXA15);
        randomOperations2.put("LXA12", AutomotiveServices.LXA12);
        randomOperations2.put("FXA6", AutomotiveServices.FXA6);


        ServiceOrder serviceOrder = new ServiceOrder(person1, motorcycle, 550, 2,
                7, randomOperations1);
        service.carServiceSpots[1].setServiceOrder(serviceOrder);

        ServiceOrder serviceOrder1 = new ServiceOrder(person2, amphibious, 310, 5,
                4, randomOperations2);
        service.carServiceSpots[3].setServiceOrder(serviceOrder1);

        ParkingSpaceOrder parkingSpaceOrder = new ParkingSpaceOrder(person3, 7);
        service.parkingSpaces[7].rent(parkingSpaceOrder, 7);
        service.parkingSpaces[7].putTheCarIn(person3, cityCar);

        ParkingSpaceOrder parkingSpaceOrder1 = new ParkingSpaceOrder(person4,9);
        service.parkingSpaces[9].rent(parkingSpaceOrder1, 9);
        service.parkingSpaces[9].putTheCarIn(person4, offRoadCar);

        ParkingSpaceOrder parkingSpaceOrder2 = new ParkingSpaceOrder(person5,3);
        service.parkingSpaces[3].rent(parkingSpaceOrder2, 4);
        service.parkingSpaces[3].putTheCarIn(person5, motorcycle1);


    }

}
