package GUI_Project;

public class Thing implements Comparable<Thing> {

    private Person owner;
    private String thingType;
    private final String thingIdentificator = Tools.thingsCounter();
    private double length;
    private double width;
    private double height;
    private double weight;
    private double ThingVolume = length * width * height;

    public Thing(Person owner, String thingType, double length, double width, double height, double weight) {
        this.owner = owner;
        this.thingType = thingType;
        this.length = length;
        this.width = width;
        this.height = height;
        this.weight = weight;
    }

    public String getThingIdentificator() {
        return thingIdentificator;
    }

    public Person getOwner() {
        return owner;
    }

    public double getThingVolume() {
        return ThingVolume;
    }

    public String getThingType() {
        return thingType;
    }

    @Override
    public int compareTo(Thing o) {
        if (ThingVolume > o.ThingVolume) {
            return -1;
        } else if (ThingVolume < o.ThingVolume) {
            return 1;
        }
        return this.thingType.compareTo(o.thingType);
    }
}
