package server.file;

public interface DataDriveFacade {
    boolean write(String message, int cell);

    String read(int cell);

    boolean clear(int cell);
}
