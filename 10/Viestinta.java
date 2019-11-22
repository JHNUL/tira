import java.util.ArrayList;
import java.util.Arrays;

public class Viestinta {
  private ArrayList<Integer>[] rakenne;
  private ArrayList<Integer>[] komponentit;
  private boolean[] vierailtu;
  int kutsuja = 0;

  public static void main(String[] args) {
    int n = 19;
    Viestinta v = new Viestinta(n);
    v.lisaaYhteys(1, 10);
    v.lisaaYhteys(1, 14);
    v.lisaaYhteys(2, 9);
    v.lisaaYhteys(2, 13);
    v.lisaaYhteys(4, 5);
    v.lisaaYhteys(4, 11);
    v.lisaaYhteys(5, 11);
    v.lisaaYhteys(6, 10);
    v.lisaaYhteys(6, 14);
    v.lisaaYhteys(6, 15);
    v.lisaaYhteys(7, 12);
    v.lisaaYhteys(7, 19);
    v.lisaaYhteys(8, 19);
    v.lisaaYhteys(9, 18);
    v.lisaaYhteys(14, 15);
    v.lisaaYhteys(17, 19);
    System.out.println(v.tarkasta(1, 6));
  }

  public Viestinta(int n) {
    this.vierailtu = new boolean[n + 1];
    this.rakenne = new ArrayList[n + 1];
    this.komponentit = new ArrayList[n];
    for (int i = 1; i < rakenne.length; i++) {
      this.rakenne[i] = new ArrayList<>();
      this.komponentit[i-1] = new ArrayList<>();
    }
  }

  void lisaaYhteys(int a, int b) {
    this.rakenne[a].add(b);
    this.rakenne[b].add(a);
  }

  boolean tarkasta(int a, int b) {
    int indeksi = 0;
    if (this.kutsuja == 0) {
      for (int i = 1; i < rakenne.length; i++) {
        if (!this.vierailtu[i]) {
          haku(i, this.komponentit[indeksi]);
          indeksi++;
        }
      }
    }
    this.kutsuja++;
    int k = 0;
    while(this.komponentit[k].size() > 0) {
      if (this.komponentit[k].contains(a) && this.komponentit[k].contains(b)) {
        return true;
      }
      k++;
    }
    return false;
  }

  void haku(int x, ArrayList<Integer> komponentti) {
    if (this.vierailtu[x] == true) {
      return;
    } else {
      this.vierailtu[x] = true;
      komponentti.add(x);
      for (Integer naapuri : this.rakenne[x]) {
        haku(naapuri, komponentti);
      }
    }
  }
}
