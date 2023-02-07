package GUI_Project;

public class Info {

    private String forename;
    private String surname;
    private String ID;
    private String orderNumber;
    private double charge;

    public Info(String forename, String surname, String ID, String orderNumber, double charge) {
        this.forename = forename;
        this.surname = surname;
        this.ID = ID;
        this.orderNumber = orderNumber;
        this.charge = charge;
    }

    @Override
    public String toString() {
        return "Info{" +
                "forename='" + forename + '\'' +
                ", surname='" + surname + '\'' +
                ", ID='" + ID + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", charge=" + charge +
                '}';
    }
}

