package client;

import client.input.Input;
import com.beust.jcommander.JCommander;
import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import static client.input.CommandEnum.SET;

public class Main {

    public static void main(String[] args) throws IOException {
        Input empty = new Input();
        JCommander.newBuilder()
                .addObject(empty)
                .build()
                .parse(args);
        if (empty.equals(Input.empty())) {
            throw new IllegalArgumentException("Bad arguments");
        }
        String address = "127.0.0.1";
        int port = 23456;
        Socket socket = new Socket(InetAddress.getByName(address), port);
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        System.out.println("Client started!");
        System.out.println("Send: " + new Gson().toJson(empty));
        output.writeUTF(new Gson().toJson(empty));

        String received = input.readUTF();
        received = "Received: " + received;
        System.out.println(received);
    }
}
