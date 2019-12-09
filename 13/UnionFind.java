public class UnionFind {
  public static void main(String[] args) {
    UnionFind k = new UnionFind(5);
    System.out.println(k.laske()); // 5
    k.yhdista(1, 2);
    k.yhdista(2, 3);
    System.out.println(k.laske()); // 3
    k.yhdista(1, 3);
    System.out.println(k.laske()); // 3
    k.yhdista(4, 5);
    System.out.println(k.laske()); // 2
  }

  private int komponentteja;
  private int[] vanhempi;
  private int[] koot;

  public UnionFind(int n) {
    this.komponentteja = n;
    this.vanhempi = new int[n + 1];
    this.koot = new int[n + 1];
    /* Aluksi jokainen solmu on oma joukkonsa, koko = 1 */
    for (int i = 1; i <= n; i++) {
      this.vanhempi[i] = i;
      this.koot[i] = 1;
    }
  }

  int laske() {
    return this.komponentteja;
  }

  void yhdista(int a, int b) {
    a = edustaja(a);
    b = edustaja(b);
    if (a == b)
      return;
    if (this.koot[a] > this.koot[b]) {
      this.vanhempi[b] = a;
      this.koot[a] += this.koot[b];
    } else {
      this.vanhempi[a] = b;
      this.koot[b] += this.koot[a];
    }
    this.komponentteja--;
  }

  boolean sama(int a, int b) {
    return edustaja(a) == edustaja(b);
  }

  int edustaja(int x) {
    while (x != this.vanhempi[x]) {
      x = this.vanhempi[x];
    }
    return x;
  }

  void tulostaJoukko(int solmu) {
    while (solmu != this.vanhempi[solmu]) {
      System.out.print(String.format("%s ", solmu));
      solmu = this.vanhempi[solmu];
    }
    System.out.print(String.format("%s ", this.vanhempi[solmu]));
    System.out.println(String.format("%s", ""));
    System.out.println(String.format("%s", "--------------"));
  }
}