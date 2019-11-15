import java.util.TreeSet;

public class PieninEtaisyys {

  private TreeSet<Integer> rakenne;
  private int min;

  public static void main(String[] args) {
    PieninEtaisyys p = new PieninEtaisyys();
    p.lisaa(3);
    p.lisaa(8);
    System.out.println(p.laske()); // 5
  }

  public PieninEtaisyys() {
    this.rakenne = new TreeSet<>();
    this.min = 1000000000;
  }

  public int laske() {
    return this.min;
  }

  public void lisaa(int x) {
    this.rakenne.add(x);
    int erotusPienempaan = this.rakenne.lower(x) != null ? x - this.rakenne.lower(x) : 1000000000;
    int erotusSuurempaan = this.rakenne.higher(x) != null ? this.rakenne.higher(x) - x : 1000000000;
    this.min = Math.min(erotusPienempaan, erotusSuurempaan);
  }
}

/*
 * Tehtäväsi on toteuttaa luokka, joka pitää yllä lukujen kokoelmaa. Kokoelmaan
 * voi lisätä uuden luvun sekä kysyä, mikä on pienin etäisyys kahden eri luvun
 * välillä.
 * 
 * Tee luokka PieninEtaisyys, jossa on seuraavat metodit:
 * 
 * void lisaa(int x) int laske(): palauttaa pienimmän etäisyyden kahden eri
 * luvun välillä Rajat:
 * 
 * 1 ≤ x ≤ 10^9 jokaisessa testissä metodeita kutsutaan yhteensä enintään 10^6
 * kertaa metodia laske ei kutsuta, ennen kuin kokoelmassa on kaksi eri lukua
 * Seuraava koodi esittelee luokan käyttämistä:
 * 
 * PieninEtaisyys p = new PieninEtaisyys(); p.lisaa(3); p.lisaa(8);
 * System.out.println(p.laske()); // 5 p.lisaa(20);
 * System.out.println(p.laske()); // 5 p.lisaa(9);
 * System.out.println(p.laske()); // 1
 */