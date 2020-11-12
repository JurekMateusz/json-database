package server.commands.set;

import server.commands.Command;
import server.file.DataDriveFacade;
import server.input.Input;

import java.util.Objects;

public class SetCommand extends Command {

    public SetCommand(Input input, DataDriveFacade dataSave) {
        super(input, dataSave);
    }

    @Override
    public boolean execute() {
        if (isNumberCellValid() || Objects.isNull(input.getValue())) {
            return false;
        }
        return dataSave.write(getMsgInput(), getCell());
    }

    private String getMsgInput() {
        return input.getValue();
    }

    private int getCell() {
        return input.getCell();
    }

}
