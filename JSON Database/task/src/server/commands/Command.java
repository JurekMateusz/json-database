package server.commands;

import server.file.DataDriveFacade;
import server.input.Input;

public abstract class Command {
    protected Input input;
    protected DataDriveFacade dataSave;

    protected Command(Input input, DataDriveFacade dataSave) {
        this.input = input;
        this.dataSave = dataSave;
    }

    public abstract boolean execute();

    protected boolean isNumberCellValid() {
        return input.getCell() < 0 || input.getCell() > 100;
    }
}
