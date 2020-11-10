package server.input;

import server.commands.CommandEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Input {
    private CommandEnum command;
    private int cell;
    private String value;

    public static Input of(CommandEnum commandEnum, int cell) {
        return new Input(commandEnum, cell, "Not given");
    }

    public static Input of(CommandEnum commandEnum, int cell, String value) {
        return new Input(commandEnum, cell, value);
    }
}
