package server.commands;

import server.input.Input;

public abstract class Command {
    protected Input input;

    protected Command(Input input) {
        this.input = input;
    }

    public abstract boolean execute();

    protected boolean isNumberCellValid() {
        return input.getCell() < 0 || input.getCell() > 100;
    }
}
