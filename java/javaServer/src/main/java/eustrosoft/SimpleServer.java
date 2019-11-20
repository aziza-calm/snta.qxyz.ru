package eustrosoft;
import java.io.*;
import java.net.*;
import java.nio.charset.*;
public class SimpleServer {
	static PrintWriter writer;
	static BufferedReader reader;
	static ServerSocket servSocket;
	static Socket socket;
	public static void main(String[] args) {
		try {servSocket = new ServerSocket(3000);
			 socket = servSocket.accept();
			 writer  = new PrintWriter(socket.getOutputStream());
			 reader = new BufferedReader(new InputStreamReader(socket.getInputStream(),StandardCharsets.UTF_8));
			 writer.println("Hello World!");
			 writer.flush();} catch (IOException e) {e.printStackTrace();}
		finally {try {if(socket!=null)socket.close();
				      if(writer!=null)writer.close();
				      if(reader!=null)reader.close();}catch(Exception e) {}
		}
	}
}
