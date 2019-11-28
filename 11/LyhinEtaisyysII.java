import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class LyhinEtaisyysII {

  class Tuple implements Comparable<Tuple> {
    private int etaisyys;
    private int solmu;

    public Tuple(int etaisyys, int solmu) {
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

  void lisaaTie(int a, int b, int p) {
    Kaari k1 = new Kaari(a, b, p);
    this.rakenne[a].add(k1);
    Kaari k2 = new Kaari(b, a, p);
    this.rakenne[b].add(k2);
  }

  ArrayList<Integer> muodosta(int a, int b) {
    /* Vain yksi kaupunki */
    if (a == b) return new ArrayList<Integer>(Arrays.asList(a));

    ArrayList<Integer> lista = new ArrayList<>();
    PriorityQueue<Tuple> keko = new PriorityQueue<>();

    /* Tallennetaan etäisyydet siten, että solmun numero on avaimena ja arvona on [etäisyys, vanhempi] */
    HashMap<Integer, Integer[]> etaisyys = new HashMap<>();
    boolean[] kasitelty = new boolean[this.n + 1];
    for (int i = 1; i <= n; i++) {
      etaisyys.put(i, new Integer[2]);
      etaisyys.get(i)[0] = 999999999;
      kasitelty[i] = false;
    }
    keko.add(new Tuple(0, a));
    etaisyys.get(a)[0] = 0;
    while (!keko.isEmpty()) {
      int solmu = keko.poll().getSolmu();
      if (kasitelty[solmu]) continue;
      kasitelty[solmu] = true;
      for (Kaari k : this.rakenne[solmu]) {
        int nyky = etaisyys.get(k.getLoppu())[0];
        int uusi = etaisyys.get(solmu)[0] + k.getPaino();
        if (uusi < nyky) {
          etaisyys.get(k.getLoppu())[0] = uusi;
          /* Tässä laitetaan lyhyemmän kaaren lähtöpiste vanhemmaksi määräsolmulle */
          etaisyys.get(k.getLoppu())[1] = solmu;
          Tuple t = new Tuple(uusi, k.getLoppu());
          keko.add(t);
        }
      }
    }
    Integer[] loppuSolmu = etaisyys.get(b);
    if (loppuSolmu[0] == 999999999) {
      return null;
    }
    /* for (Integer i : etaisyys.keySet()) {
      System.out.println(String.format("%s: %s", i, Arrays.toString(etaisyys.get(i))));
    } */
    lista.add(b);
    /* Käydään läpi vanhempien ketju, josta saatiin lyhin reitti */
    Integer vanhempi = loppuSolmu[1];
    while (vanhempi != null) { // alkusolmulla ei ole vanhempaa
      lista.add(vanhempi);
      vanhempi = etaisyys.get(vanhempi)[1];
    }
    Collections.reverse(lista);
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