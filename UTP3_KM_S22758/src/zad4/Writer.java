/**
 *
 *  @author Kilman Miłosz S22758
 *
 */

package zad4;

import java.util.concurrent.BlockingQueue;


public class Writer implements Runnable {


    private final BlockingQueue blockingQueue;

    public Writer (Author auth) {
        this.blockingQueue = auth.AuthQueue;
    }

    @Override
    public void run() {
        while(true){
            try {
                System.out.println("-> " + blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
