import java.io.IOException;

/**
 * @brief The ChatServer class is the application entry point, containing the main. 
 * Thus, the application can be run with 'java ChatServer' with an optional argument
 * to indicate the server listening port.
 * The main calls either the Server constructor with a default port, or the one with
 * the port specified as argument.
 */
public class ChatServer {

    public static void main(String[] args) throws IOException {
          Server serv;

          if (args.length==1) {
                try {
                      int portRequested=Integer.parseInt(args[0]);
                      serv = new Server(portRequested);
                }
                catch (NumberFormatException e) {
                      System.err.println("* Error bad port number. Switching to default port");
                      serv = new Server();
                }
          }
          else
                serv = new Server();
    }
}