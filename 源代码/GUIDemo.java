import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUIDemo {
    public static void createGUI()
    {
        MyFrame frame = new MyFrame("Kmean演示系统");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,300);
        frame.setVisible(true);
    }
}
