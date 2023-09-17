package zad1;

import java.sql.*;

class Database {


    private String url;
    private TravelData travelData;

    Database(String url, TravelData travelData) {
        this.travelData = travelData;
        this.url = url;
    }

    void create() {
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                conn.getMetaData();
            }

            assert conn != null;
            Statement statement = conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS traveldata (data TEXT NOT NULL);");

            PreparedStatement preparedStatement
                    = conn.prepareStatement("insert into traveldata (data) values (?)");

            for (Record r : travelData.getData()) {
                preparedStatement.setString(1, r.toString());
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    void showGui() {
        new GUI(travelData.getData());
    }
}