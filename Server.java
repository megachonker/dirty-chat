import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;


import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {


    private Vector<Client>clients;
    private Vector<Message>messages;
    private int port = 3100;
    private ServerSocket serverSok;

    
    protected void register(Client cli){
        clients.add(cli);
    }

    protected void unregister(Client cli){
        clients.remove(cli);
    }

    protected void publish(Client client, String msg){
        String hdj = Calendar.getInstance().getTime().toString();
        messages.add(new Message(hdj,client,msg));
    }

    protected Vector<Message> history(Client cli){
        return messages;
    }


    Server() throws IOException{
        serverSok = new ServerSocket(port);  // xtor le plus simple

    }

    Server(int pPort) throws IOException{
        port = pPort;
        serverSok = new ServerSocket(port);  // xtor le plus simple
    }

    /**
     * @brief sends a formatted message to display on a given socket (i.e
     *          to a given client).
     * @param pseudo the client pseudo to display
     * @param sock the socket to send to
     * @param message the message to send
     */
    private void send(String pseudo,Socket sock, String message) {
        try {
        PrintWriter writer = new PrintWriter(sock.getOutputStream());
        writer.write("[" + pseudo + "] " + message);
        writer.flush();
        }
        catch (IOException e) {
            System.err.println("* Error trying to reach "+ pseudo +" to send a message.");
        }
 }

    public ServerSocket getServerSocket() {
        return serverSok;
    }    
}
