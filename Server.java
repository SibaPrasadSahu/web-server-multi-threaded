package project;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

class QuoteService {
    private static final Map<String, String> productInfo = new HashMap<>();
    
    static {
        productInfo.put("a", "100");
        productInfo.put("b", "200");
        productInfo.put("c", "300");
    }
    
    public String getQuote(String product) {
        return productInfo.get(product);
    }
}

class Task implements Runnable {
    private final Socket socket;
    private final String name;
    private static final AtomicInteger count = new AtomicInteger(0);
    private static final QuoteService quoteService = new QuoteService();

    Task(String name, Socket socket) {
        this.socket = socket;
        this.name = name;
    }

    public void run() {
        try (
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            Socket closeableSocket = socket // This ensures socket is closed properly
        ) {
            byte[] request = new byte[1024]; // Increased buffer size
            int bytesRead = in.read(request);

            if (bytesRead > 0) {
                String productName = new String(request, 0, bytesRead).trim();
                System.out.println("Received product name - " + productName);

                String price = quoteService.getQuote(productName);
                if (price == null) {
                    price = "Invalid Product";
                }

                out.write(price.getBytes());
                System.out.println("Sent response number: " + count.incrementAndGet());
            } else {
                System.out.println("No data received from client.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class Server {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(9);
        
        try (ServerSocket serverSocket = new ServerSocket(9999)) {
            System.out.println("Started listening on port 9999");
            
            while (true) {
                try {
                    System.out.println("Waiting for client...");
                    Socket clientSocket = serverSocket.accept();
                    pool.execute(new Task("task", clientSocket));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
        /*problems faced while making this:
         * binding socket problem cause i put the server socket in an infinite loop
         */
    }
}


