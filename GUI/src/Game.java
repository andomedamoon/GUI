import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

public class Game extends JFrame implements ActionListener {

    public static int points;
    public static int days;
    public static int sumOfMinuses;
    Timer time;
    MainThread mainThread;
    JButton germany;
    JButton france;
    JButton sweden;
    JButton norway;
    JButton finland;
    JButton italy;
    JButton spain;
    JButton portugal;
    JButton uk;
    JButton netherlands;
    JButton luxembourg;
    JButton belgium;
    JButton austria;
    JButton denmark;
    JButton switzerland;
    JButton ireland;
    JLabel label = new JLabel();

    final Germany GER;
    final France FRA;
    final Sweden SWE;
    final Norway NOR;
    final Finland FIN;
    final Italy ITA;
    final Spain SPA;
    final Portugal POR;
    final UK UK_TEMP;
    final Netherlands NETH;
    final Luxembourg LUX;
    final Belgium BEL;
    final Austria AUS;
    final Denmark DEN;
    final Switzerland SWITZ;
    final Ireland IRE;

    JLabel ukAirport = new JLabel();
    JLabel spainAirport = new JLabel();
    JLabel franceAirport = new JLabel();
    JLabel germanyAirport = new JLabel();
    JLabel norwayAirport = new JLabel();
    JLabel swedenAirport = new JLabel();
    JLabel finlandAirport = new JLabel();
    JLabel italyAirport = new JLabel();
    JLabel portugalAirport = new JLabel();
    JLabel irelandAirport = new JLabel();

    JLabel spainPort = new JLabel();
    JLabel irelandPort = new JLabel();
    JLabel ukPort = new JLabel();
    JLabel francePort = new JLabel();
    JLabel norwayPort = new JLabel();
    JLabel denmarkPort = new JLabel();
    JLabel finlandPort = new JLabel();
    JLabel italyPort = new JLabel();

    JLabel germanyTrainStation = new JLabel();
    JLabel netherlandsTrainStation = new JLabel();
    JLabel italyTrainStation = new JLabel();
    JLabel spainTrainStation = new JLabel();
    JLabel franceTrainStation = new JLabel();
    JLabel switzerlandTrainStation = new JLabel();
    JLabel austriaTrainStation = new JLabel();
    JLabel ukTrainStation = new JLabel();

    JLabel infections = new JLabel();
    JLabel infections2 = new JLabel();
    JLabel recovered = new JLabel();
    JLabel recovered2 = new JLabel();
    JLabel points_label = new JLabel();
    JLabel points_label2 = new JLabel();
    JLabel days_label = new JLabel();
    JLabel days_label2 = new JLabel();



