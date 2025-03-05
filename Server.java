package project;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

class Node {
    String key;
    String value;
    Node prev;
    Node next;

    public Node(String key, String value) {
        this.key = key;
        this.value = value;
    }
}

class LRUCache {
    private final int capacity;
    private final Map<String, Node> cache;
    private final Node head;
    private final Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = new Node("", "");
        this.tail = new Node("", "");
        head.next = tail;
        tail.prev = head;
    }

    public String get(String key) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            remove(node);
            insert(node);
            return node.value;
        }
        return null; // Key not found
    }

    public void put(String key, String value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            remove(node);
            insert(node);
        } else {
            if (cache.size() == capacity) {
                Node lruNode = tail.prev;
                remove(lruNode);
                cache.remove(lruNode.key);
            }
            Node newNode = new Node(key, value);
            insert(newNode);
            cache.put(key, newNode);
        }
    }

    private void remove(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    private void insert(Node node) {
        Node prev = head;
        Node next = head.next;
        prev.next = node;
        next.prev = node;
        node.prev = prev;
        node.next = next;
    }
}

class QuoteService {
    private static final Map<String, String> productInfo = new HashMap<>();
    private static final LRUCache cache = new LRUCache(10);

    static {
        for (char c = 'a'; c <= 'z'; c++) {
            productInfo.put(String.valueOf(c), String.valueOf(c * 100));
        }
    }

    public String getQuote(String product) {
        String price = cache.get(product);
        if (price != null) {
            return price;
        }

        price = productInfo.get(product);
        if (price != null) {
            cache.put(product, price);
        } else {
            price = "Invalid Product";
        }

        return price;
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
            Socket closeableSocket = socket
        ) {
            byte[] request = new byte[1024];
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
    }
}
