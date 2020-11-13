package server.commands.get;

import server.commands.Command;
import server.file.DataDrive;
import server.model.Input;
import server.model.Output;

import java.util.Objects;

public class GetCommand extends Command {

    public GetCommand(Input input, DataDrive dataHardDrive) {
        super(input, dataHardDrive);
    }

    @Override
    public Output execute() {
        String json = dataSave.read(input.getKey());
        if (Objects.isNull(json) || json.isEmpty()) {
            return Output.failureWithReason("No such key");
        }
        Input input = getInput(json);
        return Output.successWithValue(input.getValue());
    }
}
