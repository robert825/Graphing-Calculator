import javax.swing.*;
import java.awt.*;
public class GraphDriver
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame("Graphing Functions");
      frame.setSize(600,700);
      frame.setLayout(null);
      frame.setLocation(0,0);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(new Graph());
      frame.setVisible(true);
      frame.setResizable(false);
   }
}
