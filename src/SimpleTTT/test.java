package SimpleTTT;

import java.util.Random;
import java.util.concurrent.CancellationException;

public class test {
    public static void main(String[] args) {
        Random random = new Random();
        System.out.println(random.nextInt(4));
    }

    public static void breakIt(int count) throws CancellationException {
        if (count == 10) {
            throw new CancellationException();
        }
    }
}