    Game() {
        super("GAME");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(650, 250, 690, 800);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        validate();

        mainThread = new MainThread();

        GER = new Germany();
        AUS = new Austria();
        BEL = new Belgium();
        DEN = new Denmark();
        FIN = new Finland();
        FRA = new France();
        IRE = new Ireland();
        ITA = new Italy();
        LUX = new Luxembourg();
        NETH = new Netherlands();
        NOR = new Norway();
        POR = new Portugal();
        SPA = new Spain();
        SWE = new Sweden();
        SWITZ = new Switzerland();
        UK_TEMP = new UK();


        label.setIcon(new ImageIcon("west_europe_map.png"));
        add(label);



        ukAirport.setIcon(new ImageIcon("flight.png"));
        label.add(ukAirport).setBounds(304, 415, 20, 20);
        irelandAirport.setIcon(new ImageIcon("flight.png"));
        label.add(irelandAirport).setBounds(250, 396, 20, 20);
        spainAirport.setIcon(new ImageIcon("flight.png"));
        label.add(spainAirport).setBounds(280, 575, 20, 20);
        franceAirport.setIcon(new ImageIcon("flight.png"));
        label.add(franceAirport).setBounds(340, 465, 20, 20);
        germanyAirport.setIcon(new ImageIcon("flight.png"));
        label.add(germanyAirport).setBounds(435, 405, 20, 20);
        norwayAirport.setIcon(new ImageIcon("flight.png"));
        label.add(norwayAirport).setBounds(415, 276, 20, 20);
        swedenAirport.setIcon(new ImageIcon("flight.png"));
        label.add(swedenAirport).setBounds(485, 285, 20, 20);
        finlandAirport.setIcon(new ImageIcon("flight.png"));
        label.add(finlandAirport).setBounds(555, 262, 20, 20);
        italyAirport.setIcon(new ImageIcon("flight.png"));
        label.add(italyAirport).setBounds(430, 550, 20, 20);
        portugalAirport.setIcon(new ImageIcon("flight.png"));
        label.add(portugalAirport).setBounds(226, 595, 20, 20);

        spainPort.setIcon(new ImageIcon("sailboat.png"));
        label.add(spainPort).setBounds(320, 565, 20, 20);
        irelandPort.setIcon(new ImageIcon("sailboat.png"));
        label.add(irelandPort).setBounds(215, 380, 20, 20);
        ukPort.setIcon(new ImageIcon("sailboat.png"));
        label.add(ukPort).setBounds(290, 330, 20, 20);
        francePort.setIcon(new ImageIcon("sailboat.png"));
        label.add(francePort).setBounds(290, 455, 20, 20);
        norwayPort.setIcon(new ImageIcon("sailboat.png"));
        label.add(norwayPort).setBounds(375, 220, 20, 20);
        italyPort.setIcon(new ImageIcon("sailboat.png"));
        label.add(italyPort).setBounds(478, 575, 20, 20);
        finlandPort.setIcon(new ImageIcon("sailboat.png"));
        label.add(finlandPort).setBounds(555, 165, 20, 20);
        denmarkPort.setIcon(new ImageIcon("sailboat.png"));
        label.add(denmarkPort).setBounds(415, 325, 20, 20);

        germanyTrainStation.setIcon(new ImageIcon("train_map.png"));
        label.add(germanyTrainStation).setBounds(415, 450, 20, 20);
        franceTrainStation.setIcon(new ImageIcon("train_map.png"));
        label.add(franceTrainStation).setBounds(325, 510, 20, 20);
        italyTrainStation.setIcon(new ImageIcon("train_map.png"));
        label.add(italyTrainStation).setBounds(420, 515, 20, 20);
        netherlandsTrainStation.setIcon(new ImageIcon("train_map.png"));
        label.add(netherlandsTrainStation).setBounds(370, 405, 20, 20);
        switzerlandTrainStation.setIcon(new ImageIcon("train_map.png"));
        label.add(switzerlandTrainStation).setBounds(390, 485, 20, 20);
        spainTrainStation.setIcon(new ImageIcon("train_map.png"));
        label.add(spainTrainStation).setBounds(260, 545, 20, 20);
        austriaTrainStation.setIcon(new ImageIcon("train_map.png"));
        label.add(austriaTrainStation).setBounds(455, 475, 20, 20);
        ukTrainStation.setIcon(new ImageIcon("train_map.png"));
        label.add(ukTrainStation).setBounds(285, 400, 20, 20);


        infections.setText("INFECTIONS: ");
        infections2.setText("0");

        label.add(infections).setBounds( 265, 60, 90, 30);
        label.add(infections2).setBounds(365, 60,  90, 30);

        recovered.setText("RECOVERED: ");
        recovered2.setText("0");

        label.add(recovered).setBounds(265, 90, 90, 30);
        label.add(recovered2).setBounds(365, 90, 90, 30);

        points_label.setText("POINTS: ");
        points_label2.setText("0");

        label.add(points_label).setBounds(55, 40, 90, 30);
        label.add(points_label2).setBounds(155, 40, 90, 30);

        days_label.setText("DAY: ");
        days_label2.setText("0");

        label.add(days_label).setBounds(465, 40, 90, 30);
        label.add(days_label2).setBounds(565,40, 90, 30);

        ActionListener counter = new generalInfectionsUpdater();

        time = new Timer(1000, counter);
        time.start();


        germany = new JButton();
        germany.setText("GERMANY");
        germany.setBounds(4, 640, 90, 30);
        label.add(germany);
        germany.addActionListener(this);

        france = new JButton();
        france.setText("FRANCE");
        france.setBounds(100, 640, 90, 30);
        label.add(france);
        france.addActionListener(this);

        sweden = new JButton();
        sweden.setText("SWEDEN");
        sweden.setBounds(196, 640, 90, 30);
        label.add(sweden);
        sweden.addActionListener(this);

        norway = new JButton();
        norway.setText("NORWAY");
        norway.setBounds(292, 640, 90, 30);
        label.add(norway);
        norway.addActionListener(this);

        finland = new JButton();
        finland.setText("FINLAND");
        finland.setBounds(388, 640, 90, 30);
        label.add(finland);
        finland.addActionListener(this);

        italy = new JButton();
        italy.setText("ITALY");
        italy.setBounds(484, 640, 90, 30);
        label.add(italy);
        italy.addActionListener(this);

        spain = new JButton();
        spain.setText("SPAIN");
        spain.setBounds(580, 640, 90, 30);
        label.add(spain);
        spain.addActionListener(this);

        switzerland = new JButton();
        switzerland.setText("SWITZERLAND");
        switzerland.setBounds(24, 674, 120, 30);
        label.add(switzerland);
        switzerland.addActionListener(this);

        luxembourg = new JButton();
        luxembourg.setText("LUXEMBOURG");
        luxembourg.setBounds(150, 674, 120, 30);
        label.add(luxembourg);
        luxembourg.addActionListener(this);

        portugal = new JButton();
        portugal.setText("PORTUGAL");
        portugal.setBounds(276, 674, 120, 30);
        label.add(portugal);
        portugal.addActionListener(this);

        netherlands = new JButton();
        netherlands.setText("NETHERLANDS");
        netherlands.setBounds(402, 674, 120, 30);
        label.add(netherlands);
        netherlands.addActionListener(this);

        denmark = new JButton();
        denmark.setText("DENMARK");
        denmark.setBounds(528, 674, 120, 30);
        label.add(denmark);
        denmark.addActionListener(this);

        uk = new JButton();
        uk.setText("UNITED KINGDOM");
        uk.setBounds(114, 708, 145, 30);
        label.add(uk);
        uk.addActionListener(this);

        belgium = new JButton();
        belgium.setText("BELGIUM");
        belgium.setBounds(265, 708, 90, 30);
        label.add(belgium);
        belgium.addActionListener(this);

        ireland = new JButton();
        ireland.setText("IRELAND");
        ireland.setBounds(362, 708, 90, 30);
        label.add(ireland);
        ireland.addActionListener(this);

        austria = new JButton();
        austria.setText("AUSTRIA");
        austria.setBounds(458, 708, 90, 30);
        label.add(austria);
        austria.addActionListener(this);



    }

