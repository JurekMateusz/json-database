package server;

import server.commands.Command;
import server.commands.UnknownCommand;
import server.commands.delete.DeleteCommand;
import server.commands.exit.ExitCommand;
import server.commands.get.GetCommand;
import server.commands.set.SetCommand;
import server.input.Input;
import server.input.InputProvider;
import server.view.Console;

import static server.commands.CommandEnum.*;
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
            boolean status = command.execute();
            print(status, input);
        }
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
