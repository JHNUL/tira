import java.util.Arrays;

public class Lists {
  public static void main(String[] args) {
    Lists l = new Lists();
    l.lisaaAlkuun(150);
    l.lisaaAlkuun(11);
    l.lisaaLoppuun(12);
    l.lisaaLoppuun(14);
    System.out.println(String.format("%s", l.haeAlkio(0)));
    System.out.println(String.format("%s", l.haeAlkio(1)));
    System.out.println(String.format("%s", l.haeAlkio(2)));
    System.out.println(String.format("%s", l.haeAlkio(3)));
    l.lisaaLoppuun(3);
    l.lisaaLoppuun(5);
    System.out.println(l.haeAlkio(1)); // 5
    l.lisaaAlkuun(2);
    System.out.println(l.haeAlkio(1)); // 3
  }

  private int[] rakenne;
  private int alkuIndeksi;
  private int loppuIndeksi;

  public Lists() {
    this.rakenne = new int[4];
    this.alkuIndeksi = -1;
    this.loppuIndeksi = this.rakenne.length;
  }

  void lisaaAlkuun(int x) {
    this.loppuIndeksi--;
    this.rakenne[this.loppuIndeksi] = x;
  }

  void lisaaLoppuun(int x) {
    this.alkuIndeksi++;
    this.rakenne[this.alkuIndeksi] = x;
  }

  int haeAlkio(int k) {
    if (this.loppuIndeksi + k <= this.rakenne.length - 1) {
      return this.rakenne[this.loppuIndeksi + k];
    }
    return this.rakenne[(this.loppuIndeksi + k) - (this.rakenne.length)];
  }

}

/*
 * Tehtäväsi on toteuttaa listarakenne, jossa pystyy lisäämään tehokkaasti
 * alkion listan alkuun ja loppuun sekä hakemaan annetussa kohdassa olevan
 * alkion.
 * 
 * Lista on alussa tyhjä. Kun siihen lisätään alkioita, ne numeroidaan 0, 1, 2,
 * jne. listan alusta alkaen.
 * 
 * Tee luokka Lista, jossa on seuraavat metodit:
 * 
 * void lisaaAlkuun(int x): lisää alkion x listan alkuun void lisaaLoppuun(int
 * x): lisää alkion x listan loppuun int haeAlkio(int k): palauttaa kohdassa k
 * olevan alkion Rajat:
 * 
 * 1 ≤ x ≤ 109 k osoittaa johonkin listan kohtaan jokaisessa testissä metodeita
 * kutsutaan yhteensä enintään 106 kertaa Seuraava koodi esittelee luokan
 * käyttämistä:
 * 
 * Lista l = new Lista(); l.lisaaLoppuun(3); l.lisaaLoppuun(5);
 * System.out.println(l.haeAlkio(1)); // 5 l.lisaaAlkuun(2);
 * System.out.println(l.haeAlkio(1)); // 3
 */