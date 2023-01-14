import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public class MainApp {
    private static final Logger logger = Logger.getLogger(MainApp.class);
    JFrame frame = new JFrame("Poland Airports Statistics");
    JPanel panelCont = new JPanel();

    CardLayout cl = new CardLayout();

    public MainApp() {
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


        JButton b = new JButton("Update all airports");
        b.setBounds(50,40,200,50);
        b.setFocusable(false);
        b.setFont(new Font("SansSerif", Font.PLAIN, 16));
        JButton b1 = new JButton("Start program without updating data");
        b1.setBounds(350,40,300,50);
        b1.setFocusable(false);
        b1.setFont(new Font("SansSerif", Font.PLAIN, 16));
        JButton b2 = new JButton("Update specific airports");
        b2.setBounds(750,40,210,50);
        b2.setFocusable(false);
        b2.setFont(new Font("SansSerif", Font.PLAIN, 16));

        JLabel buttonLaabel = new JLabel();
        buttonLaabel.add(b);
        buttonLaabel.add(b1);
        buttonLaabel.add(b2);

        helloPanel.add(txtLabel);
        helloPanel.add(label);
        helloPanel.add(txtLabel2);
        helloPanel.add(txtLabel2);
        helloPanel.add(buttonLaabel);

        txtLabel.setLocation(90,0);
        txtLabel.setSize(1000,60);

        label.setLocation(90,60);
        label.setSize(900,400);

        txtLabel2.setLocation(370,425);
        txtLabel2.setSize(1000,100);

        buttonLaabel.setLocation(0,460);
        buttonLaabel.setSize(1000,100);

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

        JButton b3 = new JButton("Back");
        b3.setBounds(200,450,200,50);
        b3.setFocusable(false);
        b3.setFont(new Font("SansSerif", Font.PLAIN, 16));

        JButton b4 = new JButton("Start update");
        b4.setBounds(600,450,200,50);
        b4.setFocusable(false);
        b4.setFont(new Font("SansSerif", Font.BOLD, 16));

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
        boxLabel.add(b3);
        boxLabel.add(b4);


        selectionPanel.add(txtLabel3);
        selectionPanel.add(boxLabel);

        txtLabel3.setLocation(90,0);
        txtLabel3.setSize(1000,60);

        boxLabel.setLocation(0,60);
        boxLabel.setSize(1000,540);

        // DATA DOWNLOADING PANEL
        JPanel downloadPanel = new JPanel();
        downloadPanel.setBounds(0,0,1000,600);
        downloadPanel.setBackground(mainColor);
        downloadPanel.setLayout(null);

        JLabel txtLabel4 = new JLabel("Updating data ...");
        txtLabel4.setFont(new Font("Impact", Font.BOLD, 50));

        downloadPanel.add(txtLabel4);

        txtLabel4.setLocation(90,80);
        txtLabel4.setSize(1000,60);


        // adding Panels to cards
        panelCont.add(helloPanel, "1");
        panelCont.add(selectionPanel, "2");
        panelCont.add(downloadPanel, "3");
        cl.show(panelCont, "1");


        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                b2.setEnabled(false);
                b2.setText("Updating...");
                b.setEnabled(false);
                b.setText("Updating...");

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
                            b.setText("Update all airports");
                            b.setEnabled(true);
                            b2.setText("Update specific airports");
                            b2.setEnabled(true);
                            JOptionPane.showMessageDialog(new JFrame(), "All airports successfully updated.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } catch (Exception e) {
                        }
                    }
                };
                thread1.start();
            }
        });
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                logger.info("Starting program");
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                cl.show(panelCont, "2");
            }
        });

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                cl.show(panelCont, "1");
            }
        });

        b4.addActionListener(new ActionListener() {
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
                    b2.setEnabled(false);
                    b2.setText("Updating...");
                    b.setEnabled(false);
                    b.setText("Updating...");
                    Thread thread1 = new Thread() {
                        public void run() {
                            try {
                                new UpdateAirports(airportsToDownload);
                                b.setText("Update all airports");
                                b.setEnabled(true);
                                b2.setText("Update specific airports");
                                b2.setEnabled(true);
                                String msg;
                                if(airportsToDownload.size()==11){msg="All airports successfully updated.";}else {msg=airportsToDownload.size()+" Selected airports successfully updated.";}
                                JOptionPane.showMessageDialog(new JFrame(), msg, "Success", JOptionPane.INFORMATION_MESSAGE);
                            } catch (Exception e) {
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
//        frame.setLocation(300,200);
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
                new MainApp();
            }
        });
    }

}