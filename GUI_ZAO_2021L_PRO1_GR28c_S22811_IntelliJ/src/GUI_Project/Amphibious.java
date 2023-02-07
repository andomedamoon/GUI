package GUI_Project;

public class Amphibious extends Vehicle {

    private boolean band;
    private int tyresNumber;
    private int doorsNumber;
    private int waterPumpCapacity;
    private double maximumSpeedInTheWater;


    public Amphibious(Person owner, double length, double width, double height, String colour,
                      String registrationNumber, boolean band, int tyresNumber, int doorsNumber, int waterPumpCapacity, double maximumSpeedInTheWater) {
        super(owner, length, width, height, colour, registrationNumber);
        this.band = band;
        this.tyresNumber = tyresNumber;
        this.doorsNumber = doorsNumber;
        this.waterPumpCapacity = waterPumpCapacity;
        this.maximumSpeedInTheWater = maximumSpeedInTheWater;
    }


    @Override
    public String toString() {
        return "AMPHIBIOUS[REGISTRATION NUMBER: "  + getRegistrationNumber() +
                "BAND: " + band +
                " TYRES NUMBER: " + tyresNumber +
                " DOORS NUMBER: " + doorsNumber +
                " WATER PUMP CAPACITY (LITERS): " + waterPumpCapacity +
                " MAXIMUM SPEED IN WATER: " + maximumSpeedInTheWater +
                "]";
    }
}
