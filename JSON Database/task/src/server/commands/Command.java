package server.commands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import server.file.DataDrive;
import server.model.Input;
import server.model.Output;

public abstract class Command {
    protected final Gson gson;
    protected Input input;
    protected DataDrive dataSave;

    protected Command(Input input, DataDrive dataSave) {
        this.input = input;
        this.dataSave = dataSave;
        this.gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    protected String getValueToSave() {
        String json = gson.toJson(input);
        return json.substring(1, json.length() - 1);
    }

    protected Input getInput(String json) {
        return gson.fromJson("{"+json+"}", Input.class);
    }

    public abstract Output execute();

}
