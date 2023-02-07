package GUI_Project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.function.Function;

public class Control {

    public static void console(Service service) {
        Scanner keyboard = new Scanner(System.in);
        int temp = 0;
        Person user = null;
        do {
            System.out.println("-1 - END THE PROGRAM.");
            System.out.println(" 0 - LOG IN.");
            System.out.println(" 1 - LIST THE DATA WITH THE ROOMS.");
            System.out.println(" 2 - SHOW EMPTY ROOMS.");
            System.out.println(" 3 - RENT A ROOM.");
            System.out.println(" 4 - SHOW THE CONTENT OF THE SELECTED ROOM.");
            System.out.println(" 5 - PARK A CAR OR PLACE AN ITEM.");
            System.out.println(" 6 - REMOVE THE CAR / ITEM.");
            System.out.println(" 7 - SUBMIT A SERVICE ORDER.");
            System.out.println(" 8 - START REPAIR.");
            System.out.println(" 9 - SAVE SERVICE STATUS TO FILES.");
            System.out.println("10 - REGISTER.");
            System.out.println("Issue the order by entering the number consistent with the command above:");
            temp = keyboard.nextInt();
            keyboard.nextLine();

            if (temp == -1) {
                System.exit(0);
            } else if (temp == 0) {
                System.out.println("ENTER YOUR ID:");
                String id_temp = keyboard.nextLine();
                user = logIn(id_temp, service);

                if (user == null) {
                    System.out.println("THIS CLIENT DOES NOT APPEAR IN THE SYSTEM!");
                } else {
                    System.out.println("WELCOME BACK " + user.getForename() + " " + user.getSurname() + "!");
                }
                System.out.println();
                Tools.endOfTheCommandMessage();

            } else if (temp == 1) {
                if (user == null) {
                    System.out.println("BEFORE MAKING THIS COMMAND YOU HAVE TO LOG IN!");
                    System.out.println();
                    Tools.endOfTheCommandMessage();
                } else {
                    System.out.println(user.toString());
                    System.out.println(user.infos);
                    System.out.println();
                    Tools.endOfTheCommandMessage();
                }
            } else if (temp == 2) {
                if (user == null) {
                    System.out.println("BEFORE MAKING THIS COMMAND YOU HAVE TO LOG IN!");
                    System.out.println();
                    Tools.endOfTheCommandMessage();
                } else {
                    System.out.println("EMPTY SERVICES:");
                    for (int i = 0; i < service.carServiceSpots.length; i++) {
                        if (service.carServiceSpots[i].getStatus().equals("EMPTY")) {
                            System.out.println(service.carServiceSpots[i]);
                        }
                    }
                    System.out.println("EMPTY CONSUMER WAREHOUSES:");
                    for (int i = 0; i < service.consumerWarehouses.length; i++) {
                        if (service.consumerWarehouses[i].getStatus().equals("EMPTY")) {
                            System.out.println(service.consumerWarehouses[i]);
                        }
                    }
                    System.out.println("EMPTY PARKING SPACES:");
                    for (int i = 0; i < service.parkingSpaces.length; i++) {
                        if (service.parkingSpaces[i].getStatus().equals("EMPTY")) {
                            System.out.println(service.parkingSpaces[i]);
                        }
                    }
                    Tools.endOfTheCommandMessage();
                }
            } else if (temp == 3) {
                if (user == null) {
                    System.out.println("BEFORE MAKING THIS COMMAND YOU HAVE TO LOG IN!");
                    System.out.println();
                    Tools.endOfTheCommandMessage();
                } else {
                    System.out.println("1 - PRIVATE WAREHOUSE.");
                    System.out.println("2 - PARKING SPACE.");
                    System.out.println("WHAT TYPE OF ROOM WOULD YOU LIKE TO RENT?");
                    int temp2 = keyboard.nextInt();
                    if (temp2 == 1) {
                        for (int i = 0; i < service.consumerWarehouses.length; i++) {
                            if (service.consumerWarehouses[i].getStatus().equals("EMPTY")) {
                                System.out.println(service.consumerWarehouses[i]);
                            }
                        }
                        System.out.println("ENTER THE NUMBER OF THE ROOM YOU WANT TO RENT:");
                        int temp3 = keyboard.nextInt();
                        System.out.println("ENTER THE TIME (IN DAYS) YOU WANT TO RENT THE ROOM:");
                        int temp4 = keyboard.nextInt();
                        ConsumerWarehouseOrder order = new ConsumerWarehouseOrder(user, temp4, temp3);
                        service.consumerWarehouses[temp3].rent(order);
                        System.out.println();
                        Tools.endOfTheCommandMessage();
                    } else if (temp2 == 2) {
                        for (int i = 0; i < service.parkingSpaces.length; i++) {
                            if (service.parkingSpaces[i].getStatus().equals("EMPTY")) {
                                System.out.println(service.parkingSpaces[i]);
                            }
                        }
                        System.out.println("ENTER THE NUMBER OF THE ROOM YOU WANT TO RENT:");
                        int temp3 = keyboard.nextInt();
                        System.out.println("ENTER THE TIME (IN DAYS) YOU WANT TO RENT THE ROOM:");
                        int temp4 = keyboard.nextInt();
                        ParkingSpaceOrder order = new ParkingSpaceOrder(user, temp4, temp3);
                        service.parkingSpaces[temp3].rent(order, temp4);
                        System.out.println();
                        Tools.endOfTheCommandMessage();
                    }
                }
            } else if (temp == 4) {
                if (user == null) {
                    System.out.println("BEFORE MAKING THIS COMMAND YOU HAVE TO LOG IN!");
                    System.out.println();
                    Tools.endOfTheCommandMessage();
                } else {
                    System.out.println("1 - SERVICE WAREHOUSE.");
                    System.out.println("2 - PRIVATE WAREHOUSE.");
                    System.out.println("3 - PARKING SPACE.");
                    System.out.println("CHOOSE THE TYPE OF ROOM WHICH INTERNAL CONTENT YOU WANT TO SEE:");
                    int temp5 = keyboard.nextInt();
                    System.out.println("ENTER THE NUMBER OF THE ROOM WHICH THE CONTENT YOU WANT TO SEE:");
                    int temp6 = keyboard.nextInt();
                    if (temp5 == 1) {
                        System.out.println(service.carServiceSpots[temp6].serviceOrder.client);
                        System.out.println(service.carServiceSpots[temp6].serviceOrder.getVehicle());
                    } else if (temp5 == 2) {
                        System.out.println(service.consumerWarehouses[temp6].getTenant());
                        System.out.println(service.consumerWarehouses[temp6].things);
                    } else if (temp5 == 3) {
                        System.out.println(service.parkingSpaces[temp6].getTenant());
                        System.out.println(service.parkingSpaces[temp6].getVehicle());
                    }
                }
            } else if (temp == 5) {
                if (user == null) {
                    System.out.println("BEFORE MAKING THIS COMMAND YOU HAVE TO LOG IN!");
                    System.out.println();
                    Tools.endOfTheCommandMessage();
                } else {
                    System.out.println("1 - PARK A CAR.");
                    System.out.println("2 - PLACE AN ITEM.");
                    System.out.println("WHAT DO YOU WANT TO DO?");
                    int temp6 = keyboard.nextInt();
                    if (temp6 == 1) {
                        System.out.println("1 - OFF-ROAD CAR.");
                        System.out.println("2 - CITY CAR.");
                        System.out.println("3 - MOTORCYCLE.");
                        System.out.println("4 - AMPHIBIOUS.");
                        System.out.println("WHAT TYPE OF VEHICLE DO YOU TRY TO PARK:");
                        int temp7 = keyboard.nextInt();
                        if (temp7 == 1) {
                            System.out.println("ENTER THE LENGTH:");
                            double length = keyboard.nextDouble();
                            System.out.println("ENTER THE WIDTH:");
                            double width = keyboard.nextDouble();
                            System.out.println("ENTER THE HEIGHT:");
                            double height = keyboard.nextDouble();
                            System.out.println("ENTER THE COLOUR:");
                            String colour = keyboard.nextLine();
                            System.out.println("ENTER THE ENGINE TYPE:");
                            String engineType = keyboard.nextLine();
                            System.out.println("ENTER THE DISPLACEMENT OF THE ENGINE:");
                            int displacement = keyboard.nextInt();
                            System.out.println("DOES YOUR VEHICLE HAS TRANSFER CASE?");
                            System.out.println("TRUE");
                            System.out.println("FALSE");
                            boolean trueOrFalse = keyboard.nextBoolean();
                            System.out.println("ENTER THE DRIVE OF YOUR VEHICLE:");
                            String drive = keyboard.nextLine();
                            System.out.println("ENTER DRIVING GEAR:");
                            String drivingGear = keyboard.nextLine();
                            System.out.println("ENTER THE FUEL TYPE:");
                            String fuelType = keyboard.nextLine();
                            System.out.println("ENTER THE BOOT CAPACITY:");
                            int bootCapacity = keyboard.nextInt();
                            System.out.println("ENTER THE REGISTRATION NUMBER OF THE VEHICLE:");
                            String registrationNumber = keyboard.nextLine();
                            Vehicle vehicle = new OffRoadCar(user, length, width, height, colour, registrationNumber,
                                    engineType, trueOrFalse, displacement, drivingGear, drive, fuelType, bootCapacity);
                            System.out.println("STATE OF PARKING PLACES:");
                            for (int i = 0; i < service.parkingSpaces.length; i++) {
                                System.out.println(service.parkingSpaces[i]);
                            }
                            System.out.println("ENTER THE ROOM NUMBER OF THE EMPTY PARKING SPOT IN WHICH YOU WOULD LIKE" +
                                    " TO PUT THE VEHICLE:");
                            int roomNumber = keyboard.nextInt();
                            service.parkingSpaces[roomNumber].putTheCarIn(user, vehicle);
                            System.out.println();
                            Tools.endOfTheCommandMessage();
                        } else if (temp7 == 2) {
                            System.out.println("ENTER THE LENGTH:");
                            double length = keyboard.nextDouble();
                            System.out.println("ENTER THE WIDTH:");
                            double width = keyboard.nextDouble();
                            System.out.println("ENTER THE HEIGHT:");
                            double height = keyboard.nextDouble();
                            System.out.println("ENTER THE COLOUR:");
                            String colour = keyboard.nextLine();
                            System.out.println("ENTER THE CAR TYPE:");
                            String carType = keyboard.nextLine();
                            System.out.println("ENTER THE DOORS NUMBER:");
                            int doorsNumber = keyboard.nextInt();
                            System.out.println("ENTER THE ENGINE POWER:");
                            int enginePower = keyboard.nextInt();
                            System.out.println("ENTER THE GEARBOX TYPE:");
                            String gearbox = keyboard.nextLine();
                            System.out.println("ENTER THE FUEL TYPE:");
                            String fuelType = keyboard.nextLine();
                            System.out.println("ENTER THE AMOUNT OF THE CO2 EMISSIONS YOUR VEHICLE PRODUCES:");
                            double CO2Emissions = keyboard.nextDouble();
                            System.out.println("ENTER THE DISPLACEMENT OF THE ENGINE:");
                            int displacement = keyboard.nextInt();
                            System.out.println("ENTER THE BOOT CAPACITY:");
                            int bootCapacity = keyboard.nextInt();
                            System.out.println("ENTER THE DRIVE:");
                            String drive = keyboard.nextLine();
                            keyboard.nextLine();
                            System.out.println("ENTER THE REGISTRATION NUMBER:");
                            String registrationNumber = keyboard.nextLine();
                            Vehicle vehicle = new CityCar(user, length, width, height, colour, registrationNumber, carType,
                                    doorsNumber, enginePower, displacement, gearbox, fuelType, CO2Emissions, drive, bootCapacity);
                            System.out.println("STATE OF PARKING PLACES:");
                            for (int i = 0; i < service.parkingSpaces.length; i++) {
                                System.out.println(service.parkingSpaces[i]);
                            }
                            System.out.println("ENTER THE ROOM NUMBER OF THE EMPTY PARKING SPOT IN WHICH YOU WOULD LIKE" +
                                    " TO PUT THE VEHICLE:");
                            int roomNumber = keyboard.nextInt();
                            service.parkingSpaces[roomNumber].putTheCarIn(user, vehicle);
                            System.out.println();
                            Tools.endOfTheCommandMessage();
                        } else if (temp7 == 3) {
                            System.out.println("ENTER THE LENGTH:");
                            double length = keyboard.nextDouble();
                            System.out.println("ENTER THE WIDTH:");
                            double width = keyboard.nextDouble();
                            System.out.println("ENTER THE HEIGHT:");
                            double height = keyboard.nextDouble();
                            System.out.println("ENTER THE COLOUR:");
                            String colour = keyboard.nextLine();
                            System.out.println("ENTER THE TYPE OF MOTORCYCLE: ");
                            String typeOfMotorcycle = keyboard.nextLine();
                            System.out.println("ENTER THE ENGINE TYPE:");
                            String engineType = keyboard.nextLine();
                            System.out.println("ENTER THE DISPLACEMENT OF THE ENGINE:");
                            int displacement = keyboard.nextInt();
                            System.out.println("ENTER THE GEARBOX TYPE: ");
                            String gearBoxType = keyboard.nextLine();
                            System.out.println("DOES YPUR VEHICLE HAS A TROLLEY?");
                            System.out.println("TRUE");
                            System.out.println("FALSE");
                            boolean doesHaveATrolley = keyboard.nextBoolean();
                            System.out.println("ENTER THE REGISTRATION NUMBER OF THE VEHICLE:");
                            String registrationNumber = keyboard.nextLine();
                            Vehicle vehicle = new Motorcycle(user, length, width, height, colour, registrationNumber,
                                    typeOfMotorcycle, engineType, displacement, gearBoxType, doesHaveATrolley);
                            System.out.println("STATE OF PARKING PLACES:");
                            for (int i = 0; i < service.parkingSpaces.length; i++) {
                                System.out.println(service.parkingSpaces[i]);
                            }
                            System.out.println("ENTER THE ROOM NUMBER OF THE EMPTY PARKING SPOT IN WHICH YOU WOULD LIKE" +
                                    " TO PUT THE VEHICLE:");
                            int roomNumber = keyboard.nextInt();
                            service.parkingSpaces[roomNumber].putTheCarIn(user, vehicle);
                            System.out.println();
                            Tools.endOfTheCommandMessage();
                        } else if (temp7 == 4) {
                            System.out.println("ENTER THE LENGTH:");
                            double length = keyboard.nextDouble();
                            System.out.println("ENTER THE WIDTH:");
                            double width = keyboard.nextDouble();
                            System.out.println("ENTER THE HEIGHT:");
                            double height = keyboard.nextDouble();
                            System.out.println("ENTER THE COLOUR:");
                            String colour = keyboard.nextLine();
                            System.out.println("DOES THE VEHICLE HAVE TRACKS?");
                            System.out.println("TRUE");
                            System.out.println("FALSE");
                            boolean band = keyboard.nextBoolean();
                            System.out.println("ENTER THE TYRES NUMBER:");
                            int tyres = keyboard.nextInt();
                            System.out.println("ENTER THE DOORS NUMBER:");
                            int doorsNumber = keyboard.nextInt();
                            System.out.println("ENTER THE WATER PUMP CAPACITY:");
                            int wateerPumpCapacity = keyboard.nextInt();
                            System.out.println("ENTER THE MAXIMUM SPEED IN THE WATER:");
                            double maxSpeedInWater = keyboard.nextDouble();
                            System.out.println("ENTER THE REGISTRATION NUMBER OF THE VEHICLE:");
                            String registrationNumber = keyboard.nextLine();
                            keyboard.nextLine();
                            Vehicle vehicle = new Amphibious(user, length, width, height, colour, registrationNumber,
                                    band, tyres, doorsNumber, wateerPumpCapacity, maxSpeedInWater);
                            System.out.println("STATE OF PARKING PLACES:");
                            for (int i = 0; i < service.parkingSpaces.length; i++) {
                                System.out.println(service.parkingSpaces[i]);
                            }
                            System.out.println("ENTER THE ROOM NUMBER OF THE EMPTY PARKING SPOT IN WHICH YOU WOULD LIKE" +
                                    " TO PUT THE VEHICLE:");
                            int roomNumber = keyboard.nextInt();
                            service.parkingSpaces[roomNumber].putTheCarIn(user, vehicle);
                            System.out.println();
                            Tools.endOfTheCommandMessage();
                        }
                    } else if (temp6 == 2) {
                        System.out.println("1. SERVICE WAREHOUSE.");
                        System.out.println("2. CONSUMER WAREHOUSE.");
                        System.out.println("TO WHICH WAREHOUSE WOULD YOU LIKE TO PUT A THING?");
                        int tempWarehouse = keyboard.nextInt();
                        if (tempWarehouse == 1) {
                            System.out.println("ENTER WHAT YOU WANT TO PUT IN STORAGE?");
                            String thingType = keyboard.nextLine();
                            System.out.println("ENTER THE LENGTH OF THE OBJECT:");
                            double length = keyboard.nextDouble();
                            System.out.println("ENTER THE WIDTH OF THE OBJECT:");
                            double width = keyboard.nextDouble();
                            System.out.println("ENTER THE HEIGHT OF THE OBJECT:");
                            double height = keyboard.nextDouble();
                            System.out.println("ENTER THE WEIGHT OF THE OBJECT:");
                            double weight = keyboard.nextDouble();

                            Thing thing = new Thing(user, thingType, length, width, height, weight);
                            service.serviceWarehouse.putThingIntoServiceWarehouse(thing);

                        } else {
                            System.out.println("ENTER WHAT YOU WANT TO PUT IN STORAGE?");
                            String thingType = keyboard.nextLine();
                            System.out.println("ENTER THE LENGTH OF THE OBJECT:");
                            double length = keyboard.nextDouble();
                            System.out.println("ENTER THE WIDTH OF THE OBJECT:");
                            double width = keyboard.nextDouble();
                            System.out.println("ENTER THE HEIGHT OF THE OBJECT:");
                            double height = keyboard.nextDouble();
                            System.out.println("ENTER THE WEIGHT OF THE OBJECT:");
                            double weight = keyboard.nextDouble();

                            Thing thing = new Thing(user, thingType, length, width, height, weight);
                            System.out.println("STATE OF THE PRIVATE WAREHOUSES:");
                            for (int i = 0; i < service.consumerWarehouses.length; i++) {
                                System.out.println(service.consumerWarehouses[i]);
                            }

                            System.out.println("ENTER THE NUMBER OF THE WAREHOUSE YOU'RE ALREADY RENTING:");
                            int roomNumber = keyboard.nextInt();
                            try {
                                service.consumerWarehouses[roomNumber].putThingIntoWarehouse(thing);
                            } catch (NoPermissionException e) {
                                System.out.println(e.getMessage());
                            }
                            System.out.println();
                            Tools.endOfTheCommandMessage();
                        }
                    }
                }
            } else if (temp == 6) {
                if (user == null) {
                    System.out.println("BEFORE MAKING THIS COMMAND YOU HAVE TO LOG IN!");
                    System.out.println();
                    Tools.endOfTheCommandMessage();
                } else {
                    System.out.println("1 - REMOVE THE CAR.");
                    System.out.println("2 - REMOVE AN ITEM.");
                    System.out.println("WHAT DO YOU WANT TO DO?");
                    int temp8 = keyboard.nextInt();
                    if (temp8 == 1) {
                        System.out.println("STATE OF PARKING PLACES:");
                        for (int i = 0; i < service.parkingSpaces.length; i++) {
                            System.out.println(service.parkingSpaces[i]);
                        }
                        System.out.println("ENTER THE NUMBER OF THE PARKING SPACE YOU WANT TO MAKE EMPTY:");
                        int roomNumber = keyboard.nextInt();
                        service.parkingSpaces[roomNumber].pullTheCarOut(user);
                        System.out.println();
                        Tools.endOfTheCommandMessage();
                    } else if (temp8 == 2) {
                        System.out.println("STATE OF THE PRIVATE WAREHOUSES:");
                        for (int i = 0; i < service.consumerWarehouses.length; i++) {
                            System.out.println(service.consumerWarehouses[i]);
                        }
                        System.out.println("ENTER THE NUMBER OF YOUR WAREHOUSE FROM WHICH YOU WANT TO TAKE THE THING OUT:");
                        int roomNumber = keyboard.nextInt();
                        try {
                            service.consumerWarehouses[roomNumber].showThings(user);
                            System.out.println("TO TAKE OUT A SPECIFIC ITEM, ENTER ITS IDENTIFICATION CODE:");
                            String identificationCode = keyboard.nextLine();
                            try {
                                service.consumerWarehouses[roomNumber].putThingOutOfTheWarehouse(user, identificationCode);
                            } catch (NoSuchElementException e) {
                                System.out.println(e.getMessage());
                            }
                        } catch (NoPermissionException e) {
                            System.out.println(e.getMessage());
                        }
                        System.out.println();
                        Tools.endOfTheCommandMessage();
                    }
                }
            } else if (temp == 7) {
                if (user == null) {
                    System.out.println("BEFORE MAKING THIS COMMAND YOU HAVE TO LOG IN!");
                    System.out.println();
                    Tools.endOfTheCommandMessage();
                } else {
                    System.out.println("1. REPAIR BY MECHANIC.");
                    System.out.println("2. REPAIR BY YOURSELF.");
                    int temp9 = keyboard.nextInt();
                    System.out.println("1 - OFF-ROAD CAR.");
                    System.out.println("2 - CITY CAR.");
                    System.out.println("3 - MOTORCYCLE.");
                    System.out.println("4 - AMPHIBIOUS.");
                    System.out.println("CHOOSE A VEHICLE:");
                    int temp10 = keyboard.nextInt();
                    if (temp9 == 1) {
                        if (temp10 == 1) {
                            System.out.println("ENTER THE LENGTH:");
                            double length = keyboard.nextDouble();
                            System.out.println("ENTER THE WIDTH:");
                            double width = keyboard.nextDouble();
                            System.out.println("ENTER THE HEIGHT:");
                            double height = keyboard.nextDouble();
                            System.out.println("ENTER THE COLOUR:");
                            String colour = keyboard.nextLine();
                            System.out.println("ENTER THE ENGINE TYPE:");
                            String engineType = keyboard.nextLine();
                            System.out.println("ENTER THE DISPLACEMENT OF THE ENGINE:");
                            int displacement = keyboard.nextInt();
                            System.out.println("DOES YOUR VEHICLE HAS TRANSFER CASE?");
                            System.out.println("TRUE");
                            System.out.println("FALSE");
                            boolean trueOrFalse = keyboard.nextBoolean();
                            System.out.println("ENTER THE DRIVE OF YOUR VEHICLE:");
                            String drive = keyboard.nextLine();
                            System.out.println("ENTER DRIVING GEAR:");
                            String drivingGear = keyboard.nextLine();
                            System.out.println("ENTER THE FUEL TYPE:");
                            String fuelType = keyboard.nextLine();
                            System.out.println("ENTER THE BOOT CAPACITY:");
                            int bootCapacity = keyboard.nextInt();
                            System.out.println("ENTER THE REGISTRATION NUMBER OF THE VEHICLE:");
                            String registrationNumber = keyboard.nextLine();
                            Vehicle vehicle = new OffRoadCar(user, length, width, height, colour, registrationNumber,
                                    engineType, trueOrFalse, displacement, drivingGear, drive, fuelType, bootCapacity);
                            HashMap<String, AutomotiveServices> operationsTemp = new HashMap<>();
                            String afterUpper = "";
                            double amount = 0;
                            do {
                                AutomotiveServices[] values = AutomotiveServices.values();
                                for (int i = 0; i < values.length; i++) {
                                    System.out.println("CODE: " + values[i] + " SERVICE TYPE DESCRIPTION: " + values[i].getDescription() + " COST: " + values[i].getCost());
                                }
                                System.out.println("CHOOSE THE TYPE OF SERVICE YOU ARE INTERESTED IN," +
                                        "AND WHEN YOU FINISH IT, ENTER THE \"STOP\" COMMAND:");
                                String val = keyboard.nextLine();

                                Function<String, String> function = s -> s.toUpperCase().trim();

                                afterUpper = function.apply(val);
                                try {
                                    AutomotiveServices operation = AutomotiveServices.valueOf(afterUpper);
                                    amount += operation.getCost();
                                    operationsTemp.put(afterUpper, operation);
                                } catch (IllegalArgumentException e) {
                                    if (!afterUpper.equals("STOP")) {
                                        System.out.println("THE COMMAND IS INCORRECT! TRY AGAIN:");
                                        System.out.println();
                                    }
                                }
                            } while (!afterUpper.equals("STOP"));

                            int servicesCount = 0;
                            int parkingsCount = 0;
                            System.out.println("STATE OF THE SERVICES:");
                            for (int i = 0; i < service.carServiceSpots.length; i++) {
                                System.out.println(service.carServiceSpots[i]);
                                if (service.carServiceSpots[i].getStatus().equals("EMPTY")) {
                                    servicesCount++;
                                }
                            }
                            System.out.println("STATE OF THE PARKING SPACES:");
                            for (int i = 0; i < service.parkingSpaces.length; i++) {
                                System.out.println(service.parkingSpaces[i]);
                                if (service.parkingSpaces[i].getStatus().equals("EMPTY")) {
                                    parkingsCount++;
                                }
                            }
                            if (servicesCount == 0 && parkingsCount == 0) {
                                System.out.println("UNFORTUNATELY, BUT IT'S NOT CURRENTLY POSSIBLE TO EXECUTE THE ORDER.\nTRY AGAIN LATER.");
                            } else if (servicesCount == 0 && parkingsCount > 0) {
                                System.out.println("1 - YES.");
                                System.out.println("0 - NO.");
                                System.out.println("ALL SERVICE PLACES ARE CURRENTLY RENTED. DO YOU WANT TO SIGN UP FOR THE" +
                                        " RESERVE LIST?");
                                int yesOrNo;
                                do {
                                    yesOrNo = keyboard.nextInt();
                                } while (yesOrNo != 1 && yesOrNo != 0);

                                if (yesOrNo == 1) {
                                    System.out.println("STATE OF THE PARKING SPACES:");
                                    for (int i = 0; i < service.parkingSpaces.length; i++) {
                                        System.out.println(service.parkingSpaces[i]);
                                    }
                                    System.out.println("ENTER THE NUMBER OF THE PARKING PLACE TO WHICH YOUR ORDER WILL" +
                                            " BE RELATED TO:");
                                    int temp11 = keyboard.nextInt();

                                    System.out.println("DO YOU WANT TO EXTEND THE RENTAL OF THE PARKING SPACE AFTER THE END OF THE SERVICE?\n" +
                                            "IF SO, ENTER THE TIME PERIOD IN DAYS BEARING IN MIND THAT THE MAXIMUM ADDITIONAL PARKING PERIOD IS 14 DAYS,\n" +
                                            "IF NO, ENTER THE ZERO NUMBER:");
                                    int temp12 = keyboard.nextInt();

                                    ServiceOrder serviceOrder = new ServiceOrder(user, vehicle, amount, temp11, temp12, operationsTemp);
                                    service.addToServiceQueue(serviceOrder);
                                    service.parkingSpaces[temp11].setVehicle(vehicle);
                                    service.parkingSpaces[temp11].setSpotUsedDuringQueue(true);
                                    service.parkingSpaces[temp11].setStatus("TAKEN");
                                    service.parkingSpaces[temp11].peopleWithAccess.add(user);

                                    System.out.println();
                                    Tools.endOfTheCommandMessage();
                                } else {
                                    return;
                                }
                            } else {
                                System.out.println("STATE OF THE SERVICES:");
                                for (int i = 0; i < service.carServiceSpots.length; i++) {
                                    System.out.println(service.carServiceSpots[i]);
                                }
                                System.out.println("ENTER THE NUMBER OF THE SERVICE SPOT TO WHICH YOUR ORDER WILL BE RELATED TO:");
                                int temp13 = keyboard.nextInt();
                                System.out.println("STATE OF THE PARKING SPACES:");
                                for (int i = 0; i < service.parkingSpaces.length; i++) {
                                    System.out.println(service.parkingSpaces[i]);
                                }
                                System.out.println("ENTER THE NUMBER OF THE PARKING SPACE TO WHICH YOUR ORDER WILL BE RELATED TO:");
                                int temp14 = keyboard.nextInt();
                                System.out.println("DO YOU WANT TO EXTEND THE RENTAL OF THE PARKING SPACE AFTER THE END OF THE SERVICE?\n" +
                                        " IF SO, ENTER THE TIME PERIOD IN DAYS BEARING IN MIND THAT THE MAXIMUM ADDITIONAL PARKING PERIOD IS 14 DAYS,\n" +
                                        "IF NO, ENTER THE ZERO NUMBER:");
                                int temp15 = keyboard.nextInt();
                                ServiceOrder serviceOrder = new ServiceOrder(user, vehicle, amount, temp13, temp14, temp15, operationsTemp);
                                service.carServiceSpots[temp13].setServiceOrder(serviceOrder);
                                System.out.println();
                                Tools.endOfTheCommandMessage();
                            }
                        } else if (temp10 == 2) {
                            System.out.println("ENTER THE LENGTH:");
                            double length = keyboard.nextDouble();
                            System.out.println("ENTER THE WIDTH:");
                            double width = keyboard.nextDouble();
                            System.out.println("ENTER THE HEIGHT:");
                            double height = keyboard.nextDouble();
                            System.out.println("ENTER THE COLOUR:");
                            String colour = keyboard.nextLine();
                            System.out.println("ENTER THE CAR TYPE:");
                            String carType = keyboard.nextLine();
                            System.out.println("ENTER THE DOORS NUMBER:");
                            int doorsNumber = keyboard.nextInt();
                            System.out.println("ENTER THE ENGINE POWER:");
                            int enginePower = keyboard.nextInt();
                            System.out.println("ENTER THE GEARBOX TYPE:");
                            String gearbox = keyboard.nextLine();
                            System.out.println("ENTER THE FUEL TYPE:");
                            String fuelType = keyboard.nextLine();
                            System.out.println("ENTER THE AMOUNT OF THE CO2 EMISSIONS YOUR VEHICLE PRODUCES:");
                            double CO2Emissions = keyboard.nextDouble();
                            System.out.println("ENTER THE DISPLACEMENT OF THE ENGINE:");
                            int displacement = keyboard.nextInt();
                            System.out.println("ENTER THE BOOT CAPACITY:");
                            int bootCapacity = keyboard.nextInt();
                            System.out.println("ENTER THE DRIVE:");
                            String drive = keyboard.nextLine();
                            keyboard.nextLine();
                            System.out.println("ENTER THE REGISTRATION NUMBER:");
                            String registrationNumber = keyboard.nextLine();
                            Vehicle vehicle = new CityCar(user, length, width, height, colour, registrationNumber, carType,
                                    doorsNumber, enginePower, displacement, gearbox, fuelType, CO2Emissions, drive, bootCapacity);
                            HashMap<String, AutomotiveServices> operationsTemp = new HashMap<>();
                            String afterUpper = "";
                            double amount = 0;
                            do {
                                AutomotiveServices[] values = AutomotiveServices.values();
                                for (int i = 0; i < values.length; i++) {
                                    System.out.println("CODE: " + values[i] + " SERVICE TYPE DESCRIPTION: " + values[i].getDescription() + " COST: " + values[i].getCost());
                                }
                                System.out.println("CHOOSE THE TYPE OF SERVICE YOU ARE INTERESTED IN," +
                                        "AND WHEN YOU FINISH IT, ENTER THE \"STOP\" COMMAND:");
                                String val = keyboard.nextLine();

                                Function<String, String> function = s -> s.toUpperCase().trim();

                                afterUpper = function.apply(val);
                                try {
                                    AutomotiveServices operation = AutomotiveServices.valueOf(afterUpper);
                                    amount += operation.getCost();
                                    operationsTemp.put(afterUpper, operation);
                                } catch (IllegalArgumentException e) {
                                    if (!afterUpper.equals("STOP")) {
                                        System.out.println("THE COMMAND IS INCORRECT! TRY AGAIN:");
                                        System.out.println();
                                    }
                                }
                            } while (!afterUpper.equals("STOP"));

                            int servicesCount = 0;
                            int parkingsCount = 0;
                            System.out.println("STATE OF THE SERVICES:");
                            for (int i = 0; i < service.carServiceSpots.length; i++) {
                                System.out.println(service.carServiceSpots[i]);
                                if (service.carServiceSpots[i].getStatus().equals("EMPTY")) {
                                    servicesCount++;
                                }
                            }
                            System.out.println("STATE OF THE PARKING SPACES:");
                            for (int i = 0; i < service.parkingSpaces.length; i++) {
                                System.out.println(service.parkingSpaces[i]);
                                if (service.parkingSpaces[i].getStatus().equals("EMPTY")) {
                                    parkingsCount++;
                                }
                            }
                            if (servicesCount == 0 && parkingsCount == 0) {
                                System.out.println("UNFORTUNATELY, BUT IT'S NOT CURRENTLY POSSIBLE TO EXECUTE THE ORDER.\nTRY AGAIN LATER.");
                            } else if (servicesCount == 0 && parkingsCount > 0) {
                                System.out.println("1 - YES.");
                                System.out.println("0 - NO.");
                                System.out.println("ALL SERVICE PLACES ARE CURRENTLY RENTED. DO YOU WANT TO SIGN UP FOR THE" +
                                        " RESERVE LIST?");
                                int yesOrNo;
                                do {
                                    yesOrNo = keyboard.nextInt();
                                } while (yesOrNo != 1 && yesOrNo != 0);

                                if (yesOrNo == 1) {
                                    System.out.println("STATE OF THE PARKING SPACES:");
                                    for (int i = 0; i < service.parkingSpaces.length; i++) {
                                        System.out.println(service.parkingSpaces[i]);
                                    }
                                    System.out.println("ENTER THE NUMBER OF THE PARKING PLACE TO WHICH YOUR ORDER WILL" +
                                            " BE RELATED TO:");
                                    int temp11 = keyboard.nextInt();

                                    System.out.println("DO YOU WANT TO EXTEND THE RENTAL OF THE PARKING SPACE AFTER THE END OF THE SERVICE?\n" +
                                            "IF SO, ENTER THE TIME PERIOD IN DAYS BEARING IN MIND THAT THE MAXIMUM ADDITIONAL PARKING PERIOD IS 14 DAYS,\n" +
                                            "IF NO, ENTER THE ZERO NUMBER:");
                                    int temp12 = keyboard.nextInt();

                                    ServiceOrder serviceOrder = new ServiceOrder(user, vehicle, amount, temp11, temp12, operationsTemp);
                                    service.addToServiceQueue(serviceOrder);
                                    service.parkingSpaces[temp11].setVehicle(vehicle);
                                    service.parkingSpaces[temp11].setSpotUsedDuringQueue(true);
                                    service.parkingSpaces[temp11].setStatus("TAKEN");
                                    service.parkingSpaces[temp11].peopleWithAccess.add(user);

                                    System.out.println();
                                    Tools.endOfTheCommandMessage();
                                } else {
                                    return;
                                }
                            } else {
                                System.out.println("STATE OF THE SERVICES:");
                                for (int i = 0; i < service.carServiceSpots.length; i++) {
                                    System.out.println(service.carServiceSpots[i]);
                                }
                                System.out.println("ENTER THE NUMBER OF THE SERVICE SPOT TO WHICH YOUR ORDER WILL BE RELATED TO:");
                                int temp13 = keyboard.nextInt();
                                System.out.println("STATE OF THE PARKING SPACES:");
                                for (int i = 0; i < service.parkingSpaces.length; i++) {
                                    System.out.println(service.parkingSpaces[i]);
                                }
                                System.out.println("ENTER THE NUMBER OF THE PARKING SPACE TO WHICH YOUR ORDER WILL BE RELATED TO:");
                                int temp14 = keyboard.nextInt();
                                System.out.println("DO YOU WANT TO EXTEND THE RENTAL OF THE PARKING SPACE AFTER THE END OF THE SERVICE?\n" +
                                        " IF SO, ENTER THE TIME PERIOD IN DAYS BEARING IN MIND THAT THE MAXIMUM ADDITIONAL PARKING PERIOD IS 14 DAYS,\n" +
                                        "IF NO, ENTER THE ZERO NUMBER:");
                                int temp15 = keyboard.nextInt();
                                ServiceOrder serviceOrder = new ServiceOrder(user, vehicle, amount, temp13, temp14, temp15, operationsTemp);
                                service.carServiceSpots[temp13].setServiceOrder(serviceOrder);
                                System.out.println();
                                Tools.endOfTheCommandMessage();
                            }
                        } else if (temp10 == 3) {
                            System.out.println("ENTER THE LENGTH:");
                            double length = keyboard.nextDouble();
                            System.out.println("ENTER THE WIDTH:");
                            double width = keyboard.nextDouble();
                            System.out.println("ENTER THE HEIGHT:");
                            double height = keyboard.nextDouble();
                            System.out.println("ENTER THE COLOUR:");
                            String colour = keyboard.nextLine();
                            System.out.println("ENTER THE TYPE OF MOTORCYCLE: ");
                            String typeOfMotorcycle = keyboard.nextLine();
                            System.out.println("ENTER THE ENGINE TYPE:");
                            String engineType = keyboard.nextLine();
                            System.out.println("ENTER THE DISPLACEMENT OF THE ENGINE:");
                            int displacement = keyboard.nextInt();
                            System.out.println("ENTER THE GEARBOX TYPE: ");
                            String gearBoxType = keyboard.nextLine();
                            System.out.println("DOES YPUR VEHICLE HAS A TROLLEY?");
                            System.out.println("TRUE");
                            System.out.println("FALSE");
                            boolean doesHaveATrolley = keyboard.nextBoolean();
                            System.out.println("ENTER THE REGISTRATION NUMBER OF THE VEHICLE:");
                            String registrationNumber = keyboard.nextLine();
                            Vehicle vehicle = new Motorcycle(user, length, width, height, colour, registrationNumber,
                                    typeOfMotorcycle, engineType, displacement, gearBoxType, doesHaveATrolley);
                            HashMap<String, AutomotiveServices> operationsTemp = new HashMap<>();
                            String afterUpper = "";
                            double amount = 0;
                            do {
                                AutomotiveServices[] values = AutomotiveServices.values();
                                for (int i = 0; i < values.length; i++) {
                                    System.out.println("CODE: " + values[i] + " SERVICE TYPE DESCRIPTION: " + values[i].getDescription() + " COST: " + values[i].getCost());
                                }
                                System.out.println("CHOOSE THE TYPE OF SERVICE YOU ARE INTERESTED IN," +
                                        "AND WHEN YOU FINISH IT, ENTER THE \"STOP\" COMMAND:");
                                String val = keyboard.nextLine();

                                Function<String, String> function = s -> s.toUpperCase().trim();

                                afterUpper = function.apply(val);
                                try {
                                    AutomotiveServices operation = AutomotiveServices.valueOf(afterUpper);
                                    amount += operation.getCost();
                                    operationsTemp.put(afterUpper, operation);
                                } catch (IllegalArgumentException e) {
                                    if (!afterUpper.equals("STOP")) {
                                        System.out.println("THE COMMAND IS INCORRECT! TRY AGAIN:");
                                        System.out.println();
                                    }
                                }
                            } while (!afterUpper.equals("STOP"));

                            int servicesCount = 0;
                            int parkingsCount = 0;
                            System.out.println("STATE OF THE SERVICES:");
                            for (int i = 0; i < service.carServiceSpots.length; i++) {
                                System.out.println(service.carServiceSpots[i]);
                                if (service.carServiceSpots[i].getStatus().equals("EMPTY")) {
                                    servicesCount++;
                                }
                            }
                            System.out.println("STATE OF THE PARKING SPACES:");
                            for (int i = 0; i < service.parkingSpaces.length; i++) {
                                System.out.println(service.parkingSpaces[i]);
                                if (service.parkingSpaces[i].getStatus().equals("EMPTY")) {
                                    parkingsCount++;
                                }
                            }
                            if (servicesCount == 0 && parkingsCount == 0) {
                                System.out.println("UNFORTUNATELY, BUT IT'S NOT CURRENTLY POSSIBLE TO EXECUTE THE ORDER.\nTRY AGAIN LATER.");
                            } else if (servicesCount == 0 && parkingsCount > 0) {
                                System.out.println("1 - YES.");
                                System.out.println("0 - NO.");
                                System.out.println("ALL SERVICE PLACES ARE CURRENTLY RENTED. DO YOU WANT TO SIGN UP FOR THE" +
                                        " RESERVE LIST?");
                                int yesOrNo;
                                do {
                                    yesOrNo = keyboard.nextInt();
                                } while (yesOrNo != 1 && yesOrNo != 0);

                                if (yesOrNo == 1) {
                                    System.out.println("STATE OF THE PARKING SPACES:");
                                    for (int i = 0; i < service.parkingSpaces.length; i++) {
                                        System.out.println(service.parkingSpaces[i]);
                                    }
                                    System.out.println("ENTER THE NUMBER OF THE PARKING PLACE TO WHICH YOUR ORDER WILL" +
                                            " BE RELATED TO:");
                                    int temp11 = keyboard.nextInt();

                                    System.out.println("DO YOU WANT TO EXTEND THE RENTAL OF THE PARKING SPACE AFTER THE END OF THE SERVICE?\n" +
                                            "IF SO, ENTER THE TIME PERIOD IN DAYS BEARING IN MIND THAT THE MAXIMUM ADDITIONAL PARKING PERIOD IS 14 DAYS,\n" +
                                            "IF NO, ENTER THE ZERO NUMBER:");
                                    int temp12 = keyboard.nextInt();

                                    ServiceOrder serviceOrder = new ServiceOrder(user, vehicle, amount, temp11, temp12, operationsTemp);
                                    service.addToServiceQueue(serviceOrder);
                                    service.parkingSpaces[temp11].setVehicle(vehicle);
                                    service.parkingSpaces[temp11].setSpotUsedDuringQueue(true);
                                    service.parkingSpaces[temp11].setStatus("TAKEN");
                                    service.parkingSpaces[temp11].peopleWithAccess.add(user);

                                    System.out.println();
                                    Tools.endOfTheCommandMessage();
                                } else {
                                    return;
                                }
                            } else {
                                System.out.println("STATE OF THE SERVICES:");
                                for (int i = 0; i < service.carServiceSpots.length; i++) {
                                    System.out.println(service.carServiceSpots[i]);
                                }
                                System.out.println("ENTER THE NUMBER OF THE SERVICE SPOT TO WHICH YOUR ORDER WILL BE RELATED TO:");
                                int temp13 = keyboard.nextInt();
                                System.out.println("STATE OF THE PARKING SPACES:");
                                for (int i = 0; i < service.parkingSpaces.length; i++) {
                                    System.out.println(service.parkingSpaces[i]);
                                }
                                System.out.println("ENTER THE NUMBER OF THE PARKING SPACE TO WHICH YOUR ORDER WILL BE RELATED TO:");
                                int temp14 = keyboard.nextInt();
                                System.out.println("DO YOU WANT TO EXTEND THE RENTAL OF THE PARKING SPACE AFTER THE END OF THE SERVICE?\n" +
                                        " IF SO, ENTER THE TIME PERIOD IN DAYS BEARING IN MIND THAT THE MAXIMUM ADDITIONAL PARKING PERIOD IS 14 DAYS,\n" +
                                        "IF NO, ENTER THE ZERO NUMBER:");
                                int temp15 = keyboard.nextInt();
                                ServiceOrder serviceOrder = new ServiceOrder(user, vehicle, amount, temp13, temp14, temp15, operationsTemp);
                                service.carServiceSpots[temp13].setServiceOrder(serviceOrder);
                                System.out.println();
                                Tools.endOfTheCommandMessage();
                            }
                        } else if (temp10 == 4) {
                            System.out.println("ENTER THE LENGTH:");
                            double length = keyboard.nextDouble();
                            System.out.println("ENTER THE WIDTH:");
                            double width = keyboard.nextDouble();
                            System.out.println("ENTER THE HEIGHT:");
                            double height = keyboard.nextDouble();
                            System.out.println("ENTER THE COLOUR:");
                            String colour = keyboard.nextLine();
                            System.out.println("DOES THE VEHICLE HAVE TRACKS?");
                            System.out.println("TRUE");
                            System.out.println("FALSE");
                            boolean band = keyboard.nextBoolean();
                            System.out.println("ENTER THE TYRES NUMBER:");
                            int tyres = keyboard.nextInt();
                            System.out.println("ENTER THE DOORS NUMBER:");
                            int doorsNumber = keyboard.nextInt();
                            System.out.println("ENTER THE WATER PUMP CAPACITY:");
                            int wateerPumpCapacity = keyboard.nextInt();
                            System.out.println("ENTER THE MAXIMUM SPEED IN THE WATER:");
                            double maxSpeedInWater = keyboard.nextDouble();
                            System.out.println("ENTER THE REGISTRATION NUMBER OF THE VEHICLE:");
                            String registrationNumber = keyboard.nextLine();
                            keyboard.nextLine();
                            Vehicle vehicle = new Amphibious(user, length, width, height, colour, registrationNumber,
                                    band, tyres, doorsNumber, wateerPumpCapacity, maxSpeedInWater);
                            HashMap<String, AutomotiveServices> operationsTemp = new HashMap<>();
                            String afterUpper = "";
                            double amount = 0;
                            do {
                                AutomotiveServices[] values = AutomotiveServices.values();
                                for (int i = 0; i < values.length; i++) {
                                    System.out.println("CODE: " + values[i] + " SERVICE TYPE DESCRIPTION: " + values[i].getDescription() + " COST: " + values[i].getCost());
                                }
                                System.out.println("CHOOSE THE TYPE OF SERVICE YOU ARE INTERESTED IN," +
                                        "AND WHEN YOU FINISH IT, ENTER THE \"STOP\" COMMAND:");
                                String val = keyboard.nextLine();

                                Function<String, String> function = s -> s.toUpperCase().trim();

                                afterUpper = function.apply(val);
                                try {
                                    AutomotiveServices operation = AutomotiveServices.valueOf(afterUpper);
                                    amount += operation.getCost();
                                    operationsTemp.put(afterUpper, operation);
                                } catch (IllegalArgumentException e) {
                                    if (!afterUpper.equals("STOP")) {
                                        System.out.println("THE COMMAND IS INCORRECT! TRY AGAIN:");
                                        System.out.println();
                                    }
                                }
                            } while (!afterUpper.equals("STOP"));

                            int servicesCount = 0;
                            int parkingsCount = 0;
                            System.out.println("STATE OF THE SERVICES:");
                            for (int i = 0; i < service.carServiceSpots.length; i++) {
                                System.out.println(service.carServiceSpots[i]);
                                if (service.carServiceSpots[i].getStatus().equals("EMPTY")) {
                                    servicesCount++;
                                }
                            }
                            System.out.println("STATE OF THE PARKING SPACES:");
                            for (int i = 0; i < service.parkingSpaces.length; i++) {
                                System.out.println(service.parkingSpaces[i]);
                                if (service.parkingSpaces[i].getStatus().equals("EMPTY")) {
                                    parkingsCount++;
                                }
                            }
                            if (servicesCount == 0 && parkingsCount == 0) {
                                System.out.println("UNFORTUNATELY, BUT IT'S NOT CURRENTLY POSSIBLE TO EXECUTE THE ORDER.\nTRY AGAIN LATER.");
                            } else if (servicesCount == 0 && parkingsCount > 0) {
                                System.out.println("1 - YES.");
                                System.out.println("0 - NO.");
                                System.out.println("ALL SERVICE PLACES ARE CURRENTLY RENTED. DO YOU WANT TO SIGN UP FOR THE" +
                                        " RESERVE LIST?");
                                int yesOrNo;
                                do {
                                    yesOrNo = keyboard.nextInt();
                                } while (yesOrNo != 1 && yesOrNo != 0);

                                if (yesOrNo == 1) {
                                    System.out.println("STATE OF THE PARKING SPACES:");
                                    for (int i = 0; i < service.parkingSpaces.length; i++) {
                                        System.out.println(service.parkingSpaces[i]);
                                    }
                                    System.out.println("ENTER THE NUMBER OF THE PARKING PLACE TO WHICH YOUR ORDER WILL" +
                                            " BE RELATED TO:");
                                    int temp11 = keyboard.nextInt();

                                    System.out.println("DO YOU WANT TO EXTEND THE RENTAL OF THE PARKING SPACE AFTER THE END OF THE SERVICE?\n" +
                                            "IF SO, ENTER THE TIME PERIOD IN DAYS BEARING IN MIND THAT THE MAXIMUM ADDITIONAL PARKING PERIOD IS 14 DAYS,\n" +
                                            "IF NO, ENTER THE ZERO NUMBER:");
                                    int temp12 = keyboard.nextInt();

                                    ServiceOrder serviceOrder = new ServiceOrder(user, vehicle, amount, temp11, temp12, operationsTemp);
                                    service.addToServiceQueue(serviceOrder);
                                    service.parkingSpaces[temp11].setVehicle(vehicle);
                                    service.parkingSpaces[temp11].setSpotUsedDuringQueue(true);
                                    service.parkingSpaces[temp11].setStatus("TAKEN");
                                    service.parkingSpaces[temp11].peopleWithAccess.add(user);

                                    System.out.println();
                                    Tools.endOfTheCommandMessage();
                                } else {
                                    return;
                                }
                            } else {
                                System.out.println("STATE OF THE SERVICES:");
                                for (int i = 0; i < service.carServiceSpots.length; i++) {
                                    System.out.println(service.carServiceSpots[i]);
                                }
                                System.out.println("ENTER THE NUMBER OF THE SERVICE SPOT TO WHICH YOUR ORDER WILL BE RELATED TO:");
                                int temp13 = keyboard.nextInt();
                                System.out.println("STATE OF THE PARKING SPACES:");
                                for (int i = 0; i < service.parkingSpaces.length; i++) {
                                    System.out.println(service.parkingSpaces[i]);
                                }
                                System.out.println("ENTER THE NUMBER OF THE PARKING SPACE TO WHICH YOUR ORDER WILL BE RELATED TO:");
                                int temp14 = keyboard.nextInt();
                                System.out.println("DO YOU WANT TO EXTEND THE RENTAL OF THE PARKING SPACE AFTER THE END OF THE SERVICE?\n" +
                                        " IF SO, ENTER THE TIME PERIOD IN DAYS BEARING IN MIND THAT THE MAXIMUM ADDITIONAL PARKING PERIOD IS 14 DAYS,\n" +
                                        "IF NO, ENTER THE ZERO NUMBER:");
                                int temp15 = keyboard.nextInt();
                                ServiceOrder serviceOrder = new ServiceOrder(user, vehicle, amount, temp13, temp14, temp15, operationsTemp);
                                service.carServiceSpots[temp13].setServiceOrder(serviceOrder);
                                System.out.println();
                                Tools.endOfTheCommandMessage();
                            }
                        }
                    } else if (temp9 == 2) {



                    }
                }
            } else if (temp == 8) {




            } else if (temp == 9) {
                if (user == null) {
                    System.out.println("BEFORE MAKING THIS COMMAND YOU HAVE TO LOG IN!");
                    System.out.println();
                    Tools.endOfTheCommandMessage();
                } else {
                    System.out.println("1 - YES.");
                    System.out.println("0 - NO.");
                    System.out.println("DO YOU WANT TO SAVE THE CURRENT STATUS OF THE SERVICE TO FILES?");
                    int yesOrNo;
                    do {
                        yesOrNo = keyboard.nextInt();
                        if (yesOrNo != 1 && yesOrNo != 0) {
                            System.out.println("INCORRECT WRITING. TRY AGAIN!");
                        }
                    } while (yesOrNo != 1 && yesOrNo != 0);

                    if (yesOrNo == 1) {

                        Service.ConsumerWarehouse[] tempWarehouses = new Service.ConsumerWarehouse[service.consumerWarehouses.length];
                        for (int i = 0; i < service.consumerWarehouses.length; i++) {
                            tempWarehouses[i] = service.consumerWarehouses[i];
                        }

                        Arrays.sort(tempWarehouses);

                        File file1 = new File("warehouses.txt");
                        try {
                            PrintWriter writer1 = new PrintWriter(file1);


                            writer1.println("\n\tDATE: " + Time.myObj);
                            writer1.println("\n\tSTATUS OF CONSUMER WAREHOUSES:\n");

                            for (int i = 0; i < tempWarehouses.length; i++) {
                                writer1.println("\t" + tempWarehouses[i]);
                                writer1.println();
                            }

                            writer1.println("\n\tCONTENT OF THE CONSUMER WAREHOUSES:\n");

                            for (int i = 0; i < tempWarehouses.length; i++) {


                                Set<Map.Entry<String, Thing>> entrySet = service.consumerWarehouses[i].things.entrySet();

                                List<Map.Entry<String, Thing>> list = new ArrayList<>(entrySet);

                                list.sort((o1, o2) -> {
                                    if (o1.getValue().getThingVolume() > o2.getValue().getThingVolume()) {
                                        return -1;
                                    } else if (o1.getValue().getThingVolume() < o2.getValue().getThingVolume()) {
                                        return 1;
                                    } else {
                                        return o1.getValue().getThingType().compareTo(o2.getValue().getThingType());
                                    }
                                });

                                writer1.println("\tROOM NUMBER " + i + " CONTENT:");
                                if (list.isEmpty()) {
                                    writer1.println("\tNO CONTENT!");
                                } else {
                                    list.forEach(s -> writer1.println("\t" + s.getKey() + " " + s.getValue().getThingType() + " " + s.getValue().getThingVolume()));
                                }
                                writer1.println();
                            }

                            writer1.close();

                            File file2 = new File("services.txt");
                            PrintWriter writer2 = new PrintWriter(file2);

                            writer2.println("\n\tDATE: " + Time.myObj);
                            writer2.println();
                            for (int i = 0; i < service.carServiceSpots.length; i++) {
                                writer2.println("\t" + service.carServiceSpots[i]);
                                writer2.println();
                                writer2.println();
                            }

                            writer2.println("\tQUEUE OF THE SERVICE ORDERS (THE MORE AT THE TOP, THE HIGHER PRIORITY):");
                            writer2.println();

                            if (service.pendingServiceOrders.isEmpty()) {
                                writer2.println("\tTHE SERVICE ORDERS QUEUE IS EMPTY!");
                            } else {
                                service.pendingServiceOrders.forEach(s -> writer2.println("\tORDER NUMBER: " + s.getOrderNumber() + ", CLIENT NAME: "
                                        + s.getClientName() + ", CLIENT ID: " + s.getClientID() + ", VEHICLE REGISTRATION NUMBER: " + s.getVehicle().getRegistrationNumber() +
                                        ", PARKING SPOT: " + s.getParkingSetNumber() + "\n"));
                            }
                            writer2.println();
                            writer2.println();

                            writer2.println("\tQUEUE OF THE INDEPENDENT SERVICE ORDERS (THE MORE AT THE TOP, THE HIGHER PRIORITY):");
                            writer2.println();

                            if (service.pendingIndependentCarServiceSpotOrders.isEmpty()) {
                                writer2.println("\tTHE INDEPENDENT SERVICE ORDERS QUEUE IS EMPTY!");
                            } else {
                                service.pendingIndependentCarServiceSpotOrders.forEach(s -> writer2.println("\tORDER NUMBER: " + s.getOrderNumber() + ", CLIENT NAME: "
                                        + s.getClientName() + ", CLIENT ID: " + s.getClientID() + ", VEHICLE REGISTRATION NUMBER: " + s.getVehicle().getRegistrationNumber() +
                                        ", PARKING SPOT: " + s.getParkingSetNumber() + "\n"));
                            }

                            writer2.close();

                            System.out.println("THE DATA HAS BEEN SAVED CORRECTLY.");
                        } catch (FileNotFoundException e) {
                            System.out.println("I CAN'T FIND THE DESIRED FILES!");
                        }
                        System.out.println();
                        Tools.endOfTheCommandMessage();

                    } else if (yesOrNo == 2) {
                        return;
                    }
                }
            } else if (temp == 10) {
                System.out.println("ENTER YOUR FORENAME:");
                String forename = keyboard.nextLine();
                System.out.println("ENTER YOUR SURNAME:");
                String surname = keyboard.nextLine();
                System.out.println("ENTER YOUR ID (ELEVEN NUMBERS):");
                String ID = "";
                do {
                    ID = keyboard.nextLine();
                    if (ID.length() != 11) {
                        System.out.println("YOUR ID DOESN'T HAVE ELEVEN NUMBERS. TRY AGAIN!");
                    }
                } while (ID.length() != 11);
                System.out.println("ENTER YOUR CITY OF RESIDENCE:");
                String cityOfResidence = keyboard.nextLine();
                System.out.println("ENTER YOUR BIRTH YEAR:");
                int yearOfBirth = keyboard.nextInt();
                System.out.println("ENTER YOUR BIRTH MONTH:");
                int monthOfBirth = keyboard.nextInt();
                System.out.println("ENTER YOUR BIRTH DAY:");
                int dayOfBirth = keyboard.nextInt();

                Person person = new Person(forename, surname, ID, cityOfResidence, yearOfBirth, monthOfBirth, dayOfBirth);
                service.clients.put(ID, person);
                System.out.println("YOUR DATA WAS ADDED TO THE SERVICE DATABASE.");
                System.out.println();
                Tools.endOfTheCommandMessage();
            }

        } while (temp != -1);
    }

        public static Person logIn (String ID, Service service) {
            Person person = service.clients.get(ID);

            return person;
        }




}
