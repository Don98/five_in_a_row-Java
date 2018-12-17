import javafx.application.Application;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import java.awt.event.*;
public class RoundBorder implements Border {
 
 public Insets getBorderInsets(Component c) {
  return new Insets(0,0,0,0);
 }
 public boolean isBorderOpaque() {
  return false;
 }
 public void paintBorder(Component c, Graphics g, int x, int y,
   int width, int height) {
  g.setColor(Color.BLACK);
  g.drawRoundRect(0, 0, c.getWidth()-1, c.getHeight()-1, 5, 5);
 }

}