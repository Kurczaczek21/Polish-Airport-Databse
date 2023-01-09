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
        frame.setSize(612,383);    // 492   805
        Color aColor = Color.decode("#0174DF");     //#08298A
        frame.getContentPane().setBackground(aColor);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth()/2;
        double height = screenSize.getHeight()/2;
        frame.setLocation((int) width-(frame.getWidth()/2),(int) height-(frame.getHeight()/2));


        JLabel label = new JLabel();
        label.setSize(frame.getSize());
        label.setVisible(true);
        label.setText("Witam w wykresawce lotow");
        ImageIcon image2 = new ImageIcon("project_data/images/Entry_panel.jpg");
        label.setIcon(image2);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setVerticalTextPosition(SwingConstants.CENTER);   //  TODO: add label on top label
//        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Serif", Font.PLAIN, 60));
        frame.add(label);

        logger.info("JPanel created");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



//        JPanel cards = new JPanel(new CardLayout());
//        cards.add(frame.getContentPane(), HELLOSCREEN);
//        cards.add(frame2.getContentPane(), CHOOSEDATA);


    }
}
