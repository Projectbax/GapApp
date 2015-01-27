package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RecieveThread extends Thread {

	private Client client;

	public RecieveThread(Client user) {
		this.client = user;
	}

	public void run() {
		BufferedReader in;

		try {
			in = new BufferedReader(new InputStreamReader(
					client.serverSocket.getInputStream()));
			while (true) {
				
				int number = 0;
				String firstLine = in.readLine();
				String[] fLH = firstLine.split(" ");
				number = Integer.valueOf(fLH[3]);
				char[] charArr = new char[number]; 
				
				in.read(charArr, 0, number);
				
				String body = new String(charArr);
				
				client.listener(firstLine, body);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}