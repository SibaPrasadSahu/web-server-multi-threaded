MULTITHREADED WEB SERVER WITH LRU CACHING

![image](https://github.com/user-attachments/assets/fd528fd8-ee89-41e6-a9d1-fec51a457dcc)

A high-performance, multithreaded web server designed to efficiently handle concurrent requests. This server utilizes an LRU (Least Recently Used) caching strategy to optimize the retrieval of frequently accessed data, significantly improving response times.

Key Features:
1. Multithreading: Handles multiple client requests simultaneously using a thread pool.
2. LRU Caching: Implements a Least Recently Used caching mechanism to store frequently accessed products, enhancing server performance.
3.Scalability: Designed to scale with increasing traffic by efficiently managing resources.
4.Real-world Application: Simulates a product information retrieval system, demonstrating practical use cases for caching in web applications.

Technologies Used
1. Java: Utilizes Java's robust multithreading capabilities and data structures.
2. Socket Programming: Employs socket programming for client-server communication.
3. LRU Cache Implementation: Custom implementation of an LRU cache using a doubly linked list and hashmap.

Project Structure
Server: Handles client requests and manages the LRU cache.

Client: Simulates client requests to the server.
