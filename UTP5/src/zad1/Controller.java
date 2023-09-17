package zad1;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Controller {
    private String modelName;
    private Map<String, Object> data;
    private Map<String, Object> results;

    public Controller(String modelName) {
        this.modelName = modelName;
        data = new HashMap<>();
        results = new HashMap<>();
    }

    public Controller readDataFrom(String fname) {
        try (BufferedReader br = new BufferedReader(new FileReader(fname))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts[0].equals("LATA")) {
                    int LL = parts.length - 1;
                    data.put("LL", LL);
                } else {
                    double[] values = new double[(int)data.get("LL")];
                    for (int i = 1; i < parts.length; i++) {
                        values[i - 1] = Double.parseDouble(parts[i]);
                    }
                    data.put(parts[0], values);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public Controller runModel() {
        return this.modelName;
    }

    public void runScriptFromFile(String fname) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("groovy");
        try {
            engine.eval(new FileReader(fname));
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public Controller runScript(String script) {
        // code to run script from string
        return this;
    }

    public String getResultsAsTsv() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : results.entrySet()) {
            sb.append(entry.getKey() + "\t");
            if (entry.getValue() instanceof double[]) {
                double[] values = (double[]) entry.getValue();
                for (double value : values) {
                    sb.append(value + "\t");
                }
            } else {
                sb.append(entry.getValue() + "\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
