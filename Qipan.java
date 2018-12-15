import javafx.application.Application;
import javax.swing.JFrame;
import java.awt.*;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Qipan extends JFrame
{
	private JMenuBar bar;
	private JMenu xitong;
	private JMenuItem renrenduiyi;
	private JMenu renjiduiyi;
	private JMenuItem renzhihei;
	private JMenuItem renzhibai;
	
	
	
	public Qipan()
	{
		super("陈钦德五子棋v1.0");
		this.setSize(600,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)d.getWidth();
		int hegiht = (int)d.getHeight();
		
		this.setLocation((width - 600)/2,(hegiht - 600)/2);
		this.setVisible(true);
	}
	
	public void init()
	{
		bar = new JMenuBar();
		xitong = new JMenu("系统功能");
		renrenduiyi = new JMenuItem("人人对弈");
		renjiduiyi = new JMenu("人机对弈");
		renzhihei = new JMenuItem("人执黑棋");
		renzhibai = new JMenuItem("人执白棋");
		
		bar.add(xitong);
		xitong.add(renrenduiyi);
		xitong.add(renjiduiyi);
		
		renjiduiyi.add(renzhihei);
		renjiduiyi.add(renzhibai);
		
		this.setJMenuBar(bar);
		
	}
	
	public void addListener()
	{
		
	}
	public static void main(String[] args)
	{
		new Qipan();
	}
}