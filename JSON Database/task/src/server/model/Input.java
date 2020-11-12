package server.model;

import lombok.*;
import server.input.CommandEnum;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Input {
    private CommandEnum command;
    private String key;
    private String value;

    public static Input empty() {
        return new Input(CommandEnum.UNKNOWN, "key", "Not given");
    }
}
