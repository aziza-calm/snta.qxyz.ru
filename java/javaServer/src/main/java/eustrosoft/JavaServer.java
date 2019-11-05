package eustrosoft;

/**
 * @Eustrosoft java web service
 * 
 * @author yadzuka
 * @version 1.0
 *
 */
public class JavaServer
{
	
    public static void main( String[] args )
    {
    	Server server = new Server();
        try {
        	server.start();
        }catch(Exception ex) {
        	System.err.println("JavaServer.main()");
        	System.err.println(ex.getMessage());
        	System.err.println("Server downs");
        }
    }
}
