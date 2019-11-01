package eustrosoft;

/**
 * Hello world!
 *
 */
public class JavaServer
{
	
    public static void main( String[] args )
    {
        try {
        	Server server = new Server();
        	
        	server.start();
        }catch(Exception ex) {
        	System.out.println(ex.getMessage());
        	System.out.println("Не удалось установить соединение.");
        }
    }
}
