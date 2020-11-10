package server;

import server.input.Input;
import server.input.InputProvider;
import server.view.Console;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import static server.commands.CommandEnum.EXIT;
import static server.commands.CommandEnum.GET;

public class Database {
    private boolean isRunning = true;
    private InputProvider inputProvider;

    public Database(InputProvider inputProvider) {
        this.inputProvider = inputProvider;
    }

    public void start() throws IOException {
        Console.log("Server started!");
//        while (isRunning) {
//            Input input = inputProvider.getInputCommand();
//            Command command = Match(input.getCommand()).of(
//                    Case($(GET), new GetCommand(input)),
//                    Case($(SET), new SetCommand(input)),
//                    Case($(DELETE), new DeleteCommand(input)),
//                    Case($(EXIT), new ExitCommand(this)),
//                    Case($(UNKNOWN), new UnknownCommand(input))
//            );
//            boolean status = command.execute();
//            print(status, input);
//        }
        String address = "127.0.0.1";
        int port = 23456;
        ServerSocket server = new ServerSocket(port, 50, InetAddress.getByName(address));
        Socket socket = server.accept();
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        String inputt = input.readUTF();
        inputt = "Received: " + inputt;
        System.out.println(inputt);
        System.out.println("Sent: A record # 12 was sent!");
        output.writeUTF("A record # 12 was sent!");

    }

    private void print(boolean status, Input input) {
        if ((input.getCommand() == GET && status) || input.getCommand() == EXIT) {
            return;
        }
        String message = status ? "OK" : "ERROR";
        Console.log(message);
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}
