package server;

import server.commands.Command;
import server.commands.UnknownCommand;
import server.commands.delete.DeleteCommand;
import server.commands.exit.ExitCommand;
import server.commands.get.GetCommand;
import server.commands.set.SetCommand;
import server.file.DataDrive;
import server.input.InputProvider;
import server.input.SocketInputProviderImpl;
import server.model.Input;
import server.model.Output;
import server.output.OutputProvider;
import server.output.SocketOutputProvider;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static io.vavr.API.*;
import static server.input.CommandEnum.*;

public class Database implements Runnable {
    private Socket socket;
    private ServerSocket server;
    private InputProvider inputProvider;
    private OutputProvider outputProvider;
    private DataDrive dataHardDrive;

    public Database(Socket socket, DataDrive dataHardDrive, ServerSocket server) throws IOException {
        this.dataHardDrive = dataHardDrive;
        this.socket = socket;
        this.server = server;
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        inputProvider = new SocketInputProviderImpl(input);
        outputProvider = new SocketOutputProvider(output);
    }

    @Override
    public void run() {
        handleRequest();
    }

    public void handleRequest() {
        Input input = inputProvider.getInputCommand();
        Command command = Match(input.getCommand()).of(
                Case($(GET), new GetCommand(input, dataHardDrive)),
                Case($(SET), new SetCommand(input, dataHardDrive)),
                Case($(DELETE), new DeleteCommand(input, dataHardDrive)),
                Case($(EXIT), new ExitCommand(server)),
                Case($(UNKNOWN), new UnknownCommand(input))
        );
        Output output = command.execute();
        outputProvider.send(output);
        closeConnection();
    }

    public void closeConnection() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
