package eustrosoft;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Server extends Thread {

	private final int PORT_NUMBER = 3000;
	private ServerSocket serverSocket;
	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;
	private FileOutputStream fos;
	private File path;
	String message;

	public void start() {
		try {
			serverSocket = new ServerSocket(PORT_NUMBER);

			System.out.println("Server started!");
			path = new File("/home/yadzuka/workspace/snta.qxyz.ru/docs/", "vote.html");
			System.out.println(path.exists());
			fos = new FileOutputStream(path);
			
			while (true) {
				socket = serverSocket.accept();
				System.out.println("Client connected!");

				input = new BufferedReader
						(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
				output = new PrintWriter(socket.getOutputStream());

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
				output.
				output.println(path);
				output.flush();

				System.out.println("Client disconnected!");

				socket.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				serverSocket.close();
				output.close();
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
