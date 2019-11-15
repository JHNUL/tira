import java.util.HashMap;

public class Summahaku {
  public static void main(String[] args) {
    Summahaku s = new Summahaku();
    System.out.println(s.laske(new int[] { 1, -1, 1, -1 }, 0)); // 4
  }

  int laske(int t[], int x) {

    HashMap<Integer, Integer> prevSum = new HashMap<>();

    int res = 0;

    int currsum = 0;

    for (int i = 0; i < t.length; i++) {

      currsum += t[i];

      if (currsum == x) {
        res++;
      }

      if (prevSum.containsKey(currsum - x))
        res += prevSum.get(currsum - x);

      Integer count = prevSum.get(currsum);
      if (count == null)
        prevSum.put(currsum, 1);
      else
        prevSum.put(currsum, count + 1);
    }

    return res;
  }
}

/*
 * Annettuna on taulukko, jossa on n kokonaislukua. Tehtäväsi on laskea,
 * monessako yhtenäisessä alitaulukossa lukujen summa on x.
 * 
 * Tee luokka Summahaku, jossa on seuraavat metodit:
 * 
 * long laske(int[] t, int x): palauttaa alitaulukoiden määrän Rajat:
 * 
 * 1 ≤ n ≤ 106 1 ≤ x ≤ 109 jokainen taulukon alkio on välillä –100...100
 * Seuraava koodi esittelee luokan käyttämistä:
 * 
 * SamaSumma s = new Summahaku(); System.out.println(s.laske(new int[]
 * {1,3,2,4}, 4)); // 2 System.out.println(s.laske(new int[] {0,0,0,0}, 0)); //
 * 10 System.out.println(s.laske(new int[] {1,-1,1,-1}, 0)); // 4
 */