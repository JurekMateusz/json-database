package server.file;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DataDriveFileImpl implements DataDrive {
    private final String EMPTY_CELL;
    private final int SIZE_CELL;
    private final int SIZE_DATABASE;
    private final Path file;
    private final Lock readLock;
    private final Lock writeLock;

    public DataDriveFileImpl(String path, int capacityKeysDatabase, int sizeCell) {
        this.file = Path.of(path);
        this.SIZE_CELL = sizeCell;
        this.SIZE_DATABASE = capacityKeysDatabase;
        this.EMPTY_CELL = " ".repeat(this.SIZE_CELL - 1);

        ReadWriteLock lock = new ReentrantReadWriteLock();
        this.readLock = lock.readLock();
        this.writeLock = lock.writeLock();

        prepareFile();
    }

    private void prepareFile() {
        byte[] jsonDBStart = "{".getBytes();
        byte[] jsonDBEnd = "}".getBytes();

        ByteBuffer start = ByteBuffer.wrap(jsonDBStart);
        ByteBuffer end = ByteBuffer.wrap(jsonDBEnd);
        try (FileChannel fc = FileChannel.open(this.file, StandardOpenOption.WRITE)) {
            try {
                fc.position(0);
                while (start.hasRemaining()) {
                    fc.write(start);
                }
                for (int i = 0; i < SIZE_CELL * SIZE_DATABASE; i++) {
                    fc.write(ByteBuffer.wrap(" ".getBytes()));
                }
                while (end.hasRemaining()) {
                    fc.write(end);
                }
            } catch (Throwable var9) {
                throw var9;
            }
        } catch (IOException var10) {
            var10.printStackTrace();
        }
    }

    private int countCellPosition(String key) {
        key = key.replace("'", "");
        return (key.hashCode() % 1000) * SIZE_CELL;
    }

    private byte[] createCellBytes(String message) {
        StringBuilder messageBuilder = new StringBuilder(message);
        messageBuilder.append(" ".repeat(Math.max(0, this.SIZE_CELL - messageBuilder.length())));
        message = messageBuilder.toString();
        return message.getBytes();
    }

    @Override
    public boolean write(String message, String key) {
        assert Objects.nonNull(message);
        assert message.length() < this.SIZE_CELL;

        byte[] bytes = this.createCellBytes(message);
        ByteBuffer out = ByteBuffer.wrap(bytes);
        writeLock.lock();
        try (FileChannel fc = FileChannel.open(this.file, StandardOpenOption.WRITE)) {
            try {
                int cellPosition = this.countCellPosition(key);
                fc.position(cellPosition);

                while (out.hasRemaining()) {
                    fc.write(out);
                }
            } catch (Throwable var9) {
                throw var9;
            }
        } catch (IOException var10) {
            var10.printStackTrace();
        }
        writeLock.unlock();
        return true;
    }

    @Override
    public String read(String key) {
        assert Objects.nonNull(key);
        ByteBuffer result = ByteBuffer.allocate(this.SIZE_CELL);
        readLock.lock();
        try (FileChannel fc = FileChannel.open(this.file, StandardOpenOption.READ)) {
            try {
                int position = this.countCellPosition(key);
                fc.read(result, position);
            } catch (Throwable var7) {
                throw var7;
            }
        } catch (IOException var8) {
            var8.printStackTrace();
        }
        result.flip();
        readLock.unlock();
        return (new String(result.array())).trim();
    }

    @Override
    public boolean clear(String key) {
        return !read(key).isBlank() && this.write(this.EMPTY_CELL, key);
    }

}
