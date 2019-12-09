import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Reitit {

  public static void main(String[] args) {
    Reitit r = new Reitit(6);
    r.lisaaKaari(1, 2);
    r.lisaaKaari(1, 3);
    r.lisaaKaari(2, 3);
    r.lisaaKaari(2, 4);
    r.lisaaKaari(3, 5);
    r.lisaaKaari(5, 4);
    r.lisaaKaari(4, 6);
    r.lisaaKaari(5, 6);
    System.out.println(r.laske()); // 5
  }

  private ArrayList<Integer>[] verkko;
  private int n;
  private long[] polkuja;
  private ArrayList<Integer> topojarjestys;
  private int[] tilat;

  public Reitit(int n) {
    this.polkuja = new long[n + 1];
    this.topojarjestys = new ArrayList<>();
    this.tilat = new int[n + 1];
    this.n = n;
    this.verkko = new ArrayList[n + 1];
    for (int i = 1; i <= n; i++) {
      this.verkko[i] = new ArrayList<>();
    }
  }

  void lisaaKaari(int a, int b) {
    this.verkko[a].add(b);
  }

  void syvyysHaku(int solmu) {
    if (this.tilat[solmu] == 2)
      return;
    if (this.tilat[solmu] == 1)
      throw new IllegalStateException();
    if (this.tilat[solmu] == 0) {
      this.tilat[solmu] = 1;
      for (int edellinen : this.verkko[solmu]) {
        syvyysHaku(edellinen);
      }
      this.tilat[solmu] = 2;
      /* Täytetään topologista järjestystä */
      this.topojarjestys.add(solmu);
    }
  }

  /** Metodi toimii kun verkko on topologisessa järjestyksessä */
  long laske() {

    /* suoritetaan joukko syvyyshakuja, jotta saadaan topologinen järjestys */
    for (int i = 1; i <= this.n; i++) {
      syvyysHaku(i);
    }
    Collections.reverse(this.topojarjestys);
    System.out.println(String.format("%s", this.topojarjestys));
    /* Ekaan solmuun on 1 polku */
    this.polkuja[1] = 1;

    /*
     * Lisätään jokaiselle solmusta x saavutettavalle solmulle x:n polut Esimerkiksi
     * kaikille solmun 1 naapureille tulee polkuja 1
     */
    for (int i = 0; i < this.topojarjestys.size(); i++) {
      for (int naapuri : this.verkko[this.topojarjestys.get(i)]) {
        this.polkuja[naapuri] += this.polkuja[this.topojarjestys.get(i)];
      }
    }
    System.out.println(String.format("%s", Arrays.toString(this.polkuja)));
    return this.polkuja[n];
  }

}