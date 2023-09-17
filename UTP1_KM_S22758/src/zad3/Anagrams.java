/**
 *
 *  @author Kilman Miłosz S22758
 *
 */

package zad3;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Anagrams {
    private List<String> slowa = new ArrayList<>();
    private HashMap<String, List<String>> anagrams = new HashMap<>();
    private boolean status=true;

    public Anagrams(String fileWithWords) {
        try {
            Files.readAllLines(Paths.get(fileWithWords)).forEach(line ->{
                //określenie znaku podziału
                String[] strings = line.split("\\s+");
                //String [] strings1 = line.split("\t");
                for (int i = 0; i < strings.length ; i++) {
                    slowa.add(strings[i]);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        //wstępne sortowanie słów i przypisywanie do hashmapy
        for (String slowo : slowa) {
            //zamiana słów na tablicę znaków
            char [] toArr = slowo.toCharArray();
            //sortowanie tablicy
            Arrays.sort(toArr);
            //przypisanie tablicy znaków do stringa
            String postSort = new String(toArr);
            //przypisujemy do hashmapy klucze i wartośći

            //jeżeli klucz istnieje dla posortowanego stringu dodaj do niego wartość
            if (anagrams.containsKey(postSort)) {
                anagrams.get(postSort).add(slowo);
            }
            //w przeciwnym raziwe tworzymy nową parę klucz-wartość
            else {
                //dodatkowa lista, którą następnie dodajemy do hashmapy jako wartość
                List<String> list = new ArrayList<>();
                list.add(slowo);
                anagrams.put(postSort, list);
            }
        }
    }

    private static List<String> apply(List<String> a, List<String> b) {
        throw new AssertionError();
    }

    public void show(){
        for (int i = 0; i <slowa.size() ; i++) {
            System.out.println(slowa.get(i));
        }
    }

    public Iterable<List<String>> getSortedByAnQty() {
        //szukamy par klucz - wartość i sortujemy za pomocą komparatora po długości
        //interując po mapie pobiramy klucze i wartości w kolejnych iteracjach
        List<Map.Entry<String, List<String>>> toSort = new ArrayList<>();
        for (Map.Entry<String, List<String>> stringListEntry : anagrams.entrySet()) {
            toSort.add(stringListEntry);
        }
        toSort.sort((str1, str2) -> Integer.compare(str2.getValue().size(), str1.getValue().size()));
        LinkedHashMap<String, List<String>> map = new LinkedHashMap<>();
        //dodajemy za pomocą złączenia klucze i wartości
        for (Map.Entry<String, List<String>> stringListEntry : toSort) {
            map.merge(stringListEntry.getKey(), stringListEntry.getValue(), Anagrams::apply);
        }
        //dodajemy złączone wartości do listy
        Collection<List<String>> values = map.values();
        return values;
    }


    public String getAnagramsFor(String s) {
        //zamiana słów na tablicę znaków
        char [] toArr = s.toCharArray();
        //sortowanie tablicy
        Arrays.sort(toArr);
        //przypisanie tablicy znaków do stringa
        String postSort = new String(toArr);
        String finalSTR="";
        //jeżeli jest klucz odpowiadający stringowi s wchodzimy do ifa
        if (anagrams.containsKey(postSort)) {
            //pobieramy odppowiadający klucz i flitrujemy go, jeżeli str nie zgadza się z kluczem posortowanym to dodajemy go do kolekcji
            // dzięki temu otrzymujemy anagramy dla każdego słowa s
            finalSTR = anagrams.get(postSort).stream().filter(str -> !str.equals(s)).collect(Collectors.joining(", "));
        }

        return s + ": [" + finalSTR + "]";
    }

}
