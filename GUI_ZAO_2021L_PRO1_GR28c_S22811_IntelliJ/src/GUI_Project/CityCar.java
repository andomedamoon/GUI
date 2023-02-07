package GUI_Project;

public class CityCar extends Vehicle {

    private String carType;
    private int doorsNumber;
    private int enginePower;
    private int displacement;
    private String gearbox;
    private String fuelType;
    private double CO2_Emissions;
    private String drive;
    private int bootCapacity;

    public CityCar(Person owner, double length, double width, double height, String colour, String registrationNumber,
                   String carType, int doorsNumber, int enginePower, int displacement, String gearbox, String fuelType,
                   double CO2_Emissions, String drive, int bootCapacity) {
        super(owner, length, width, height, colour, registrationNumber);
        this.carType = carType;
        this.doorsNumber = doorsNumber;
        this.enginePower = enginePower;
        this.displacement = displacement;
        this.gearbox = gearbox;
        this.fuelType = fuelType;
        this.CO2_Emissions = CO2_Emissions;
        this.drive = drive;
        this.bootCapacity = bootCapacity;
    }

    @Override
    public String toString() {
        return "CITY CAR[" +
                "CAR TYPE: " + carType +
                " REGISTRATION NUMBER: " + getRegistrationNumber() +
                " DOORS NUMBER: " + doorsNumber +
                " ENGINE POWER: " + enginePower +
                " DISPLACEMENT: " + displacement +
                " GEARBOX: " + gearbox +
                " FUEL TYPE: " + fuelType +
                " CO2 EMISSIONS: " + CO2_Emissions +
                " DRIVE: " + drive +
                " BOOT CAPACITY: " + bootCapacity +
                "]";
    }
}
