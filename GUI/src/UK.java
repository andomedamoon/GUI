import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UK extends JFrame implements ActionListener {

    Timer time;
    public static final int POPULATION = 66_600_000;

    private ImageListener imageListener;

    JButton backButton;

    JButton closeHairDressers;
    JButton closeGyms;
    JButton closeAirports;
    JButton closePorts;
    JButton dutyOfMasks;
    JButton socialDistancing;
    JButton closeRestaurants;
    JButton closeTheBorders;
    JButton testing;
    JButton closeTheTheaters;
    JButton closeTheStadiums;
    JButton closeTheRailwayStation;

    JLabel label = new JLabel();

    JLabel infections = new JLabel();
    JLabel infections2 = new JLabel();
    JLabel recovered = new JLabel();
    JLabel recovered2 = new JLabel();

    public UK() {

        setBounds(650, 150, 690, 800);
        setVisible(false);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        backButton = new JButton();
        backButton.setText("BACK");
        backButton.setBounds(470, 640, 150, 60);
        backButton.addActionListener(this);
        add(backButton);

        closeHairDressers = new JButton();
        closeHairDressers.setBounds(470, 210, 100, 100);
        closeHairDressers.setIcon(new ImageIcon("hair_dresser.png"));

        closeHairDressers.addActionListener(this);
        add(closeHairDressers);

        closeGyms = new JButton();
        closeGyms.setBounds(470, 330, 100, 100);
        closeGyms.setIcon(new ImageIcon("gym.png"));
        closeGyms.addActionListener(this);
        add(closeGyms);

        closeAirports = new JButton();
        closeAirports.setBounds(470, 450, 100, 100);
        closeAirports.setIcon(new ImageIcon("airplane.png"));
        closeAirports.addActionListener(this);
        add(closeAirports);

        closePorts = new JButton();
        closePorts.setBounds(350, 450, 100, 100);
        closePorts.setIcon(new ImageIcon("cruise.png"));
        closePorts.addActionListener(this);
        add(closePorts);

        dutyOfMasks = new JButton();
        dutyOfMasks.setBounds(350, 330, 100, 100);
        dutyOfMasks.setIcon(new ImageIcon("masks.png"));
        dutyOfMasks.addActionListener(this);
        add(dutyOfMasks);

        socialDistancing = new JButton();
        socialDistancing.setBounds(350, 210, 100, 100);
        socialDistancing.setIcon(new ImageIcon("social_distancing.png"));
        socialDistancing.addActionListener(this);
        add(socialDistancing);

        closeRestaurants = new JButton();
        closeRestaurants.setBounds(230, 210, 100, 100);
        closeRestaurants.setIcon(new ImageIcon("restaurants.png"));
        closeRestaurants.addActionListener(this);
        add(closeRestaurants);

        closeTheBorders = new JButton();
        closeTheBorders.setBounds(110, 210, 100, 100);
        closeTheBorders.setIcon(new ImageIcon("border.png"));
        closeTheBorders.addActionListener(this);
        add(closeTheBorders);

        testing = new JButton();
        testing.setBounds(110, 330, 100, 100);
        testing.setIcon(new ImageIcon("testing.png"));
        testing.addActionListener(this);
        add(testing);

        closeTheTheaters = new JButton();
        closeTheTheaters.setBounds(110, 450, 100, 100);
        closeTheTheaters.setIcon(new ImageIcon("cinema.png"));
        closeTheTheaters.addActionListener(this);
        add(closeTheTheaters);

        closeTheStadiums = new JButton();
        closeTheStadiums.setBounds(230, 330, 100, 100);
        closeTheStadiums.setIcon(new ImageIcon("stadium.png"));
        closeTheStadiums.addActionListener(this);
        add(closeTheStadiums);

        closeTheRailwayStation = new JButton();
        closeTheRailwayStation.setBounds(230, 450, 100, 100);
        closeTheRailwayStation.setIcon(new ImageIcon("train.png"));
        closeTheRailwayStation.addActionListener(this);
        add(closeTheRailwayStation);



        infections.setText("INFECTIONS: ");
        infections2.setText("0");
        recovered.setText("RECOVERED: ");
        recovered2.setText("0");
        label.add(infections).setBounds(250, 100,  90, 30);
        label.add(infections2).setBounds(350, 100, 90, 30);
        label.add(recovered).setBounds(250, 140, 90, 30);
        label.add(recovered2).setBounds(350, 140, 90, 30);
        add(label);

        ActionListener counter = new ukInfectionsUpdater();

        time = new Timer(1000, counter);
        time.start();

    }

    public void setImageListener(ImageListener imageListener) {
        this.imageListener = imageListener;
    }

    class ukInfectionsUpdater implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            infections2.setText(String.valueOf(UKInfections.getInfections()));
            recovered2.setText(String.valueOf(UKInfections.recovered));

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton)e.getSource();

        if (clicked == backButton) {
            this.toBack();
            setVisible(false);
        } else if (clicked == closeAirports) {
            UKInfections.divideMultiple(4);
            imageListener.imageUpdate(1);
            closeAirports.setEnabled(false);
            Game.sumOfMinuses += 300000;
        } else if (clicked == closeHairDressers) {
            UKInfections.changeAmount(400);
            closeHairDressers.setEnabled(false);
            Game.sumOfMinuses += 30000;
        } else if (clicked == closePorts) {
            UKInfections.divideMultiple(3);
            imageListener.imageUpdate(2);
            closePorts.setEnabled(false);
            Game.sumOfMinuses += 100000;
        } else if (clicked == closeGyms) {
            UKInfections.changeAmount(200);
            closeGyms.setEnabled(false);
            Game.sumOfMinuses += 45000;
        } else if (clicked == dutyOfMasks) {
            UKInfections.divideMultiple(3);
            dutyOfMasks.setEnabled(false);
            Game.sumOfMinuses += 300000;
        } else if (clicked == socialDistancing) {
            UKInfections.divideMultiple(5);
            socialDistancing.setEnabled(false);
            Game.sumOfMinuses += 500000;
        } else if (clicked == closeRestaurants) {
            UKInfections.divideMultiple(2);
            closeRestaurants.setEnabled(false);
            Game.sumOfMinuses += 75000;
        } else if (clicked == closeTheBorders) {
            UKInfections.changeAmount(1000);
            closeTheBorders.setEnabled(false);
            Game.sumOfMinuses += 400000;
        } else if (clicked == testing) {
            UKInfections.divideMultiple(6);
            testing.setEnabled(false);
            Game.sumOfMinuses += 200000;
        } else if (clicked == closeTheTheaters) {
            UKInfections.changeAmount(150);
            closeTheTheaters.setEnabled(false);
            Game.sumOfMinuses += 50000;
        } else if (clicked == closeTheStadiums) {
            UKInfections.changeAmount(750);
            closeTheStadiums.setEnabled(false);
            Game.sumOfMinuses += 300000;
        } else if (clicked == closeTheRailwayStation) {
            UKInfections.divideMultiple(3);
            imageListener.imageUpdate(3);
            closeTheRailwayStation.setEnabled(false);
            Game.sumOfMinuses += 175000;
        }
    }
}