    class generalInfectionsUpdater implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            int sumOfInfections = AustriaInfections.getInfections() + BelgiumInfections.getInfections() +
                    DenmarkInfections.getInfections() + FinlandInfections.getInfections() +
                    FranceInfections.getInfections() + GermanyInfections.getInfections() +
                    IrelandInfections.getInfections() + ItalyInfections.getInfections() +
                    LuxembourgInfections.getInfections() + NetherlandsInfections.getInfections() +
                    NorwayInfections.getInfections() + PortugalInfections.getInfections() +
                    SpainInfections.getInfections() + SwedenInfections.getInfections() +
                    SwitzerlandInfections.getInfections() + UKInfections.getInfections();

            int sumOfRecovered = AustriaInfections.recovered + BelgiumInfections.recovered +
                    DenmarkInfections.recovered + FinlandInfections.recovered + FranceInfections.recovered +
                    GermanyInfections.recovered + IrelandInfections.recovered + ItalyInfections.recovered +
                    LuxembourgInfections.recovered + NetherlandsInfections.recovered + NorwayInfections.recovered
                    + PortugalInfections.recovered + SpainInfections.recovered + SwedenInfections.recovered +
                    SwitzerlandInfections.recovered + UKInfections.recovered;

            infections2.setText(String.valueOf(sumOfInfections));
            recovered2.setText(String.valueOf(sumOfRecovered));

            points = sumOfRecovered - sumOfMinuses;
            points_label2.setText(String.valueOf(points));
            days++;

            int sumOfPopulation = Austria.POPULATION + Belgium.POPULATION + Denmark.POPULATION + Finland.POPULATION +
                    France.POPULATION + Germany.POPULATION + Ireland.POPULATION + Italy.POPULATION +
                    Luxembourg.POPULATION + Netherlands.POPULATION + Norway.POPULATION + Portugal.POPULATION +
                    Spain.POPULATION + Sweden.POPULATION + Switzerland.POPULATION + UK.POPULATION;

            days_label2.setText(String.valueOf(days));

            String name = "";



