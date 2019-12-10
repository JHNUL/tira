import java.util.Arrays;

public class Ruudukko {
  public static void main(String[] args) {
    Ruudukko r = new Ruudukko(1);
    r.teeLattia(1, 1);
    System.out.println(String.format("%s", r.laskeHuoneet())); // 1
    r.teeLattia(1, 1);
    System.out.println(String.format("%s", r.laskeHuoneet())); // 1
  }

  int[][] rakenne;
  int[][] solmurakenne;
  int[] vanhempi;
  int[] koot;
  int komponentit;
  int n;

  public Ruudukko(int n) {
    this.n = n;
    this.vanhempi = new int[n * n + 1];
    this.koot = new int[n * n + 1];
    this.rakenne = new int[n + 1][n + 1];
    this.solmurakenne = new int[n + 1][n + 1];
    this.komponentit = 0;
    int solmu = 1;
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j++) {
        this.solmurakenne[i][j] = solmu;
        this.vanhempi[solmu] = solmu;
        this.koot[solmu] = 1;
        solmu++;
      }
    }
  }

  int laskeHuoneet() {
    return this.komponentit;
  }

  void tarkistaNaapuri(int solmu, int y, int x) {
    if (x < 1 || x > this.n || y < 1 || y > this.n) {
      return;
    }
    int naapuri = this.rakenne[y][x];
    if (naapuri == 1) {
      yhdista(solmu, this.solmurakenne[y][x]);
    }
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
    this.komponentit--;
  }

  void teeLattia(int y, int x) {
    if (this.rakenne[y][x] == 1) return;
    this.rakenne[y][x] = 1;
    this.komponentit++;
    tarkistaNaapuri(this.solmurakenne[y][x], y - 1, x);
    tarkistaNaapuri(this.solmurakenne[y][x], y + 1, x);
    tarkistaNaapuri(this.solmurakenne[y][x], y, x - 1);
    tarkistaNaapuri(this.solmurakenne[y][x], y, x + 1);
  }

  int edustaja(int x) {
    while (x != this.vanhempi[x]) {
      x = this.vanhempi[x];
    }
    return x;
  }

  void testaa() {
    tulosta(this.rakenne);
    System.out.println(String.format("%s", "----------"));
    tulosta(this.solmurakenne);
    System.out.println(String.format("%s", "----------"));
    System.out.println(String.format("%s", Arrays.toString(this.vanhempi)));
  }

  void tulosta(int[][] r) {
    for (int y = 1; y < r.length; y++) {
      for (int x = 1; x < r.length; x++) {
        System.out.print(String.format("%s ", r[y][x]));
      }
      System.out.println(String.format("%s", ""));
    }
  }
}