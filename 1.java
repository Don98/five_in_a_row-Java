import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


class Text2Frame {
	public static void main(String[] args) {

	JTextField jt1=new JTextField();
	JTextArea jt2=new JTextArea();
	JScrollPane jsp=new JScrollPane(jt2);
	String title = "Hello";
	JFrame jf=new JFrame(title);
	Container container=jf.getContentPane();
	jf.setVisible(true);
	jf.setSize(600, 400);
	jf.setLayout(new BorderLayout());
	jf.add(BorderLayout.NORTH,jt1);
	jt1.setBackground(Color.yellow);
	jf.add(BorderLayout.CENTER,jt2);
	jt2.setBackground(Color.blue);
	jt1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		jt2.setText(jt1.getText());
		}
	});
	} 
}