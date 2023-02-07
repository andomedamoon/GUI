package GUI_Project;

import java.time.LocalDate;
import java.util.HashMap;

import static GUI_Project.Service.setOrderNumber;

public class ServiceOrder extends Order {

    private Vehicle vehicle;
    private final String orderNumber = setOrderNumber();
    private double charge;
    private final int CHARGE_FOR_MECHANICS = 100;
    private String orderType;
    private int serviceRoomNumber;
    private int parkingSetNumber;
    private int parkingSpotRentDuration = 3;

    HashMap setOfOperationsDuringService;

    public ServiceOrder(Person client, Vehicle vehicle, double charge, int serviceRoomNumber, int parkingSetNumber,
                        int parkingSpotRentDuration, HashMap setOfOperationsDuringService) {
        super(client);
        this.vehicle = vehicle;
        this.charge = charge + CHARGE_FOR_MECHANICS + (parkingSpotRentDuration * ParkingSpaceOrder.getRENTAL_RATE());
        this.serviceRoomNumber = serviceRoomNumber;
        this.parkingSetNumber = parkingSetNumber;
        this.parkingSpotRentDuration += parkingSpotRentDuration;
        this.setOfOperationsDuringService = setOfOperationsDuringService;
        if (this.parkingSpotRentDuration > 3) {
            this.orderType = "SERVICE WITH EXTENDED PARKING.";
        } else {
            this.orderType = "SERVICE WITH STANDARD PARKING.";
        }
        client.addCharge(this.charge);
    }

    public ServiceOrder(Person client, Vehicle vehicle, double charge, int parkingSetNumber, int parkingSpotRentDuration,
                        HashMap setOfOperationsDuringService) {
        super(client);
        this.vehicle = vehicle;
        this.charge = charge + parkingSpotRentDuration * ParkingSpaceOrder.getRENTAL_RATE();
        this.parkingSetNumber = parkingSetNumber;
        this.parkingSpotRentDuration += parkingSpotRentDuration;
        this.setOfOperationsDuringService = setOfOperationsDuringService;

        client.addCharge(this.charge);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public double getCharge() {
        return charge;
    }

    public int getServiceRoomNumber() {
        return serviceRoomNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setServiceRoomNumber(int serviceRoomNumber) {
        this.serviceRoomNumber = serviceRoomNumber;
    }

    public int getParkingSetNumber() {
        return parkingSetNumber;
    }

    public int getParkingSpotRentDuration() {
        return parkingSpotRentDuration;
    }

    public String getOrderType() {
        return orderType;
    }

    void makeInfo() throws ProblematicTenantException {
        if (client.infos.size() > 3) {
            throw new ProblematicTenantException("PERSON: " + client.getForename().toUpperCase() + " " + client.getSurname().toUpperCase() +
                    " HAS ALREADY RENT THE FOLLOWING ROOMS: " + client.showAlerts());
        } else {
            client.infos.put(getOrderNumber(), new Info(client.getForename().toUpperCase(), client.getSurname().toUpperCase(), client.getID(), getOrderNumber(),
                    getCharge()));
            try {
                LocalDate onlyToShowException = client.getDateOfTheFirstRent();
            } catch (NeverRentException e) {
                System.out.println(e.getMessage());
                client.setDateOfTheFirstRent(Time.myObj);
            }

        }
    }

    @Override
    String getClientName() {
        return client.getForename() + " " + client.getSurname();
    }

    @Override
    String getClientID() {
        return client.getID();
    }


    @Override
    public String toString() {
        return "ORDER NUMBER: " + orderNumber + "\n ORDER TYPE: " +
                orderType + "\n VEHICLE: " + vehicle + "\n RESERVED PARKING SPACE: " +
                parkingSetNumber + "\n SCOPE OF SERVICES PROVIDED: " + setOfOperationsDuringService.keySet();
    }

}
