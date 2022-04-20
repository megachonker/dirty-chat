import java.net.Socket;

public class Client {
    protected String pseudo;
    protected Socket sock;

    Client(String pseudo, Socket sock){
        this.pseudo = pseudo;
        this.sock   = sock;
    }

    protected Socket getSocket(){
        return sock;
    }

    protected String getPseudo(){
        return pseudo;
    }

}
