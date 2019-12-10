import java.util.PriorityQueue;
import java.util.Random;

public class Kunnostus {

  class Kaari implements Comparable<Kaari> {
    int alku, loppu, paino;

    public Kaari(int a, int b, int p) {
      this.alku = a;
      this.loppu = b;
      this.paino = p;
    }

    @Override
    public int compareTo(Kaari o) {
      return this.paino - o.paino;
    }

    @Override
    public String toString() {
      return String.format("%s->%s (%s)", alku, loppu, paino);
    }
  }

  public static void main(String[] args) {
    int n = 100000;
    Kunnostus k = new Kunnostus(n);
    Random r = new Random(1337);
    for (int i = 1; i <= n; i++) {
      int a = r.nextInt(n) + 1;
      int b = r.nextInt(n) + 1;
      int x = r.nextInt(1000) + 1;
      k.lisaaTie(a, b, x);
    }
    System.out.println(String.format("%s", k.laske())); // -1
  }

  private int[] vanhempi;
  private int komponentteja;
  private int[] koko;
  private int n;
  private PriorityQueue<Kaari> kaaret;

  public Kunnostus(int n) {
    this.n = n;
    this.komponentteja = n;
    this.vanhempi = new int[n + 1];
    this.koko = new int[n + 1];
    this.kaaret = new PriorityQueue<>();
    /* Aluksi jokainen solmu on oma joukkonsa */
    for (int i = 1; i <= n; i++) {
      this.vanhempi[i] = i;
      this.koko[i] = 1;
    }
  }

  int laske() {
    if (this.n == 1)
      return 0;
    int painot = 0;
    while (!this.kaaret.isEmpty()) {
      Kaari k = this.kaaret.poll();
      int a = edustaja(k.alku);
      int b = edustaja(k.loppu);
      if (a != b) {
        if (this.koko[a] > this.koko[b]) {
          this.vanhempi[b] = a;
          this.koko[a] += this.koko[b];
        } else {
          this.vanhempi[a] = b;
          this.koko[b] += this.koko[a];
        }
        this.komponentteja--;
        painot += k.paino;
      }
    }
    return painot == 0 || this.komponentteja > 1 ? -1 : painot;
  }

  void lisaaTie(int a, int b, int x) {
    if (a==b) return;
    this.vanhempi[a] = b;
    Kaari k = new Kaari(a, b, x);
    kaaret.add(k);
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

  void tulostaPino() {
    while (!this.kaaret.isEmpty()) {
      System.out.println(String.format("%s", this.kaaret.poll()));
    }
  }
}