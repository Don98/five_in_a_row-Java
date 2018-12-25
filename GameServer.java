import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;


public class GameServer
{
	private ServerSocket server;
	private Map<User,Socket> players;
	private int [] randomStack;
	private int oneColor;
	private	int theOtherColor;
	private int whichColor;
	private int[][] place;
	private int gameEnd = 0;
	private Content content;
	private int counter;
	public GameServer()
	{
		place = new int[15][15];
		randomStack = new int[2];
		content = new Content();
		content.setInput_content("");
		randomStack[0] = 2;randomStack[1] = 1;
		oneColor =(int)(Math.random()*2)+1;
		theOtherColor = randomStack[oneColor-1];
		players = new HashMap<User,Socket>();
		whichColor = 1;
		counter = 0;				
		try
		{
			server = new ServerSocket(8888);
			System.out.println("服务器启动...");
			while(true)
			{	
				System.out.println("服务器等待客户端连接...");
				Socket s = server.accept();
				GameData data = (GameData)IOUtil.readObject(s.getInputStream());
				
				// Point p = new Point(-1,-1);
				// data.setPoint(p);
				// content.addInput_content(data.getContent().getInput_content());
				// data.setContent(content);
				// System.out.println(data.getUser().getChessColor());
				// if(counter == 0 && data.getUser().getChessColor() == 0) data.getUser().setChessColor(theOtherColor); 
				// if(counter == 1 && data.getUser().getChessColor() == 0) data.getUser().setChessColor(oneColor);
				// counter ++;
				
				players.put(data.getUser(),s);
				
				// IOUtil.writeObject(data,s.getOutputStream());
				
				System.out.println("用户：" + data.getUser().getUsername() + "上线了.");
				new Thread(new GameServerThread(data.getUser(),s)).start();
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	class GameServerThread implements Runnable
	{
		private User user;
		private Socket s;
		
		public GameServerThread(User user,Socket s)
		{
			this.user = user;
			this.s = s;
		}
		
		@Override
		public void run()
		{
			while(true)
			{
				try
				{
					if(s.getInputStream().available() != 0)
					{	
					
						GameData data = (GameData) IOUtil.readObject(s.getInputStream());
						Point p = new Point(-1,-1);
						// content.addInput_content(data.getContent().getInput_content());
						// data.setContent(content);
						
						
						// System.out.println(data.getPoint().getX()+ " " +data.getPoint().getY());
						// System.out.println(data.getGameStart());
						if(data.getFirst())
						{
							// System.out.println(data.getUser().getUsername() + " : " + data.getUser().getChessColor());
							if(counter == 0)
							{
								oneColor =(int)(Math.random()*2)+1;
								theOtherColor = randomStack[oneColor-1];
							}
							if(counter == 0 && data.getUser().getChessColor() == 0) 
							{
								data.getUser().setChessColor(theOtherColor); 
								data.setPoint(p);
								
								counter ++;
							}
							if(counter == 1 && data.getUser().getChessColor() == 0)
							{
								data.getUser().setChessColor(oneColor);
								data.setPoint(p);
								counter = 0;
							}
							IOUtil.writeObject(data,s.getOutputStream());
							// System.out.println("counter : " + counter);
						}
						
						else{
							// System.out.println("here2");
							if(data.getPoint().getX() != -1 && data.getPoint().getY() != -1)
							{
								Set<User> users = players.keySet();
								if(data.getPoint().getX() >= 0 && data.getPoint().getY() >= 0){
									place[data.getPoint().getX()][data.getPoint().getY()] = data.getUser().getChessColor();
									int t = getWin(data.getPoint().getX(),data.getPoint().getY());
									if(t != 0) gameEnd = t;
									else	gameEnd = 0;
									data.setWin(gameEnd);
									if(gameEnd != 0)
									{
										for(int i = 0; i < 15;i++)
										{
											for(int j = 0; j < 15;j++)
											{
												place[i][j] = 0;
											}
										}
									}
									for(User temp:users)
									{
										// if(temp.equals(user)) continue;
										// System.out.println("用户名 ：" + temp.getUsername() + " " + gameEnd);
										IOUtil.writeObject(data,players.get(temp).getOutputStream());
									}
									
								}else if(data.getPoint().getX() == -2){
									for(User temp:users)
									{
										// if(temp.equals(user)) continue;
										// System.out.println("用户名 ：" + temp.getUsername() + " " + gameEnd);
										IOUtil.writeObject(data,players.get(temp).getOutputStream());
									}
								}else if(data.getPoint().getX() == -3){
									for(User temp:users)
									{
										if(temp.equals(user)) continue;
										// System.out.println("用户名 ：" + temp.getUsername() + " " + gameEnd);
										IOUtil.writeObject(data,players.get(temp).getOutputStream());
									}
								}else if(data.getPoint().getX() == -4 && data.getContent().getPeace() == 2){//不同意和棋
									for(User temp:users)
									{
										if(temp.equals(user)) continue;
										// System.out.println("用户名 ：" + temp.getUsername() + " " + gameEnd);
										IOUtil.writeObject(data,players.get(temp).getOutputStream());
									}
								}else if(data.getPoint().getX() == -4 && data.getContent().getPeace() == 1){ //同意和棋
									for(User temp:users)
									{
										// if(temp.equals(user)) continue;
										// System.out.println("用户名 ：" + temp.getUsername() + " " + gameEnd);
										IOUtil.writeObject(data,players.get(temp).getOutputStream());
									}
								}else if(data.getPoint().getX() == -5 || data.getPoint().getX() == -6){
									for(User temp:users)
									{
										if(temp.equals(user)) continue;
										// System.out.println("用户名 ：" + temp.getUsername() + " " + gameEnd);
										IOUtil.writeObject(data,players.get(temp).getOutputStream());
									}
								}else if(data.getPoint().getX() == -7){
									int[][] retractChess = data.getRetractChess();
									if(retractChess[0][0] >= 0)
										place[retractChess[0][0]][retractChess[0][1]] = 0;
									if(retractChess[1][0] >= 0)
										place[retractChess[1][0]][retractChess[1][1]] = 0;
									for(User temp:users)
									{
										// if(temp.equals(user)) continue;
										// System.out.println("用户名 ：" + temp.getUsername() + " " + gameEnd);
										IOUtil.writeObject(data,players.get(temp).getOutputStream());
									}
								}
								else if(data.getPoint().getX() == -8)
								{
									System.out.println(data.getContent().getInput_content());
									for(User temp:users)
									{
										if(temp.equals(user)) continue;
										IOUtil.writeObject(data,players.get(temp).getOutputStream());
									}
								}
								// for(User temp:users)
								// {
									// if(temp.equals(user)) continue;
									// System.out.println("用户名 ：" + temp.getUsername() + " " + gameEnd);
									// IOUtil.writeObject(data,players.get(temp).getOutputStream());
								// }
								// users.clear();
							}
						}
						
						
					}
				} catch (IOException e)
				{
					e.printStackTrace();
					break;
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
	public static void main(String[] args)
	{
		new GameServer();
	}
}