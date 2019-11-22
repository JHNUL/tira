import java.util.ArrayList;
import java.util.Arrays;

public class Yhtenaisyys {
  private ArrayList<Integer>[] rakenne;
  private boolean[] vierailtu;
  private int c;

  public static void main(String[] args) {
    Yhtenaisyys k = new Yhtenaisyys(6);
    k.lisaaYhteys(1, 2);
    k.lisaaYhteys(2, 3);
    k.lisaaYhteys(1, 3);
    k.lisaaYhteys(3, 4);
    k.lisaaYhteys(5, 6);
    System.out.println(k.laske()); // 2
  }

  public Yhtenaisyys(int n) {
    this.vierailtu = new boolean[n + 1];
    this.c = 0;
    this.rakenne = new ArrayList[n + 1];
    for (int i = 1; i < rakenne.length; i++) {
      this.rakenne[i] = new ArrayList<>();
    }
  }

  void lisaaYhteys(int a, int b) {
    this.rakenne[a].add(b);
    this.rakenne[b].add(a);
  }

  int laske() {
    for (int i = 1; i < rakenne.length; i++) {
      if (!this.vierailtu[i]) {
        haku(i);
        this.c++;
      }
    }
    return this.c;
  }

  void haku(int x) {
    if (this.vierailtu[x] == true) {
      return;
    } else {
      this.vierailtu[x] = true;
      for (Integer naapuri : this.rakenne[x]) {
        haku(naapuri);
      }
    }
  }
}
