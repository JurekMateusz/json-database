package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException {

        String address = "127.0.0.1";
        int port = 23456;
        Socket socket = new Socket(InetAddress.getByName(address), port);
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        System.out.println("Client started!");
        String msg = "Sent: Give me a record # 12";
        System.out.println(msg);
        output.writeUTF("Give me a record # 12");
        String inputt = input.readUTF();
        inputt = "Received: " + inputt;
        System.out.println(inputt);
    }
}
