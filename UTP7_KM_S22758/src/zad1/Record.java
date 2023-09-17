package zad1;

import java.util.Date;
import java.util.Locale;

public class Record {
    private Locale countryCode;
    private String countryName;
    private Date dateFrom; //YYYY-MM-DD
    private Date dateTo; //YYYY-MM-DD
    private String location;
    private Double price;
    private String currency;

    Record(Locale countryCode, String countryName, Date dateFrom, Date dateTo, String location, Double price, String currency) {
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.location = location;
        this.price = price;
        this.currency = currency;
    }

    Locale getCountryCode() {
        return countryCode;
    }

    String getCountryName() {
        return countryName;
    }

    Date getDateFrom() {
        return dateFrom;
    }

    Date getDateTo() {
        return dateTo;
    }

    String getLocation() {
        return location;
    }

    Double getPrice() {
        return price;
    }

    String getCurrency() {
        return currency;
    }

    Object[] toArray() {
        return new Object[]{countryCode, countryName, dateFrom, dateTo, location, price, currency};
    }
}