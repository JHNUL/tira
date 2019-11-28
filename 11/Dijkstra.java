import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

public class Dijkstra {

  class Kaari {
    private int alku, loppu, paino;

    public Kaari(int alku, int loppu, int paino) {
      this.alku = alku;
      this.loppu = loppu;
      this.paino = paino;
    }

    public int getAlku() {
      return alku;
    }

    public int getLoppu() {
      return loppu;
    }

    public int getPaino() {
      return paino;
    }
  }

  class Pari implements Comparable<Pari> {
    private int etaisyys;
    private int solmu;

    public Pari(int etaisyys, int solmu) {
      this.etaisyys = etaisyys;
      this.solmu = solmu;
    }

    public int getEtaisyys() {
      return this.etaisyys;
    }

    public int getSolmu() {
      return this.solmu;
    }

    @Override
    public int compareTo(Pari o) {
      return this.etaisyys - o.etaisyys;
    }
  }

  public static void main(String[] args) {
    int n = 1000000;
    Dijkstra d = new Dijkstra(n);
    for (int i = 1; i <= n - 1; i++) {
      d.lisaaYhteys(i, i + 1, 1);
    }
    long alku = System.nanoTime();
    System.out.println(String.format("%s", d.laske(1, 159814)));
    long loppu = System.nanoTime();
    System.out.println("Aikaa kului "+((loppu-alku)/1e9)+" s");
  }

  private ArrayList<Kaari>[] rakenne;
  private int n;

  public Dijkstra(int n) {
    this.rakenne = new ArrayList[n + 1];
    this.n = n;
    for (int i = 1; i <= n; i++) {
      this.rakenne[i] = new ArrayList<>();
    }
  }

  void lisaaYhteys(int a, int b, int p) {
    this.rakenne[a].add(new Kaari(a, b, p));
    this.rakenne[b].add(new Kaari(b, a, p));
  }

  int laske(int a, int b) {
    PriorityQueue<Pari> keko = new PriorityQueue<>();
    int[] etaisyys = new int[this.n + 1];
    boolean[] kasitelty = new boolean[this.n + 1];
    for (int i = 1; i <= n; i++) {
      etaisyys[i] = 999999999;
      kasitelty[i] = false;
    }
    etaisyys[a] = 0;
    keko.add((new Pari(0, a)));
    while (!keko.isEmpty()) {
      int solmu = keko.poll().getSolmu();
      if (kasitelty[solmu])
        continue;
      kasitelty[solmu] = true;
      for (Kaari k : this.rakenne[solmu]) {
        int nyky = etaisyys[k.getLoppu()];
        int uusi = etaisyys[solmu] + k.getPaino();
        if (uusi < nyky) {
          etaisyys[k.getLoppu()] = uusi;
          keko.add(new Pari(uusi, k.getLoppu()));
        }
      }
    }
    return etaisyys[b] == 999999999 ? -1 : etaisyys[b];
  }

}