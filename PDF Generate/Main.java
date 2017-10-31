import GUI.Marco;
import java.awt.Dimension;
import javax.swing.JFrame;
public class Main {
    public static void main(String [] args){
        Marco gui = new Marco();
        gui.pack();
        gui.setMinimumSize(new Dimension(600,600));
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);
    }
}
