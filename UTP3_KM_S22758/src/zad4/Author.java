/**
 *
 *  @author Kilman Miłosz S22758
 *
 */

package zad4;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.IntStream;

import static java.util.stream.IntStream.*;

public class Author implements Runnable {
    public final BlockingQueue AuthQueue;
    private String[] s;
    public Author(String[] string) {
        this.s = string;
        AuthQueue = new LinkedBlockingQueue();
    }


    @Override
    public void run() {
        int bound = s.length;
        range(0, bound).forEach(i -> {
            try {
                Thread.sleep(1000);
                AuthQueue.put(s[i]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }


}