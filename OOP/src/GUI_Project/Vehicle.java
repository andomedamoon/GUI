package GUI_Project;

public abstract class Vehicle {

    Person owner;
    private double length;
    private double width;
    private double height;
    private String colour;
    private String registrationNumber;
    private double surfaceArea = length * width * height;

    public Vehicle(Person owner, double length, double width, double height, String colour, String registrationNumber) {
        this.owner = owner;
        this.length = length;
        this.width = width;
        this.height = height;
        this.colour = colour;
        this.registrationNumber = registrationNumber;
    }

    public double getSurfaceArea() {
        return surfaceArea;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }
}
