import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class Server extends Thread {
	public ServerSocket svrSocket = null;
	public Socket socket = null;

	public DataInputStream dataStream = null;
	public PrintStream printStream = null;
	public DataOutputStream dataoutputStream = null;
	public String message;
	public BufferedReader charStream = new BufferedReader(new InputStreamReader(System.in));
	
	ClientThread[] client=new ClientThread[2];
	public void run() {
		try {
			svrSocket = new ServerSocket(1056);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (int i=0;i<=1;i++) {
			try {
				socket = svrSocket.accept();
				System.out.println("\nConnect to Client!\n");
				client[i]=new ClientThread(this,socket,i);
				client[i].start();
				
			} catch (UnknownHostException e) {
				System.out.println("Error : Cannot find server." + e);
			} catch (IOException e) {
				System.out.println("Error : I/O Error." + e);
			}
		}
	}
	
	public static void main(String args[]) {
		Server svr = new Server();
		svr.start();
	}
}
