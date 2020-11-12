package server.file;

public interface DataDriveFacade {
    boolean write(String message, String key);

    String read(String key);

    boolean clear(String key);
}
