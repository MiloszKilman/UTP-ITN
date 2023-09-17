package zad2;

import java.nio.charset.StandardCharsets;

public class StringTask  implements Runnable{
    private String slowo;
    private int ile;
    volatile TaskState status = TaskState.ABORTED;
    private String wynik;
    private boolean done = false;
    private String tekstNaWyjscie;

    public enum TaskState {
        CREATED, RUNNING ,ABORTED ,READY
    }
    public Thread thread;
    public StringTask(String string, int i) {
        this.slowo = string;
        this.ile = i;
        this.status = TaskState.CREATED;
        this.thread = new Thread(this);
    }

    public TaskState getState() {

        return this.status;
    }

    public boolean isDone() {

        return this.done;
    }

    public String getResult() {

        return wynik;
    }
    //
//    public void run(){
//        this.status = TaskState.READY;
//        for(int i = 0; i < slowo.length() ; i++){
//            wynik = wynik  + slowo + " ";
//        }
//        try {
//            this.abort();
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
//
//    }
    public void run(){
        byte [] tab = slowo.getBytes(StandardCharsets.UTF_8);
        byte [] tmp = new byte[slowo.length()];
        for (int i = 0; i < tab.length; i++) {
            tmp[tab.length -i -1] = tab[i];
        }
        slowo = tmp.toString();

        for (int i = 0; i < ile; i++) {
            tekstNaWyjscie +=slowo;
            if(thread.isInterrupted()){
                return;
            }
        }
        this.status= TaskState.READY;
    }
    public void start() {
        thread.run();
        this.status = TaskState.RUNNING;
    }

    public void abort() throws Throwable {
        thread.isInterrupted();
        this.status = TaskState.ABORTED;

    }

}
