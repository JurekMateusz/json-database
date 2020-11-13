import java.math.BigInteger;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExecutorService executor = Executors.newFixedThreadPool(4);

        while (scanner.hasNext()) {
            int number = scanner.nextInt();
            PrintIfPrimeTask printIfPrimeTask = new PrintIfPrimeTask(number);
            executor.submit(printIfPrimeTask::run);
        }
        executor.shutdown();
    }
}

class PrintIfPrimeTask implements Runnable {
    private final int number;

    public PrintIfPrimeTask(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        if(number == 1){
            return;
        }
        BigInteger bigInteger = BigInteger.valueOf(number);
        boolean probablePrime = bigInteger.isProbablePrime((int) Math.log(number));
        if (probablePrime) {
            System.out.println(number);
        }
    }
}
