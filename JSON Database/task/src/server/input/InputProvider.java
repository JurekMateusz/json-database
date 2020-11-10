package server.input;

import server.commands.CommandEnum;
import io.vavr.control.Try;

import java.util.Scanner;

import static server.commands.CommandEnum.SET;
import static server.commands.CommandEnum.UNKNOWN;

public class InputProvider {
    private Scanner scanner = new Scanner(System.in);

    public Input getInputCommand() {
        String input = scanner.nextLine();
        String[] split = input.split(" +",3);
        CommandEnum commandEnum = parseCommand(split[0]);
        int cell = parseCell(split);
        if (commandEnum == SET) {
            Try<String> valueTry = Try.of(() -> split[2]);
            if (valueTry.isSuccess()) {
                return Input.of(commandEnum, cell, valueTry.get());
            }
        }
        return Input.of(commandEnum, cell);
    }


    private CommandEnum parseCommand(String input) {
        Try<CommandEnum> command = Try.of(() -> CommandEnum.valueOf(input.toUpperCase()));
        return command.getOrElse(UNKNOWN);
    }

    private int parseCell(String[] cell) {
        Try<Integer> of = Try.of(() -> Integer.parseInt(cell[1]));
        return of.getOrElse(-1);
    }

}
