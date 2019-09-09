import java.util.Arrays;
import java.util.Random;

public class Kierrokset {
  public static void main(String[] args) {
    Kierrokset kr = new Kierrokset();
    System.out.println(kr.laske(new int[] { 5, 3, 14, 6, 9, 10, 11, 15, 8, 4, 7, 12, 13, 1, 2 })); // 6

  }

  public int laske(int[] t) {

    int[] indeksiTaulukko = new int[t.length + 1];
    for (int i = 0; i < t.length; i++) {
      indeksiTaulukko[t[i]] = i;
    }
    int pudotuksia = 0; // jokainen pudotus merkitsee uutta hakua
    for (int i = 0; i < indeksiTaulukko.length - 1; i++) {
      if (indeksiTaulukko[i] > indeksiTaulukko[i + 1]) {
        pudotuksia++;
      }
    }
    return pudotuksia + 1;
  }
}
