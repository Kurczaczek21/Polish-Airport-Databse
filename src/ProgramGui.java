import javax.swing.*;
import java.awt.*;

public class ProgramGui {
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame();
        frame.setTitle("Poland Airports Statistics");
        ImageIcon image = new ImageIcon("project_data/images/plane_2.png");
        frame.setIconImage(image.getImage());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(612*2,383*2);
        frame.setVisible(true);
        Color aColor = Color.decode("#0174DF");     //#08298A
        frame.getContentPane().setBackground(aColor);

        JLabel label = new JLabel();
        label.setText("Witam w wykresawce lotow");
        ImageIcon image2 = new ImageIcon("project_data/images/Entry_panel.jpg");
        label.setIcon(image2);
        label.setHorizontalTextPosition(JLabel.CENTER);
        frame.add(label);
    }
}
