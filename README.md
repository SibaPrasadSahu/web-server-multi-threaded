Client-Server Project
A simple client-server application using Java sockets for communication.

Overview
This project consists of two main components:

Client: Connects to the server, sends a product name, and receives the corresponding price.

Server: Listens for incoming connections, processes client requests, and responds with product prices.

Features
Multi-threaded Server: Handles multiple client connections concurrently using a fixed thread pool.

Product Price Retrieval: The server maintains a map of products with their prices and returns the price for a given product name.

Setup and Usage
Prerequisites
Java Development Kit (JDK) installed on your system.

A compatible IDE or text editor for running Java programs.

Running the Server
Compile the Server.java file.

Run the Server class. The server will start listening on port 9999.

The server will continuously accept client connections.

Running the Client
Compile the Client.java file.

Run the Client class.

Enter a product name when prompted (e.g., "a", "b", "c").

The client will send the product name to the server and display the received price.

Code Structure
Client: Located in Client.java, this class establishes a connection to the server, sends a product name, and prints the server's response.

Server: Located in Server.java, this class sets up a server socket, accepts client connections, and uses a thread pool to handle requests concurrently.

Known Issues
The server runs indefinitely until manually stopped.

The client does not handle invalid server responses.

Future Improvements
Implement error handling for invalid product names and server errors.

Enhance the server to handle a larger number of concurrent connections efficiently.

Add more features like user authentication or data encryption.

Feel free to modify this README to better suit your project's specific needs or additional features you might add.

![one](https://github.com/user-attachments/assets/eaccb03d-9af0-4400-9358-892d28db2005)
