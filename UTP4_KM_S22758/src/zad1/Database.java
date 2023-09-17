package zad1;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    private String url;
    private Connection con;
    private TravelData travelData;

    public Database(String url, TravelData travelData) {
        this.url=url;
        this.travelData=travelData;


    }
    public Connection create() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "root", "");
            Statement statement = con.createStatement();
            statement.execute("Create Database IF NOT EXISTS JBDC");
            con.close();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/jbdc","root", "");
            Statement statement1 = connection.createStatement();
            statement1.execute(
                    "CREATE TABLE IF NOT EXISTS TravelData (Lokalizacja VARCHAR(255), KRAJ VARCHAR(255), " +
                            "od VARCHAR(255), do VARCHAR(255), miejscce VARCHAR(255), Cena VARCHAR(255), Waluta VARCHAR(255))");
            statement1.close();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO TravelData VALUES(?,?,?,?,?,?,?)");

            ArrayList<Line> datas = travelData.getArrData();
            for (int i = 0; i < datas.size(); i++) {
                Line l = datas.get(i);
                preparedStatement.setString(1, l.getCountryCode());
                preparedStatement.setString(2, l.getCountryName());
                preparedStatement.setString(3, String.valueOf(l.getDateFrom()));
                preparedStatement.setString(4, String.valueOf(l.getDateTo()));
                preparedStatement.setString(5, l.getLocation());
                preparedStatement.setString(6, l.getPrice());
                preparedStatement.setString(7, l.getCurrency());
                preparedStatement.execute();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return con;
    }
    public void showGui() {
        new GUI(travelData.getArrData());
    }
}
