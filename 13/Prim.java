import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

public class Prim {

  class Kaari {
    int alku, loppu, paino;

    public Kaari(int a, int b, int p) {
      this.alku = a;
      this.loppu = b;
      this.paino = p;
    }
  }

  class Pari implements Comparable<Pari> {
    int avain, solmu;

    public Pari(int a, int s) {
      this.avain = a;
      this.solmu = s;
    }

    @Override
    public int compareTo(Pari p) {
      return this.avain - p.avain;
    }

  }

  public static void main(String[] args) {
    int n = 10000000;
    Prim k = new Prim(n);
    for (int i = 1; i <= n - 1; i++) {
      k.lisaaTie(i, i + 1, 10);
    }
    long alku = System.nanoTime();
    int t = k.laske();
    long loppu = System.nanoTime();
    System.out.println("Aikaa kului " + ((loppu - alku) / 1e9) + " s");
    System.out.println(String.format("%s tulos Prim", t));
  }

  private ArrayList<Kaari> solmut[];
  private boolean[] lisatyt;
  private int[] etaisyydet;
  private final int INF = 999999999;

  public Prim(int n) {
    this.solmut = new ArrayList[n + 1];
    this.lisatyt = new boolean[n + 1];
    this.etaisyydet = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      this.solmut[i] = new ArrayList<>();
      this.lisatyt[i] = false;
      this.etaisyydet[i] = INF;
    }
  }

  void lisaaTie(int a, int b, int x) {
    if (a == b)
      return;
    this.solmut[a].add(new Kaari(a, b, x));
  }

  int laske() {
    PriorityQueue<Pari> parit = new PriorityQueue<>();
    parit.add(new Pari(0, 1));
    int painot = 0;
    while (!parit.isEmpty()) {
      Pari pari = parit.poll();
      if (lisatyt[pari.solmu])
        continue;
      lisatyt[pari.solmu] = true;
      painot += pari.avain;
      for (Kaari k : this.solmut[pari.solmu]) {
        int nyky = this.etaisyydet[k.loppu];
        int uusi = k.paino;
        if (uusi < nyky) {
          this.etaisyydet[k.loppu] = uusi;
          parit.add(new Pari(uusi, k.loppu));
        }
      }
    }
    return painot;
  }
}
