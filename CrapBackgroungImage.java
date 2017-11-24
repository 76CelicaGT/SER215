/**import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
**/ 
//Extra crap

import java.awt.Dimension;
import javax.swing.*;  

public class CrapBackgroungImage {  
    public CrapBackgroungImage() {  
        JFrame frame = new JFrame("Display Image");  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  

        JPanel panel = (JPanel)frame.getContentPane();  
        panel.setLayout(null);

        JLabel label = new JLabel();  
        label.setIcon(new ImageIcon("C:\\Users\\Thamiti\\Desktop\\big boy.gif"));
        panel.add(label);
        Dimension size = label.getPreferredSize();
        label.setBounds(500, 100, size.width, size.height);

        frame.setSize(600,800);
        frame.setVisible(true);  
    }  

    public static void main (String args[]) {  
        SwingUtilities.invokeLater(new Runnable() {  
            public void run() {  
                new CrapBackgroungImage();  
            }  
        });  
    }  
}  