package eustrosoft;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;

public class Server extends Thread {

	private final int PORT_NUMBER = 3000;
	private ServerSocket serverSocket;
	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;
	private FileOutputStream fos;
	private Hashtable<String, byte[]> filesHash;
	String message;

	public void start() {
		try {
			
			String []p = {new File("snta.qxyz.ru/docs/vote.html").getAbsolutePath(),
					new File("snta.qxyz.ru/docs/js/add_me.js").getAbsolutePath(),
					new File("snta.qxyz.ru/docs/js/voters").getAbsolutePath()
			};
			byte[][]array = {Files.readAllBytes(Paths.get(p[0])),
					         Files.readAllBytes(Paths.get(p[1])),
					         Files.readAllBytes(Paths.get(p[2]))
			};
			
			filesHash = new Hashtable<String, byte[]>();
			filesHash.put("vote.html", array[0]);
			filesHash.put("add_me.js", array[1]);
			filesHash.put("voters", array[2]);
			
			serverSocket = new ServerSocket(PORT_NUMBER);

			System.out.println("Server started!");
			
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
				output.println("<p>Hello World!</p>");
				
				fos.flush();
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
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
