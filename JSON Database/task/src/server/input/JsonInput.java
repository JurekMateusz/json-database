package server.input;

import com.beust.jcommander.Parameter;
import lombok.Getter;

@Getter
public class JsonInput {
    private String type;
    private String key;
    private String value;
}
