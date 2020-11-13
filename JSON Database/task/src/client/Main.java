package client;

import client.input.Input;
import client.input.InputFromCommand;
import com.beust.jcommander.JCommander;
import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class Main {

    public static void main(String[] args) throws IOException {
        InputFromCommand empty = new InputFromCommand();
        JCommander.newBuilder()
                .addObject(empty)
                .build()
                .parse(args);
        if (empty.equals(InputFromCommand.empty())) {
            throw new IllegalArgumentException("Bad arguments");
        }
        Input inputToJson;
        if (Objects.nonNull(empty.getFile())) {
            inputToJson = getCommandFromFile(empty.getFile());
        } else {
            inputToJson = Input.builder()
                    .key(empty.getKey())
                    .type(empty.getType())
                    .value(empty.getValue())
                    .build();
        }


        String address = "127.0.0.1";
        int port = 23456;
        Socket socket = new Socket(InetAddress.getByName(address), port);
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        System.out.println("Client started!");
        System.out.println("Send: " + new Gson().toJson(inputToJson));
        output.writeUTF(new Gson().toJson(inputToJson));

        String received = input.readUTF();
        received = "Received: " + received;
        System.out.println(received);

    }

    private static Input getCommandFromFile(Path file) throws IOException {
        String actual = Files.readString(file);
        return new Gson().fromJson(actual,Input.class);
    }
}
