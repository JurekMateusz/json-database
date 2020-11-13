package client.input;

import com.beust.jcommander.Parameter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Input {
    private String type;
    private String key;
    private String value;
}
