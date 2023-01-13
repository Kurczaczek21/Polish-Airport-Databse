import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.apache.log4j.Logger;

public class MainApp {
    private static final Logger logger = Logger.getLogger(MainApp.class);
    JFrame frame = new JFrame("CardLayout demo");
    JPanel panelCont = new JPanel();
    JPanel panelFirst = new JPanel();
    JPanel panelSecond = new JPanel();
    JButton buttonOne = new JButton("Switch to second panel/workspace");
    JButton buttonSecond = new JButton("Switch to first panel/workspace");
    CardLayout cl = new CardLayout();

    public MainApp() {
        Color mainColor = Color.decode("#0174DF");     //#08298A
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


        JButton b = new JButton("Update specific airports");
        b.setBounds(50,40,200,50);
        b.setFocusable(false);
        b.setFont(new Font("SansSerif", Font.PLAIN, 16));
        JButton b1 = new JButton("Start program without updating data");
        b1.setBounds(350,40,300,50);
        b1.setFocusable(false);
        b1.setFont(new Font("SansSerif", Font.PLAIN, 16));
        JButton b2 = new JButton("Update all airports");
        b2.setBounds(750,40,200,50);
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

        JCheckBox checkBox = new JCheckBox();
        checkBox.setText("Krakow");
        checkBox.setBounds(750,40,200,50);
        checkBox.setFocusable(false);
        checkBox.setFont(new Font("SansSerif", Font.PLAIN, 16));

        JLabel boxLabel = new JLabel();
        boxLabel.add(checkBox);


        selectionPanel.add(txtLabel3);
//        selectionPanel.add(buttonSecond);
        selectionPanel.add(boxLabel);

        txtLabel3.setLocation(90,0);
        txtLabel3.setSize(1000,60);

        boxLabel.setLocation(-300,60);
        boxLabel.setSize(1000,200);

        buttonSecond.setLocation(300,400);


        panelCont.add(helloPanel, "1");
        panelCont.add(selectionPanel, "2");
        cl.show(panelCont, "1");

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                cl.show(panelCont, "2");
            }
        });

        buttonSecond.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                cl.show(panelCont, "1");
            }
        });



        frame.add(panelCont);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth()/2;
        double height = screenSize.getHeight()/2;
        frame.setLocation(300,200);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1000,600);
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