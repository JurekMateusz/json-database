package server;

import server.file.DataDrive;
import server.file.DataDriveFileImpl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 23456;
    private static final String ABSOLUTE_FILE_PATH = "D:\\PROJEKTY\\JSON Database\\JSON Database\\task\\src\\server\\data\\db.json";
    private final ExecutorService executor;
    private final DataDrive dataDrive =
            new DataDriveFileImpl(ABSOLUTE_FILE_PATH, 1000, 100);
    private boolean isRunning = true;

    public Server() {
        int poolSize = Runtime.getRuntime().availableProcessors();
        this.executor = Executors.newFixedThreadPool(poolSize);
    }

    public void start() throws IOException {
        System.out.println("Server started!");
        try (ServerSocket server = new ServerSocket(PORT, 50, InetAddress.getByName(ADDRESS))) {
            while (true) {
                Socket socket = server.accept();
                executor.execute(
                        new Database(socket, dataDrive, server));
            }
        } catch (SocketException e) {
            executor.shutdown();
        }
    }

    public synchronized void setRunning(boolean running) {
        isRunning = running;
    }
}
