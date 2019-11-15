public class Binomit {
  public static void main(String[] a) {
    Binomit b = new Binomit();
    System.out.println(String.format("%s", b.binomikerroin(50)));
  }

  /*
   * yht 84 tehtävää (6*14)
   * kuitenkin 42 tehtävää (3*14) on pakollisia, eli
   * valintojen joukko n on 42, mutta yhdelle viikolle
   * voi asettaa yhteensä vain 6 tehtävää
   */

  long binomikerroin(int k) {
    int n = 42;
    long[][] binom = new long[n+1][k-42+1];
    binom[0][0] = 1;
    for (int i = 1; i <= n; i++) {
      binom[i][0] = 1;
      for (int j = 1; j <= k; j++) {
        binom[i][j] = binom[i-1][j-1]+binom[i-1][j];
      }
    }
    return binom[n][k];
  }
}
