package client.commands.get;

import client.commands.Command;
import client.file.FileChannelFacade;
import client.input.Input;
import client.view.Console;

public class GetCommand extends Command {

    public GetCommand(Input input) {
        super(input);
    }

    @Override
    public boolean execute() {
        if (isNumberCellValid()) {
            return false;
        }
        FileChannelFacade fileChannelFacade = new FileChannelFacade();
        String message = fileChannelFacade.read(input.getCell());
        Console.log(message);
        return true;
    }
}
