package GUI_Project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Person {

    private String forename;
    private String surname;
    private String ID;
    private String cityOfResidence;
    private LocalDate dateOfBirth;
    private LocalDate dateOfTheFirstRent;
    private double obligations;


    Map<String, Info> infos = new HashMap<>();
    private ArrayList<Date> commandHistory = new ArrayList<>();
    Map<String, TenantAlert> alerts = new HashMap<>();


    public Person(String forename, String surname, String ID, String cityOfResidence, int year, int month, int day) {

        this.forename = forename;
        this.surname = surname;
        this.ID = ID;
        this.cityOfResidence = cityOfResidence;
        this.dateOfBirth = LocalDate.of(year, month, day);
    }

    public void putDate(ServiceOrder serviceOrder, LocalDate tempDate) throws NeverRentException {
        if (commandHistory.size() == 0) {
            throw new NeverRentException("This person has never rented a room in this workshop before!");
        }
        commandHistory.add(new Date(serviceOrder, tempDate));
    }

    public String getForename() {
        return forename;
    }

    public String getSurname() {
        return surname;
    }

    public String getID() {
        return ID;
    }

    public void addCharge(double amount) {
        obligations += amount;
    }

    public void setDateOfTheFirstRent(LocalDate dateOfTheFirstRent) {
        this.dateOfTheFirstRent = dateOfTheFirstRent;
    }

    public LocalDate getDateOfTheFirstRent() throws NeverRentException {
        if (dateOfTheFirstRent == null) {
            throw new NeverRentException("THIS PERSON HAS NEVER RENTED ANY ROOM!");
        }

        return dateOfTheFirstRent;
    }

    public void reduceTheBalance(double amount) {
        obligations -= amount;
    }

    public double getObligations() {
        return obligations;
    }

    public void makeTenantAlert(String orderNumber, String roomCode , String forename, String surname, String ID, LocalDate endOfTheLease, double amountToPay) {
        alerts.put(orderNumber, new TenantAlert(orderNumber, roomCode, forename, surname, ID, endOfTheLease, amountToPay));
    }

    void deleteAlert(String orderNumber) {
        if (alerts.containsKey(orderNumber)) {
            alerts.remove(orderNumber);
        }
    }

    Map showAlerts() {
        return infos;
    }

    @Override
    public String toString() {
        return "Person{" +
                "forename='" + forename + '\'' +
                ", surname='" + surname + '\'' +
                ", ID='" + ID + '\'' +
                '}';
    }
}
