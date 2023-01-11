import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainApp {
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

        panelFirst.add(buttonOne);
        panelSecond.add(buttonSecond);
        panelFirst.setBackground(Color.BLUE);
        panelSecond.setBackground(Color.GREEN);

        JPanel topPanel = new JPanel();
        topPanel.setBackground(mainColor);
        topPanel.setBounds(0,0,1000,600);

        JLabel txtLabel = new JLabel();
        txtLabel.setText("Welcome to polish airports database");
        txtLabel.setFont(new Font("Impact", Font.BOLD, 50));     // SansSerif
        txtLabel.setHorizontalTextPosition(SwingConstants.CENTER);

        JLabel label = new JLabel();
        ImageIcon image2 = new ImageIcon("project_data/images/krkairp.jpg");
        Image img = image2.getImage();
        Image imgScaled = img.getScaledInstance(image2.getIconWidth(),image2.getIconHeight(),Image.SCALE_SMOOTH);
        ImageIcon image3 = new ImageIcon(imgScaled);
        label.setIcon(image3);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setVerticalTextPosition(SwingConstants.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);          //  TODO: add log4j



        JLabel txtLabel2 = new JLabel("Select startup option:");
        txtLabel2.setFont(new Font("SansSerif", Font.BOLD, 25));
        txtLabel2.setBounds(370,0,260,30);
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
        topPanel.setLayout( new BorderLayout());

        topPanel.add(txtLabel);
        topPanel.add(label);
        topPanel.add(txtLabel2);
        topPanel.add(b);
        topPanel.add(b1);
        topPanel.add(b2);

        panelCont.add(topPanel, "1");
        panelCont.add(panelSecond, "2");
        cl.show(panelCont, "1");

        buttonOne.addActionListener(new ActionListener() {
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
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
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