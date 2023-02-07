package GUI_Project;

public class Motorcycle extends Vehicle {

    private String typeOfMotorcycle;
    private String engineType;
    private int displacement;
    private String gearBox;
    private boolean doesHaveATrolley;


    public Motorcycle(Person owner, double length, double width, double height, String colour,
                      String registrationNumber, String typeOfMotorcycle, String engineType, int displacement, String gearBox, boolean doesHaveATrolley) {
        super(owner, length, width, height, colour, registrationNumber);
        this.typeOfMotorcycle = typeOfMotorcycle;
        this.engineType = engineType;
        this.displacement = displacement;
        this.gearBox = gearBox;
        this.doesHaveATrolley = doesHaveATrolley;
    }

    @Override
    public String toString() {
        return "MOTORCYCLE[" +
                "TYPE OF MOTORCYCLE: " + typeOfMotorcycle +
                " REGISTRATION NUMBER: " + getRegistrationNumber() +
                " ENGINE TYPE: " + engineType +
                " DISPLACEMENT: " + displacement +
                " GEAR BOX: " + gearBox +
                " DOES HAVE A TROLLEY: " + doesHaveATrolley +
                "]";
    }
}
