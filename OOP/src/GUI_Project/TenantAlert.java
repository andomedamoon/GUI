package GUI_Project;

import java.time.LocalDate;

public class TenantAlert {

    private String orderNumber;
    private String roomCode;
    private String forename;
    private String surname;
    private String ID;
    private LocalDate endOfTheLease;
    private double charge;

    public TenantAlert(String orderNumber, String roomCode, String forename, String surname, String ID, LocalDate endOfTheLease, double charge) {
        this.orderNumber = orderNumber;
        this.roomCode = roomCode;
        this.forename = forename;
        this.surname = surname;
        this.ID = ID;
        this.endOfTheLease = endOfTheLease;
        this.charge = charge;
    }

    @Override
    public String toString() {
        return "TenantAlert{" +
                "orderNumber='" + orderNumber + '\'' +
                ", roomCode='" + roomCode + '\'' +
                ", forename='" + forename + '\'' +
                ", surname='" + surname + '\'' +
                ", ID='" + ID + '\'' +
                ", endOfTheLease=" + endOfTheLease +
                ", charge=" + charge +
                '}';
    }
}
