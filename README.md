Multithreaded Web Server with LRU Caching

A high-performance, multithreaded web server designed to efficiently handle concurrent requests. This server utilizes an LRU (Least Recently Used) caching strategy to optimize the retrieval of frequently accessed data, significantly improving response times.

Key Features
Multithreading: Handles multiple client requests simultaneously using a thread pool.

LRU Caching: Implements a Least Recently Used caching mechanism to store frequently accessed products, enhancing server performance.

Scalability: Designed to scale with increasing traffic by efficiently managing resources.

Real-world Application: Simulates a product information retrieval system, demonstrating practical use cases for caching in web applications.

Technologies Used
Java: Utilizes Java's robust multithreading capabilities and data structures.

Socket Programming: Employs socket programming for client-server communication.

LRU Cache Implementation: Custom implementation of an LRU cache using a doubly linked list and hashmap.

Project Structure
Server: Handles client requests and manages the LRU cache.

Client: Simulates client requests to the server.

Getting Started
Clone the repository.

Build and run the server.

Use the client application to test the server.

Contributions
Contributions are welcome! Feel free to enhance the caching strategy, improve performance, or add new features.
