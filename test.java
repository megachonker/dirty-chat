import java.util.Vector;

/**
 * test
 */
public class test {
    public static void main(String[] args) {
        Vector<Message> mes_message = new Vector<Message>();
        mes_message.add(new Message("0",null,"GA"));
        mes_message.add(new Message("1",null,"BU"));
        mes_message.add(new Message("2",null,"ZO"));
        mes_message.add(new Message("2",null,"MEU"));

        mes_message.forEach(arg0 -> System.out.println(arg0.mesg));
        
    }
}