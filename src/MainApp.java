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
    private static final Logger logger = Logger.getLogger(MainApp.class);
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

        JButton b1 = new JButton(); //KRK
        b1.setBounds(270,395,20,20);
        b1.setFocusable(false);
        b1.setFont(new Font("SansSerif", Font.PLAIN, 16));
        b1.setBackground(Color.RED);

        JButton b2 = new JButton(); //kat
        b2.setBounds(220,375,20,20);
        b2.setFocusable(false);
        b2.setFont(new Font("SansSerif", Font.PLAIN, 16));
        b2.setBackground(Color.RED);

        JButton b3 = new JButton(); // rzeszow
        b3.setBounds(375,400,20,20);
        b3.setFocusable(false);
        b3.setFont(new Font("SansSerif", Font.PLAIN, 16));
        b3.setBackground(Color.RED);

        JButton b4 = new JButton(); // lublin
        b4.setBounds(395,305,20,20);
        b4.setFocusable(false);
        b4.setFont(new Font("SansSerif", Font.PLAIN, 16));
        b4.setBackground(Color.RED);

        JButton b5 = new JButton(); // lodz
        b5.setBounds(250,265,20,20);
        b5.setFocusable(false);
        b5.setFont(new Font("SansSerif", Font.PLAIN, 16));
        b5.setBackground(Color.RED);

        JButton b6 = new JButton(); // waw
        b6.setBounds(332,222,20,20);
        b6.setFocusable(false);
        b6.setFont(new Font("SansSerif", Font.PLAIN, 16));
        b6.setBackground(Color.RED);

        JButton b7 = new JButton(); // modlin
        b7.setBounds(300,190,20,20);
        b7.setFocusable(false);
        b7.setFont(new Font("SansSerif", Font.PLAIN, 16));
        b7.setBackground(Color.RED);

        JButton b8 = new JButton(); // poznan
        b8.setBounds(120,210,20,20);
        b8.setFocusable(false);
        b8.setFont(new Font("SansSerif", Font.PLAIN, 16));
        b8.setBackground(Color.RED);

        JButton b9 = new JButton(); // bydgoszcz
        b9.setBounds(195,150,20,20);
        b9.setFocusable(false);
        b9.setFont(new Font("SansSerif", Font.PLAIN, 16));
        b9.setBackground(Color.RED);

        JButton b10 = new JButton(); // olsztyn
        b10.setBounds(315,90,20,20);
        b10.setFocusable(false);
        b10.setFont(new Font("SansSerif", Font.PLAIN, 16));
        b10.setBackground(Color.RED);

        JButton b11 = new JButton(); // olsztyn
        b11.setBounds(215,40,20,20);
        b11.setFocusable(false);
        b11.setFont(new Font("SansSerif", Font.PLAIN, 16));
        b11.setBackground(Color.RED);

        picLabel.add(b1);
        picLabel.add(b2);
        picLabel.add(b3);
        picLabel.add(b4);
        picLabel.add(b5);
        picLabel.add(b6);
        picLabel.add(b7);
        picLabel.add(b8);
        picLabel.add(b9);
        picLabel.add(b10);
        picLabel.add(b11);

        JLabel menuLabel = new JLabel();

        JButton airportsDep = new JButton("PLOT  Departures - airport graph"); // plot lotniska od liczby przylotow
        airportsDep.setBounds(60,70,380,60);
        airportsDep.setFocusable(false);
        airportsDep.setFont(new Font("SansSerif", Font.PLAIN, 22));

        JButton airportsArr = new JButton("PLOT  Arrivals - airport graph"); // plot lotniska od liczby przylotow
        airportsArr.setBounds(60,220,380,60);
        airportsArr.setFocusable(false);
        airportsArr.setFont(new Font("SansSerif", Font.PLAIN, 22));

        JButton backButt2 = new JButton("Back");
        backButt2.setBounds(180,370,140,60);
        backButt2.setFocusable(false);
        backButt2.setFont(new Font("SansSerif", Font.PLAIN, 22));

        menuLabel.add(airportsDep);
        menuLabel.add(airportsArr);
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

        JLabel txtLabel5 = new JLabel("Krakow - KRK");    // PO KLIKNIECIU ZMIEN  choosenAirport
        txtLabel5.setFont(new Font("Impact", Font.BOLD, 40));

        JLabel txtLabel6 = new JLabel("Select graph options");
        txtLabel6.setFont(new Font("Impact", Font.BOLD, 30));

        JLabel contentLabel = new JLabel();

        JCheckBox depBox = new JCheckBox();
        depBox.setText("Departures");
        depBox.setBounds(250,10,150,50);
        depBox.setFocusable(false);
        depBox.setFont(new Font("SansSerif", Font.PLAIN, 19));

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
        cl.show(panelCont, "4");


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
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                cl.show(panelCont, "1");
            }
        });

        backButt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                cl.show(panelCont, "1");
            }
        });

        backButt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                cl.show(panelCont, "3");
            }
        });

        arrBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(arrBox.isSelected()&&depBox.isSelected()){
                    depBox.setSelected(false);
                }
            }
        });

        depBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(arrBox.isSelected()&&depBox.isSelected()){
                    arrBox.setSelected(false);
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
        logger.info("Created gui");
        frame.setVisible(true);

    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new MainApp();
                } catch (IOException e) {
                    System.out.println(e);
                    logger.error("PROGRAM CRASHED!");
                }
            }
        });
    }

}