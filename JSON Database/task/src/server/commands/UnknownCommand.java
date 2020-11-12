package server.commands;

import server.model.Input;
import server.model.Output;
import server.output.OutputProvider;

public class UnknownCommand extends Command {

    public UnknownCommand(Input input) {
        super(input, null);
    }

    @Override
    public Output execute() {
        return Output.failureWithReason("Unknown command");
    }
}
