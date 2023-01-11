import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

public class ProgramGui {

    private static final Logger logger= LogManager.getLogger(WebScrape.class);
    final static String HELLOSCREEN= "Card with hello screen message";
    final static String CHOOSEDATA= "Card with boxes to download data";
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame();
        frame.setTitle("Poland Airports Statistics");
        ImageIcon image = new ImageIcon("project_data/images/plane_2.png");
        frame.setIconImage(image.getImage());
        frame.setResizable(false);
        frame.setSize(1308,800);
        Color mainColor = Color.decode("#0174DF");     //#08298A
        frame.getContentPane().setBackground(mainColor);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth()/2;
        double height = screenSize.getHeight()/2;
        frame.setLocation((int) width-(frame.getWidth()/2),(int) height-(frame.getHeight()/2));
        frame.setLayout(null);

        JPanel topPanel = new JPanel();
        topPanel.setBackground(mainColor);
        topPanel.setBounds(0,0,1300,100);

        JPanel midPanel = new JPanel();
        midPanel.setBackground(mainColor);
        midPanel.setBounds(50,100,1201,483);

        JPanel botPanel = new JPanel();
        botPanel.setBackground(mainColor);
        botPanel.setBounds(0,600,1300,200);
        botPanel.setLayout(null);


        JLabel txtLabel = new JLabel();
        txtLabel.setText("Welcome to polish airports database");
        txtLabel.setFont(new Font("Impact", Font.BOLD, 60));     // SansSerif
        txtLabel.setHorizontalTextPosition(SwingConstants.CENTER);

        JLabel label = new JLabel();
        ImageIcon image2 = new ImageIcon("project_data/images/palnek.png");
        label.setIcon(image2);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setVerticalTextPosition(SwingConstants.CENTER);   //  TODO: add label on top label
        label.setHorizontalAlignment(JLabel.CENTER);          //  TODO: add log4j


        JLabel txtLabel2 = new JLabel("Select option:");
        txtLabel2.setFont(new Font("SansSerif", Font.BOLD, 30));
        txtLabel2.setBounds(550,0,300,40);
        JButton b = new JButton("Update specific airports");
        b.setBounds(50,70,200,50);
        b.setFocusable(false);
        b.setFont(new Font("Arial", Font.PLAIN, 16));
        JButton b1 = new JButton("Start program without updating data");
        b1.setBounds(500,70,300,50);
        b1.setFocusable(false);
        b1.setFont(new Font("Arial", Font.PLAIN, 16));
        JButton b2 = new JButton("Update all airports");
        b2.setBounds(1050,70,200,50);
        b2.setFocusable(false);
        b2.setFont(new Font("Arial", Font.PLAIN, 16));

        topPanel.add(txtLabel);
        midPanel.add(label);
        botPanel.add(txtLabel2);
        botPanel.add(b);
        botPanel.add(b1);
        botPanel.add(b2);

        frame.add(midPanel);
        frame.add(topPanel);
        frame.add(botPanel);

        logger.info("JPanel created");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



//        JPanel cards = new JPanel(new CardLayout());
//        cards.add(frame.getContentPane(), HELLOSCREEN);
//        cards.add(frame2.getContentPane(), CHOOSEDATA);


    }
}
