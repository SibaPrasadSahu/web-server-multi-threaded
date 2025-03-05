package project;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Connecting to server");
            Socket sock = new Socket("127.0.0.1", 9999);
            System.out.println("Connected to server");

            System.out.print("Enter product name (a-z): ");
            String product = scanner.nextLine();

            try  {
            	InputStream in = sock.getInputStream(); 
            	OutputStream out = sock.getOutputStream();
            	System.out.println("Sending product information: ");
                out.write(product.getBytes());

                byte[] response = new byte[100];
                int bytesRead = in.read(response);

                if (bytesRead > 0) {
                    String strResponse = new String(response, 0, bytesRead).trim();
                    System.out.println("Obtained response is: " + strResponse);
                } else
                    System.out.println("No data received from server.");
            } finally {
                sock.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
