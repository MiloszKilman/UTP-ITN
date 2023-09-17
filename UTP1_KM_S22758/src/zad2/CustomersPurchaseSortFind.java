/**
 *
 *  @author Kilman Miłosz S22758
 *
 */

package zad2;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
public class CustomersPurchaseSortFind {
    private List<Purchase> listaZakupow;


    public void readFile(String fname) {
        listaZakupow = new ArrayList<>();
        try {
            //odczytanie wszystkich linii i określenie czym rozdzielone są poszczególne pola klasy
            Files.readAllLines(Paths.get(fname)).forEach(line -> {
                //określenie znaku podziału
                String[] attributes = line.split(";");
                //utworzenie obiektu klasy pomocniocznej
                Purchase purchase = new Purchase(line);
                //odzczyt id
                purchase.setId(attributes[0]);
                //odczyt imienia i nazwiska oraz rozdzielenia na dwa stringi
                String[] fullName = attributes[1].split(" ");
                purchase.setImie(fullName[1]);
                purchase.setNazwisko(fullName[0]);
                //odczyt produktu
                purchase.setProdukt(attributes[2]);
                //odczyt ilości
                purchase.setIlosc(Double.parseDouble(attributes[3]));
                //odczyt ceny
                purchase.setCena(Double.parseDouble(attributes[4]));
                //dodanie oddczytanych danych obiektu Purchase do listy
                listaZakupow.add(purchase);


            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showSortedBy(String sortByt) {
        if (sortByt == "Nazwiska") {
            System.out.println("Nazwiska");
            //prównianie nazwisk za pomocą compareTo
            //jeżeli 0 to dodatkowo porownujemy po ID
            //wyświetlenie wyselekcjonowanych wyżej linii
            List<Purchase> toSort = new ArrayList<>();
            for (Purchase purchase : listaZakupow) {
                toSort.add(purchase);
            }
            toSort.sort((obj1, obj2) -> {
                //prównianie nazwisk za pomocą compareTo
                //jeżeli 0 to dodatkowo porownujemy po ID
                if (obj1.getNazwisko().compareToIgnoreCase(obj2.getNazwisko()) == 0) {
                    return obj1.getId().compareToIgnoreCase(obj2.getId());
                } else return obj1.getNazwisko().compareToIgnoreCase(obj2.getNazwisko());
                //wyświetlenie wyselekcjonowanych wyżej linii
            });
            for (Purchase purchase : toSort) {
                System.out.println(purchase.getLine());
            }
        } else if (sortByt == "Koszty") {
            System.out.println("Koszty");
            //prównianie kosztów za pomocą compare                \
            // mnożąc przez -1 otrzymujemy porządek malejący
            //jeżeli 0 to dodatkowo porownujemy po ID
            //wyświetlenie wyselekcjonowanych wyżej linii
            List<Purchase> toSort = new ArrayList<>();
            for (Purchase purchase : listaZakupow) {
                toSort.add(purchase);
            }
            toSort.sort((obj1, obj2) -> {
                //prównianie kosztów za pomocą compare                \
                // mnożąc przez -1 otrzymujemy porządek malejący
                //jeżeli 0 to dodatkowo porownujemy po ID
                if (Double.compare((obj1.getIlosc() * obj1.getCena() * -1), (obj2.getIlosc() * obj2.getCena() * -1)) == 0) {
                    return obj1.getId().compareToIgnoreCase(obj2.getId());
                } else return Double.compare(
                        (obj1.getIlosc() * obj1.getCena() * -1)
                        , (obj2.getIlosc() * obj2.getCena() * -1)
                );
                //wyświetlenie wyselekcjonowanych wyżej linii
            });
            for (Purchase purchase : toSort) {//przypisanie kosztu konkretnego obiektu w celu wyświetlenia
                double koszt = purchase.getCena() * purchase.getIlosc();
                System.out.println(purchase.getLine() + " (koszt: " + koszt + ")");
            }
        }
        System.out.println();
    }

    public void showPurchaseFor(String id) {
        System.out.println("Klient" + id);
        for (Purchase purchase : listaZakupow) {
            if (purchase.getId().equals(id)) {
                System.out.println(purchase.getLine());
            }
        }
        System.out.println();
    }
}

