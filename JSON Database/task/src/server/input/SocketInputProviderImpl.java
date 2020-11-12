package server.input;

import com.beust.jcommander.JCommander;
import io.vavr.control.Try;

import java.io.DataInputStream;

public class SocketInputProviderImpl implements InputProvider {
    private final DataInputStream input;

    public SocketInputProviderImpl(DataInputStream input) {
        this.input = input;
    }

    @Override
    public Input getInputCommand() {
        Input result = Input.empty();
        Try<String> input = Try.of(this.input::readUTF);
        input.onSuccess(s -> JCommander.newBuilder()
                .addObject(result)
                .build()
                .parse(s.trim().split(" ", 6))
        );
        return result;
    }
}
