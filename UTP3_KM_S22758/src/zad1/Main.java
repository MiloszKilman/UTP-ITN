/**
 *
 *  @author Kilman Miłosz S22758
 *
 */

package zad1;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    Letters letters = new Letters("ABCD");
    for (Thread t : letters.getThreads()) System.out.println(t.getName());
    /*<- tu uruchomić 
         wszystkie kody w wątkach 
     */
    Letters letters1 = new Letters("A");
    Letters letters2 = new Letters("B");
    Letters letters3 = new Letters("C");
    Letters letters4 = new Letters("D");
    Thread a = new Thread(letters1);
    Thread b = new Thread(letters2);
    Thread c = new Thread(letters3);
    Thread d = new Thread(letters4);
    a.start();
    b.start();
    c.start();
    d.start();

    Thread.sleep(5000);

    /*<- tu trzeba zapisać
       fragment, który kończy działanie kodów, wypisujących litery 
    */

    a.interrupt();
    b.interrupt();
    c.interrupt();
    d.interrupt();
      System.out.println("\nProgram skończył działanie");
  }

}
