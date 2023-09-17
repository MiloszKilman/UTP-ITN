package zad1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TravelData {

    private Properties dictionary;

    private List<Record> data;

    public TravelData(File dataDir) {
        data = new ArrayList<>();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Arrays.stream(Objects.requireNonNull(dataDir.listFiles())).forEach(file -> {
            try {
                Files.lines(Paths.get(file.getPath())).forEach(line -> {
                    String[] lineData = line.split("\t");

                    int i = 0;

                    Record record;

                    Locale locale = Locale.forLanguageTag(lineData[i++].replace("_", "-"));
                    NumberFormat numberFormat = NumberFormat.getInstance(locale);

                    try {
                        record = new Record(
                                locale,
                                lineData[i++],
                                simpleDateFormat.parse(lineData[i++]),
                                simpleDateFormat.parse(lineData[i++]),
                                lineData[i++],
                                numberFormat.parse(lineData[i++]).doubleValue(),
                                lineData[i]
                        );

                        data.add(record);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

            try (InputStream input = new FileInputStream("dictionary.properties")) {
                dictionary = new Properties();
                dictionary.load(input);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    List<String> getOffersDescriptionsList(String loc, String dateFormat) {
        List<String> descList = new ArrayList<>();

        Locale destLocale = Locale.forLanguageTag(loc.replace("_", "-"));
        NumberFormat numberFormat = NumberFormat.getInstance(destLocale);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

        data.forEach(d -> {
            StringBuilder sb = new StringBuilder();

            String country = translateCountry(d.getCountryCode(), destLocale, d.getCountryName());
            sb.append(country).append(" ");
            sb.append(simpleDateFormat.format(d.getDateFrom())).append(" ");
            sb.append(simpleDateFormat.format(d.getDateTo())).append(" ");
            sb.append(translateWord(d.getCountryCode(), destLocale, d.getLocation())).append(" ");
            sb.append(numberFormat.format(d.getPrice())).append(" ");
            sb.append(d.getCurrency());

            descList.add(sb.toString());
        });

        return descList;
    }

    private String translateCountry(Locale inLocale, Locale outLocale, String countrName) {
        for (Locale l : Locale.getAvailableLocales()) {
            if (l.getDisplayCountry(inLocale).equals(countrName)) {
                return l.getDisplayCountry(outLocale);
            }
        }

        return null;
    }

    private String translateWord(Locale inLocale, Locale outLocale, String word) {
        return dictionary.getProperty(inLocale.getLanguage() + "-" + outLocale.getLanguage() + "." + word, word);
    }

    List<Record> getData() {
        return data;
    }
}