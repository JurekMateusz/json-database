package server.input;

import com.google.gson.Gson;
import io.vavr.control.Try;
import server.model.Input;

import java.io.DataInputStream;

public class SocketInputProviderImpl implements InputProvider {
    private final DataInputStream input;
    private final Gson gson = new Gson();

    public SocketInputProviderImpl(DataInputStream input) {
        this.input = input;
    }

    @Override
    public Input getInputCommand() {
        return Try.of(this.input::readUTF)
                .map(s -> gson.fromJson(s, JsonInput.class))
                .flatMap(jsonInput -> Try.of(() -> Input.builder()
                        .command(CommandEnum.valueOf(jsonInput.getType().toUpperCase()))
                        .key(jsonInput.getKey())
                        .value(jsonInput.getValue())
                        .build())
                ).getOrElse(Input.empty());
    }
}
