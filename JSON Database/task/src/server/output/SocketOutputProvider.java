package server.output;

import java.io.DataOutputStream;
import java.io.IOException;

public class SocketOutputProvider implements OutputProvider {
    private DataOutputStream output;

    public SocketOutputProvider(DataOutputStream output) {
        this.output = output;
    }

    @Override
    public void send(String msg) {
        try {
            output.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
