import java.net.Socket;

public class Client {
    private String pseudo;
    private Socket sock;

    Client(String pseudo, Socket sock){

    }

    protected Socket getSocket(){
        return sock;
    }

    protected String getPseudo(){
        return pseudo;
    }

}
