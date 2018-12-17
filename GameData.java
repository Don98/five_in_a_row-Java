import java.io.Serializable;

public class GameData implements Serializable
{
	private User user;
	private Point p;
	public GameData()
	{
		super();
	}
	public GameData(User user,Point p)
	{
		super();
		this.user = user;
		this.p = p;
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
}