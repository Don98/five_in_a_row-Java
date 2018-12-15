public class User
{
	private String username;
	private int chess_color;//1黑 0白
	public User()
	{
		super();
	}
	public User(String username1,int chess_color1)
	{
		this.username = username1;
		this.chess_color = chess_color;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username1)
	{
		this.username = username1;
	}
	public int getChessColor()
	{
		return chess_color;
	}
	public void setChessColor(int chess_color1)
	{
		this.chess_color = chess_color1;
	}
}