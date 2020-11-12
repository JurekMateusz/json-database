package server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Output {
    private String response;
    private String value;
    private String reason;

    public static Output failureWithReason(String reason) {
        return new Output("ERROR", null, reason);
    }

    public static Output success() {
        return new Output("OK", null, null);
    }

    public static Output successWithValue(String value) {
        return new Output("OK", value, null);
    }
}
