package zad5;

// Klasa dla ustalania i pobierania tekstów
class Teksty {

    String txt = null;
    boolean newTxt = false;

    // Metoda ustalająca tekst - wywołuje Autor
    synchronized void setTextToWrite(String s) {
        while (newTxt == true) {
            try {
                wait();
            } catch(InterruptedException exc) {}
        }
        txt = s;
        newTxt = true;
        notifyAll();
    }

    // Metoda pobrania tekstu - wywołuje Writer
    synchronized String getTextToWrite() {
        while (newTxt == false) {
            try {
                wait();
            } catch(InterruptedException exc) {}
        }
        newTxt = false;
        notifyAll();
        return txt;
    }

}