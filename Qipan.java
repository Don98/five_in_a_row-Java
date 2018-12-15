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
	private JMenu renrenduiyi;
	private JMenuItem rr_renzhihei;
	private JMenuItem rr_renzhibai;
	private JMenu renjiduiyi;
	private JMenuItem rj_renzhihei;
	private JMenuItem rj_renzhibai;
	private Draw draw;
	private JLabel userInfo;
	private JLabel gameInfo;
	
	
	public Qipan()
	{
		super("陈钦德五子棋v1.0");
		
		init();
		
		this.setSize(1200,900);
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
		renrenduiyi = new JMenu("人人对弈");
		rr_renzhihei = new JMenuItem("人执黑棋");
		rr_renzhibai = new JMenuItem("人执白棋");
		renjiduiyi = new JMenu("人机对弈");
		rj_renzhihei = new JMenuItem("人执黑棋");
		rj_renzhibai = new JMenuItem("人执白棋");
		draw = new Draw();
		userInfo = new JLabel("未登录");
		userInfo.setHorizontalAlignment(JLabel.CENTER);
		gameInfo = new JLabel("游戏未开始");
		gameInfo.setHorizontalAlignment(JLabel.CENTER);
		
		bar.add(xitong);
		xitong.add(renrenduiyi);
		xitong.add(renjiduiyi);
		
		renrenduiyi.add(rr_renzhihei);
		renrenduiyi.add(rr_renzhibai);
		renjiduiyi.add(rj_renzhihei);
		renjiduiyi.add(rj_renzhibai);
		
		this.setLayout(new BorderLayout());
		
		this.setJMenuBar(bar);
		this.add(userInfo,BorderLayout.NORTH);
		this.add(draw,BorderLayout.CENTER);
		this.add(gameInfo,BorderLayout.SOUTH);
		
	}
	
	public void addListener()
	{
		rr_renzhihei.addActionListener(new ActionListener())
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				draw.getUser().setChessColor(1);
				draw.getUser().setChessColor(1);
			}
		};
		rr_renzhibai.addActionListener(new ActionListener())
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				
			}
		};
	}
	public static void main(String[] args)
	{
		new Qipan();
	}
}