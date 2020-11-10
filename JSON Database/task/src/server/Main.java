package server;

import server.input.InputProvider;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        File myObj = new File("D:\\PROJEKTY\\JSON Database\\database.txt");
        myObj.createNewFile();
        new Database(new InputProvider()).start();
    }
}
