import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class LyhinEtaisyysII {

  class Tuple implements Comparable<Tuple> {
    private int etaisyys;
    private int solmu;
    private int vanhempi;

    public Tuple(int etaisyys, int solmu, int vanhempi) {
      this.etaisyys = etaisyys;
      this.solmu = solmu;
      this.vanhempi = vanhempi;
    }

    public int getEtaisyys() {
      return this.etaisyys;
    }

    public int getSolmu() {
      return this.solmu;
    }

    public int getVanhempi() {
      return vanhempi;
    }

    @Override
    public int compareTo(Tuple o) {
      return this.etaisyys - o.etaisyys;
    }

    @Override
    public String toString() {
      return String.format("{%d, %d}", this.etaisyys, this.solmu);
    }
  }

  class Kaari {
    private int alku;
    private int loppu;
    private int paino;

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

  private ArrayList<Kaari>[] rakenne;
  private int n;

  public static void main(String[] args) {
    LyhinEtaisyysII l = new LyhinEtaisyysII(5);
    l.lisaaTie(1, 2, 7);
    l.lisaaTie(2, 4, 2);
    l.lisaaTie(1, 3, 6);
    l.lisaaTie(3, 4, 5);
    l.lisaaTie(4, 5, 3);
    System.out.println(l.muodosta(1, 5)); // [1, 2, 4, 5]
  }

  public LyhinEtaisyysII(int n) {
    this.n = n;
    this.rakenne = new ArrayList[n + 1];
    for (int i = 0; i < rakenne.length; i++) {
      this.rakenne[i] = new ArrayList<>();
    }
  }

  // void testaa() {
  //   Tuple t1 = new Tuple(5, 10);
  //   Tuple t2 = new Tuple(1, 5);
  //   Tuple t4 = new Tuple(0, 5);
  //   Tuple t5 = new Tuple(17, 5);
  //   Tuple t3 = new Tuple(3, 7);
  //   PriorityQueue<Tuple> keko = new PriorityQueue<>();
  //   keko.add(t3);
  //   keko.add(t1);
  //   keko.add(t5);
  //   keko.add(t2);
  //   keko.add(t4);
  //   System.out.println(String.format("%s", keko.poll()));
  //   System.out.println(String.format("%s", keko.poll()));
  //   System.out.println(String.format("%s", keko.poll()));
  //   System.out.println(String.format("%s", keko.poll()));
  //   System.out.println(String.format("%s", keko.poll()));
  // }

  void lisaaTie(int a, int b, int p) {
    Kaari k1 = new Kaari(a, b, p);
    this.rakenne[a].add(k1);
    Kaari k2 = new Kaari(b, a, p);
    this.rakenne[b].add(k2);
  }

  ArrayList<Integer> muodosta(int a, int b) {
    ArrayList<Integer> lista = new ArrayList<>();
    PriorityQueue<Tuple> keko = new PriorityQueue<>();
    int[] etaisyys = new int[this.n + 1];
    boolean[] kasitelty = new boolean[this.n + 1];
    for (int i = 1; i <= n; i++) {
      etaisyys[i] = 999999999;
      kasitelty[i] = false;
    }
    etaisyys[a] = 0;
    keko.add(new Tuple(0, a, -1));
    lista.add(a);
    while (!keko.isEmpty()) {
      Tuple tt = keko.poll();
      int solmu = tt.getSolmu();
      int vanhempi = tt.getVanhempi();
      if (kasitelty[solmu]) continue;
      kasitelty[solmu] = true;
      if (solmu != a && !lista.get(lista.size()-1).equals(vanhempi)) lista.add(vanhempi);
      for (Kaari k : this.rakenne[solmu]) {
        int nyky = etaisyys[k.getLoppu()];
        int uusi = etaisyys[solmu] + k.getPaino();
        if (uusi < nyky) {
          etaisyys[k.getLoppu()] = uusi;
          Tuple t = new Tuple(uusi, k.getLoppu(), solmu);
          keko.add(t);
        }
      }
    }
    if (etaisyys[b] == 999999999) {
      return null;
    }
    lista.add(b);
    return lista;
  }

}
// keko.push((0,alku))
// while not keko.empty()
//    solmu = keko.pop()[1]
//    if kasitelty[solmu]
//      continue
//    kasitelty[solmu] = true
//    for kaari in verkko[solmu]
//      nyky = etaisyys[kaari.loppu]
//      uusi = etaisyys[solmu]+kaari.paino
//      if uusi < nyky
//        etaisyys[kaari.loppu] = uusi
//        keko.push((uusi,kaari.loppu))