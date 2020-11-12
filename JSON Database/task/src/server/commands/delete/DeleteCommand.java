package server.commands.delete;

import server.commands.Command;
import server.file.DataDriveFacade;
import server.input.Input;

public class DeleteCommand extends Command {
    public DeleteCommand(Input input, DataDriveFacade dataSave) {
        super(input, dataSave);
    }

    @Override
    public boolean execute() {
        if (isNumberCellValid()) {
            return false;
        }
        return dataSave.clear(input.getCell());
    }
}
