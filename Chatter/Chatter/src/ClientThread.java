import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ClientThread extends Thread{
	public BufferedReader reader;
	public BufferedWriter writer;
	public Server svr;
	public Socket soc;
	int id;
	public ClientThread(Server svr,Socket soc,int id) throws IOException {
		this.svr=svr;
		this.id=id;
		this.soc=soc;
		reader = new BufferedReader(new InputStreamReader(soc.getInputStream()));
		writer = new BufferedWriter(new OutputStreamWriter(soc.getOutputStream()));
	}
	public void run() {
		while (true) {
			try{
				String message=read();
				svr.client[id^1].send(id+":"+message);
			}	catch (IOException e) {
				System.out.println("Error : I/O Error." + e);
			}
		}
	}	
	
	public String read() throws IOException {
		return reader.readLine();
	}
	
	public void send(String data) throws IOException {
		writer.write(data);
		writer.newLine();
		writer.flush();
	}
}
