package server.output;

import server.model.Output;

public class Console implements OutputProvider {
    public void send(Output msg) {
        System.out.println(msg);
    }
}
