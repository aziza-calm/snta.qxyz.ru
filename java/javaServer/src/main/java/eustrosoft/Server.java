package eustrosoft;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.Vector;

public class Server extends Thread {

	public int PORT_NUMBER = 3000;
	public static String WWW_ROOT_DIR = "/home/yadzuka/workspace/snta.qxyz.ru/";
	//
	private ServerSocket serverSocket;
	private Socket socket;
	private BufferedReader input;
	private FileOutputStream fos;
	private Hashtable<String, byte[]> filesHash;
	
	private String [] names = {"test1","test2","test3"};
	private String[] files = { "docs/vote.html", };
	private String message;

	public void start() {
		PrintWriter output = null;
		try {
			// getFileBytes();
			serverSocket = new ServerSocket(PORT_NUMBER);
			do_log("Server started!");

			while (true) {
				Vector<String> reqHeader = new Vector();
				socket = serverSocket.accept();
				do_log("Client connected! : " + socket.getInetAddress().toString());

				input = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
				OutputStream out_stream = socket.getOutputStream();
				
				output = new PrintWriter(out_stream);

				do
				{
					message = input.readLine();
					if(message == null) message =""; //!!!!
					reqHeader.add(message);
					do_debug_log("while : '" + message +"'");
				}while (message.length() !=0 );

				String reqURI = getRequestURI(out_stream, reqHeader);

				if(reqURI != null) {
					String[] FilePath = getPathByURI(reqURI);
					if(FilePath != null)
					{
						if(FilePath[0].endsWith("add_me.js")) {
							//String name = reqHeader.get(index)
									
							//names[2] = name;
						}
						
					 do_debug_log(FilePath[0] + "," + FilePath[1]);
					 //
					 write_http_file(out_stream,FilePath);
					}
					else 
					{
						do_http_error(output, 404, "File not found (not defined!)" );
					}
				}
				output.flush();
				do_log("Client disconnected!");
				out_stream.close();
				socket.close();
			}
		} catch (IOException ex) {
			do_err(ex);
		}catch (Exception e) {
			do_err(e);
		} 
		finally {
			try {
				if(serverSocket != null) serverSocket.close();
				if(output != null) output.close();
				if(input != null) input.close();
				// fos.close();
			} catch (Exception e) {
				do_err(e);
			}
		}
	}

	String[] getPathByURI(String uri) {
		if (uri == null)
			return (null);
		switch (uri) {
		case "/":
		case "/vote.html":
			return (new String[] { "docs/vote.html", "text/html" });
		case "/js/vote.js":
			return (new String[] { "docs/js/vote.js", "application/javascript" });
		case "/js/add_me.js":
			return new String[] { "docs/js/add_me.js", "application/javascript" };
		case "/js/voters":
			return new String[] { "docs/js/voters", "text/javascript" };
		default:
			return (null);
		}
	} // getPathByURI(String uri)

	/*
	 * private void getFileBytes() throws IOException { try { p = new String[] { new
	 * File("snta.qxyz.ru/docs/vote.html"). getAbsolutePath().replace(new
	 * String("/snta.qxyz.ru/java/javaServer/"),"/"), new
	 * File("snta.qxyz.ru/docs/js/add_me.js"). getAbsolutePath().replace(new
	 * String("/snta.qxyz.ru/java/javaServer/"),"/"), new
	 * File("snta.qxyz.ru/docs/js/voters"). getAbsolutePath().replace(new
	 * String("/snta.qxyz.ru/java/javaServer/"),"/") };
	 * 
	 * System.out.printf("%s ,%s ,%s \n", new File(p[0]).exists(), new
	 * File(p[1]).exists(), new File(p[2]).exists());
	 * 
	 * array = new byte[][] { Files.readAllBytes(Paths.get(p[0])),
	 * Files.readAllBytes(Paths.get(p[1])), Files.readAllBytes(Paths.get(p[2]))};
	 * 
	 * System.out.println();
	 * 
	 * filesHash = new Hashtable<String, byte[]>(); filesHash.put("vote.html",
	 * array[0]); filesHash.put("add_me.js", array[1]); filesHash.put("voters",
	 * array[2]);
	 * 
	 * fos = new FileOutputStream(p[0]); } catch (IOException ex) {
	 * System.out.println("Files does not exists."); } }
	 */
	
	public static void write_http_file(OutputStream out_stream,String [] filePath) throws IOException {
		//output.printf("%s, %s \n", filePath[0],filePath[1]);
		String path = WWW_ROOT_DIR + filePath[0];
		String mime_type = filePath[1];
		PrintWriter output = new PrintWriter(out_stream);
		File f = new File(path);
		
		if(!f.canRead()) { do_http_error(output, 500,"can't read file " + path); return; }
		//FileReader fr = new FileReader(f);
		FileInputStream fis = new FileInputStream(f);
		//
		output.println("HTTP/1.1 200 OK");
		output.println("Content-Type: " + mime_type);
		output.println("Content-Length: " + f.length());
		output.println();
		output.flush();
		byte b;
		while((b = (byte)fis.read())!=-1) {
			out_stream.write(b);
		}
		out_stream.flush();
	}
	
	public String getRequestURI(OutputStream out_stream, Vector<String> reqHeader) {
		PrintWriter output = new PrintWriter(out_stream);
		String message = null;
		if(reqHeader.size() <= 0) {
			do_http_error(output, 500,"ivalid request"); return(null);
		}
		message = reqHeader.get(0); 
		if(!message.startsWith("GET ")) {
			do_http_error(output, 500,"ivalid GET request"); return(null);
		}
		message = message.split(" ", 3)[1];
		return message;
		
	
	}
	public static  void do_http_hello(PrintWriter output)
	{
		output.println("HTTP/1.1 200 OK");
		output.println("Content-Type: text/html; charset=utf-8");
		output.println();
		output.println("<p>Hello World!</p>");
		output.flush();
	}
	
	public static  void do_http_error(PrintWriter output,int errorCode,String message) {
		output.println("HTTP/1.1 " + errorCode + " " + message);
		output.println("Content-Type: text/plain; charset=utf-8");
		output.println();
		output.println(errorCode + " " + message);
		output.flush();
	}
	
	public static  void do_debug_log(String msg) {
		do_log(msg);
	}

	
	public static  void do_log(String msg) {
		System.out.println(msg);
	}

	public static  void do_log() {
		System.out.println();
	}

	public static  void do_err(String msg) {
		System.err.println(msg);
	}

	public static  void do_err(Exception e) {
		System.err.println("Exception! : " + e.getMessage());
		e.printStackTrace(System.err);
	}
}
