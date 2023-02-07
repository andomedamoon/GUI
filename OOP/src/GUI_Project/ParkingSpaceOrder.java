package GUI_Project;

import java.time.LocalDate;

import static GUI_Project.Service.setOrderNumber;

public class ParkingSpaceOrder extends Order {

    private final String orderNumber = setOrderNumber();
    private double charge;
    private final static int RENTAL_RATE = 15;
    private final String orderType = "PARKING A VEHICLE";
    private int parkingSpotNumber;

    public ParkingSpaceOrder(Person client, int parkingSpotNumber) {
        super(client);
        this.parkingSpotNumber = parkingSpotNumber;
    }

    public ParkingSpaceOrder(Person client, int rentDuration, int parkingSpotNumber) {
        super(client);
        this.charge = rentDuration * RENTAL_RATE;
        this.parkingSpotNumber = parkingSpotNumber;

        client.addCharge(this.charge);
    }

    public static int getRENTAL_RATE() {
        return RENTAL_RATE;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public double getCharge() {
        return charge;
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
