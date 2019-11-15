import java.util.HashMap;

public class HatutJaPallot {

  private int eniten;
  private HashMap<Integer, Integer> rakenne;

  public static void main(String[] args) {
    HatutJaPallot h = new HatutJaPallot();
    h.lisaaPallo(2);
    h.lisaaPallo(3);
    h.lisaaPallo(5);
    System.out.println(h.monessakoYksi()); // 3
    System.out.println(h.suurinMaara()); // 1
    h.lisaaPallo(3);
    System.out.println(h.monessakoYksi()); // 3
    System.out.println(h.suurinMaara()); // 2
  }

  public HatutJaPallot() {
    this.eniten = 0;
    this.rakenne = new HashMap<>();
  }

  void lisaaPallo(int x) {
    int arvo = this.rakenne.get(x) == null ? 0 : this.rakenne.get(x);
    int uusiArvo = arvo + 1;
    if (uusiArvo > this.eniten)
      this.eniten = uusiArvo;
    this.rakenne.put(x, uusiArvo);
  }

  int monessakoYksi() {
    return this.rakenne.size();
  }

  int suurinMaara() {
    return this.eniten;
  }
}

/*
 * Sinulla on 109 hattua, jotka on numeroitu 1, 2, ..., 10^9. Aluksi jokainen
 * hattu on tyhjä, mutta sitten niihin aletaan lisätä palloja.
 * 
 * Tee luokka HatutJaPallot, jossa on seuraavat metodit:
 * 
 * void lisaaPallo(int x): lisää pallon hattuun x int monessakoYksi():
 * palauttaa, monessako hatussa on ainakin yksi pallo int suurinMaara():
 * palauttaa, mikä on suurin pallojen määrä yhdessä hatussa Rajat:
 * 
 * 1 ≤ x ≤ 10^9 jokaisessa testissä metodeita kutsutaan yhteensä enintään 10^6
 * kertaa Seuraava koodi esittelee luokan käyttämistä:
 * 
 * HatutJaPallot h = new HatutJaPallot(); h.lisaaPallo(2); h.lisaaPallo(3);
 * h.lisaaPallo(5); System.out.println(h.monessakoYksi()); // 3
 * System.out.println(h.suurinMaara()); // 1 h.lisaaPallo(3);
 * System.out.println(h.monessakoYksi()); // 3
 * System.out.println(h.suurinMaara()); // 2
 */