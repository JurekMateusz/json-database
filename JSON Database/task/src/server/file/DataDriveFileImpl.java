package server.file;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.util.Objects;

import static java.nio.file.StandardOpenOption.READ;
import static java.nio.file.StandardOpenOption.WRITE;

public class DataDriveFileImpl implements DataDriveFacade {
    private final String EMPTY_CELL;
    private final int SIZE_CELL;
    private final Path file;//Path.of("D:\\PROJEKTY\\JSON Database\\database.txt");

    public DataDriveFileImpl(String path, int sizeCell) {
        file = Path.of(path);
        SIZE_CELL = sizeCell;
        EMPTY_CELL = " ".repeat(SIZE_CELL - 1);
    }

    private byte[] createCellBytes(String message) {
        StringBuilder messageBuilder = new StringBuilder(message);
        messageBuilder.append(" ".repeat(Math.max(0, SIZE_CELL - messageBuilder.length())));
        message = messageBuilder.toString();
        return message.getBytes();
    }

    private int countCellPosition(int cell) {
        return cell * SIZE_CELL;
    }

    @Override
    public boolean write(String message, int cell) {
        assert Objects.nonNull(message);
        assert message.length() < SIZE_CELL;

        byte[] bytes = createCellBytes(message);
        ByteBuffer out = ByteBuffer.wrap(bytes);
        try (FileChannel fc = (FileChannel.open(file, WRITE))) {
            int cellPosition = countCellPosition(cell);
            fc.position(cellPosition);
            while (out.hasRemaining())
                fc.write(out);
        } catch (IOException x) {
            x.printStackTrace();
        }
        return true;
    }

    @Override
    public String read(int cell) {
        ByteBuffer result = ByteBuffer.allocate(SIZE_CELL);
        try (FileChannel fc = (FileChannel.open(file, READ))) {
            int position = countCellPosition(cell);
            fc.read(result, position);
        } catch (IOException x) {
            x.printStackTrace();
        }
        result.flip();
        return new String(result.array()).trim();
    }

    @Override
    public boolean clear(int cell) {
        return write(EMPTY_CELL, cell);
    }

}
