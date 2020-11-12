package server.commands;

import server.input.Input;
import server.output.OutputProvider;

public class UnknownCommand extends Command {
    private final OutputProvider outputProvider;

    public UnknownCommand(Input input, OutputProvider outputProvider) {
        super(input, null);
        this.outputProvider = outputProvider;
    }

    @Override
    public boolean execute() {
        outputProvider.send("Unknown Command");
        return false;
    }
}
