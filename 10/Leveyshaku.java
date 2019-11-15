import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Leveyshaku {
  public static void main(String[] args) {
    Leveyshaku syvyyshaku = new Leveyshaku();
    int n = 5;
    ArrayList<Integer>[] verkko = new ArrayList[n + 1];
    for (int i = 1; i <= n; i++) {
      verkko[i] = new ArrayList<>();
    }
    verkko[1].addAll(Arrays.asList(2, 3));
    verkko[2].addAll(Arrays.asList(1, 4, 5));
    verkko[3].addAll(Arrays.asList(1));
    verkko[4].addAll(Arrays.asList(2, 5));
    verkko[5].addAll(Arrays.asList(2, 4));
    int[] vierailtu = new int[n + 1];
    syvyyshaku.haku(1, verkko, vierailtu);
  }

  void haku(int solmu, ArrayList<Integer>[] verkko, int[] vierailtu) {
    if (vierailtu[solmu] == 1) {
      return;
    }
    vierailtu[solmu] = 1;
    for (int i : verkko[solmu]) {
      haku(i, verkko, vierailtu);
    }
  }
}