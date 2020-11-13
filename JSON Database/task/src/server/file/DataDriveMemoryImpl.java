package server.file;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DataDriveMemoryImpl implements DataDrive {
    private Map<String, String> data = new HashMap<>();

    @Override
    public boolean write(String message, String key) {
        data.put(key, message);
        return true;
    }

    @Override
    public String read(String key) {
        return data.get(key);
    }

    @Override
    public boolean clear(String key) {
        return Objects.isNull(data.remove(key));
    }
}
