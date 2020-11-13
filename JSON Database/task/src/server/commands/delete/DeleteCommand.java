package server.commands.delete;

import server.commands.Command;
import server.file.DataDrive;
import server.model.Input;
import server.model.Output;

public class DeleteCommand extends Command {
    public DeleteCommand(Input input, DataDrive dataSave) {
        super(input, dataSave);
    }

    @Override
    public Output execute() {
        if (!dataSave.clear(input.getKey())) {
            return Output.failureWithReason("No such key");
        }
        return Output.success();
    }
}
