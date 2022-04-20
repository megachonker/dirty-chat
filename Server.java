import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;



public class Server {


    private Vector<Client>clients   = new Vector<Client>();
    private Vector<Message>messages = new Vector<Message>();
    private int port = 3100;
    private ServerSocket serverSok;

    
    protected void register(Client cli){
        clients.add(cli);
    }

    protected void unregister(Client cli){
        clients.remove(cli);
    }

    protected void publish(Client client, String msg){
        //save msg
        String hdj = Calendar.getInstance().getTime().toString();
        messages.add(new Message(hdj,client,msg));
        //broadcast msg
        for (Client monclient : clients) {
            send(monclient,messages.lastElement());
        }
    }

    protected Vector<Message> history(Client cli){
        //all client
        for (Client client : clients) {
            //all message
            for (Message message : messages) {
                send(client,message);
            }
        }
        return messages;
    }


    Server() throws IOException{
        serverSok = new ServerSocket(port);  // xtor le plus simple
        Listener a = new Listener(this);
        a.run();
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
    private void send(Client client, Message message) {
        try {
            PrintWriter writer = new PrintWriter(client.sock.getOutputStream());
            writer.write(message.to_string()+"\n");
            writer.flush();
            }
            catch (IOException e) {
                System.err.println("* Error trying to reach "+ client.pseudo +" to send a message.");
            }
    }


    public ServerSocket getServerSocket() {
        return serverSok;
    }    
}
