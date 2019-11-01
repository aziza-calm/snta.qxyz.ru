package eustrosoft;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Server {

	String message;

	public void start() {
		try (ServerSocket serverSocket = new ServerSocket(8082)) {
			System.out.println("Server started!");
			File path = new File("/vote");
			
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("Client connected!");

				try (BufferedReader input = new BufferedReader(
						new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
						PrintWriter output = new PrintWriter(socket.getOutputStream())) {

					while (!input.ready())
						;

					System.out.println();
					while (input.ready()) {
						System.out.println(input.readLine());
					}

					output.println("HTTP/1.1 200 OK");
					output.println("Content-Type: text/html; charset=utf-8");
					output.println();
					output.println("<p>Привет всем!</p>");
					output.flush();

					System.out.println("Client disconnected!");
					
					socket.close();
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
