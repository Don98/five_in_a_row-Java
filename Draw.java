import javax.swing.JPanel;
import java.awt.*;
import javax.swing.ImageIcon;
import java.awt.event.*;
import java.net.Socket;
import java.io.IOException;
public class Draw extends JPanel
{
	private int shamt_x = 80;
	private int shamt_y = 50;
	private User user;
	private boolean gameStart;
	private int whichColor;
	private int[][] place;//0无,1黑,2白
	private ImageIcon black,white;
	private int size = 15;
	private int length = 50;
	private int gameState;
	private Socket s;
	
	public Draw()
	{
		place = new int[15][15];
		// String basePath = this.getClass().getResource("\\").getPointath();
		// System.out.println(basePath);
		// black = new ImageIcon(basePath + "./black.png");
		// white = new ImageIcon(basePath + "./white.png");
		black = new ImageIcon("./black.png");
		white = new ImageIcon("./white.png");
		this.setSize(800,800);
		// this.setBackground(new Color(220,191,157)); // 木色
		this.setBackground(new Color(192,192,192)); // 亮银色
		// this.setBackground(new Color(255,255,255));
		user = new User();
		addListener();
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		paintQipan(g);
		paintQizi(g);
	}
	
	public void paintQipan(Graphics a)
	{		
		
		Graphics2D g = (Graphics2D)a;
		g.setStroke(new BasicStroke(3f));
		g.drawLine(shamt_x + 0,shamt_y + 0,shamt_x + (size - 1) * length,shamt_y + 0);
		g.drawLine(shamt_x + 0,shamt_y + 0 ,shamt_x + 0,shamt_y + (size - 1) * length);
		g.drawLine(shamt_x + 0,shamt_y + (size - 1) * length ,shamt_x + (size - 1) * length,shamt_y + (size - 1) * length);
		g.drawLine(shamt_x + (size - 1) * length,shamt_y + 0,shamt_x + (size - 1) * length,shamt_y + (size - 1) * length);
		g.setStroke(new BasicStroke());

		for(int i = 0;i < size;i++)
		{
			g.drawString((i+1)+"",length * i + shamt_x - 3,shamt_y - 10);
			g.drawString((i+1)+"",shamt_y + 10,length * i + shamt_y);
		}
		for(int i = 0;i < size;i++)
		{
			g.drawLine(shamt_x + 0,shamt_y + i * length,shamt_x + (size - 1) * length,shamt_y + i * length);
			g.drawLine(shamt_x + i * length ,shamt_y + 0 ,shamt_x + i * length,shamt_y + (size - 1) * length);
		}
		g.fillOval(shamt_x + length * 3 - 3,shamt_y + length * 3 - 3,6,6);
		g.fillOval(shamt_x + length * 3 - 3,shamt_y + length * 11 - 3,6,6);
		g.fillOval(shamt_x + length * 11 - 3,shamt_y + length * 3 - 3,6,6);
		g.fillOval(shamt_x + length * 11 - 3,shamt_y + length * 11 - 3,6,6);
		g.fillOval(shamt_x + length * 7 - 3,shamt_y + length * 7 - 3,6,6);
	}
	
	public void paintQizi(Graphics g)
	{
		for(int i = 0;i < 15 ;i++)
		{
			for(int j = 0;j < 15;j++)
			{
				if(place[i][j] == 1)
				{
					g.drawImage(black.getImage(),i * length + shamt_x - length / 4,j * length + shamt_y - length / 4,length / 2 ,length / 2,this);
				}
				else if(place[i][j] == 2)
				{
					g.drawImage(white.getImage(),i * length + shamt_x - length / 4,j * length + shamt_y - length / 4,length / 2 ,length / 2,this);
				}
			}
		}
	}
	public void addListener()
	{
		this.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(!gameStart) return;
				if(user.getChessColor() != whichColor) return;
				
				int x = e.getX();
				int y = e.getY();
				
				if(x >= 0 + shamt_x - 10 && x <= length * size + shamt_x + 10&& y >= 0 + shamt_y - 10&& y <= length * size + shamt_y + 10)
				{
					
					x = (int)Math.round((x - shamt_x) / (length * 1.0));
					y = (int)Math.round((y - shamt_y) / (length * 1.0));
					if(place[x][y] != 0)	return;
					place[x][y] = user.getChessColor();
					
					repaint();
					
					// System.out.println(x);
					// System.out.println(y);
				
					changeChessColor();
					showGameInfo();
					
					if(gameState == 1)
					{
						GameData data = new GameData();
						data.setUser(user);
						data.setPoint(new Point(x,y));
						sendIonfo2Server(data);
					}
					else if(gameState == 2)
					{
						
					}
				}
			}		
		});
	}
	
	public void connectServer()
	{
		try
		{
			s = new Socket("127.0.0.1",8888);
			GameData d = new GameData();
			d.setUser(user);
			IOUtil.writeObject(d,s.getOutputStream());
			new Thread(new GetInforFromServer()).start();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void sendIonfo2Server(GameData data)
	{
		try
		{
			IOUtil.writeObject(data,s.getOutputStream());
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void changeChessColor()
	{		
		if(whichColor == 1)	whichColor = 2;
		else if(whichColor == 2) whichColor = 1;
	}
	
	public void showGameInfo()
	{
		if(whichColor == 1)
		{
			Qipan.gameInfo.setText("轮到黑方下棋");
		}
		else if(whichColor == 2)
		{
			Qipan.gameInfo.setText("轮到白方下棋");
		}
	}
	
	public void playNewGame()
	{
		place = new int[15][15];
		gameStart = true;
		// whichColor = this.getUser().getChessColor();
		whichColor = 1;
	}
	
	public User getUser()
	{
		return user;
	}
	public void setUser(User user)
	{
		this.user = user;
	}
	public int getGameSate()
	{
		return gameState;
	}
	public void setGameState(int gameState)
	{
		this.gameState = gameState;
	}
	
	class GetInforFromServer implements Runnable
	{
		@Override
		public void run()
		{
			while(true)
			{
				try
				{
					GameData data = (GameData)IOUtil.readObject(s.getInputStream());
					place[data.getPoint().getX()][data.getPoint().getY()] = data.getUser().getChessColor();
					repaint();
					changeChessColor();
					showGameInfo();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
				
			}
		}
	}
}