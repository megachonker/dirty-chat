import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Vector;

public class ClientConnect implements Runnable {

   // attributes
   private Client cli;        // the client i'm looking after
   private Server myserver;   // the server i'm attached to
 
   ClientConnect(Client cli, Server myserver){
      this.cli = cli;
      this.myserver = myserver;
   }
   /**
    * @brief: handles a connected client, listening and managing 
    *         its requests
    **/
   public void run(){
      System.err.println("Starting client management thread");
      Socket clientsock = cli.getSocket();

      // iterate on listening incoming connections
      // sock is of type Socket, and supposed to be opened to the client
      while(!clientsock.isClosed()){
         try {
            // reads client's request 
            String clientInput = read(clientsock);

            //routing imput message
            switch (clientInput) {
               case "/hist":
                  printHistory(myserver.history(cli));
                  break;
               case "/quit":
                  myserver.unregister(cli);
               default:
                  myserver.publish(cli, clientInput);
                  break;
            }
 
         }
         catch(SocketException e){
            System.err.println("Connection cut.");
            break;
         } catch (IOException e) {
            e.printStackTrace();
         }         
      }
   }
   
   private void printHistory(Vector<Message> historique){
      historique.forEach((messages) -> messages.toString());
   }


   /**
   * @brief: reads one byte block on the socket dedicated to that client. 
   * @return the bytes read as a String, or "" if the stream was closed.
   **/ 
   private String read(Socket sock) throws IOException{ 
      String response = "";
      int bytesRead;
      int MAX_MSG_LEN=2048;
      BufferedInputStream reader = 
         new BufferedInputStream(sock.getInputStream()); 
      byte[] buff = new byte[MAX_MSG_LEN];
      // Reads bytes into the specified byte array, 
      // starting at the given offset.
      // Return -1 if the end of the stream is reached.
      bytesRead = reader.read(buff,0,MAX_MSG_LEN);
      if (bytesRead !=-1)
            response = new String(buff, 0, bytesRead);
      return response;
   }

}
