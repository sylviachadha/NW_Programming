package server;

import java.net.*; // all socket stuff is in here
import java.io.*;

public class Server {
    public static int SERVER_PORT = 5000; // arbitrary, but above 1023
    // Helper method to get the ServerSocket started
    private ServerSocket goOnline() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("SERVER online");
        } catch (IOException e) {
            System.out.println("SERVER: Error creating network connection");
        }
        return serverSocket;
    }

    // Handle all requests
    private void handleRequests(ServerSocket serverSocket) {
        while (true) {
            Socket socket = null;
            BufferedReader in = null;
            PrintWriter out = null;
            try {
                // Wait for an incoming client request
                socket = serverSocket.accept();
                // At this point, a client connection has been made
                in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream());

            } catch (IOException e) {
                System.out.println("SERVER: Error connecting to client");
                System.exit(-1);
            }
            // Read in the client's request
            try {
                String request = in.readLine();
                System.out.println("CLIENT: " + request);
                String[] parts = request.split(" ");
                String response = "";
                if (parts.length == 3) {
                    int num1 = Integer.parseInt(parts[1]);
                    int num2 = Integer.parseInt(parts[2]);
                    if (request.startsWith("Add")) {
                        response = String.valueOf(Calc_addition(num1, num2));
                        out.println(response);
                    } else if(request.startsWith("Multiply")) {
                        response = String.valueOf(Calc_multiply(num1, num2));
                        out.println(response);
                    } else if(request.startsWith("Divide")) {
                        response = String.valueOf(Calc_divide(num1, num2));
                        out.println(response);
                    }
                } else
                    System.out.println("SERVER: Unknown request: " + request);
                out.flush(); // Now make sure that the response is sent
                socket.close(); // We are done with the client's request
            } catch (IOException e) {
                System.out.println("SERVER: Error communicating with client");
            }
        }
    }

    public static void main(String[] args) {
        Server s = new Server();
        ServerSocket ss = s.goOnline();
        if (ss != null)
            s.handleRequests(ss);
    }


    public static int Calc_addition(int num1, int num2) {
        // To calculate Sum
        int sum = num1 + num2;
        return sum;
    }

    public static int Calc_difference(int num1, int num2) {
        int diff;
        // To calculate Difference
        if (num1 > num2) {
            diff = (num1 - num2);
        } else {
            diff = (num2 - num1);
        }
        return diff;
    }

    public static int Calc_multiply(int num1, int num2) {
        int mul;
        // To calculate Multiply
        mul = num1 * num2;
        return mul;
    }

    public static float Calc_divide(float num1, float num2) {
        // To calculate Division
        float div = num1 / num2;
        return div;
    }
} 