import java.io.Serializable;

public class GameData implements Serializable
{
	private User user;
	private Point p;
	private Content content;
	private int whichColor;
	private int win;
	private int[][] retractChess; 
	private boolean gameStart;
	private boolean first;
	public GameData()
	{
		super();
	}
	public GameData(User user,Point p)
	{
		super();
		this.user = user;
		this.p = p;
		this.win = 0;
		this.gameStart = false;
		this.first = false;
	}
	public User getUser()
	{
		return this.user;
	}
	public void setUser(User user)
	{
		this.user = user;
	}
	
	public Point getPoint()
	{
		return this.p;
	}
	
	public void setPoint(Point p)
	{
		this.p = p;
	}
	
	public Content getContent()
	{
		return this.content;
	}
	
	public void setContent(Content content)
	{
		this.content = content;
	}
	
	public int getWinner()
	{
		return this.win;
	}	
	public void setWin(int win)
	{
		this.win = win;
	}
	public int getWhichColor()
	{
		return this.whichColor;
	}
	public void setWhichColor(int whichColor)
	{
		this.whichColor = whichColor;
	}
	public int[][] getRetractChess(){
		return this.retractChess;
	}
	public void setRetractChess(int[][] array){
		this.retractChess = array;
	}
	public boolean getGameStart()
	{
		return this.gameStart;
	}
	public void setGameStart(boolean gameStart)
	{
		this.gameStart = gameStart;
	}
	public boolean getFirst()
	{
		return this.first;
	}
	public void setFirst(boolean first)
	{
		this.first = first;
	}
}