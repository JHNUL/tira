import java.util.Arrays;

public class Recursion {

  public static void main(String[] args) {
    Recursion v = new Recursion();
    System.out.println(v.laske(new int[] { 1, 3, 55, 57 }, 1)); // 2
    System.out.println(v.laske(new int[] { 1, 3, 5, 7 }, 2)); // 2
    System.out.println(v.laske(new int[] { 1, 3, 5, 7 }, 3)); // 1
  }

  public int laske(int[] t, int k) {
    return -1;
  }

}

// Tien varrella on n taloa, joista jokaisella on tietty sijainti (kohta
// x-akselilla).
// Tehtäväsi on rakentaa tielle bussipysäkkejä niin, että jokaisen talon
// etäisyys lähimpään
// pysäkkiin on enintään k. Mikä on pienin mahdollinen määrä pysäkkejä?

// Tee luokka Pysakit , jossa on seuraavat metodit:

// int laske(int[] t, int k): palauttaa pienimmän pysäkkien määrän
// Rajat:

// 1 ≤ n ≤ 10^6
// jokainen talon sijainti on välillä 1...10^9
// 1 ≤ k ≤ 10^9
// Seuraava koodi esittelee luokan käyttämistä:

// Pysakit p = new Pysakit();
// System.out.println(p.laske(new int[] {3,7,1,5}, 1)); // 2
//
// System.out.println(p.laske(new int[] {3,7,1,5}, 2)); // 2
//
// System.out.println(p.laske(new int[] {3,7,1,5}, 3)); // 1
