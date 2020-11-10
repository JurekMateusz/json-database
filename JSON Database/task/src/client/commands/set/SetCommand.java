package client.commands.set;

import client.commands.Command;
import client.file.FileChannelFacade;
import client.input.Input;

public class SetCommand extends Command {

    public SetCommand(Input input) {
        super(input);
    }

    @Override
    public boolean execute() {
        if (isNumberCellValid()) {
            return false;
        }

        FileChannelFacade fileChannelFacade = new FileChannelFacade();
        return fileChannelFacade.write(getMsgInput(), getCell());
    }

    private String getMsgInput() {
        return input.getValue();
    }

    private int getCell() {
        return input.getCell();
    }

}
