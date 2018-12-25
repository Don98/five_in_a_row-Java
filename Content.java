import java.io.Serializable;

public class Content implements Serializable
{
	private String input_content;
	private int retract;
	private int give_up;
	private int peace;
	
	public Content()
	{
		super();
	}
	public Content(String input_content,int retract,int give_up,int peace)
	{
		this.input_content = input_content;
		this.retract = retract;
		this.give_up = give_up;
		this.peace = peace;
	}
	public String getInput_content()
	{
		return this.input_content;
	}
	public void setInput_content(String input_content)
	{
		this.input_content = input_content;
	}
	public void addInput_content(String input_content)
	{
		this.input_content += input_content;
	}
	public int getRetract()
	{
		return this.retract;
	}
	public void setRetract(int retract)
	{
		this.retract = retract;
	}
	public int getGive_up()
	{
		return this.give_up;
	}
	public void setGive_up(int give_up)
	{
		this.give_up = give_up;
	}
	public int getPeace()
	{
		return this.peace;
	}
	public void setPeace(int peace)
	{
		this.peace = peace;
	}
}