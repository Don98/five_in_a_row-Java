import javax.swing.JPanel;
import java.awt.*;

public class Draw extends JPanel
{
	private int shamt_x = 80;
	private int shamt_y = 50;
	
	public Draw()
	{
		this.setSize(800,800);
		// this.setBackground(new Color(220,191,157)); // 木色
		this.setBackground(new Color(192,192,192)); // 亮色
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		paintQipan(g);
	}
	
	public void paintQipan(Graphics g)
	{
		int size = 15;
		int length = 50;
		for(int i = 0;i < size;i++)
		{
			g.drawLine(shamt_x + 0,shamt_y + i * length,shamt_x + (size - 1) * length,shamt_y + i * length);
			g.drawLine(shamt_x + i * length ,shamt_y + 0 ,shamt_x + i * length,shamt_y + (size - 1) * length);
		}
	}
	
	public void paintQizi(Graphics g)
	{
		
	}
}