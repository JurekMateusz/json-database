package client;

import client.commands.*;
import client.commands.delete.DeleteCommand;
import client.commands.exit.ExitCommand;
import client.commands.get.GetCommand;
import client.commands.set.SetCommand;
import client.input.Input;
import client.input.InputProvider;

import static client.commands.CommandEnum.*;
import static io.vavr.API.*;

public class Database {
    private boolean isRunning = true;
    private InputProvider inputProvider;

    public Database(InputProvider inputProvider) {
        this.inputProvider = inputProvider;
    }

    public void start() {
        while (isRunning) {
            Input input = inputProvider.getInputCommand();
            Command command = Match(input.getCommand()).of(
                    Case($(GET), new GetCommand(input)),
                    Case($(SET), new SetCommand(input)),
                    Case($(DELETE), new DeleteCommand(input)),
                    Case($(EXIT), new ExitCommand(this)),
                    Case($(UNKNOWN), new UnknownCommand(input))
            );
            command.execute();
        }
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}
