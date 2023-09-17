/**
 *
 *  @author Kilman Miłosz S22758
 *
 */

package zad1;


public class Main {
  public static void main(String[] args) {
    String dataDir = System.getProperty("user.home") + "/Modeling/data/";
    String scriptDir = System.getProperty("user.home") + "/Modeling/scripts/";
    Controller ctl = new Controller("Model1");
    ctl.readDataFrom(dataDir + "data2.txt") 
                    .runModel()
                    .runScriptFromFile(scriptDir + "script1.groovy");
    String res= ctl.getResultsAsTsv();
    System.out.println(res);
  }
}    
