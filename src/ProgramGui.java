import org.apache.logging.log4j.LogManager;     // term 0 -> 19:00 27 stycznia
import org.apache.logging.log4j.Logger;         // term 1 -> 20:00 ??? 6 lutego
import javax.swing.*;                           // term 2 -> 9:30 13 lutego
import java.awt.*;                              // 16 stycznia 9:30 do 12 siedzi jak sa ludzie -> projekty
import java.awt.event.ActionEvent;              // 23 stycznia -||-
import java.awt.event.ActionListener;           // 25 stycznia 16:00 do 18:00 [termin 0]
                                                // 30 stycznia 9:30 do 10:30
                                                // 1 lutego 14:30 do 17 [termin 1 (2 lub 6 lutego)]
// King Of Sat

// jakie info tam mozna brac
// klasa abstrakcyjna Web Pages
// NIE MOZNA stworzyc instancji
// trzeba zrobic konstruktor overload, protected
// w dziedziczacaej tak, on dostaje URL jako obiekt lub Stringa i tworzy obiekt Webpages
// metoda bezparam co zwraca ArrayListe satelitow

// klasa Satelite
// przechowuje info o satelitach
//
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
        frame.setSize(1000,600);        //1308,800
        Color mainColor = Color.decode("#0174DF");     //#08298A
        frame.getContentPane().setBackground(mainColor);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth()/2;
        double height = screenSize.getHeight()/2;
        frame.setLocation((int) width-(frame.getWidth()/2),(int) height-(frame.getHeight()/2));
        frame.setLayout(null);

        JPanel topPanel = new JPanel();
        topPanel.setBackground(mainColor);
        topPanel.setBounds(0,0,1000,60);

        JPanel midPanel = new JPanel();
        midPanel.setBackground(mainColor);
        midPanel.setBounds(50,60,900,400);

        JPanel botPanel = new JPanel();
        botPanel.setBackground(mainColor);
        botPanel.setBounds(0,460,1000,200);
        botPanel.setLayout(null);


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


        JFrame frame_getSpecificData = new JFrame();
        frame_getSpecificData.setTitle("Poland Airports Statistics");
        frame_getSpecificData.setIconImage(image.getImage());
        frame_getSpecificData.setResizable(false);
        frame_getSpecificData.setSize(1000,600);        //1308,800
        frame_getSpecificData.setLocation((int) width-(frame.getWidth()/2),(int) height-(frame.getHeight()/2));
        frame_getSpecificData.setLayout(null);


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

        topPanel.add(txtLabel);
        midPanel.add(label);
        botPanel.add(txtLabel2);
        botPanel.add(b);
        botPanel.add(b1);
        botPanel.add(b2);



        frame.add(midPanel);
        frame.add(topPanel);
        frame.add(botPanel);

//        JPanel sigmaPanel = new JPanel();
//        sigmaPanel.add(frame.getContentPane());
//        sigmaPanel.setVisible(true);

        logger.info("Helloscreen created");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        // J CARDS

//        JPanel cards = new JPanel(new CardLayout());
//        cards.add(frame.getContentPane(), HELLOSCREEN);
//        cards.add(frame_getSpecificData.getContentPane(), CHOOSEDATA);

    }
}
