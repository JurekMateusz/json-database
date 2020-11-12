package server.output;

import com.google.gson.Gson;
import server.model.Output;

import java.io.DataOutputStream;
import java.io.IOException;

public class SocketOutputProvider implements OutputProvider {
    private DataOutputStream outputStream;
    private Gson jsonParser = new Gson();

    public SocketOutputProvider(DataOutputStream output) {
        this.outputStream = output;
    }

    @Override
    public void send(Output output) {
        String json = jsonParser.toJson(output);
        try {
            outputStream.writeUTF(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
