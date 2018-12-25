import javax.swing.JPanel;
import java.awt.*;
import javax.swing.ImageIcon;
import java.awt.event.*;
import java.net.Socket;
import java.io.IOException;
import javax.swing.JOptionPane;

public class Draw extends JPanel
{
	private int shamt_x = 80;
	private int shamt_y = 50;
	private User user;
	private Content content;
	private boolean gameStart;
	private boolean is_first;
	private int whichColor;
	private int[][] place;//0无,1黑,2白
	private ImageIcon black,white;
	private int size = 15;
	private int length = 50;
	private int gameState;
	private Socket s;
	private int gameEnd;
	private Computer computer;
	private Point new_point;
	private int[][] retractChess;
	private Qipan qipan;
	public Draw()
	{
		gameEnd = 0;
		place = new int[15][15];
		retractChess = new int[2][2];
		// String basePath = this.getClass().getResource("\\").getPointath();
		// System.out.println(basePath);
		// black = new ImageIcon(basePath + "./black.png");
		// white = new ImageIcon(basePath + "./white.png");
		black = new ImageIcon("./black.png");
		white = new ImageIcon("./white.png");
		this.setSize(800,800);
		// this.setBackground(new Color(220,191,157)); // 木色
		this.setBackground(new Color(192,192,192)); // 亮银色
		whichColor = 2;
		// whichColor = 1;
		user = new User();
		content = new Content("",0,0,0);
		is_first = true;
		new_point = new Point(-1,-1);
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
		if(new_point.getX() != -1 && new_point.getY() != -1)
		{
			g.setColor(Color.RED);
			g.fillOval(shamt_x + length * new_point.getX() - 3,shamt_y + length * new_point.getY() - 3,6,6);
			g.setColor(Color.BLACK);
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
					if(user.getChessColor() == 1) {
						retractChess[0][0] = x;
						retractChess[0][1] = y;
						retractChess[1][0] = -7;
						retractChess[1][1] = -7;
					}else if(user.getChessColor() == 2){
						retractChess[1][0] = x;
						retractChess[1][1] = y;
						retractChess[0][0] = -7;
						retractChess[0][1] = -7;
					}
					new_point.setX(x);
					new_point.setY(y);
					repaint();
					
					
					// System.out.println("paint " + user.getUsername() + " " + user.getChessColor() + " " + whichColor + " " + gameEnd);
					
					// changeChessColor();
					
					// System.out.println("paint " + user.getUsername() + " " + user.getChessColor() + " " + whichColor + " " + gameEnd);
					showGameInfo();
					if(gameState == 1)
					{
						GameData data = new GameData();
						data.setUser(user);
						data.setPoint(new Point(x,y));
						data.setContent(content);
						data.setGameStart(gameStart);
						sendIonfo2Server(data);
					}
					else if(gameState == 2)
					{
						// System.out.println("123");
						System.out.println("People : " + x + " , " + y);
						gameEnd = getWin(x,y);
						System.out.println("gameEnd : " + gameEnd);
						judge_win();
						changeChessColor();
						
						if(gameEnd == 0)
						{
							Point pp = new Point(x,y);
							computer.PersonPlay(pp);
							Point cp = computer.ComputerPlay();
							place[cp.getX()][cp.getY()] = 3 - user.getChessColor();
							gameEnd = getWin(cp.getX(),cp.getY());
							new_point = cp;
							judge_win();
							repaint();
							changeChessColor();
							System.out.println("AI : " + cp.getX() + " " + cp.getY());
						}
						
						// System.out.println("paint " + user.getUsername() + " " + user.getChessColor() + " " + whichColor + " " + gameEnd);
					}
				}
			}		
		});
	}
	
	
	public void connectServer()
	{
		try
		{
			s = new Socket("172.18.62.216",8888);
			// s = new Socket("127.0.0.1",8888);
			GameData d = new GameData();
			d.setUser(user);
			d.setContent(content);
			d.setGameStart(false);
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
		if(gameEnd == 0){
			if(whichColor == 1)
			{
				Qipan.gameInfo.setText("轮到黑方下棋");
			}
			else if(whichColor == 2)
			{
				Qipan.gameInfo.setText("轮到白方下棋");
			}
		}else{
			if(gameEnd == 1){
				Qipan.gameInfo.setText("黑方获胜");
			}else if(gameEnd == 2){
				Qipan.gameInfo.setText("白方获胜");
			}
		}
	}
	

	
	public void playNewGame(boolean ai)
	{
		place = new int[15][15];
		gameEnd = 0;
		gameStart = true;
		is_first = true;
		for(int i = 0;i < 15;++ i)
		{
			for(int j = 0;j < 15;++ j)
			{
				place[i][j] = 0;
			}
		}
		repaint();
		judge_win();
		if(!ai)
		{
			whichColor = 1;
			GameData data = new GameData();
			data.setContent(content);
			data.setGameStart(true);
			Point p = new Point(-1,-1);
			data.setUser(user);
			data.setPoint(p);
			data.setFirst(true);
			sendIonfo2Server(data);
			data.setFirst(false);
		}
		if(ai)
		{
			whichColor = 1;
			System.out.println(user.getChessColor() + " " + gameState + " ");	
			computer = new Computer(0,3 - user.getChessColor());
			if(user.getChessColor() == 2)
			{
				Point cp = computer.ComputerPlay();
				place[cp.getX()][cp.getY()] = 3 - user.getChessColor();
				System.out.println("AI : " + cp.getX() + " " + cp.getY());	
				new_point = cp;
				repaint();
				changeChessColor();
			}
		}
	}
	
	
	public void restart(){//
		// for(int i = 0;i < 15;++ i){
			// for(int j = 0;j < 15;++ j){
				// place[i][j] = 0;
			// }
		// }
		gameStart = false;
		gameState = 0;
		whichColor = 1;
		gameEnd = 0;
		content = new Content();
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
					System.out.println(data.getUser().getChessColor());
					if(data.getPoint().getX() == -1 && data.getPoint().getY() == -1) user.setChessColor(data.getUser().getChessColor());
					if(data.getPoint().getX() != -1 && data.getPoint().getY() != -1)
					{
						if( data.getPoint().getX() >= 0 && data.getPoint().getY() >= 0){
							place[data.getPoint().getX()][data.getPoint().getY()] = data.getUser().getChessColor();
							new_point.setX(data.getPoint().getX());
							new_point.setY(data.getPoint().getY());
						}
						
						if(data.getUser().getChessColor() == 1 && data.getPoint().getX() >= 0) {
							retractChess[0][0] = data.getPoint().getX();
							retractChess[0][1] = data.getPoint().getY();
							// System.out.println("retract:"+retractChess[0][0]);
							// System.out.println("retract:"+retractChess[1][0]);
						}else if(data.getUser().getChessColor() == 2 && data.getPoint().getX() >= 0){
							retractChess[1][0] = data.getPoint().getX();
							retractChess[1][1] = data.getPoint().getY();
							// System.out.println("retract:"+retractChess[0][0]);
							// System.out.println("retract:"+retractChess[1][0]);
						}
					}
					gameEnd = data.getWinner();
					content = data.getContent();
					if(data.getPoint().getX() >= 0)	changeChessColor();
					judge_win();
					repaint();
					System.out.println("data  " + user.getUsername() + " " + user.getChessColor() + " " + whichColor + " " + gameEnd);

					
					
					System.out.println("data  " + user.getUsername() + " " + user.getChessColor() + " " + whichColor + " " + gameEnd);
					
					showGameInfo();
					if(data.getPoint().getX() == -2&& data.getPoint().getY() == -2){
						judge_giveUp();
					}
					else if(data.getPoint().getX() == -3&& data.getPoint().getY() == -3){
						String[] options = {"同意", "拒绝"};
						int x = JOptionPane.showOptionDialog(null, "对方请求和棋",
								"和棋",
								JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
						if(x == 0){
							//System.out.println("重新开始");
							//重新开始游戏
							Content cont = getContent();
							//System.out.println("giveUp: "+draw.getContent().getGive_up());
							cont.setPeace(1);
							setContent(cont);
							//System.out.println("giveUp: "+draw.getContent().getGive_up());
							GameData newData = getData(-4); //同意和棋
							sendIonfo2Server(newData);
						}else{
							Content cont = getContent();
							//System.out.println("giveUp: "+draw.getContent().getGive_up());
							cont.setPeace(2);
							setContent(cont);
							//System.out.println("giveUp: "+draw.getContent().getGive_up());
							GameData newData = getData(-4); //不同意和棋
							sendIonfo2Server(newData);
							//System.out.println("取消");
						}
					}else if(data.getPoint().getX() == -4&& data.getPoint().getY() == -4){
						judge_peace(data.getContent().getPeace());
					}else if(data.getPoint().getX() == -5&& data.getPoint().getY() == -5){
						String[] options = {"同意", "拒绝"};
						int x = JOptionPane.showOptionDialog(null, "对方请求悔棋",
								"悔棋",
								JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
						if(x == 0){
							//System.out.println("重新开始");
							Content cont = getContent();
							//System.out.println("giveUp: "+draw.getContent().getGive_up());
							cont.setRetract(1);
							setContent(cont);
							//System.out.println("giveUp: "+draw.getContent().getGive_up());
							GameData newData = getData(-6); //同意悔棋
							sendIonfo2Server(newData);
						}else{
							Content cont = getContent();
							//System.out.println("giveUp: "+draw.getContent().getGive_up());
							cont.setRetract(2);
							setContent(cont);
							//System.out.println("giveUp: "+draw.getContent().getGive_up());
							GameData newData = getData(-6); //不同意悔棋
							sendIonfo2Server(newData);
							//System.out.println("取消");
						}
					}else if(data.getPoint().getX() == -6&& data.getPoint().getY() == -6){
						judge_retract(data.getContent().getRetract());
					}else if(data.getPoint().getX() == -7&& data.getPoint().getY() == -7){
						int[][] retracts = data.getRetractChess();
						retractChesses(retracts);
					}
					else if(data.getPoint().getX() == -8)
					{
						qipan.setOutput(data.getContent().getInput_content());
					}
					
					
				} catch (IOException e)
				{
					e.printStackTrace();
				}
				
			}
		}
	}
	
	public int getWin(int x,int y){
		int chessColor = place[x][y];
		int counter = 1;
		int indexX = x;
		int indexY = y;
		//向左搜索
		for(indexX=x-1;indexX >= 0;indexX --){
			if(place[indexX][y] == chessColor)counter ++;
			else break;
		}
		
		//向右搜索
		for(indexX = x+1;indexX <= 14;indexX ++){
			if(place[indexX][y] == chessColor)counter ++;
			else break;
		}
		if(counter >= 5){
			return chessColor;
		}else{
			counter = 1;
		}
		
		//向上搜索
		for(indexY=y-1;indexY >= 0;indexY --){
			if(place[x][indexY] == chessColor)counter ++;
			else break;
		}
		
		//向下搜索
		for(indexY = y+1;indexY <= 14;indexY ++){
			if(place[x][indexY] == chessColor)counter ++;
			else break;
		}
		if(counter >= 5){
			return chessColor;
		}else{
			counter = 1;
		}
		
		
		//向左上搜索
		for(indexX = x-1,indexY = y-1;indexX >= 0 && indexY >= 0;indexX --,indexY --){
			if(place[indexX][indexY] == chessColor)counter ++;
			else break;
		}
		
		//向右下搜索
		for(indexX = x+1,indexY = y+1;indexX <= 14 && indexY <= 14;indexX ++,indexY ++){
			if(place[indexX][indexY] == chessColor)counter ++;
			else break;
		}
		if(counter >= 5){
			return chessColor;
		}else{
			counter = 1;
		}
		//向右上搜索
		for(indexX = x+1,indexY = y-1;indexX <= 14 && indexY >= 0;indexX ++,indexY --){
			if(place[indexX][indexY] == chessColor)counter ++;
			else break;
		}
		
		//向左下搜索
		for(indexX = x-1,indexY = y+1;indexX >= 0 && indexY <= 14;indexX --,indexY ++){
			if(place[indexX][indexY] == chessColor)counter ++;
			else break;
		}
		if(counter >= 5){
			return chessColor;
		}else{
			return 0;
		}
	}
	public void judge_giveUp(){
		if(content.getGive_up() != 0){
			if(content.getGive_up() == 1){
				String[] options = {"确定"};
				int x = JOptionPane.showOptionDialog(null, "黑方认输",
				"认输",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			}else{
				String[] options = {"确定"};
				int x = JOptionPane.showOptionDialog(null, "白方认输",
				"认输",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			}
			restart();
		}
	}
	
	public void judge_peace(int agree){
		if(agree == 1){
			String[] options = {"确认"};
			int x = JOptionPane.showOptionDialog(null, "双方和棋",
					"和棋",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			restart();		
		}else{
			String[] options = {"确认"};
			int x = JOptionPane.showOptionDialog(null, "对方不同意和棋",
					"和棋",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

		}
	}
	
	public void judge_retract(int agree){
		System.out.println("agree"+agree);
		if(agree == 1){
			String[] options = {"确认"};
			int x = JOptionPane.showOptionDialog(null, "对方同意悔棋",
					"悔棋",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			//悔棋操作
			GameData newData = getData(-7);
			newData.setRetractChess(retractChess);////
			sendIonfo2Server(newData);
		}else{
			String[] options = {"确认"};
			int x = JOptionPane.showOptionDialog(null, "对方不同意悔棋",
					"悔棋",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		}
	}
	
	public void retractChesses(int[][] array){
		// System.out.println("retract"+array[0][0]);
		// System.out.println("retract"+array[1][0]);
		int retractCounter = 0;
		if(array[0][0] >= 0){
			retractCounter++;
			place[array[0][0]][array[0][1]] = 0;
		}
			
		if(array[1][0] >= 0){
			retractCounter++;
			place[array[1][0]][array[1][1]] = 0;
		}
		repaint();
		if(retractCounter == 1){
			changeChessColor();
		}
	}
	
	public void judge_win()
	{
		if(user.getChessColor() == gameEnd && gameEnd != 0)
		{
			JOptionPane.showConfirmDialog(null,"恭喜你获得胜利!");
			restart();		
			// s.close();
		}
		if(user.getChessColor() != gameEnd && gameEnd != 0)
		{
			JOptionPane.showConfirmDialog(null,"很遗憾你失败了!");
			restart();		
			// s.close();
		}
		if(user.getChessColor() == 1 && is_first)
		{
			JOptionPane.showConfirmDialog(null,"游戏开始，你是黑方！");
			is_first = false;
		}
		if(user.getChessColor() == 2 && is_first)
		{
			JOptionPane.showConfirmDialog(null,"游戏开始，你是白方！");
			is_first = false;
		}
	}
	public GameData getData(int num){
		GameData data = new GameData();
		data.setUser(user);
		data.setPoint(new Point(num,num));
		data.setContent(content);
		data.setGameStart(gameStart);
		return data;
	}
	public User getUser()
	{
		return user;
	}
	public void setUser(User user)
	{
		this.user = user;
	}
	public int getGameState()
	{
		return gameState;
	}
	public void setGameState(int gameState)
	{
		this.gameState = gameState;
	}
	
	public Content getContent()
	{
		return this.content;
	}
	public void setContent(Content content)
	{
		this.content = content;
	}
	public void setQipan(Qipan qipan)
	{
		this.qipan = qipan;
	}
	public Qipan getQipan()
	{
		return this.qipan;
	}
}