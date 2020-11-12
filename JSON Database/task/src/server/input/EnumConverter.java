package server.input;

import com.beust.jcommander.IStringConverter;

public class EnumConverter implements IStringConverter<CommandEnum> {
    @Override
    public CommandEnum convert(String value) {
        return CommandEnum.valueOf(value.toUpperCase());
    }
}

