package zad1;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class TravelData {

    private ArrayList<Line> arrData;
    private SimpleDateFormat simpleDateFormat;
    private  Properties properties;
    private File dir;
    TravelData(File data) {
        arrData = new ArrayList<>();
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        File dir = data;
        File[] files = dir.listFiles();
        for (File file : files) {
            try{
            if(file.isFile()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = null;
                while ((line = br.readLine()) != null) {
                    String lines[] = line.split("\t");
                    Line line1 = new Line(lines[0], lines[1], lines[2], lines[3], lines[4], lines[5], lines[6]);
                    arrData.add(line1);

                    this.properties = new Properties();
                    this.properties.load(new FileInputStream("prop.properties"));
                }
            }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        }


    public ArrayList<Line> getArrData() {
        return arrData;
    }

List<String> getOffersDescriptionsList(String locale, String dateFormat) {
    List<String> descList = new ArrayList<>();

    Locale destLocale = Locale.forLanguageTag(locale.replace("_","-"));

    Locale.setDefault(destLocale);
    simpleDateFormat = new SimpleDateFormat(dateFormat);

    for (Line d : getArrData()) {
        Locale inLocale = Locale.forLanguageTag(d.getCountryCode().replace("_", "-"));
        String countrName = null;
        for (Locale l : Locale.getAvailableLocales()) {
            if (l.getDisplayCountry(inLocale).equals(d.getCountryName())) {
                countrName = l.getDisplayCountry(destLocale);
            } else if (countrName == null) {
                countrName = properties.getProperty(inLocale + "-" + destLocale.getLanguage() + "." + d.getCountryName(), d.getCountryName());
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(countrName).append(" " + "\t").append(simpleDateFormat.format(d.getDateFrom())).append(" " + "\t").
                append(simpleDateFormat.format(d.getDateTo())).append(" \t").
                append(properties.getProperty(inLocale.getLanguage() +
                        "-" + destLocale.getLanguage() + "." + d.getLocation(), d.getLocation())).
                append(" \t").append(d.getPrice()).append(" \t").append(d.getCurrency());
        descList.add(sb.toString());
    }

    return descList;
}

}
