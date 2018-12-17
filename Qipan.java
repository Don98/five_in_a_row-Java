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
import java.awt.event.*;
 // this.setTitle("绝对布局");//设置标题名字
	   // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//默认退出
	   // this.setBounds(100, 100, 250, 100);//设置窗体的大小
	   // this.contentPane=new JPanel();//初始化面板
	   // this.contentPane.setLayout(null);//设置布局NULL
	   // this.button1=new JButton("按钮1");//给按钮名字
	   // this.button1.setBounds(6,6,90,30);//设置按钮名字
	   // this.contentPane.add(button1);//加入面板中
	   // this.button2=new JButton("按钮2");
	   // this.contentPane.add(button2);
	   // this.button2.setBounds(138, 26, 90, 30);
	   // this.add(this.contentPane);
	   // this.setVisible(true);
	   
public class Qipan extends JFrame
{
	private JMenuBar bar;
	private JMenu xitong;
	private JMenuItem login;
	private JMenu renrenduiyi;
	private JMenuItem rr_renzhihei;
	private JMenuItem rr_renzhibai;
	private JMenu renjiduiyi;
	private JMenuItem rj_renzhihei;
	private JMenuItem rj_renzhibai;
	private Draw draw;
	public static JLabel userInfo;
	public static JLabel gameInfo;
	private JPanel buttons;
	private JButton one;
	private JButton two;
	private JButton retract;
	private JButton give_up;
	private JButton peace;
	
	public Qipan()
	{
		super("陈钦德五子棋v1.0");
		
		init();
		
		this.setSize(1200,900);
		// this.setSize(900,900);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)d.getWidth();
		int hegiht = (int)d.getHeight();
		
		this.setLocation((width - 900)/2,(hegiht - 900)/2);
		this.setVisible(true);
		
		addListener();
	}
	
	public void init()
	{
		bar = new JMenuBar();
		xitong = new JMenu("系统功能");
		login = new JMenuItem("登录");
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
		
		buttons = new JPanel();
		buttons.setLayout(null);
		buttons.setBackground(new Color(192,192,192));
		buttons.setBounds(800,20,400,790);
		// buttons.setBackground(new Color(255,255,255));
		
		one = new JButton("人人对弈");
		one.setFont(new Font("宋体", Font.PLAIN, 20));
		// start.setBorder(new RoundBorder());
		one.setBounds(80,20,200,70);

		two = new JButton("人机对弈");
		two.setFont(new Font("宋体", Font.PLAIN, 20));
		// start.setBorder(new RoundBorder());
		two.setBounds(80,120,200,70);
		
		retract = new JButton("悔棋");
		retract.setFont(new Font("宋体", Font.PLAIN, 20));
		// start.setBorder(new RoundBorder());
		retract.setBounds(80,220,200,70);
		
		give_up = new JButton("认输");
		give_up.setFont(new Font("宋体", Font.PLAIN, 20));
		// start.setBorder(new RoundBorder());
		give_up.setBounds(80,320,200,70);
		
		peace = new JButton("求和");
		peace.setFont(new Font("宋体", Font.PLAIN, 20));
		// start.setBorder(new RoundBorder());
		peace.setBounds(80,420,200,70);
		
		bar.add(xitong);
		xitong.add(login);
		xitong.add(renrenduiyi);
		xitong.add(renjiduiyi);
		
		renrenduiyi.add(rr_renzhihei);
		renrenduiyi.add(rr_renzhibai);
		renjiduiyi.add(rj_renzhihei);
		renjiduiyi.add(rj_renzhibai);
		
		// this.setLayout(new BorderLayout());
		this.setLayout(null);
		bar.setBounds(0,0,100,100);
		this.setJMenuBar(bar);
		userInfo.setBounds(0,0,1200,20);
		this.add(userInfo);
		gameInfo.setBounds(0,810,1200,20);
		this.add(gameInfo);
		draw.setBounds(0,20,800,790);
		this.add(draw);
		this.add(buttons);
		buttons.add(one);
		buttons.add(two);
		buttons.add(retract);
		buttons.add(give_up);
		buttons.add(peace);
		
		// all.setLayout(null);
		
		// all.add(bar);
		// all.add(userInfo,BorderLayout.NORTH);
		// all.add(draw,BorderLayout.CENTER);
		// all.add(gameInfo,BorderLayout.SOUTH);
		// all.add(start);
	}
	
	public void addListener()
	{
		rr_renzhihei.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				draw.getUser().setChessColor(1);
				draw.setGameState(1);
				draw.playNewGame();
				gameInfo.setText("游戏开始,轮到黑方下棋");
				draw.connectServer();
			}
		});
		rr_renzhibai.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				draw.getUser().setChessColor(2);
				draw.setGameState(1);
				draw.playNewGame();
				gameInfo.setText("游戏开始,轮到白方下棋");
				draw.connectServer();
			}
		});
		login.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String username = JOptionPane.showInputDialog("请输入用户名");
				if(username != null)
				{
					if(username.trim().equals(""))
					{
						JOptionPane.showConfirmDialog(null,"用户名不能为空!");
						return;
					}
					else 
					{
						draw.getUser().setUsername(username);
						userInfo.setText("欢迎你，" + username);
					}
				}
			}
		});
	}
	public Draw getDraw()
	{
		return draw;
	}
	public void setDraw(Draw draw)
	{
		this.draw = draw;
	}
	
	public static void main(String[] args)
	{
		// User user = new User();
		// user.setUsername("铁蛋");
		// new Qipan().getDraw().setUser(user);
		new Qipan();
	}
}