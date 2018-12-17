import java.io.*;
import java.util.*;
import java.net.*;

public class Clientin extends Thread {
	Client clt;
	
	public Clientin(Client clt) {
		this.clt = clt;
	}
	
	public void run() {
		try {
			Scanner cin = new Scanner(System.in);
			while (true) {
				String saying = cin.nextLine();
				if (saying == null) break;
				clt.send(saying);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}
}
