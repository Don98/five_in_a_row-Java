//package test3;

import java.util.*;
import java.net.*;
import java.io.*;

public class Client extends Thread {
	String name;
	String ip;
	public BufferedReader reader;
	public BufferedWriter writer;
	Client(String name,String ip){
		this.name=name;
		this.ip=ip;
	}

	@Override public void run() {

		// Initialize Socket
		Socket socket = null;
		String message;

		try {
			message = new String("Hi! I am a client");
			socket = new Socket(ip, 1056);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			Clientin i = new Clientin(this);
			i.start();
			Clientout o = new Clientout(this);
			o.start();
		} catch (UnknownHostException e) {
			System.out.println("Error : Cannot find server." + e);
		} catch (IOException e) {
			System.out.println("Error : I/O Error." + e);
		}
	} 

	public String read() throws IOException {
		return reader.readLine();
	}
	
	public void send(String data) throws IOException {
		writer.write(name+":"+data);
		writer.newLine();
		writer.flush();
	}
	
	public static void main(String args[]) {
		System.out.println("read name and ip plz\n");
		Scanner cin=new Scanner(System.in);
		String name=cin.nextLine();
		String ip=cin.nextLine();
		Client c = new Client(name,ip);
		c.start();
	}
} 
