import java.util.TreeMap;

public class SamatLuvut {

  private TreeMap<Integer, Integer> rakenne;

  public static void main(String[] args) {
    SamatLuvut s = new SamatLuvut();
    s.lisaa(2);
    s.lisaa(7);
    s.lisaa(2);
    System.out.println(s.pienin()); // 2
    s.poista(2);
    System.out.println(s.pienin()); // 2
    s.poista(2);
    System.out.println(s.pienin()); // 7
  }

  public SamatLuvut() {
    this.rakenne = new TreeMap<>();
  }

  public void lisaa(int x) {
    if (this.rakenne.containsKey(x)) {
      this.rakenne.put(x, this.rakenne.get(x) + 1);
    } else {
      this.rakenne.put(x, 1);
    }
  }

  public void poista(int x) {
    if (!this.rakenne.containsKey(x)) {
      return;
    }
    int arvo = this.rakenne.get(x);
    if (arvo == 1) {
      this.rakenne.remove(x);
    } else {
      this.rakenne.put(x, this.rakenne.get(x) - 1);
    }
  }

  public int pienin() {
    return this.rakenne.firstKey();
  }
}

// Tehtäväsi on toteuttaa luokka, joka pitää yllä lukujen kokoelmaa. Kokoelmaan
// voi lisätä luvun,
// sieltä voi poistaa luvun sekä kysyä, mikä on pienin luku. Huomaa, että
// kokoelmassa voi olla useita kertoja sama luku.

// Tee luokka SamatLuvut, jossa on seuraavat metodit:

// void lisaa(int x): lisää kokoelmaan luvun x
// void poista(int x): poistaa kokoelmasta luvun x (jos kokoelmassa ei ole lukua
// x, metodi ei tee mitään)
// int pienin(): palauttaa pienimmän luvun
// Rajat:

// 1 ≤ x ≤ 10^9
// jokaisessa testissä metodeita kutsutaan yhteensä enintään 10^6 kertaa
// metodia pienin ei kutsuta, jos kokoelma on tyhjä
// Seuraava koodi esittelee luokan käyttämistä:

// SamatLuvut s = new SamatLuvut();
// s.lisaa(2);
// s.lisaa(7);
// s.lisaa(2);
// System.out.println(s.pienin()); // 2
// s.poista(2);
// System.out.println(s.pienin()); // 2
// s.poista(2);
// System.out.println(s.pienin()); // 7
