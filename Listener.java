
import java.io.IOException;
import java.io.BufferedInputStream;
import java.net.Socket;


public class Listener {

   private static final int MAX_PSEUDO_LEN = 25;


   Server server;

   Listener(Server mysServer){
       this.server = mysServer;
   }


    /**
    * @brief start a listening thread on behalf the server.
    *        Accepts connections and registers a client when it connects
    *        Delegates further dialog with the client to a new thread 
    *        running a ClientConnect object.
    **/
    public void run(){
        boolean isRunning=true;
        while(isRunning == true){ // should probably stay always true
          try {
            // Wait for an incoming client connection
            Socket sockclient = server.getServerSocket().accept();
            // ok, we have a socket for that client.
            // Read what it sent to me as a header (I expect its pseudo as first string)
            String pseudo = headerRead(sockclient);
            // and create one Client, register it on the server.
            Client cli = new Client(pseudo, sockclient);
            server.register( cli );

            System.out.print(cli.getPseudo());

            // Create a thread to handle client requests
            // Pass the client object and myself as server attached to this client
            Thread t = new Thread(new ClientConnect(cli, server));
            t.start();
          } catch (IOException e) {
            e.printStackTrace();
          }

          //if shuting down
          //isRunning = false;
        }
        try {
          server.getServerSocket().close();
        } catch (IOException e) {
        e.printStackTrace();
        server = null;
        }
    } // end void run()


    /**
     * @brief reads first input from client which is expected to be its pseudo
     * @param sock the socket on which data are to be read
     * @return the bytes issued by 1 read, as a string
     */
    private String headerRead(Socket sock) throws IOException {
        BufferedInputStream reader = new BufferedInputStream(sock.getInputStream()); 
        String pseudo = "";
        int stream;
        byte[] b = new byte[MAX_PSEUDO_LEN];
        stream = reader.read(b,0,MAX_PSEUDO_LEN);
        pseudo = new String(b, 0, stream);
        return pseudo.trim();
    }
}
