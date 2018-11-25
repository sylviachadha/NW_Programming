package client;

import java.net.*;
import java.util.Date;
import java.io.*;

public class Client {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	public static int SERVER_PORT = 5000;

	// Make a connection to the server
	private void connectToServer() {
		try {
			socket = new Socket("10.99.88.224", Client.SERVER_PORT);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			System.out.println("CLIENT: Cannot connect to server");
			System.exit(-1);
		}
	}

	// Disconnect from the server
	private void disconnectFromServer() {
		try {
			socket.close();
		} catch (IOException e) {
			System.out.println("CLIENT: Cannot disconnect from server");
		}
	}

	private void askForAdd(int num1, int num2) {
		connectToServer();
		out.println("Add " + num1 + " " + num2);
		out.flush();
		try {
			String response = in.readLine();
			System.out.println("SERVER:" + response);
		} catch (IOException e) {
			System.out.println("CLIENT: Cannot receive time from server");
		}
		disconnectFromServer();
	}

	private void askForDivide(int num1, int num2) {
		connectToServer();
		out.println("Divide " + num1 + " " + num2);
		out.flush();
		try {
			String response = in.readLine();
			System.out.println("SERVER:" + response);
		} catch (IOException e) {
			System.out.println("CLIENT: Cannot receive time from server");
		}
		disconnectFromServer();
	}

	private void askForMultiply(int num1, int num2) {
		connectToServer();
		out.println("Multiply " + num1 + " " + num2);
		out.flush();
		try {
			String response = in.readLine();
			System.out.println("SERVER:" + response);
		} catch (IOException e) {
			System.out.println("CLIENT: Cannot receive time from server");
		}
		disconnectFromServer();
	}

	public static void main(String[] args) {
		Client c = new Client();

		Date d1 = new Date();

		for (int i = 1; i <= 1000; i++) {
			c.askForAdd(55, 99);
			c.askForDivide(50, 10);
			c.askForMultiply(2, 2);
		}

		Date d2 = new Date();
		System.out.println(d2.getTime() - d1.getTime()); // gives the time difference in milliseconds.
		System.out.println((d2.getTime() - d1.getTime()) / 1000); // gives the time difference in seconds.

	}

}
