package zad5;
// Klasa "wypisywacza"
class Writer extends Thread {

    Teksty txtArea;

    Writer(Teksty t) {
        txtArea=t;
    }

    public void run() {
        String txt = txtArea.getTextToWrite();
        while(txt != null) {
            System.out.println("-> " + txt);
            txt = txtArea.getTextToWrite();
        }
    }

}