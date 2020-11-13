package server.file;

public interface DataDrive {
    boolean write(String message, String key);

    String read(String key);

    boolean clear(String key);
}
