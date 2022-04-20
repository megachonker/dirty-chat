

public class Message {
    public Client cli;
    public String mesg;
    public String date;

    Message(String date,Client cli,String msg){
        this.date = date;
        this.cli = cli;
        this.mesg = msg;
    }

    public String to_string(){
        return date+"->"+cli.getPseudo()+":\t"+mesg;
    }

}
