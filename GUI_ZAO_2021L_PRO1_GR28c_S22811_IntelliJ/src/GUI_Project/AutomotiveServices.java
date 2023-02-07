package GUI_Project;

public enum AutomotiveServices {

    AXA1("BATTERY REPLACEMENT", 210),
    BXA2("REPLACEMENT OF FILTERS", 140),
    CXA3("OIL CHANGE", 110),
    DXA4("BODYWORK REPAIR", 400),
    EXA5("CHECKING AND CHANGING THE BRAKE FLUID", 300),
    FXA6("CAR WASHING AND WAXING", 180),
    GXA7("TECHNICAL TESTING OF THE VEHICLE", 100),
    HXA8("FILLING THE SHOCK ABSORBERS", 160),
    IXA9("FILLING THE AIR CONDITIONING", 235),
    JXA10("GLASS REPLACEMENT", 170),
    KXA11("LIGHTS REPLACEMENT", 130),
    LXA12("TYRE CHANGE", 70),
    MXA13("BODYWORK REPLACEMENT", 800),
    NXA14("REPLACEMENT OF SHOCK ABSORBERS", 220),
    OXA15("REPLACEMENT OF THE SILENCER", 60),
    PXA16("COMPREHENSIVE OVERVIEW", 500)
    ;

    private final String description;
    private final double cost;


    AutomotiveServices(String description, double cost) {
        this.description = description;
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public double getCost() {
        return cost;
    }
}
