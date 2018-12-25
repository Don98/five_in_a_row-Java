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
	private Draw draw;
	public static JLabel userInfo;
	public static JLabel gameInfo;
	private JPanel buttons;
	private JButton hvh;
	private JButton hvA;
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
		super("五子棋v1.0");
		
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
		draw = new Draw();
		draw.setQipan(this);
		userInfo = new JLabel("未登录");
		userInfo.setHorizontalAlignment(JLabel.CENTER);
		gameInfo = new JLabel("游戏未开始");
		gameInfo.setHorizontalAlignment(JLabel.CENTER);
		
		buttons = new JPanel();
		buttons.setLayout(null);
		buttons.setBackground(new Color(192,192,192));
		buttons.setBounds(800,20,400,790);
		// buttons.setBackground(new Color(255,255,255));
		
		hvh = new JButton("人人对弈");
		hvh.setFont(new Font("宋体", Font.PLAIN, 20));
		// start.setBorder(new RoundBorder());
		hvh.setBounds(80,20,200,70);

		hvA = new JButton("人机对弈");
		hvA.setFont(new Font("宋体", Font.PLAIN, 20));
		// start.setBorder(new RoundBorder());
		hvA.setBounds(80,120,200,70);
		
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
		buttons.add(hvh);
		buttons.add(hvA);
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
						Content t = draw.getContent();
						t.addInput_content("系统提示：欢迎你，" + username + "!\n\n");
						input_content = t.getInput_content();
						draw.setContent(t);
						output.setText(input_content);
						draw.connectServer();
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
						String t = draw.getContent().getInput_content();
						t += draw.getUser().getUsername() + " : " + input.getText().toString() + "\n\n";
						input.setText("");
						output.setText(t);
						draw.getContent().setInput_content(t);
						GameData data = draw.getData(-8);
						data.getContent().setInput_content(t);
						draw.sendIonfo2Server(data);
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
		hvh.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(!is_login)
				{
					warn_login();
				}
				else if(draw.getGameState() == 0)
				{
					draw.getUser().setChessColor(0);
					draw.setGameState(1);
					draw.playNewGame(false);
					// draw.connectServer();
					System.out.println(draw.getUser().getChessColor());
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
		hvA.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(!is_login)
				{
					warn_login();
				}
				else if(draw.getGameState() == 0)
				{
					draw.getUser().setChessColor((int)(Math.random()*2)+1);
					// draw.getUser().setChessColor(2);
					draw.setGameState(2);
					draw.playNewGame(true);
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
				else if(draw.getGameState() == 0){
					
				}
				else
				{	
					String[] options = {"确定", "取消"};
					int x = JOptionPane.showOptionDialog(null, "向对方发送悔棋请求",
							"悔棋",
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
					if(x == 0){
						//System.out.println("重新开始");
						//重新开始游戏
						Content cont = draw.getContent();
						//System.out.println("giveUp: "+draw.getContent().getGive_up());
						cont.setGive_up(draw.getUser().getChessColor());
						draw.setContent(cont);
						//System.out.println("giveUp: "+draw.getContent().getGive_up());
						GameData data = draw.getData(-5);
						draw.sendIonfo2Server(data);
					}else{
						//System.out.println("取消");
					}
					//用2x2的矩阵存储黑棋和白棋最近下的位置
					//轮到自己下的时候把另一个棋子代表的行清除为-1
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
				else if(draw.getGameState() == 0){
					
				}
				else
				{
					String[] options = {"确定", "取消"};
					int x = JOptionPane.showOptionDialog(null, "确定认输吗？",
							"认输",
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
					if(x == 0){
						//System.out.println("重新开始");
						//重新开始游戏
						Content cont = draw.getContent();
						//System.out.println("giveUp: "+draw.getContent().getGive_up());
						cont.setGive_up(draw.getUser().getChessColor());
						draw.setContent(cont);
						//System.out.println("giveUp: "+draw.getContent().getGive_up());
						GameData data = draw.getData(-2);
						draw.sendIonfo2Server(data);
					}else{
						//System.out.println("取消");
					}
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
				else if(draw.getGameState() == 0){
					
				}
				else
				{
					String[] options = {"确定", "取消"};
					int x = JOptionPane.showOptionDialog(null, "向对方发送求和请求",
							"求和",
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
					if(x == 0){
						//System.out.println("重新开始");
						//重新开始游戏
						Content cont = draw.getContent();
						//System.out.println("giveUp: "+draw.getContent().getGive_up());
						//cont.setPeace(draw.getUser().getChessColor());
						draw.setContent(cont);
						//System.out.println("giveUp: "+draw.getContent().getGive_up());
						GameData data = draw.getData(-3);
						draw.sendIonfo2Server(data);
					}else{
						//System.out.println("取消");
					}
					//询问对方;
				}
			}
		});
		send.addMouseListener(new MouseAdapter()
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
					if(input.getText().toString() != null && !input.getText().toString().trim().equals("") && is_login){
						String t = draw.getContent().getInput_content();
						t += draw.getUser().getUsername() + " : " + input.getText().toString() + "\n\n";
						input.setText("");
						output.setText(t);
						draw.getContent().setInput_content(t);
						GameData data = draw.getData(-8);
						data.getContent().setInput_content(t);
						draw.sendIonfo2Server(data);
					}
					else if(input.getText().toString() == null || input.getText().toString().trim().equals("") || !is_login)
					{
						input.setText("");
					}
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
	public void setOutput(String message)
	{
		output.setText(message);
	}
}