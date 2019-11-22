import java.util.ArrayList;
import java.util.Arrays;

public class Saavutettavuus {
  private ArrayList<Integer>[] rakenne;
  private boolean[] vierailtu;
  private int c;

  public static void main(String[] args) {
    Saavutettavuus s = new Saavutettavuus(6);
    s.lisaaYhteys(1, 2);
    s.lisaaYhteys(2, 3);
    s.lisaaYhteys(1, 3);
    s.lisaaYhteys(3, 4);
    s.lisaaYhteys(5, 6);
    System.out.println(String.format("%s", s.laske(1)));
  }

  public Saavutettavuus(int n) {
    this.vierailtu = new boolean[n + 1];
    this.c = 0;
    this.rakenne = new ArrayList[n + 1];
    for (int i = 1; i < rakenne.length; i++) {
      this.rakenne[i] = new ArrayList<>();
    }
  }

  void lisaaYhteys(int a, int b) {
    if (!this.rakenne[a].contains(b)) {
      this.rakenne[a].add(b);
    }
    if (!this.rakenne[b].contains(a)) {
      this.rakenne[b].add(a);
    }
  }

  int laske(int x) {
    haku(x);
    return this.c-1;
  }

  void haku(int x) {
    if (this.vierailtu[x] == true) {
      return;
    } else {
      this.vierailtu[x] = true;
      this.c++;
      for (Integer naapuri : this.rakenne[x]) {
        haku(naapuri);
      }
    }
  }
}
