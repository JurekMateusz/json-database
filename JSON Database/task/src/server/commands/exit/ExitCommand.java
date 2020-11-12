package server.commands.exit;

import server.Database;
import server.Server;
import server.commands.Command;
import server.output.OutputProvider;

public class ExitCommand extends Command {

    private final Database database;
    private OutputProvider outputProvider;
    private Server server;

    public ExitCommand(Database database, OutputProvider outputProvider, Server server) {
        super(null, null);
        this.database = database;
        this.outputProvider = outputProvider;
        this.server = server;
    }

    @Override
    public boolean execute() {
        database.setRunning(false);
        outputProvider.send("OK");

        database.closeConnection();
        server.setRunning(false);
        return true;
    }
}
