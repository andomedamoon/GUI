package GUI_Project;

import java.time.LocalDate;

import static GUI_Project.Service.setOrderNumber;

public class ConsumerWarehouseOrder extends Order {

    private final String orderNumber = setOrderNumber();
    private double charge;
    private int rentDuration;
    private final int RENTAL_RATE = 25;
    private final String orderType = "RENTING A WAREHOUSE";
    private int warehouseNumber;

    public ConsumerWarehouseOrder(Person client, int rentDuration, int warehouseNumber) {
        super(client);
        this.charge = rentDuration * RENTAL_RATE;
        this.rentDuration = rentDuration;
        this.warehouseNumber = warehouseNumber;

        client.addCharge(charge);
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

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public int getWarehouseNumber() {
        return warehouseNumber;
    }

    public void setWarehouseNumber(int warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
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

