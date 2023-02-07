package GUI_Project;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Service {

    private double length;
    private double width;
    private double height;
    private double serviceCircuit;
    private double serviceVolume;
    private final int AMOUNT;
    static int orderNumberBuilder = 1000;

    Map<String, Person> clients = new HashMap<>();
    Queue<ServiceOrder> pendingServiceOrders = new LinkedList<>();
    Queue<IndependentCarServiceSpotOrder> pendingIndependentCarServiceSpotOrders = new LinkedList<>();

    CarServiceSpot[] carServiceSpots;
    ConsumerWarehouse[] consumerWarehouses;
    ParkingSpace[] parkingSpaces;
    ServiceWarehouse serviceWarehouse;

    Random rand = new Random();

    public Service(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.serviceCircuit = length * width;
        this.serviceVolume = length * width * height;
        this.AMOUNT = (int) ((0.7 * serviceCircuit) / 9);
        this.carServiceSpots = new CarServiceSpot[AMOUNT];
        this.consumerWarehouses = new ConsumerWarehouse[10];
        this.parkingSpaces = new ParkingSpace[10];
        for (int i = 0; i < carServiceSpots.length; i++) {
            carServiceSpots[i] = new CarServiceSpot(i);
        }
        for (int i = 0; i < consumerWarehouses.length; i++) {
            consumerWarehouses[i] = new ConsumerWarehouse(i);
        }
        for (int i = 0; i < parkingSpaces.length; i++) {
            parkingSpaces[i] = new ParkingSpace(i);
        }
        serviceWarehouse = new ServiceWarehouse();
    }

    public static String setOrderNumber() {
        ++orderNumberBuilder;
        String temp = "A" + orderNumberBuilder;
        return temp;
    }

    // Ten mechanizm zaczerpnąłem z internetu.
    // Link: https://stackoverflow.com/questions/3680637/generate-a-random-double-in-a-range/32808589
    public static double getRandomDoubleAboveAndUnder() {
        double min = 4;
        double max = 7;

        return ThreadLocalRandom.current().nextDouble(min, max);
    }

    public void showStatus() {
        for (int i = 0; i < carServiceSpots.length; i++) {
            System.out.println(carServiceSpots[i].toString());
        }
    }

    public double getServiceCircuit() {
        return serviceCircuit;
    }

    public void addToServiceQueue(ServiceOrder serviceOrder) {
        pendingServiceOrders.add(serviceOrder);

        System.out.println("YOUR ORDER HAS BEEN ADDED TO THE QUEUE AND THE VEHICLE TRANSPORTED TO A SPECIFIED " +
                "PARKING PLACE.");
    }

    public void addToIndependentServiceQueue(IndependentCarServiceSpotOrder independentCarServiceSpotOrder) {
        pendingIndependentCarServiceSpotOrders.add(independentCarServiceSpotOrder);

        System.out.println("YOUR ORDER HAS BEEN ADDED TO THE QUEUE AND THE VEHICLE TRANSPORTED TO A SPECIFIED " +
                "PARKING PLACE.");
    }


    public class ServiceWarehouse {

        private final double serviceWarehouseVolume = 0.3 * serviceVolume;
        private double serviceWarehouseVolumeTemp = serviceWarehouseVolume;
        Map<String, Thing> things = new HashMap<>();

        public void putThingIntoServiceWarehouse (Thing thing) {
            if ((serviceWarehouseVolumeTemp - thing.getThingVolume()) >= 0) {
                serviceWarehouseVolumeTemp -= thing.getThingVolume();
                things.put(thing.getThingIdentificator(), thing);
                System.out.println("THE ITEM HAS BEEN LOCATED IN THE WAREHOUSE.");
            } else {
                try {
                    throw new TooManyThingsException("SORRY, BUT THE WAREHOUSE IS FULL. TRY LATER!");
                } catch (TooManyThingsException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        public void putThingOutOfTheWarehouse(String identity_code) throws NoSuchElementException {
            if (things.containsKey(identity_code)) {
                Thing thing = things.get(identity_code);

                things.remove(identity_code);

                serviceWarehouseVolumeTemp += thing.getThingVolume();
            } else {
                throw new NoSuchElementException("THIS WAREHOUSE DOES NOT HAVE AN OBJECT WITH THE GIVEN" +
                        " IDENTIFICATION NUMBER!");
            }
        }

    }

    public class CarServiceSpot {

        private Person tenant = null;
        ServiceOrder serviceOrder = null;
        private double length = 4;
        private double width = 4;
        private double height = 5.5;
        private double surfaceArea = length * width * height;
        private String status = "EMPTY";
        private final int roomNumber;
        private LocalDate startOfTheLease;
        private int rentDuration;


        public CarServiceSpot(int roomNumber) {
            this.roomNumber = roomNumber;
        }

        public void setServiceOrder(ServiceOrder serviceOrder) {
            if (status.equals("TAKEN")) {
                System.out.println("THE PARKING SPOT IS CURRENTLY OCCUPIED!");
            } else {
                try {
                    serviceOrder.makeInfo();
                    this.serviceOrder = serviceOrder;
                    clients.put(serviceOrder.client.getID(), serviceOrder.client);
                    rent(serviceOrder.client, rand.nextInt(5) + 1);
                    ParkingSpaceOrder parkingSpaceOrder = new ParkingSpaceOrder(serviceOrder.client, serviceOrder.getParkingSetNumber());
                    parkingSpaces[serviceOrder.getParkingSetNumber()].setServiceOrder(serviceOrder);
                    parkingSpaces[serviceOrder.getParkingSetNumber()].rent(parkingSpaceOrder, serviceOrder.getParkingSpotRentDuration() + rentDuration);
                    parkingSpaces[serviceOrder.getParkingSetNumber()].vehicle = serviceOrder.getVehicle();
                    setStatus("TAKEN");
                    System.out.println("THE SERVICE ORDER HAS BEEN ACCEPTED.");
                } catch (ProblematicTenantException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        public void rent(Person person, int rentDuration) {
            this.tenant = person;
            this.startOfTheLease = Time.myObj;
            this.rentDuration = rentDuration;
        }

        public void checkIfReadyToSwap() {
            if (this.serviceOrder != null)
            if (Time.myObj.minusDays(rentDuration).isEqual(startOfTheLease.plusDays(rentDuration)) ||
                    Time.myObj.minusDays(rentDuration).isAfter(startOfTheLease.plusDays(rentDuration))) {
                parkingSpaces[this.serviceOrder.getParkingSetNumber()].readyToPickUp = true;
                this.tenant = null;
                this.startOfTheLease = null;
                this.rentDuration = 0;
                this.serviceOrder = null;
                this.status = "EMPTY";
            }
        }

        public void setFromQueue() {
            if (this.status.equals("EMPTY")) {
                ServiceOrder order = pendingServiceOrders.poll();
                if (order != null) {
                    order.setServiceRoomNumber(roomNumber);
                    setServiceOrder(order);
                }
            }
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Person getTenant() {
            return tenant;
        }

        public String getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return "ROOM NUMBER: " + roomNumber + "\nTENANT ID: " + tenant + "\nSERVICE DURATION: " + rentDuration + "\n" +
                    "ORDER: " + serviceOrder;
        }
    }

        public class IndependentCarService {

            private Person tenant = null;
            private IndependentCarServiceSpotOrder order;
            private double length = 4;
            private double width = 4;
            private double height = 5.5;
            private double surfaceArea = length * width * height;
            private String status = "EMPTY";
            private final int roomNumber;
            private LocalDate startOfTheLease;
            private int rentDuration;
            private LocalDate endOfTheLease;

            ArrayList<Person> peopleWithAccess = new ArrayList<>();

            public IndependentCarService(int roomNumber) {
                this.roomNumber = roomNumber;
            }

            public void rent(IndependentCarServiceSpotOrder order) {
                if (status.equals("TAKEN")) {
                    System.out.println("THE WAREHOUSE IS CURRENTLY OCCUPIED!");
                } else {
                    clients.put(order.client.getID(), order.client);
                    try {
                        order.makeInfo();
                        this.order = order;
                        this.tenant = order.client;
                        this.peopleWithAccess.add(order.client);
                        this.startOfTheLease = Time.myObj;
                        this.rentDuration = order.getRentDuration();
                        this.endOfTheLease = startOfTheLease.plusDays(order.getRentDuration());
                        this.status = "TAKEN";
                        System.out.println("THE WAREHOUSE HAS BEEN RENTED.");
                    } catch (ProblematicTenantException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }

            public void givePermission(Person person1, Person person2) {
                if (person1.equals(peopleWithAccess.get(0))) {
                    peopleWithAccess.add(person2);
                } else {
                    System.out.println("YOU ARE NOT AUTHORIZED TO GRANT AUTHORIZATIONS FOR THIS ROOM!");
                }
            }

        }

        public class ConsumerWarehouse implements Comparable<ConsumerWarehouse> {

            private Person tenant;
            private ConsumerWarehouseOrder order;
            private double length = (int) getRandomDoubleAboveAndUnder();
            private double width = (int) getRandomDoubleAboveAndUnder();
            private double height = 5.5;
            private String code = Tools.warehousesCounter();
            private int roomNumber;
            private String status = "EMPTY";
            private double consumerWarehouseVolume = length * width * height;
            private double consumerWarehouseVolumeTemp = consumerWarehouseVolume;
            private LocalDate startOfTheLease;
            private int rentDuration;
            private LocalDate endOfTheLease;

            Map<String, Thing> things = new HashMap<>();
            ArrayList<Person> peopleWithAccess = new ArrayList<>();


            public ConsumerWarehouse(int number) {
                this.roomNumber = number;
            }

            public void rent(ConsumerWarehouseOrder order) {
                if (status.equals("TAKEN")) {
                    System.out.println("THE WAREHOUSE IS CURRENTLY OCCUPIED!");
                } else {
                    clients.put(order.client.getID(), order.client);
                    try {
                        order.makeInfo();
                        this.order = order;
                        this.tenant = order.client;
                        this.peopleWithAccess.add(order.client);
                        this.startOfTheLease = Time.myObj;
                        this.rentDuration = order.getRentDuration();
                        this.endOfTheLease = startOfTheLease.plusDays(order.getRentDuration());
                        this.status = "TAKEN";
                        System.out.println("THE WAREHOUSE HAS BEEN RENTED.");
                    } catch (ProblematicTenantException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }

            public void checkRoom() {
                if (endOfTheLease != null && (Time.myObj.isEqual(endOfTheLease) || Time.myObj.isAfter(endOfTheLease))) {
                    tenant.makeTenantAlert(order.getOrderNumber(), code, tenant.getForename(), tenant.getSurname(), tenant.getID(), endOfTheLease, order.getCharge());
                }
            }

            public void checkIfItIsTimeToCleanUp() {
                if (this.order != null) {
                    if (Time.myObj.minusDays(30).isEqual(endOfTheLease) || Time.myObj.minusDays(30).isAfter(endOfTheLease)) {
                        this.tenant = null;
                        this.order = null;
                        this.status = "EMPTY";
                        this.startOfTheLease = null;
                        this.rentDuration = 0;
                        this.endOfTheLease = null;
                        this.things.clear();
                        this.peopleWithAccess.clear();
                    }
                }
            }

            public void putThingIntoWarehouse(Thing thing) throws NoPermissionException {
                if (peopleWithAccess.contains(thing.getOwner())) {
                    if ((consumerWarehouseVolumeTemp - thing.getThingVolume()) >= 0) {
                        consumerWarehouseVolumeTemp -= thing.getThingVolume();
                        things.put(thing.getThingIdentificator(), thing);
                    } else {
                        try {
                            throw new TooManyThingsException("REMOVE SOME OLD ITEMS TO INSERT A NEW ITEM!");
                        } catch (TooManyThingsException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } else {
                        throw new NoPermissionException("YOU ARE NOT AUTHORIZED TO OPEN THIS WAREHOUSE ROOM!\n" +
                                "YOUR ITEM CANNOT BE PLACED HERE!");
                }
            }

            public void putThingOutOfTheWarehouse(Person person, String identity_code) throws NoSuchElementException, NoPermissionException {
                if (peopleWithAccess.contains(person)) {
                    if (things.containsKey(identity_code)) {
                        Thing thing = things.get(identity_code);

                        things.remove(identity_code);

                        consumerWarehouseVolumeTemp += thing.getThingVolume();
                        System.out.println("THE ITEM HAS BEEN REMOVED FROM THIS STORAGE.");
                    } else {
                        throw new NoSuchElementException("THIS ROOM DOES NOT HAVE AN OBJECT WITH THE GIVEN" +
                                " IDENTIFICATION NUMBER!");
                    }
                } else {
                    throw new NoPermissionException("YOU ARE NOT AUTHORIZED TO OPEN THIS WAREHOUSE ROOM!");
                }
            }

            public void givePermission(Person person1, Person person2) {
                if (person1.equals(peopleWithAccess.get(0))) {
                    peopleWithAccess.add(person2);
                } else {
                    System.out.println("YOU ARE NOT AUTHORIZED TO GRANT AUTHORIZATIONS FOR THIS ROOM!");
                }
            }

            public Person getTenant() {
                return tenant;
            }

            public String getStatus() {
                return status;
            }

            public void showThings(Person person) throws NoPermissionException {
                if (peopleWithAccess.contains(person)) {
                    System.out.println(peopleWithAccess);
                } else {
                    throw new NoPermissionException("YOU ARE NOT AUTHORIZED TO OPEN THIS WAREHOUSE ROOM!");
                }
            }

            @Override
            public String toString() {
                return "ROOM NUMBER: " + roomNumber +
                        ", ROOM CODE:  " + code +
                        ", TENANT: " + tenant +
                        ", STATUS: " + status  +
                        ", VOLUME: " + consumerWarehouseVolume;
            }

            @Override
            public int compareTo(ConsumerWarehouse o) {
                if (consumerWarehouseVolume > o.consumerWarehouseVolume) {
                    return 1;
                } else if (consumerWarehouseVolume < o.consumerWarehouseVolume) {
                    return -1;
                } else {
                    if (roomNumber > o.roomNumber) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            }

        }

    class ParkingSpace {

        private Person tenant;
        private ServiceOrder serviceOrder;
        private ParkingSpaceOrder order;
        private Vehicle vehicle;
        private double length = (int) getRandomDoubleAboveAndUnder();
        private double width = (int) getRandomDoubleAboveAndUnder();
        private double height = 5.5;
        private String code = Tools.parkingSpaceCounter();
        private int roomNumber;
        private double ParkingSpaceVolume = length * width * height;
        private String status = "EMPTY";
        private LocalDate startOfTheLease;
        private int rentDuration;
        private LocalDate endOfTheLease;
        private boolean readyToPickUp = false;
        private boolean spotUsedDuringQueue = false;

        ArrayList<Person> peopleWithAccess = new ArrayList<>();

        public ParkingSpace(int number) {
            this.roomNumber = number;
        }

        public void rent(ParkingSpaceOrder order, int rentDuration) {
            if (status.equals("TAKEN")) {
                System.out.println("THE PARKING SPOT IS CURRENTLY OCCUPIED!");
            } else {
                clients.put(order.client.getID(), order.client);
                try {
                    if (this.serviceOrder == null) {
                        order.makeInfo();
                    }
                    this.tenant = order.client;
                    this.order = order;
                    this.peopleWithAccess.add(order.client);
                    this.startOfTheLease = Time.myObj;
                    this.rentDuration = rentDuration;
                    this.endOfTheLease = startOfTheLease.plusDays(rentDuration);
                    this.status = "TAKEN";
                    System.out.println("THE PARKING SPACE HAS BEEN RENTED.");
                } catch (ProblematicTenantException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        public void checkRoom() {
            if (endOfTheLease != null && (Time.myObj.isEqual(endOfTheLease) || Time.myObj.isAfter(endOfTheLease))) {
                tenant.makeTenantAlert(order.getOrderNumber(), code, tenant.getForename(), tenant.getSurname(), tenant.getID(), endOfTheLease, order.getCharge());
            }
        }

        public void checkIfItIsTimeToCleanUp() {
            if (this.order != null) {
                if (Time.myObj.minusDays(30).isEqual(endOfTheLease) || Time.myObj.minusDays(30).isAfter(endOfTheLease)) {
                    this.tenant = null;
                    this.serviceOrder = null;
                    this.order = null;
                    this.vehicle = null;
                    this.status = "EMPTY";
                    this.startOfTheLease = null;
                    this.rentDuration = 0;
                    this.endOfTheLease = null;
                    this.readyToPickUp = false;
                    this.spotUsedDuringQueue = false;
                    this.peopleWithAccess.clear();
                }
            }
        }

        public void putTheCarIn(Person person, Vehicle vehicle) {
            if (!peopleWithAccess.contains(person)) {
                try {
                    throw new NoPermissionException("YOU ARE NOT AUTHORIZED TO OPEN THIS PARKING ROOM!");
                } catch (NoPermissionException e) {
                    System.out.println(e.getMessage());
                }
            } else if (this.vehicle != null) {
                System.out.println("THERE IS A VEHICLE ALREADY ON THIS PARKING PLACE. TO INSERT NEW, REMOVE THE OLD!");
            } else {
                this.vehicle = vehicle;
                this.readyToPickUp = true;
                System.out.println("THE CAR HAS BEEN PARKED.");
            }
        }

        public void pullTheCarOut(Person person) {
            if (peopleWithAccess.contains(person)) {
            if (vehicle == null) {
                System.out.println("THERE IS NO VEHICLE AT THIS PARKING PLACE!");
            } else if ((serviceOrder == null && readyToPickUp) || (serviceOrder != null && readyToPickUp)) {
                vehicle = null;
                System.out.println("THE VEHICLE LEAVED THE PARKING PLACE.");
            } else {
                System.out.println("THE VEHICLE IS DURING SERVICE, SO IT CANNOT BE RECEIVED!");
            }
        } else {
            try {
                throw new NoPermissionException("YOU ARE NOT AUTHORIZED TO OPEN THIS PARKING ROOM!");
            } catch (NoPermissionException e) {
                System.out.println(e.getMessage());
            }
        }
        }

        public void givePermission(Person person1, Person person2) {
            if (person1.equals(peopleWithAccess.get(0))) {
                peopleWithAccess.add(person2);
            } else {
                System.out.println("YOU ARE NOT AUTHORIZED TO GRANT AUTHORIZATIONS FOR THIS ROOM!");
            }
        }

        public Map getAlerts() {
            return serviceOrder.client.alerts;
        }

        public Person getTenant() {
            return tenant;
        }

        public Vehicle getVehicle() {
            return vehicle;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setServiceOrder(ServiceOrder serviceOrder) {
            this.serviceOrder = serviceOrder;
        }

        public void setSpotUsedDuringQueue(boolean spotUsedDuringQueue) {
            this.spotUsedDuringQueue = spotUsedDuringQueue;
        }

        public void setVehicle(Vehicle vehicle) {
            this.vehicle = vehicle;
        }

        @Override
        public String toString() {
            return "ParkingSpace{" +
                    "code='" + code + '\'' +
                    ", roomNumber=" + roomNumber +
                    ", ParkingSpaceVolume=" + ParkingSpaceVolume +
                    ", status='" + status + '\'' +
                    '}';
        }
    }

}