            if (sumOfPopulation == sumOfInfections) {
                try {
                    mainThread.wait();
                    time.wait();
                    name = JOptionPane.showInputDialog("IT'S OVER!\nENTER YOUR NAME, PLEASE:");
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }

                Player lastPlayer = new Player(name, points, days);
                Database database = new Database();
                database.addPlayer(lastPlayer);

                File file = new File("high_score.txt");
                try {
                    database.saveToFile(file);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                close();
                MainFrame mainFrame = new MainFrame();
            }

        }

    }


    //Tę metodę zaczerpnąłem z tego filmu: https://www.youtube.com/watch?v=hFv2Uay0qj0
    public void close() {
        WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton)e.getSource();

        if (clicked == germany) {
            this.toBack();
            GER.setVisible(true);
            GER.toFront();



            GER.setImageListener(number -> {
                if (number == 1) {
                    germanyAirport.setIcon(new ImageIcon("no_flight.png"));
                } else if (number == 3) {
                    germanyTrainStation.setIcon(new ImageIcon("no_sailboat.png"));
                }
            });
        } else if (clicked == austria) {
            this.toBack();
            AUS.setVisible(true);
            AUS.toFront();

            AUS.setImageListener(number -> {
                if (number == 3) {
                austriaTrainStation.setIcon(new ImageIcon("no_train_map.png"));
                }
            });
        } else if (clicked == belgium) {
            this.toBack();
            BEL.setVisible(true);
            BEL.toFront();
        } else if (clicked == denmark) {
            this.toBack();
            DEN.setVisible(true);
            DEN.toFront();

            DEN.setImageListener(number -> {
                if (number == 2) {
                    denmarkPort.setIcon(new ImageIcon("no_sailboat.png"));
                }
            });
        } else if (clicked == finland) {
            this.toBack();
            FIN.setVisible(true);
            FIN.toFront();

            FIN.setImageListener(number -> {
                if (number == 1) {
                    finlandAirport.setIcon(new ImageIcon("no_flight.png"));
                } else if (number == 2) {
                    finlandPort.setIcon(new ImageIcon("no_sailboat.png"));
                }
            });
        } else if (clicked == france) {
            this.toBack();
            FRA.setVisible(true);
            FRA.toFront();

            FRA.setImageListener(number -> {
                if (number == 1) {
                    franceAirport.setIcon(new ImageIcon("no_flight.png"));
                } else if (number == 2) {
                    francePort.setIcon(new ImageIcon("no_sailboat.png"));
                } else if (number == 3) {
                    franceTrainStation.setIcon(new ImageIcon("no_train_map.png"));
                }
            });
        } else if (clicked == ireland) {
            this.toBack();
            IRE.setVisible(true);
            IRE.toFront();

            IRE.setImageListener(number -> {
                if (number == 1) {
                    irelandAirport.setIcon(new ImageIcon("no_flight.png"));
                } else if (number == 2) {
                    irelandPort.setIcon(new ImageIcon("no_sailboat.png"));
                }
            });
        } else if (clicked == italy) {
            this.toBack();
            ITA.setVisible(true);
            ITA.toFront();

            ITA.setImageListener(number -> {
                if (number == 1) {
                    italyAirport.setIcon(new ImageIcon("no_flight.png"));
                } else if (number == 2) {
                    italyPort.setIcon(new ImageIcon("no_sailboat.png"));
                } else if (number == 3) {
                    italyTrainStation.setIcon(new ImageIcon("no_train_map.png"));
                }
            });
        } else if (clicked == luxembourg) {
            this.toBack();
            LUX.setVisible(true);
            LUX.toFront();
        } else if (clicked == netherlands) {
            this.toBack();
            NETH.setVisible(true);
            NETH.toFront();

            NETH.setImageListener(number -> {
                if (number == 3) {
                netherlandsTrainStation.setIcon(new ImageIcon("no_train_map.png"));
                }
            });
        } else if (clicked == norway) {
            this.toBack();
            NOR.setVisible(true);
            NOR.toFront();

            NOR.setImageListener(number -> {
                if (number == 1) {
                    norwayAirport.setIcon(new ImageIcon("no_flight.png"));
                } else if (number == 2) {
                    norwayPort.setIcon(new ImageIcon("no_sailboat.png"));
                }
            });
        } else if (clicked == portugal) {
            this.toBack();
            POR.setVisible(true);
            POR.toFront();

            POR.setImageListener(number -> {
                if (number == 1) {
                    portugalAirport.setIcon(new ImageIcon("no_flight.png"));
                }
            });
        } else if (clicked == spain) {
            this.toBack();
            SPA.setVisible(true);
            SPA.toFront();

            SPA.setImageListener(number -> {
                if (number == 1) {
                    spainAirport.setIcon(new ImageIcon("no_flight.png"));
                } else if (number == 2) {
                    spainPort.setIcon(new ImageIcon("no_sailboat.png"));
                } else if (number == 3) {
                    spainTrainStation.setIcon(new ImageIcon("no_train_map.png"));
                }
            });
        } else if (clicked == sweden) {
            this.toBack();
            SWE.setVisible(true);
            SWE.toFront();

            SWE.setImageListener(number -> {
                if (number == 1) {
                    swedenAirport.setIcon(new ImageIcon("no_flight.png"));
                }
            });
        } else if (clicked == switzerland) {
            this.toBack();
            SWITZ.setVisible(true);
            SWITZ.toFront();

            SWITZ.setImageListener(number -> {
                if (number == 3) {
                    switzerlandTrainStation.setIcon(new ImageIcon("no_train_map.png"));
                }
            });
        } else if (clicked == uk) {
            this.toBack();
            UK_TEMP.setVisible(true);
            UK_TEMP.toFront();

            UK_TEMP.setImageListener(number -> {
                if (number == 1) {
                    ukAirport.setIcon(new ImageIcon("no_flight.png"));
                } else if (number == 2) {
                    ukPort.setIcon(new ImageIcon("no_sailboat.png"));
                } else if (number == 3) {
                    ukTrainStation.setIcon(new ImageIcon("no_train_map.png"));
                }
            });
        }
    }
}
