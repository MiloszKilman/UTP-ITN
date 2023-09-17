/**
 *
 *  @author Kilman Miłosz S22758
 *
 */

package zad1.models;


public class Model1 {
  @Bind private int LL; // liczba lat  
  @Bind private  double[] twKI; // tempo wzrostu kons.ind.
  @Bind private  double[] twKS; // tempo wzrostu kons.społ.
  @Bind private  double[] twINW; // tempo wzrostu inwestycji
  @Bind private  double[] twEKS; // tempo wzrostu eksportu
  @Bind private  double[] twIMP; // tempo wzrostu importu
  @Bind private double[] KI;  // kons.ind.
  @Bind private double[] KS;  // kons.społeczna
  @Bind private double[] INW;  // inwestycje.
  @Bind private double[] EKS;  // eksport
  @Bind private double[] IMP;  // import.
  @Bind private double[] PKB;  // PKB

  private double temp; // pole nie związane z danymi dla modelu ani jego wynikami

  public Model1() {}

  public void run() {
    PKB = new double[LL];
    PKB[0] = KI[0] + KS[0] + INW[0] + EKS[0] - IMP[0];
    for (int t=1; t < LL; t++) {
      KI[t] = twKI[t]* KI[t-1];
      KS[t] = twKS[t]* KS[t-1];
      INW[t] = twINW[t]* INW[t-1];
      EKS[t] = twEKS[t]* EKS[t-1];
      IMP[t] = twIMP[t]* IMP[t-1];
      PKB[t] = KI[t] + KS[t] + INW[t] + EKS[t] - IMP[t];
    }
  }
}
