package zad1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Line {
    private String countryCode;
    private String countryName;
    private String dateFrom;
    private String dateTo;
    private String location;
    private String price;
    private String currency;
    private SimpleDateFormat formatter;
    public Line(String countryCode, String countryName, String dateFrom, String dateTo, String location, String price, String currency) {
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.location = location;
        this.price = price;
        this.currency = currency;
        this.formatter = new SimpleDateFormat("yyyy-MM-dd");
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public Date getDateFrom() {
        try {
            return formatter.parse(dateFrom);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Date getDateTo() {

        try {
            return formatter.parse(dateTo);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getLocation() {
        return location;
    }

    public String getPrice() {
        return price;
    }


    public String getCurrency() {
        return currency;
    }

    public String toString() {
        return  countryCode + "," + countryName + "," + dateFrom + "," + dateTo +  "," + location + "," +  "," + price + "," + currency;
    }
    String[] toArray() {
        return new String[]{countryCode, countryName, dateFrom, dateTo, location, price, currency};
    }


}
