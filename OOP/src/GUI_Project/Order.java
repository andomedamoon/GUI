package GUI_Project;

public abstract class Order {

    Person client;

    public Order(Person client) {
        this.client = client;
    }

    abstract void makeInfo() throws ProblematicTenantException;

    abstract String getClientName();

    abstract String getClientID();
}
