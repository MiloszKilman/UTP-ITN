package zad1;

import java.util.*;
import java.util.stream.Collectors;

public class XList<T> extends ArrayList <T> {
    //private ArrayList  list = new ArrayList<>();

    public XList  (T... arguments) {
        Collections.addAll(this, arguments);
    }

    public static <T> XList<T> of(T... t) {
        XList list = new XList<T>();
        for(T types : t) {
            list.add(types);
        }


        return list;
    }

    public static  XList<String> charsOf(String str) {
        XList listChar = new XList();
        char [] ch = str.toCharArray();
        for (char chr :  ch) {
            listChar.add(chr);
        }
        return listChar;
    }
    //używając metody of dodajemy do listy łańcuchy rozdzielając je dodatkowo za pomocą split według drugiego argumentu
    public static XList<String> tokensOf(String str, String sep) {
        XList <String> tokensList = new XList();
        Collections.addAll(tokensList, str.split(sep));
        return tokensList;
    }
    //przesłonięta metoda jednoargumentowa  wykorzystojąca wewnątrz przesłoniętą dwuargumentową
    public static XList<String> tokensOf(String str) {
        return XList.tokensOf(str, " ");
    }
    //tworzymy nową XListę dodając do niej instniejące już wartości (this), a następnie dopisujemy kolejne
    public XList<T> union(Object t) {
        XList listUnion = new XList(this);
        listUnion.addAll(XList.of(t));
        return listUnion;
    }
    //tworzymy nową XListę dodając do niej instniejące już wartości (this), a następnie usuwamy wszystkie ten podane jako argument
    //do poprawy!
    public XList<T> diff(Object... ts) {
        XList diffList = new XList(this);
        for(int i =0; i<= diffList.size(); i++) {
            diffList.removeAll(XList.of(ts));
        }
        return diffList;
    }


    public XList<T> unique() {
        XList uniqueList = new XList();
        for (T t : this) {
            if (!uniqueList.contains(t)) {
                uniqueList.add(t);
            }
        }
        return uniqueList;
    }

    public String join(String sep) {
        StringJoiner joiner = new StringJoiner(sep);
        String toString;
        for (T t : this) {
            toString = t.toString();
            joiner.add(toString);
        }
        String collect = joiner.toString();
        return collect;
    }

    public String join() {
        return join("");
    }

    public XList<XList<String>> combine() {
        XList combineList = new XList();
        XList tmpList = new XList();
        int i =0, j=0;
        for(T t: this) {
            tmpList.add(i, this.get(i));
            tmpList.add(this.get(j));


            i++;
            j++;
            combineList.addAll(tmpList);
        }
        return combineList;
    }
}





