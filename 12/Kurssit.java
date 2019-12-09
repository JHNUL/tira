import java.util.ArrayList;
import java.util.HashMap;

public class Kurssit {

  private HashMap<String, Integer> rakenne;
  private HashMap<Integer, String> nimet;
  private ArrayList<Integer>[] verkko;
  private int[] tilat;
  private int solmuja;

  public static void main(String[] args) {
    Kurssit k = new Kurssit();
    k.lisaaKurssi("OHPE");
    k.lisaaKurssi("OHJA");
    k.lisaaKurssi("TIRA");
    k.lisaaKurssi("OHTE");
    k.lisaaVaatimus("OHPE", "OHJA");
    k.lisaaVaatimus("OHJA", "OHTE");
    k.lisaaVaatimus("OHJA", "TIRA");
    // k.tulosta();
    // k.tulostaVerkko();
    System.out.println(k.muodosta()); // [OHPE, OHJA, OHTE, TIRA]
  }

  public Kurssit() {
    this.rakenne = new HashMap<>();
    this.nimet = new HashMap<>();    
    this.verkko = new ArrayList[101];
    this.solmuja = 0;
    this.tilat = new int[101]; /* Tilat 0: valkoinen, 1: harmaa, 2: musta */
    for (int i = 1; i < this.verkko.length; i++) {
      this.verkko[i] = new ArrayList<>();
    }
  }

  ArrayList<String> muodosta() {
    ArrayList<String> kurssit = new ArrayList<>();
    try {
      for (int i = 1; i <= this.solmuja; i++) {
        syvyysHaku(i, kurssit);
      }
    } catch (IllegalStateException est) {
      return null;
    }
    return kurssit;
  }

  /* 1) uusi solmu, väri valkoinen */
  /* 2) läpikäytävän solmun väri muuttuu harmaaksi */
  /* 3) kun kaikki edelliset käyty läpi, solmu muuttuu mustaksi */
  void syvyysHaku(int solmu, ArrayList<String> l) {
    if (this.tilat[solmu] == 2) return;
    if (this.tilat[solmu] == 1) throw new IllegalStateException();
    if (this.tilat[solmu] == 0) {
      this.tilat[solmu] = 1;
      for (int edellinen : this.verkko[solmu]) {
        syvyysHaku(edellinen, l);
      }
      this.tilat[solmu] = 2;
      l.add(this.nimet.get(solmu));
    }
  }

  void lisaaKurssi(String kurssi) {
    this.solmuja++;
    /* Mitään ei poisteta */
    int avain = this.rakenne.size() + 1;
    this.rakenne.put(kurssi, avain);
    this.nimet.put(avain, kurssi);
  }

  void lisaaVaatimus(String esitieto, String kurssi) {
    this.verkko[this.rakenne.get(kurssi)].add(this.rakenne.get(esitieto));
  }

  void tulosta() {
    System.out.println(String.format("%s", this.rakenne.toString()));
  }

  void tulostaVerkko() {
    for (int i = 1; i < this.verkko.length; i++) {
      System.out.println(String.format("[%s] %s", i, this.verkko[i]));
    }
  }
}