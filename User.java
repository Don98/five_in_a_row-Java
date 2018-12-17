import java.io.Serializable;

public class User implements Serializable
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
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + chess_color;
		result = prime * result + ((username == null) ? 0 :username.hashCode());
		return result;
	}
	
	public boolean equals(Object obj)
	{
		if(this == obj)	return true;
		if(obj == null) return false;
		if(getClass() != obj.getClass())	return false;
		User other = (User) obj;
		if(chess_color != other.chess_color)	return false;
		if(username == null)
		{
			if(other.username != null) return false;
		}	else if(!username.equals(other.username))	return false;
		return true;
	}
	
}