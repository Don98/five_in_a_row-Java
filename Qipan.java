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
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JScrollPane;
import java.awt.event.*;

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
	private JButton send;
	private JTextArea input;
	private JTextArea output;
	private String input_content;
	private boolean is_first;
	private boolean is_login;
	private int chose;
	
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
		is_first = true;
		is_login = false;
		chose = 0;
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
		
		send = new JButton("发送");
		send.setFont(new Font("宋体", Font.PLAIN, 18));
		// start.setBorder(new RoundBorder());
		send.setBounds(190,750,90,30);
		
		output = new JTextArea();
		// output.setBounds(80,520,200,120);
		// output.setBounds(0,0,10,20);
		output.setEditable(false);
		
		input = new JTextArea();
		input.setText("登录之后才可评论且评论不能为空！");
    	input.setForeground(new Color(144,144,144));
		// input.setBounds(0,0,10,20);
		
		output.setSelectedTextColor(Color.BLUE);
		output.setLineWrap(true);         
		input.setLineWrap(true);         
		output.setWrapStyleWord(true); 
		input.setWrapStyleWord(true); 
		
		JPanel panelOutput;
		JPanel panelInput;

		panelOutput = new JPanel();
		// panelOutput.setLayout(null);
		JScrollPane outputScorll = new JScrollPane(output);
		outputScorll.setPreferredSize(new Dimension(200,120));
		panelOutput.add(outputScorll);
		panelOutput.setBounds(80,520,200,120);
		
		panelInput = new JPanel();
		// panelInput.setLayout(null);
		JScrollPane inputScorll = new JScrollPane(input);
		inputScorll.setPreferredSize(new Dimension(200,70));
		panelInput.add(inputScorll);
		// panelInput.add(new JScrollPane(input));
		panelInput.setBounds(80,670,200,70);
		
		
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
		buttons.add(send);
		// buttons.add(output);
		// buttons.add(input);
		buttons.add(panelOutput);
		buttons.add(panelInput);
		
		// buttons.setContentPane(panelInput);
		// buttons.setContentPane(panelOutput);
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
						is_login = true;
						input_content = "系统提示：欢迎你，" + username + "!\n\n";
						output.setText(input_content);
						// output.setText(input_content + input_content + input_content + input_content + input_content + input_content + input_content + input_content + input_content + input_content + input_content + input_content + input_content + input_content + input_content);
					}
				}
			}
		});
		input.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent arg0) {
				int key = arg0.getKeyCode();
				if(!is_login)
				{
					warn_login();
				}
				else
				{
					if(key == '\n' && input.getText().toString() != null && !input.getText().toString().trim().equals("") && is_login){
						input_content += draw.getUser().getUsername() + " : " + input.getText().toString() + "\n\n";
						input.setText("");
						output.setText(input_content);
					}
					else if(input.getText().toString() == null || input.getText().toString().trim().equals("") || !is_login)
					{
						input.setText("");
					}
				}
			}
		 
			@Override
			public void keyReleased(KeyEvent e) {
			}
		 
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		input.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(is_first)
				{
					is_first = false;
					input.setText("");
					input.setForeground(new Color(0,0,0));
				}
			}
		});
		one.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(!is_login)
				{
					warn_login();
				}
				else if(chose == 2)
				{
					chose = 1;
					draw.getUser().setChessColor(0);
					draw.setGameState(1);
					draw.playNewGame();
					draw.connectServer();
					if(draw.getUser().getChessColor() == 1)
					{
						gameInfo.setText("游戏开始,轮到黑方下棋");
					}
					else if(draw.getUser().getChessColor() == 2)
					{
						gameInfo.setText("游戏开始,轮到白方下棋");
					}
				}
			}
		});
		two.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(!is_login)
				{
					warn_login();
				}
				else if(chose == 0)
				{
					chose = 2;
					draw.getUser().setChessColor(0);
					draw.setGameState(2);
					draw.playNewGame();
					draw.connectServer();
				}
			}
		});
		retract.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(!is_login)
				{
					warn_login();
				}
				else
				{
					//询问对方;
				}
			}
		});
		give_up.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(!is_login)
				{
					warn_login();
				}
				else
				{
					//询问对方;
				}
			}
		});
		peace.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(!is_login)
				{
					warn_login();
				}
				else
				{
					//询问对方;
				}
			}
		});
	}
	public void warn_login()
	{
		JOptionPane.showConfirmDialog(null,"请登录才能使用!");
		return;
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