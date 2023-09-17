package zad1;

import java.util.ArrayList;

public class Letters extends Thread {
    public String s;
    ArrayList<Thread> threads = new ArrayList<Thread>();
    public Letters(String s) {
        this.s=s;
        for(int i = 0; i < s.length(); i++){
            String letter = s.substring(i, i+1);
            threads.add(new Thread("Thread " + letter){
                public void run(){
                    while(true){
                        try{
                            System.out.print(letter);
                            Thread.sleep(500);
                        } catch (InterruptedException ie){
                            break;
                        }
                    }
                }
            });
        }
    }
    public ArrayList<Thread> getThreads(){
        return threads;
    }

}
