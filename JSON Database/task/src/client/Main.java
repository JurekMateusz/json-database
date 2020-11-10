package client;

import client.file.FileChannelFacade;
import client.input.InputProvider;

public class Main {

    public static void main(String[] args) {
        new Database(new InputProvider()).start();
    }
}
