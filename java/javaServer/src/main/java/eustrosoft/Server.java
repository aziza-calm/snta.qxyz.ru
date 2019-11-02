package eustrosoft;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;

public class Server extends Thread {

	private final int PORT_NUMBER = 3002;
	private ServerSocket serverSocket;
	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;
	private FileOutputStream fos;
	private Hashtable<String, byte[]> filesHash;

	private String[] p;
	private byte[][] array;
	private String message;

	public void start() {
		try {
			getFileBytes();

			serverSocket = new ServerSocket(PORT_NUMBER);

			System.out.println("Server started!");

			while (true) {
				socket = serverSocket.accept();
				System.out.println("Client connected!");

				input = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
				output = new PrintWriter(socket.getOutputStream());

				while (!input.ready())
					;

				System.out.println();
				while (input.ready()) {
					message = input.readLine();

				}

				// DataInputStream dis = new DataInputStream(new BufferedInputStream(new
				// FileInputStream(file)));
				// dis.write(filesHash.get("vote.html"));
				System.out.println(message);
				if (message == "GET") {
					Writer wr = new PrintWriter(socket.getOutputStream());
					wr.write(array[0][1]);
				}

				output.println("HTTP/1.1 200 OK");
				output.println("Content-Type: text/html; charset=utf-8");
				output.println();
				output.println("<p>Hello World!</p>");
				output.println(filesHash.get("vote.html"));

				// dis.flush();
				fos.flush();
				output.flush();

				System.out.println("Client disconnected!");

				socket.close();
				// dis.close();
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

	private void getFileBytes() throws IOException {
		try {
			p = new String[] { new File("snta.qxyz.ru/docs/vote.html").getAbsolutePath().replace(new String("\\snta.qxyz.ru\\java\\javaServer\\"),"\\"),
					new File("snta.qxyz.ru/docs/js/add_me.js").getAbsolutePath().replace(new String("\\snta.qxyz.ru\\java\\javaServer\\"),"\\"),
					new File("snta.qxyz.ru/docs/js/voters").getAbsolutePath().replace(new String("\\snta.qxyz.ru\\java\\javaServer\\"),"\\") };
			System.out.printf("%s ,%s ,%s \n", new File(p[0]).exists(), new File(p[1]).exists(), new File(p[2]).exists());
			array = new byte[][] { 
				Files.readAllBytes(Paths.get(p[0])),
				Files.readAllBytes(Paths.get(p[1])),
				Files.readAllBytes(Paths.get(p[2]))};
			System.out.println();
			filesHash = new Hashtable<String, byte[]>();
			filesHash.put("vote.html", array[0]);
			filesHash.put("add_me.js", array[1]);
			filesHash.put("voters", array[2]);

			fos = new FileOutputStream(p[0]);
		} catch (IOException ex) {
			System.out.println("Files does not exists.");
		}
	}
}
