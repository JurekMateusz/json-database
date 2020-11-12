package client;

import client.input.Input;
import com.beust.jcommander.JCommander;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import static client.input.CommandEnum.SET;

public class Main {

    public static void main(String[] args) throws IOException {
        Input empty = Input.empty();
        JCommander.newBuilder()
                .addObject(empty)
                .build()
                .parse(args);
        if (empty.equals(Input.empty())) {
            throw new IllegalArgumentException("Bad arguments");
        }
        System.out.println("Sent: " + empty.getCommand().name().toLowerCase() + " "
                + empty.getCell() + " "+(empty.getCommand() == SET ? empty.getValue() : ""));
        String address = "127.0.0.1";
        int port = 23456;
        Socket socket = new Socket(InetAddress.getByName(address), port);
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        System.out.println("Client started!");
        String send = "";
        for (int i = 0; i < args.length; i++) {
            send += args[i] + " ";
        }
        output.writeUTF(send);

        String received = input.readUTF();
        received = "Received: " + received;
        System.out.println(received);
    }
}
