import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class Maksimivirtaus {

  class Kaari {
    int alku, loppu, paino;
    boolean kuljettu, polulla;

    public Kaari(int a, int b, int p) {
      this.alku = a;
      this.loppu = b;
      this.paino = p;
      this.kuljettu = false;
      this.polulla = false;
    }

    public void setKuljettu(boolean kuljettu) {
      this.kuljettu = kuljettu;
    }

    public void setPolulla(boolean polulla) {
      this.polulla = polulla;
    }

    public void lisaaPainoon(long p) {
      if ((this.paino + p) < 0) throw new IllegalArgumentException();
      this.paino += p;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == this)
        return true;
      if (obj.getClass() != this.getClass())
        return false;
      Kaari olio = (Kaari) obj;
      return olio.alku == this.alku && olio.loppu == this.loppu;
    }

    @Override
    public String toString() {
      return String.format("(%s)->(%s) [%s]", this.alku, this.loppu, this.paino);
    }
  }

  public static void main(String[] args) {
    int n = 10;
    Maksimivirtaus m = new Maksimivirtaus(n);
    int k = 1000;
    Random r = new Random(1337);
    for (int i = 1; i <= k; i++) {
      int a = r.nextInt(n) + 1;
      int b = r.nextInt(n) + 1;
      int p = r.nextInt(1000000) + 1;
      m.lisaaKaari(a, b, p);
    }
    // Maksimivirtaus m = new Maksimivirtaus(2);
    // m.lisaaKaari(1,2,1);
    // m.lisaaKaari(1,2,1);
    System.out.println(m.laske()); // 32734596
    m.tulostaVerkko();
  }

  int n;
  long maksimivirtaus = 0;
  ArrayList<Kaari> verkko[];

  /* solmujen määrä annetaan konstuktorissa */
  public Maksimivirtaus(int n) {
    this.n = n;
    this.verkko = new ArrayList[n + 1];
    for (int i = 1; i <= n; i++) {
      this.verkko[i] = new ArrayList<>();
    }
  }

  /*
   * lisää verkkoon solmusta a solmuun b kaaren, jonka paino on p ja vastakkaisen
   * kaaren, jonka paino on 0
   */
  void lisaaKaari(int a, int b, int p) {
    if (a==b) return;
    Kaari uusiKaari = new Kaari(a, b, p);
    /* Lisäillään painot jos yhdistävä kaari jo löytyy */
    if (!this.verkko[a].contains(uusiKaari)) {
      this.verkko[a].add(uusiKaari);
      this.verkko[b].add(new Kaari(b, a, 0));
    } else {
      for (Kaari kaari : this.verkko[a]) {
        if (kaari.equals(uusiKaari)) {
          kaari.lisaaPainoon(p);
          break;
        }
      }
    }
  }

  /* ilmoittaa maksimivirtauksen suuruuden */
  int laske() {
    boolean jatka = haku();
    while (jatka) {
      jatka = haku();
    }
    return (int) this.maksimivirtaus;
  }

  /* Vähentää kaarelta annetun painon ja lisää sen käänteiskaarelle */
  void vahennaPaino(Kaari k, long p) {
    k.lisaaPainoon(p * (-1));
    Kaari kk = this.verkko[k.loppu].stream().filter(kaari -> kaari.loppu == k.alku).findFirst().orElse(null);
    kk.lisaaPainoon(p);
  }

  /* Nollaa kaaret ei-vierailluiksi, jos ne eivät olleet polulla */
  void nollaaKaaret() {
    for (int i = 1; i <= n; i++) {
      this.verkko[i].forEach(k -> {
        if (!k.polulla)
          k.setKuljettu(false);
      });
    }
  }

  /* Hakee jonkin polun alkusolmusta loppuun ja merkkaa kaaret vierailluiksi */
  boolean haku() {
    ArrayDeque<Integer> jono = new ArrayDeque<>();
    boolean vieraillut[] = new boolean[this.n + 1];
    Kaari kuljetut[] = new Kaari[this.n + 1];
    for (int i = 1; i <= this.n; i++) {
      vieraillut[i] = false;
      kuljetut[i] = null;
    }
    jono.add(1);
    vieraillut[1] = true;
    boolean muutos = false;
    while (!jono.isEmpty()) {
      int solmu = jono.poll();
      if (solmu == this.n)
        break;
      for (Kaari kaari : this.verkko[solmu]) {
        if (vieraillut[kaari.loppu] == false && kaari.kuljettu == false && kaari.paino > 0) {
          muutos = true;
          jono.add(kaari.loppu);
          kuljetut[kaari.loppu] = kaari;
          vieraillut[kaari.loppu] = true;
          kaari.setKuljettu(true);
        }
      }
    }
    if (muutos && vieraillut[this.n])
      this.maksimivirtaus += kasitteleKaaret(kuljetut);
    return muutos;
  }

  /*
   * Käy loppusolmusta alkaen kaaret läpi ja poista minimipaino, palauta
   * minimipaino
   */
  long kasitteleKaaret(Kaari[] kaaret) {

    /* Kerätään polulla olevat kaaret */
    ArrayList<Kaari> polkukaaret = new ArrayList<>();

    int x = this.n;
    long minimipaino = 999999999;

    while (x != 1) {
      Kaari polkukaari = kaaret[x];
      polkukaari.setPolulla(true); // aseta polkukaari
      polkukaaret.add(polkukaari);
      if (polkukaari.paino < minimipaino)
        minimipaino = polkukaari.paino;
      x = polkukaari.alku;
    }
    for (Kaari kaari : polkukaaret) {
      vahennaPaino(kaari, minimipaino);
    }
    /* Kaikki kaaret ei-polkukaaret */
    nollaaKaaret();
    return minimipaino;
  }

  /* Tulostelee verkon */
  void tulostaVerkko() {
    for (int i = 1; i <= this.n; i++) {
      System.out.print(String.format("%s [ ", i));
      for (Kaari x : this.verkko[i]) {
        System.out.print(String.format("%s, ", x.toString()));
      }
      System.out.print(String.format("]"));
      System.out.println("");
    }
  }

}
