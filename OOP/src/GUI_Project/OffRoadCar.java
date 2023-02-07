package GUI_Project;

public class OffRoadCar extends Vehicle {


    private String engineType;
    private boolean doesHaveTransferCase;
    private int displacement;
    private String drivingGear;
    private String drive;
    private String fuelType;
    private int bootCapacity;

    public OffRoadCar(Person owner, double length, double width, double height, String colour,
                      String registrationNumber, String engineType, boolean doesHaveTransferCase, int displacement,
                      String drivingGear, String drive, String fuelType, int bootCapacity) {
        super(owner, length, width, height, colour, registrationNumber);
        this.engineType = engineType;
        this.doesHaveTransferCase = doesHaveTransferCase;
        this.displacement = displacement;
        this.drivingGear = drivingGear;
        this.drive = drive;
        this.fuelType = fuelType;
        this.bootCapacity = bootCapacity;
    }

    @Override
    public String toString() {
        return "OFF ROAD CAR[" +
                "ENGINE TYPE: " + engineType +
                " DOES HAVE A TRANSFER CASE: " + doesHaveTransferCase +
                " DISPLACEMENT: " + displacement +
                " DRIVING GEAR: " + drivingGear +
                " DRIVE: " + drive +
                " FUEL TYPE: " + fuelType +
                " BOOT CAPACITY: " + bootCapacity +
                "]";
    }
}
