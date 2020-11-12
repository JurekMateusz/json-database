package server.commands.set;

import server.commands.Command;
import server.file.DataDriveFacade;
import server.model.Input;
import server.model.Output;

public class SetCommand extends Command {

    public SetCommand(Input input, DataDriveFacade dataSave) {
        super(input, dataSave);
    }

    @Override
    public Output execute() {
        dataSave.write(getMsgInput(), getKey());
        return Output.success();
    }

    private String getMsgInput() {
        return input.getValue();
    }

    private String getKey() {
        return input.getKey();
    }

}
