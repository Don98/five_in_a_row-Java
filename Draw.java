import javax.swing.JPanel;
import java.awt.*;

public class Draw extends JPanel
{
	private int shamt_x = 80;
	private int shamt_y = 50;
	private User user;
	private boolean gameStart;
	private int whichColor;
	private int[][] place;//0无,1黑,2白
	
	public Draw()
	{
		place = new int[15][15];
		this.setSize(800,800);
		// this.setBackground(new Color(220,191,157)); // 木色
		this.setBackground(new Color(192,192,192)); // 亮银色
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		paintQipan(g);
	}
	
	public void paintQipan(Graphics a)
	{		
		int size = 15;
		int length = 50;
		
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
		for(int )
	}
	public void playNewGame()
	{
		place = new int[15][15];
		gameStart = true;
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
}