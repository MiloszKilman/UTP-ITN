package zad2;

import java.util.stream.IntStream;

import static java.util.stream.IntStream.*;

public class StringTask implements Runnable {
    public enum TaskState {
        CREATED,RUNNING,ABORTED,READY;

    }

    private TaskState status;
    private String word;
    private int count;
    private String resultString;
    private Thread task;
    public StringTask(String word, int count) {
        this.status =TaskState.CREATED;
        this.task=new Thread(this);
        this.count = count;
        this.word = word;
    }
    public TaskState getState() {
        return status;
    }
    public boolean isDone() {
        if (status.equals(TaskState.ABORTED))
            return true;
        else if (status.equals(TaskState.READY))
            return true;
        else
        return false;
    }

    @Override
    public void run() {
        status=TaskState.RUNNING;
        while(!this.isDone()) {
            for (int i = 0; i < count; i++) {
                resultString = resultString+word;
                count--;
            }
            if(count == 0) {
                status =TaskState.READY;
                return;
            }
        }
        abort();

    }

    //uruchomienie w osobnym wątku
    public void start() {
        status=TaskState.RUNNING;
        Thread th = new Thread(this);
        th.start();
    }

    //funkcja zatrzymująca działanie wątku
    public void  abort() {
        task.interrupt();
        status = TaskState.ABORTED;
    }

    //zwraca całą konkatenacje
    public String getResult() {
        return resultString;

    }
}
