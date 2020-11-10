import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

public class Main {

    public static void method() throws FileNotFoundException {
        throw new FileNotFoundException();
    }

    /* Do not change code below */
    public static void main(String[] args) {
        int[] message = new int[] {114, 101, 97, 100, 32, 97, 98, 111, 117, 116, 32, 65, 83, 67, 73, 73};

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        for (int code : message) {
            outputStream.write(code);
        }

        System.out.println(outputStream.toString());
    }
}
