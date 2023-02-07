package GUI_Project;

import java.time.LocalDate;

public class Date {

    private String forename;
    private String surname;
    private String ID;
    private String orderNumber;
    private LocalDate date;

    public Date(ServiceOrder serviceOrder, LocalDate date) {
        this.forename = serviceOrder.client.getForename();
        this.surname = serviceOrder.client.getSurname();
        this.ID = serviceOrder.client.getID();
        this.orderNumber = serviceOrder.getOrderNumber();
        this.date = date;
    }

    @Override
    public String toString() {
        return "Date{" +
                "forename='" + forename + '\'' +
                ", surname='" + surname + '\'' +
                ", ID='" + ID + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", date=" + date +
                '}';
    }

}
