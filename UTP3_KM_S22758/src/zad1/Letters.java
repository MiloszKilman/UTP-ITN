package zad1;

import java.util.ArrayList;
import static java.util.stream.IntStream.*;

public class Letters implements Runnable {
    ArrayList<Thread> threads = new ArrayList<>();
    String letter;

    public Letters(String s) {
        range(0, s.length()).forEach(i -> {
            //weź podciąg od i do i+1
            this.letter = s.substring(i, i + 1);
            //dodaj do tablicy  wątków
            threads.add(new Thread("Thread " + letter));
        });
    }



    public ArrayList<Thread> getThreads() {

        return threads;
    }
    @Override
    public void run(){
        while (true){
            try {
                System.out.print(letter);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }

        }
    }
}