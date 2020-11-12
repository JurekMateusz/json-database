package client.input;

import com.beust.jcommander.Parameter;
import lombok.*;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Input {
    @Parameter(names = "-t", description = "Type of request")
    private String type;
    @Parameter(names = "-k", description = "key of the cell")
    private String key;
    @Parameter(names = "-v", description = "Message saved in database")
    private String value;

    public static Input of(String type, String key) {
        return of(type, key, "Not given");
    }

    public static Input of(String type,  String key, String value) {
        return new Input(type, key, value);
    }

    public static Input empty() {
        return of("UNKNOWN TYPE","key","Not given");
    }
}
