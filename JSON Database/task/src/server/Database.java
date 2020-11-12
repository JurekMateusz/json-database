package server;

import server.commands.Command;
import server.commands.UnknownCommand;
import server.commands.delete.DeleteCommand;
import server.commands.exit.ExitCommand;
import server.commands.get.GetCommand;
import server.commands.set.SetCommand;
import server.file.DataDriveFacade;
import server.input.Input;
import server.input.InputProvider;
import server.input.SocketInputProviderImpl;
import server.output.OutputProvider;
import server.output.SocketOutputProvider;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static io.vavr.API.*;
import static server.input.CommandEnum.*;

public class Database {
    private boolean isRunning = true;
    private Socket socket;
    private Server server;
    private InputProvider inputProvider;
    private OutputProvider outputProvider;
    private DataDriveFacade dataHardDrive;

    public Database(Socket socket, DataDriveFacade dataHardDrive, Server server) throws IOException {
        this.dataHardDrive = dataHardDrive;
        this.socket = socket;
        this.server = server;
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        inputProvider = new SocketInputProviderImpl(input);
        outputProvider = new SocketOutputProvider(output);
    }

    public void handleRequest() throws IOException {
        System.out.println("Server started!");
//        while (isRunning) {
        Input input = inputProvider.getInputCommand();
        Command command = Match(input.getCommand()).of(
                Case($(GET), new GetCommand(input, dataHardDrive, outputProvider)),
                Case($(SET), new SetCommand(input, dataHardDrive)),
                Case($(DELETE), new DeleteCommand(input, dataHardDrive)),
                Case($(EXIT), new ExitCommand(this, outputProvider, server)),
                Case($(UNKNOWN), new UnknownCommand(input, outputProvider))
        );
        boolean status = command.execute();
        print(status, input);
        closeConnection();
//        }
    }

    public void closeConnection() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void print(boolean status, Input input) {
        if (((input.getCommand() == GET && status)) || EXIT == input.getCommand()) {
            return;
        }
        String message = status ? "OK" : "ERROR";
        outputProvider.send(message);
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}
