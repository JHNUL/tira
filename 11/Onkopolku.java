
public class Onkopolku {

  public static void main(String[] args) {
    Onkopolku x = new Onkopolku(4);
    x.lisaaKaari(1, 2, 1);
    x.lisaaKaari(2, 4, 5);
    x.lisaaKaari(4, 2, 4);
    x.lisaaKaari(2, 3, 1);
    x.tulostaMatriisi();
    System.out.println(x.tutki()); // true
  }

  int[][] rakenne;
  int n;
  final int INF = 999999999;

  public Onkopolku(int n) {
    this.rakenne = new int[n + 1][n + 1];
    this.n = n;
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j++) {
        lisaaKaari(i, j, INF);
      }
    }
  }

  void lisaaKaari(int a, int b, int p) {
    this.rakenne[a][b] = p;
  }

  boolean tutki() {
    for (int k = 1; k <= this.n; k++) {
      for (int i = 1; i <= this.n; i++) {
        for (int j = 1; j <= this.n; j++) {
          if (this.rakenne[i][k] < INF && this.rakenne[k][j] < INF) {
            this.rakenne[i][j] = Math.min(this.rakenne[i][j], this.rakenne[i][k] + this.rakenne[k][j]);
          }
        }
      }
    }
    if (this.rakenne[1][this.n] == INF)
      return false;
    for (int i = 1; i <= this.n; i++) {
      /* Jos solmu kuuluu negatiiviseen sykliin, sen etäisyys itseensä on < 0
      eli tarkistetaan matriisi diagonaalisesti m[1][1], m[2][2] jne.
      Testataan päästäänkö alkusolmusta (1) jonkin negatiivisen syklin solmun kautta loppusolmuun (n). */
      if (this.rakenne[1][i] < INF && this.rakenne[i][i] < 0 && this.rakenne[i][this.n] < INF)
        return false;
    }
    return true;
  }

  void tulostaMatriisi() {
    for (int i = 1; i <= this.n; i++) {
      for (int j = 1; j <= this.n; j++) {
        System.out.print(String.format("%-" + 10 + "s", this.rakenne[i][j]));
      }
      System.out.println(String.format("%s", ""));
    }
  }

}