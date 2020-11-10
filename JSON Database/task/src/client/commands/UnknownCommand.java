package client.commands;

import client.input.Input;
import client.view.Console;

public class UnknownCommand extends Command {
    public UnknownCommand(Input input) {
        super(input);
    }

    @Override
    public boolean execute() {
        Console.log("Unknown Command");
        return false;
    }
}
