import java.io.IOException;

public class Clientout extends Thread {

	Client clt;
	
	public Clientout(Client clt) {
		this.clt = clt;
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				String str = clt.read();
				System.out.println(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}
}
