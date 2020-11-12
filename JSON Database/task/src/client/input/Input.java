package client.input;

import com.beust.jcommander.Parameter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Input {
    @Parameter(names = "-t", description = "Type of request", converter = EnumConverter.class)
    private CommandEnum command;
    @Parameter(names = "-i", description = "Index of the cell")
    private int cell;
    @Parameter(names = "-m", description = "Message saved in database")
    private String value;

    public static Input of(CommandEnum commandEnum, int cell) {
        return of(commandEnum, cell, "Not given");
    }

    public static Input of(CommandEnum commandEnum, int cell, String value) {
        return new Input(commandEnum, cell, value);
    }

    public static Input empty() {
        return of(CommandEnum.ERROR,-1,"Not given");
    }
}
