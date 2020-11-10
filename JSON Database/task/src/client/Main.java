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

        String msg = "Sent: Give me a record # 12";
        output.writeUTF(msg);
        String inputt = input.readUTF();
        inputt = "Received: " + inputt;
        System.out.println(inputt);
    }
}
