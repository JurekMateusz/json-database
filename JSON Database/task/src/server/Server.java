package server;

import server.file.DataDriveMemoryImpl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 23456;
    private boolean isRunning = true;
    private DataDriveMemoryImpl dataDriveMemory = new DataDriveMemoryImpl();


    public void start() throws IOException {
        ServerSocket server = new ServerSocket(PORT, 50, InetAddress.getByName(ADDRESS));
        while (isRunning) {
            Socket socket = server.accept();
            new Database(socket, dataDriveMemory, this).handleRequest();
        }
        server.close();
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}
