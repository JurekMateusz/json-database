package server.file;

import java.util.ArrayList;
import java.util.List;

public class DataDriveMemoryImpl implements DataDriveFacade {
    private List<String> dataInList;

    public DataDriveMemoryImpl(int size) {
        dataInList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            dataInList.add("");
        }
    }

    @Override
    public boolean write(String message, int cell) {
        dataInList.set(cell, message);
        return true;
    }

    @Override
    public String read(int cell) {
        return dataInList.get(cell);
    }

    @Override
    public boolean clear(int cell) {
        return write("", cell);
    }
}
