package server.commands.get;

import server.commands.Command;
import server.file.DataDriveFacade;
import server.input.Input;
import server.output.OutputProvider;

public class GetCommand extends Command {
    private final OutputProvider outputProvider;

    public GetCommand(Input input, DataDriveFacade dataHardDrive, OutputProvider outputProvider) {
        super(input, dataHardDrive);
        this.outputProvider = outputProvider;
    }

    @Override
    public boolean execute() {
        if (isNumberCellValid()) {
            return false;
        }
        String message = dataSave.read(input.getCell());
        if (message.isEmpty()) {
            return false;
        }
        outputProvider.send(message);
        return true;
    }
}
