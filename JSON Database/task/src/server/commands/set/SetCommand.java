package server.commands.set;

import server.commands.Command;
import server.file.DataDrive;
import server.model.Input;
import server.model.Output;

public class SetCommand extends Command {

    public SetCommand(Input input, DataDrive dataSave) {
        super(input, dataSave);
    }

    @Override
    public Output execute() {
        dataSave.write(getValueToSave(), getKey());
        return Output.success();
    }

    private String getKey() {
        return input.getKey();
    }

}
