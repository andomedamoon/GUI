package GUI_Project;

import java.time.LocalDate;

import static GUI_Project.Service.setOrderNumber;

public class IndependentCarServiceSpotOrder extends Order {

    private Vehicle vehicle;
    private final String orderNumber = setOrderNumber();
    private double charge;
    private int rentDuration;
    private final int RENTAL_RATE = 35;
    private final String orderType = "RENTING AN INDEPENDENT CUSTOMER SERVICE";
    private int parkingSetNumber;
    private int independentCarServiceSpotNumber;


    public IndependentCarServiceSpotOrder(Person client, Vehicle vehicle, double charge, int rentDuration, int parkingSetNumber, int independentCarServiceSpotNumber) {
        super(client);
        this.vehicle = vehicle;
        this.charge = charge;
        this.rentDuration = rentDuration;
        this.parkingSetNumber = parkingSetNumber;
        this.independentCarServiceSpotNumber = independentCarServiceSpotNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public double getCharge() {
        return charge;
    }

    public int getRentDuration() {
        return rentDuration;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getParkingSetNumber() {
        return parkingSetNumber;
    }

    @Override
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

}


