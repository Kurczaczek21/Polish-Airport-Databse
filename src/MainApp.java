import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public class MainApp {
    private static final Logger logger = Logger.getLogger(MainApp.class);       //TODO krk arrivals both columnes delay ==0 ? hours?
    JFrame frame = new JFrame("Poland Airports Statistics");
    JPanel panelCont = new JPanel();
    CardLayout cl = new CardLayout();
    String choosenAirport;

    public MainApp() throws IOException {
        Color mainColor = Color.decode("#5CB3FF");     //#08298A #0174DF
        panelCont.setLayout(cl);

        JPanel helloPanel = new JPanel();
        helloPanel.setBounds(0,0,1000,600);
        helloPanel.setBackground(mainColor);
        helloPanel.setLayout(null);

        JLabel txtLabel = new JLabel();
        txtLabel.setText("Welcome to polish airports database");
        txtLabel.setFont(new Font("Impact", Font.BOLD, 50));     // SansSerif

        JLabel label = new JLabel();
        ImageIcon image2 = new ImageIcon("project_data/images/krkairp.jpg");
        label.setIcon(image2);

        JLabel txtLabel2 = new JLabel("Select startup option:");
        txtLabel2.setFont(new Font("SansSerif", Font.BOLD, 25));



        JButton updateAllButton = new JButton("Update all airports");
        updateAllButton.setBounds(50,40,200,50);
        updateAllButton.setFocusable(false);
        updateAllButton.setFont(new Font("SansSerif", Font.PLAIN, 16));

        JButton startProgramButton = new JButton("Start program without updating data");
        startProgramButton.setBounds(350,40,300,50);
        startProgramButton.setFocusable(false);
        startProgramButton.setFont(new Font("SansSerif", Font.PLAIN, 16));

        JButton goToPanelSpecificButton = new JButton("Update specific airports");
        goToPanelSpecificButton.setBounds(750,40,210,50);
        goToPanelSpecificButton.setFocusable(false);
        goToPanelSpecificButton.setFont(new Font("SansSerif", Font.PLAIN, 16));

        JLabel buttonLabel = new JLabel();
        buttonLabel.add(updateAllButton);
        buttonLabel.add(startProgramButton);
        buttonLabel.add(goToPanelSpecificButton);

        helloPanel.add(txtLabel);
        helloPanel.add(label);
        helloPanel.add(txtLabel2);
        helloPanel.add(txtLabel2);
        helloPanel.add(buttonLabel);

        txtLabel.setLocation(90,0);
        txtLabel.setSize(1000,60);

        label.setLocation(90,60);
        label.setSize(900,400);

        txtLabel2.setLocation(370,425);
        txtLabel2.setSize(1000,100);

        buttonLabel.setLocation(0,460);
        buttonLabel.setSize(1000,100);

        // Panel for downloading selected data via checkboxes

        JPanel selectionPanel = new JPanel();
        selectionPanel.setBounds(0,0,1000,600);
        selectionPanel.setBackground(mainColor);
        selectionPanel.setLayout(null);

        JLabel txtLabel3 = new JLabel("Select airports to update:");
        txtLabel3.setFont(new Font("Impact", Font.BOLD, 50));

        JCheckBox checkBox1 = new JCheckBox();
        checkBox1.setText("Krakow");
        checkBox1.setBounds(100,50,200,50);
        checkBox1.setFocusable(false);
        checkBox1.setFont(new Font("SansSerif", Font.PLAIN, 16));

        JCheckBox checkBox2 = new JCheckBox();
        checkBox2.setText("Warszawa");
        checkBox2.setBounds(400,50,200,50);
        checkBox2.setFocusable(false);
        checkBox2.setFont(new Font("SansSerif", Font.PLAIN, 16));

        JCheckBox checkBox3 = new JCheckBox();
        checkBox3.setText("Katowice");
        checkBox3.setBounds(700,50,200,50);
        checkBox3.setFocusable(false);
        checkBox3.setFont(new Font("SansSerif", Font.PLAIN, 16));

        JCheckBox checkBox4 = new JCheckBox();
        checkBox4.setText("Modlin");
        checkBox4.setBounds(100,150,200,50);
        checkBox4.setFocusable(false);
        checkBox4.setFont(new Font("SansSerif", Font.PLAIN, 16));

        JCheckBox checkBox5 = new JCheckBox();
        checkBox5.setText("Gdansk");
        checkBox5.setBounds(400,150,200,50);
        checkBox5.setFocusable(false);
        checkBox5.setFont(new Font("SansSerif", Font.PLAIN, 16));

        JCheckBox checkBox6 = new JCheckBox();
        checkBox6.setText("Lublin");
        checkBox6.setBounds(700,150,200,50);
        checkBox6.setFocusable(false);
        checkBox6.setFont(new Font("SansSerif", Font.PLAIN, 16));

        JCheckBox checkBox7 = new JCheckBox();
        checkBox7.setText("Bydgoszcz");
        checkBox7.setBounds(100,250,200,50);
        checkBox7.setFocusable(false);
        checkBox7.setFont(new Font("SansSerif", Font.PLAIN, 16));

        JCheckBox checkBox8 = new JCheckBox();
        checkBox8.setText("Lodz");
        checkBox8.setBounds(400,250,200,50);
        checkBox8.setFocusable(false);
        checkBox8.setFont(new Font("SansSerif", Font.PLAIN, 16));

        JCheckBox checkBox9 = new JCheckBox();
        checkBox9.setText("Olsztyn");
        checkBox9.setBounds(700,250,200,50);
        checkBox9.setFocusable(false);
        checkBox9.setFont(new Font("SansSerif", Font.PLAIN, 16));

        JCheckBox checkBox10 = new JCheckBox();
        checkBox10.setText("Rzeszow");
        checkBox10.setBounds(100,350,200,50);
        checkBox10.setFocusable(false);
        checkBox10.setFont(new Font("SansSerif", Font.PLAIN, 16));

        JCheckBox checkBox11 = new JCheckBox();
        checkBox11.setText("Poznan");
        checkBox11.setBounds(400,350,200,50);
        checkBox11.setFocusable(false);
        checkBox11.setFont(new Font("SansSerif", Font.PLAIN, 16));

        JButton selectAll = new JButton();
        selectAll.setText("Select all");
        selectAll.setBounds(700,350,200,50);
        selectAll.setFocusable(false);
        selectAll.setFont(new Font("SansSerif", Font.BOLD, 16));
        selectAll.setBackground(Color.GREEN);

        JButton backButton = new JButton("Back");
        backButton.setBounds(200,450,200,50);
        backButton.setFocusable(false);
        backButton.setFont(new Font("SansSerif", Font.PLAIN, 16));

        JButton downloadSelectedButton = new JButton("Start update");
        downloadSelectedButton.setBounds(600,450,200,50);
        downloadSelectedButton.setFocusable(false);
        downloadSelectedButton.setFont(new Font("SansSerif", Font.BOLD, 16));

        JLabel boxLabel = new JLabel();
        boxLabel.add(checkBox1);
        boxLabel.add(checkBox2);
        boxLabel.add(checkBox3);
        boxLabel.add(checkBox4);
        boxLabel.add(checkBox5);
        boxLabel.add(checkBox6);
        boxLabel.add(checkBox7);
        boxLabel.add(checkBox8);
        boxLabel.add(checkBox9);
        boxLabel.add(checkBox10);
        boxLabel.add(checkBox11);
        boxLabel.add(selectAll);
        boxLabel.add(backButton);
        boxLabel.add(downloadSelectedButton);


        selectionPanel.add(txtLabel3);
        selectionPanel.add(boxLabel);

        txtLabel3.setLocation(90,0);
        txtLabel3.setSize(1000,60);

        boxLabel.setLocation(0,60);
        boxLabel.setSize(1000,540);

        // MAIN PROGRAM PANEL
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(0,0,1000,600);
        mainPanel.setBackground(mainColor);
        mainPanel.setLayout(null);

        JLabel txtLabel4 = new JLabel("Select airport or default option");
        txtLabel4.setFont(new Font("Impact", Font.BOLD, 40));

        BufferedImage myPicture = ImageIO.read(new File("project_data/images/polska4.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));

        JButton krakow = new JButton(); //KRK
        krakow.setBounds(270,395,20,20);
        krakow.setFocusable(false);
        krakow.setFont(new Font("SansSerif", Font.PLAIN, 16));
        krakow.setBackground(Color.RED);

        JButton katowice = new JButton(); //kat
        katowice.setBounds(220,375,20,20);
        katowice.setFocusable(false);
        katowice.setFont(new Font("SansSerif", Font.PLAIN, 16));
        katowice.setBackground(Color.RED);

        JButton rzeszow = new JButton(); // rzeszow
        rzeszow.setBounds(375,400,20,20);
        rzeszow.setFocusable(false);
        rzeszow.setFont(new Font("SansSerif", Font.PLAIN, 16));
        rzeszow.setBackground(Color.RED);

        JButton lublin = new JButton(); // lublin
        lublin.setBounds(395,305,20,20);
        lublin.setFocusable(false);
        lublin.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lublin.setBackground(Color.RED);

        JButton lodz = new JButton(); // lodz
        lodz.setBounds(250,265,20,20);
        lodz.setFocusable(false);
        lodz.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lodz.setBackground(Color.RED);

        JButton warszawa = new JButton(); // waw
        warszawa.setBounds(332,222,20,20);
        warszawa.setFocusable(false);
        warszawa.setFont(new Font("SansSerif", Font.PLAIN, 16));
        warszawa.setBackground(Color.RED);

        JButton modlin = new JButton(); // modlin
        modlin.setBounds(300,190,20,20);
        modlin.setFocusable(false);
        modlin.setFont(new Font("SansSerif", Font.PLAIN, 16));
        modlin.setBackground(Color.RED);

        JButton poznan = new JButton(); // poznan
        poznan.setBounds(120,210,20,20);
        poznan.setFocusable(false);
        poznan.setFont(new Font("SansSerif", Font.PLAIN, 16));
        poznan.setBackground(Color.RED);

        JButton bydgoszcz = new JButton(); // bydgoszcz
        bydgoszcz.setBounds(195,150,20,20);
        bydgoszcz.setFocusable(false);
        bydgoszcz.setFont(new Font("SansSerif", Font.PLAIN, 16));
        bydgoszcz.setBackground(Color.RED);

        JButton olsztyn = new JButton(); // olsztyn
        olsztyn.setBounds(315,90,20,20);
        olsztyn.setFocusable(false);
        olsztyn.setFont(new Font("SansSerif", Font.PLAIN, 16));
        olsztyn.setBackground(Color.RED);

        JButton gdansk = new JButton(); // gdn
        gdansk.setBounds(215,40,20,20);
        gdansk.setFocusable(false);
        gdansk.setFont(new Font("SansSerif", Font.PLAIN, 16));
        gdansk.setBackground(Color.RED);

        picLabel.add(krakow);
        picLabel.add(katowice);
        picLabel.add(rzeszow);
        picLabel.add(lublin);
        picLabel.add(lodz);
        picLabel.add(warszawa);
        picLabel.add(modlin);
        picLabel.add(poznan);
        picLabel.add(bydgoszcz);
        picLabel.add(olsztyn);
        picLabel.add(gdansk);

        JLabel menuLabel = new JLabel();

        JButton airportsArrivals = new JButton("Daily arrivals by airport");
        airportsArrivals.setBounds(60,40,380,60);
        airportsArrivals.setFocusable(false);
        airportsArrivals.setFont(new Font("SansSerif", Font.PLAIN, 22));

        JButton airportsDepartures = new JButton("Daily departures by airport");
        airportsDepartures.setBounds(60,120,380,60);
        airportsDepartures.setFocusable(false);
        airportsDepartures.setFont(new Font("SansSerif", Font.PLAIN, 22));

        JButton airportsDeparturesDelay = new JButton("Average departure delay by airport");
        airportsDeparturesDelay.setBounds(60,200,380,60);
        airportsDeparturesDelay.setFocusable(false);
        airportsDeparturesDelay.setFont(new Font("SansSerif", Font.PLAIN, 22));

        JButton airportsArrivalsDelay = new JButton("Average arrival delay by airport");
        airportsArrivalsDelay.setBounds(60,280,380,60);
        airportsArrivalsDelay.setFocusable(false);
        airportsArrivalsDelay.setFont(new Font("SansSerif", Font.PLAIN, 22));

        JButton backButt2 = new JButton("Back");
        backButt2.setBounds(180,360,140,60);
        backButt2.setFocusable(false);
        backButt2.setFont(new Font("SansSerif", Font.PLAIN, 22));

        menuLabel.add(airportsArrivals);
        menuLabel.add(airportsDepartures);
        menuLabel.add(airportsDeparturesDelay);
        menuLabel.add(airportsArrivalsDelay);
        menuLabel.add(backButt2);


        mainPanel.add(txtLabel4);
        mainPanel.add(picLabel);
        mainPanel.add(menuLabel);

        txtLabel4.setLocation(90,0);
        txtLabel4.setSize(1000,50);

        menuLabel.setLocation(0,60);
        menuLabel.setSize(500,500);

        picLabel.setLocation(450,60);
        picLabel.setSize(500,500);

        // AIRPORT PANEL
        JPanel airportPanel = new JPanel();
        airportPanel.setBounds(0,0,1000,600);
        airportPanel.setBackground(mainColor);
        airportPanel.setLayout(null);

        JLabel txtLabel5 = new JLabel(choosenAirport);
        txtLabel5.setFont(new Font("Impact", Font.BOLD, 40));

        JLabel txtLabel6 = new JLabel("Select graph options");
        txtLabel6.setFont(new Font("Impact", Font.BOLD, 30));

        JLabel contentLabel = new JLabel();

        JCheckBox depBox = new JCheckBox();
        depBox.setText("Departures");
        depBox.setBounds(250,10,150,50);
        depBox.setFocusable(false);
        depBox.setFont(new Font("SansSerif", Font.PLAIN, 19));
        depBox.setSelected(true);

        JCheckBox arrBox = new JCheckBox();
        arrBox.setText("Arrivals");
        arrBox.setBounds(550,10,150,50);
        arrBox.setFocusable(false);
        arrBox.setFont(new Font("SansSerif", Font.PLAIN, 19));

        JButton backButt3 = new JButton("Back");
        backButt3.setBounds(70,400,140,60);
        backButt3.setFocusable(false);
        backButt3.setFont(new Font("SansSerif", Font.PLAIN, 22));   //TODO bold andd grey BACK butts

        JButton dayOfWeekButt = new JButton("Number of flights by day of week");
        dayOfWeekButt.setBounds(50,150,400,60);
        dayOfWeekButt.setFocusable(false);
        dayOfWeekButt.setFont(new Font("SansSerif", Font.PLAIN, 20));

        JButton airlineFlights = new JButton("Number of flights by airline");
        airlineFlights.setBounds(500,150,400,60);
        airlineFlights.setFocusable(false);
        airlineFlights.setFont(new Font("SansSerif", Font.PLAIN, 20));

        JButton delayByDayOfWeek = new JButton("Average delay by day of week");
        delayByDayOfWeek.setBounds(50,260,400,60);
        delayByDayOfWeek.setFocusable(false);
        delayByDayOfWeek.setFont(new Font("SansSerif", Font.PLAIN, 20));

        JButton delayByAirline = new JButton("Average delay by airline");
        delayByAirline.setBounds(500,260,400,60);
        delayByAirline.setFocusable(false);
        delayByAirline.setFont(new Font("SansSerif", Font.PLAIN, 20));

        contentLabel.add(depBox);
        contentLabel.add(arrBox);
        contentLabel.add(backButt3);
        contentLabel.add(dayOfWeekButt);
        contentLabel.add(airlineFlights);
        contentLabel.add(delayByDayOfWeek);
        contentLabel.add(delayByAirline);


        airportPanel.add(txtLabel5);
        airportPanel.add(txtLabel6);
        airportPanel.add(contentLabel);

        txtLabel5.setLocation(60,10);
        txtLabel5.setSize(500,50);

        txtLabel6.setLocation(560,10);
        txtLabel6.setSize(500,50);

        contentLabel.setLocation(0,70);
        contentLabel.setSize(1000,500);


        // adding Panels to cards

        panelCont.add(helloPanel, "1");
        panelCont.add(selectionPanel, "2");
        panelCont.add(mainPanel, "3");
        panelCont.add(airportPanel, "4");
        cl.show(panelCont, "1");


        updateAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                startProgramButton.setEnabled(false);
                startProgramButton.setText("Updating...");
                goToPanelSpecificButton.setEnabled(false);
                goToPanelSpecificButton.setText("Updating...");
                updateAllButton.setEnabled(false);
                updateAllButton.setText("Updating...");

                ArrayList<String> airportsToDownload = new ArrayList<String>();

                logger.info("Downloading all airports data.");
                airportsToDownload.add("KRK");
                airportsToDownload.add("WAW");
                airportsToDownload.add("KTW");
                airportsToDownload.add("WMI");
                airportsToDownload.add("GDN");
                airportsToDownload.add("LUZ");
                airportsToDownload.add("BZG");
                airportsToDownload.add("LCJ");
                airportsToDownload.add("SZY");
                airportsToDownload.add("RZE");
                airportsToDownload.add("POZ");

                Thread thread1 = new Thread() {
                    public void run() {
                        try {
                            new UpdateAirports(airportsToDownload);
                            startProgramButton.setEnabled(true);
                            startProgramButton.setText("Start program without updating data");
                            updateAllButton.setEnabled(true);
                            updateAllButton.setText("Update all airports");
                            goToPanelSpecificButton.setEnabled(true);
                            goToPanelSpecificButton.setText("Update specific airports");
                            JOptionPane.showMessageDialog(new JFrame(), "All airports successfully updated.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(new JFrame(), "Update failed.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                };
                thread1.start();
            }
        });
        startProgramButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                logger.info("Starting program");
                cl.show(panelCont, "3");
            }
        });

        goToPanelSpecificButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                cl.show(panelCont, "2");
                logger.info("Switching to Selection panel");
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                cl.show(panelCont, "1");
                logger.info("Going back to Hello Panel");
            }
        });

        backButt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                cl.show(panelCont, "1");
                logger.info("Going back to Hello Panel");
            }
        });

        backButt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                cl.show(panelCont, "3");
                logger.info("Going back to Main Graph Panel");
            }
        });

        arrBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(arrBox.isSelected()&&depBox.isSelected()){
                    depBox.setSelected(false);
                }else if(!arrBox.isSelected()&&!depBox.isSelected()){
                    depBox.setSelected(true);
                }
            }
        });

        depBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(arrBox.isSelected()&&depBox.isSelected()){
                    arrBox.setSelected(false);
                }else if(!arrBox.isSelected()&&!depBox.isSelected()){
                    arrBox.setSelected(true);
                }
            }
        });


        downloadSelectedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ArrayList<String> airportsToDownload = new ArrayList<String>();
                logger.info("Downloading SELECTED data");       // if selected || nic to nic nie pobieraj !
                                                                // po kliku zmiana carda na pobieranie ...
                                                                //  ; nowa klasa update airports i pobiera arrayliste stringow z nazwami, zwraca true po zakonczeniu i true zmienia carda na panela usera
                                                                // i po wcisnieciu update [ktorykolwiek]

                                                                //TODO gdy offline internet to DISABLE buttony: pobierz 1 i pobierz 3 i ez ;)
                if(checkBox1.isSelected()){airportsToDownload.add("KRK");}
                if (checkBox2.isSelected()) {airportsToDownload.add("WAW");}
                if(checkBox3.isSelected()) {airportsToDownload.add("KTW");}
                if(checkBox4.isSelected()){airportsToDownload.add("WMI");}
                if(checkBox5.isSelected()){airportsToDownload.add("GDN");}
                if(checkBox6.isSelected()){airportsToDownload.add("LUZ");}
                if(checkBox7.isSelected()){airportsToDownload.add("BZG");}
                if(checkBox8.isSelected()){airportsToDownload.add("LCJ");}
                if(checkBox9.isSelected()){airportsToDownload.add("SZY");}
                if(checkBox10.isSelected()){airportsToDownload.add("RZE");}
                if(checkBox11.isSelected()){airportsToDownload.add("POZ");}

                if(airportsToDownload.size()==0){
                    logger.error("None of the airports selected.");
                    String message = "Data selection error"
                            + "None of the airports are selected.";
                    JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                            JOptionPane.ERROR_MESSAGE);
                }else {
                    cl.show(panelCont, "1");
                    startProgramButton.setEnabled(false);
                    startProgramButton.setText("Updating...");
                    goToPanelSpecificButton.setEnabled(false);
                    goToPanelSpecificButton.setText("Updating...");
                    updateAllButton.setEnabled(false);
                    updateAllButton.setText("Updating...");
                    Thread thread1 = new Thread() {
                        public void run() {
                            try {
                                new UpdateAirports(airportsToDownload);
                                startProgramButton.setEnabled(true);
                                startProgramButton.setText("Start program without updating data");
                                updateAllButton.setText("Update all airports");
                                updateAllButton.setEnabled(true);
                                goToPanelSpecificButton.setText("Update specific airports");
                                goToPanelSpecificButton.setEnabled(true);
                                String msg;
                                if(airportsToDownload.size()==11){msg="All airports successfully updated.";}else {msg=airportsToDownload.size()+" Selected airports successfully updated.";}
                                JOptionPane.showMessageDialog(new JFrame(), msg, "Success", JOptionPane.INFORMATION_MESSAGE);
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(new JFrame(), "Update failed.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    };
                    thread1.start();
                }
            }
        });

        selectAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                checkBox1.setSelected(true);
                checkBox2.setSelected(true);
                checkBox3.setSelected(true);
                checkBox4.setSelected(true);
                checkBox5.setSelected(true);
                checkBox6.setSelected(true);
                checkBox7.setSelected(true);
                checkBox8.setSelected(true);
                checkBox9.setSelected(true);
                checkBox10.setSelected(true);
                checkBox11.setSelected(true);
                logger.info("Selected all airports");
            }
        });



        // MAP BUTTONS

        krakow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                choosenAirport="KRK";
                txtLabel5.setText("KRAKOW - KRK");
                cl.show(panelCont, "4");
                logger.info("Choosen"+choosenAirport+" airport");
            }
        });

        katowice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                choosenAirport="KTW";
                txtLabel5.setText("KATOWICE - KTW");
                cl.show(panelCont, "4");
                logger.info("Choosen"+choosenAirport+" airport");
            }
        });

        rzeszow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                choosenAirport="RZE";
                txtLabel5.setText("RZESZOW - RZE");
                cl.show(panelCont, "4");
                logger.info("Choosen"+choosenAirport+" airport");
            }
        });

        lublin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                choosenAirport="LUZ";
                txtLabel5.setText("LUBLIN - LUZ");
                cl.show(panelCont, "4");
                logger.info("Choosen"+choosenAirport+" airport");
            }
        });

        lodz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                choosenAirport="LCJ";
                txtLabel5.setText("LODZ - LCJ");
                cl.show(panelCont, "4");
                logger.info("Choosen"+choosenAirport+" airport");
            }
        });

        warszawa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                choosenAirport="WAW";
                txtLabel5.setText("WARSZAWA - WAW");
                cl.show(panelCont, "4");
                logger.info("Choosen"+choosenAirport+" airport");
            }
        });

        modlin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                choosenAirport="WMI";
                txtLabel5.setText("MODLIN - WMI");
                cl.show(panelCont, "4");
                logger.info("Choosen"+choosenAirport+" airport");
            }
        });

        poznan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                choosenAirport="POZ";
                txtLabel5.setText("POZNAN - POZ");
                cl.show(panelCont, "4");
                logger.info("Choosen"+choosenAirport+" airport");
            }
        });

        bydgoszcz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                choosenAirport="BZG";
                txtLabel5.setText("BYDGOSZCZ - BZG");
                cl.show(panelCont, "4");
                logger.info("Choosen"+choosenAirport+" airport");
            }
        });

        olsztyn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                choosenAirport="SZY";
                txtLabel5.setText("OLSZTYN - SZY");
                cl.show(panelCont, "4");
                logger.info("Choosen"+choosenAirport+" airport");
            }
        });

        gdansk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                choosenAirport="GDN";
                txtLabel5.setText("GDANSK - GDN");
                cl.show(panelCont, "4");
                logger.info("Choosen"+choosenAirport+" airport");
            }
        });

        dayOfWeekButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JFrame frame2 = new JFrame(dayOfWeekButt.getText());
                frame2.setSize(800,600);
                frame2.add( new GraphByDayOfWeek().ramka(choosenAirport,arrBox.isSelected(),false));  //true -> delay graph
                frame2.setVisible(true);
                logger.info("Created plot "+dayOfWeekButt.getText());

            }
        });

        delayByDayOfWeek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JFrame frame2 = new JFrame(dayOfWeekButt.getText());
                frame2.setSize(800,600);
                frame2.add( new GraphByDayOfWeek().ramka(choosenAirport,arrBox.isSelected(),true));  //true -> delay graph
                frame2.setVisible(true);
                logger.info("Created plot "+dayOfWeekButt.getText());

            }
        });

        airlineFlights.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JFrame frame2 = new JFrame(airlineFlights.getText());
                frame2.setSize(800,600);
                frame2.add( new GraphByAirline().ramka(choosenAirport,arrBox.isSelected(),false));  //true -> delay graph
                frame2.setVisible(true);
                logger.info("Created plot "+airlineFlights.getText());

            }
        });

        delayByAirline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JFrame frame2 = new JFrame(delayByAirline.getText());
                frame2.setSize(800,600);
                frame2.add( new GraphByAirline().ramka(choosenAirport,arrBox.isSelected(),true));  //true -> delay graph
                frame2.setVisible(true);
                logger.info("Created plot "+delayByAirline.getText());

            }
        });

        // ALL AIRPORTS GRAPHS
        airportsArrivals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JFrame frame2 = new JFrame(airportsArrivals.getText());
                frame2.setSize(800,600);
                frame2.add( new GraphByAllAirports().ramka(true,false));
                frame2.setVisible(true);
                logger.info("Created plot "+airportsArrivals.getText());

            }
        });

        airportsDepartures.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JFrame frame2 = new JFrame(airportsDepartures.getText());
                frame2.setSize(800,600);
                frame2.add( new GraphByAllAirports().ramka(false,false));
                frame2.setVisible(true);
                logger.info("Created plot "+airportsDepartures.getText());

            }
        });

        airportsArrivalsDelay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JFrame frame2 = new JFrame(airportsArrivalsDelay.getText());
                frame2.setSize(800,600);
                frame2.add( new GraphByAllAirports().ramka(true,true));
                frame2.setVisible(true);
                logger.info("Created plot "+airportsArrivalsDelay.getText());

            }
        });

        airportsDeparturesDelay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JFrame frame2 = new JFrame(airportsDepartures.getText());
                frame2.setSize(800,600);
                frame2.add( new GraphByAllAirports().ramka(false,true));
                frame2.setVisible(true);
                logger.info("Created plot "+airportsDepartures.getText());

            }
        });



        if(new CheckConnection().isInternetAvailable()){
            logger.info("Connected to the internet");
            goToPanelSpecificButton.setEnabled(true);
            updateAllButton.setEnabled(true);
        }else {
            logger.error("No internet connection");
            JOptionPane.showMessageDialog(new JFrame(), "No internet connection, data update not available", "Error", JOptionPane.ERROR_MESSAGE);
            goToPanelSpecificButton.setEnabled(false);
            updateAllButton.setEnabled(false);
        }

        frame.add(panelCont);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth()/2;
        double height = screenSize.getHeight()/2;
        frame.setResizable(false);
        frame.setLocation((int) width-500,(int) height-300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1000,600);
        frame.setIconImage(new ImageIcon("project_data/images/plane_2.png").getImage());
        logger.info("Created Main Panel");
        frame.setVisible(true);

    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    logger.info("Program run");
                    new MainApp();
                } catch (IOException e) {
                    logger.error("PROGRAM CRASHED!");
                    logger.error(e);
                }
            }
        });
    }

}