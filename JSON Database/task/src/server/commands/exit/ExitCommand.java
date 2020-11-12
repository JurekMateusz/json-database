package server.commands.exit;

import server.Database;
import server.Server;
import server.commands.Command;
import server.model.Output;

public class ExitCommand extends Command {
    private Server server;

    public ExitCommand(Server server) {
        super(null, null);
        this.server = server;
    }

    @Override
    public Output execute() {
        server.setRunning(false);
        return Output.success();
    }
}
