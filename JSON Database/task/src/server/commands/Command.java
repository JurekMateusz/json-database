package server.commands;

import server.file.DataDriveFacade;
import server.model.Input;
import server.model.Output;
import server.output.OutputProvider;

public abstract class Command {
    protected Input input;
    protected DataDriveFacade dataSave;

    protected Command(Input input, DataDriveFacade dataSave) {
        this.input = input;
        this.dataSave = dataSave;
    }

    public abstract Output execute();

}
