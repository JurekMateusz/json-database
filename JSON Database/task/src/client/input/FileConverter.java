package client.input;

import com.beust.jcommander.IStringConverter;

import java.nio.file.Path;

public class FileConverter implements IStringConverter<Path> {
    @Override
    public Path convert(String value) {
        return Path.of("D:\\PROJEKTY\\JSON Database\\JSON Database\\task\\src\\client\\data\\" + value);
    }
}
