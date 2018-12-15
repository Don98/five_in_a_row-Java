import javafx.application.Application;
import javax.swing.JFrame;
import java.awt.*;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JLabel;

public class Qipan extends JFrame
{
	private JMenuBar bar;
	private JMenu xitong;
	private JMenuItem renrenduiyi;
	private JMenu renjiduiyi;
	private JMenuItem renzhihei;
	private JMenuItem renzhibai;
	private Draw draw;
	private JLabel userInfo;
	private JLabel gameInfo;
	
	
	public Qipan()
	{
		super("陈钦德五子棋v1.0");
		
		init();
		
		this.setSize(900,900);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)d.getWidth();
		int hegiht = (int)d.getHeight();
		
		this.setLocation((width - 900)/2,(hegiht - 900)/2);
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
		draw = new Draw();
		userInfo = new JLabel("未登录");
		userInfo.setHorizontalAlignment(JLabel.CENTER);
		gameInfo = new JLabel("游戏未开始");
		gameInfo.setHorizontalAlignment(JLabel.CENTER);
		
		bar.add(xitong);
		xitong.add(renrenduiyi);
		xitong.add(renjiduiyi);
		
		renjiduiyi.add(renzhihei);
		renjiduiyi.add(renzhibai);
		
		this.setLayout(new BorderLayout());
		
		this.setJMenuBar(bar);
		this.add(userInfo,BorderLayout.NORTH);
		this.add(draw,BorderLayout.CENTER);
		this.add(gameInfo,BorderLayout.SOUTH);
		
	}
	
	public void addListener()
	{
		
	}
	public static void main(String[] args)
	{
		new Qipan();
	}
}