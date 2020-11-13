package server.commands.exit;

import server.Database;
import server.Server;
import server.commands.Command;
import server.model.Output;

import java.io.IOException;
import java.net.ServerSocket;

public class ExitCommand extends Command {
    private ServerSocket server;

    public ExitCommand(ServerSocket server) {
        super(null, null);
        this.server = server;
    }

    @Override
    public Output execute() {
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Output.success();
    }
}
