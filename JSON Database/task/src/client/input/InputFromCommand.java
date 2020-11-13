package client.input;

import com.beust.jcommander.Parameter;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.nio.file.Path;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class InputFromCommand {
    @Parameter(names = "-t", description = "Type of request")
    private String type;
    @Parameter(names = "-k", description = "key of the cell")
    private String key;
    @Parameter(names = "-v", description = "Message saved in database")
    private String value;
    @Parameter(names = "-in", converter = FileConverter.class)
    private Path file;

    public static InputFromCommand empty() {
        return new InputFromCommand("UNKNOWN TYPE", "key", "Not given",null);
    }
}
