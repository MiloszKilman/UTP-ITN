package zad1;
import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

class GUI extends JFrame {
    private List<Line> data;
    private Properties properties;
    private Locale defLoc;
    GUI(List<Line> data) {
        this.properties = new Properties();
        try {
            this.properties.load(new FileInputStream("prop.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.data = data;
        defLoc=Locale.forLanguageTag("en");
        String[] languages = {"EN", "PL"};
        Object language = JOptionPane.showInputDialog(null, "Wybierz język!", "", 0, null, languages, "");
        switch(String.valueOf(language)) {
            case "EN":
                defLoc=Locale.forLanguageTag("en");
                break;
            case "PL":
                defLoc=Locale.forLanguageTag("pl");
                break;
        }

            setTitle("Travel Data");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));

        String[] columns = new String[] {"countryCode", "countryName", "dateFrom", "dateTo", "location", "price", "currency"};

        String[][] datas = new String[getData().size()][];
        for (int i = 0; i < getData().size(); i++) {
            Line record = getData().get(i);
            datas[i] = record.toArray();
        }
        this.setLayout(new BorderLayout());
        for (int j=0; j<columns.length; j++) {
            columns[j] = properties.getProperty(Locale.getDefault().getLanguage() + "-" + defLoc.getLanguage() + "." + columns[j], columns[j]);
        }

        JTable table = new JTable(datas, columns);

        add(new JScrollPane(table), BorderLayout.NORTH);
        pack();
        setVisible(true);
    }



    private List<Line> getData() {
        return data;
    }
}