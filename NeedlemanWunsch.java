/**
 * Algoritma, basit�e iki dizgi (string) aras�ndaki yaslama durumunu bulmay� ama�lar. 
 * Buna g�re iki dizgiden olu�turulan bir �l��m de�eri ile (metric) dizgiler kar��la�t�r�l�r 
 * ve farkl�l�klar bulunur.
 */
public class NeedlemanWunsch {

  /**
   * Global maksimum benzerlik matrisini hesaplayan fonksiyon.
   *
   * @param sequence1 �lk amino asit dizisini temsil eden bir String.
   * @param sequence2 �kinci amino asit dizisini temsil eden bir String.
   * @param matchScore E�le�melere atanan puan� temsil eden bir float degeri.
   * @param mimatchScore Uyu�mazl�klara verilen puan� temsil eden bir float degeri.
   * @param indelScore Eklemeler ve silmelere verilen puan� temsil eden bir float degeri.
   */
  public static float[][] computeMatrix(String sequence1, String sequence2, float matchScore, float mismatchScore, float indelScore) {
    sequence1 = "-" + sequence1;
    sequence2 = "-" + sequence2;

    float[][] resultMatrix = new float[sequence1.length()][sequence2.length()];

    for (int i = 0; i < sequence1.length(); i++) {
      resultMatrix[i][0] = i * indelScore;
    }
    for (int j = 0; j < sequence2.length(); j++) {
      resultMatrix[0][j] = resultMatrix[0][j] = j * indelScore;
    }

    for (int i = 1; i < sequence1.length(); i++) {
      for (int j = 1; j < sequence2.length(); j++) {
        resultMatrix[i][j] = Math.max(resultMatrix[i - 1][j] + indelScore,
        Math.max(resultMatrix[i][j - 1] + indelScore, resultMatrix[i - 1][j - 1] +
        (sequence1.charAt(i) == sequence2.charAt(j) ? matchScore : mismatchScore)));
      }
    }

    return resultMatrix;
  }
}
