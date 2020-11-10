package server.commands.get;

import server.commands.Command;
import server.file.FileChannelFacade;
import server.input.Input;
import server.view.Console;

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
        if (message.isEmpty()) {
            return false;
        } else {
            Console.log(message);
            return true;
        }
    }
}
