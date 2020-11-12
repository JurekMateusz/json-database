package server.commands.get;

import server.commands.Command;
import server.file.DataDriveFacade;
import server.model.Input;
import server.model.Output;

import java.util.Objects;

public class GetCommand extends Command {

    public GetCommand(Input input, DataDriveFacade dataHardDrive) {
        super(input, dataHardDrive);
    }

    @Override
    public Output execute() {
        String message = dataSave.read(input.getKey());
        if (Objects.isNull(message)) {
            return Output.failureWithReason("No such key");
        }
        return Output.successWithValue(message);
    }
}
