package client.commands.exit;

import client.Database;
import client.commands.Command;

public class ExitCommand extends Command {

    private final Database database;

    public ExitCommand(Database database) {
        super(null);
        this.database = database;
    }

    @Override
    public boolean execute() {
        database.setRunning(false);
        return true;
    }
}
