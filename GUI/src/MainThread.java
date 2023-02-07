import java.util.Random;

public class MainThread extends Thread {


    Random random = new Random();
    public boolean austria;
    public boolean belgium;
    public boolean denmark;
    public boolean finland;
    public boolean france;
    public boolean germany;
    public boolean ireland;
    public boolean italy;
    public boolean luxembourg;
    public boolean netherlands;
    public boolean norway;
    public boolean portugal;
    public boolean spain;
    public boolean sweden;
    public boolean switzerland;
    public boolean uk;

    public MainThread() {
        start();
    }

    @Override
    public void run() {
        try {
            while (!austria || !belgium || !denmark || !finland || !france || !germany || !ireland || !italy || !luxembourg
            || !netherlands || !norway || !portugal || !spain || !sweden || !switzerland || !uk) {
                Thread.sleep(2000);
                int countryTemp = random.nextInt(16) + 1;
                if (countryTemp == 1 && !austria) {
                    AustriaInfections austriaInfections = new AustriaInfections();
                    austria = true;
                } else if (countryTemp == 2 && !belgium) {
                    BelgiumInfections belgiumInfections = new BelgiumInfections();
                    belgium = true;
                } else if (countryTemp == 3 && !denmark) {
                    DenmarkInfections denmarkInfections = new DenmarkInfections();
                    denmark = true;
                } else if (countryTemp == 4 && !finland) {
                    FinlandInfections finlandInfections = new FinlandInfections();
                    finland = true;
                } else if (countryTemp == 5 && !france) {
                    FranceInfections franceInfections = new FranceInfections();
                    france = true;
                } else if (countryTemp == 6 && !germany) {
                    GermanyInfections germanyInfections = new GermanyInfections();
                    germany = true;
                } else if (countryTemp == 7 && !ireland) {
                    IrelandInfections irelandInfections = new IrelandInfections();
                    ireland = true;
                } else if (countryTemp == 8 && !italy) {
                    ItalyInfections italyInfections = new ItalyInfections();
                    italy = true;
                } else if (countryTemp == 9 && !luxembourg) {
                    LuxembourgInfections luxembourgInfections = new LuxembourgInfections();
                    luxembourg = true;
                } else if (countryTemp == 10 && !netherlands) {
                    NetherlandsInfections netherlandsInfections = new NetherlandsInfections();
                    netherlands = true;
                } else if (countryTemp == 11 && !norway) {
                    NorwayInfections norwayInfections = new NorwayInfections();
                    norway = true;
                } else if (countryTemp == 12 && !portugal) {
                    PortugalInfections portugalInfections = new PortugalInfections();
                    portugal = true;
                } else if (countryTemp == 13 && !spain) {
                    SpainInfections spainInfections = new SpainInfections();
                    spain = true;
                } else if (countryTemp == 14 && !sweden) {
                    SwedenInfections swedenInfections = new SwedenInfections();
                    sweden = true;
                } else if (countryTemp == 15 && !switzerland) {
                    SwitzerlandInfections switzerlandInfections = new SwitzerlandInfections();
                    switzerland = true;
                } else if (countryTemp == 16 && !uk) {
                    UKInfections ukInfections = new UKInfections();
                    uk = true;
                }
                Thread.sleep(80000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
