package server.commands.delete;

import server.commands.Command;
import server.file.FileChannelFacade;
import server.input.Input;

import static server.file.FileChannelFacade.EMPTY_CELL;

public class DeleteCommand extends Command {
    public DeleteCommand(Input input) {
        super(input);
    }

    @Override
    public boolean execute() {
        if (isNumberCellValid()) {
            return false;
        }
        FileChannelFacade fileChannelFacade = new FileChannelFacade();
        return fileChannelFacade.write(EMPTY_CELL, input.getCell());
    }
}
