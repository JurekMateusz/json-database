package server.output;

public class Console implements OutputProvider {
    public void send(String msg) {
        System.out.println(msg);
    }
}
