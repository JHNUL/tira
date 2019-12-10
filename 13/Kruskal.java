import java.util.PriorityQueue;
import java.util.Random;

public class Kruskal {

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
  }

  public static void main(String[] args) {
    int n = 10000000;
    Kruskal k = new Kruskal(n);
    for (int i = 1; i <= n - 1; i++) {
      k.lisaaTie(i, i + 1, 10);
    }
    long alku = System.nanoTime();
    int t = k.laske();
    long loppu = System.nanoTime();
    System.out.println("Aikaa kului " + ((loppu - alku) / 1e9) + " s");
    System.out.println(String.format("%s tulos Kruskal", t));
  }

  private int[] vanhempi;
  private int komponentteja;
  private int[] koko;
  private PriorityQueue<Kaari> kaaret;

  public Kruskal(int n) {
    this.komponentteja = n;
    this.vanhempi = new int[n + 1];
    this.koko = new int[n + 1];
    this.kaaret = new PriorityQueue<>();
    for (int i = 1; i <= n; i++) {
      this.vanhempi[i] = i;
      this.koko[i] = 1;
    }
  }

  int laske() {
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
    if (a == b)
      return;
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

}
