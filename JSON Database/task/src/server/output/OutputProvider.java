package server.output;

import server.model.Output;

public interface OutputProvider {
    void send(Output output);
}
