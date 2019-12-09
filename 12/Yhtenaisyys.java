import java.util.ArrayList;
import java.util.Collections;

public class Yhtenaisyys {

  private ArrayList<Integer>[] vieruslista;
  private ArrayList<Integer>[] kaannetyt;
  private boolean[] vieraillut;
  private ArrayList<Integer> jarjestys;
  private int n;

  public static void main(String[] args) {
    // Yhtenaisyys y = new Yhtenaisyys(6);
    // y.lisaaKaari(1, 3);
    // y.lisaaKaari(3, 2);
    // y.lisaaKaari(3, 4);
    // y.lisaaKaari(3, 5);
    // y.lisaaKaari(2, 1);
    // y.lisaaKaari(4, 2);
    // y.lisaaKaari(4, 6);
    // y.lisaaKaari(6, 5);
    // y.lisaaKaari(5, 6);
    // y.tutki();
    // a.lisaaKaari(1, 2);
    // a.lisaaKaari(2, 3);
    // a.lisaaKaari(3, 1);
    // System.out.println(a.tutki()); // true
    Yhtenaisyys b = new Yhtenaisyys(3);
    b.lisaaKaari(1, 2);
    b.lisaaKaari(2, 3);
    b.lisaaKaari(1, 3);
    System.out.println(b.tutki()); // false
  }

  public Yhtenaisyys(int n) {
    this.n = n;
    this.vieruslista = new ArrayList[n + 1];
    this.kaannetyt = new ArrayList[n + 1];
    this.jarjestys = new ArrayList<>();
    this.vieraillut = new boolean[n + 1];
    for (int i = 1; i <= n; i++) {
      this.vieruslista[i] = new ArrayList<>();
      this.kaannetyt[i] = new ArrayList<>();
    }
  }

  void lisaaKaari(int a, int b) {
    this.vieruslista[a].add(b);
    this.kaannetyt[b].add(a);
  }

  boolean tutki() {
    for (int i = 1; i <= n; i++) {
      if (this.vieraillut[i] == false) {
        syvyysHaku(i, this.vieruslista);
      }
    }
    /* Nollataan vierailulista toista DFS varten */
    for (int i = 1; i <= n; i++) {
      this.vieraillut[i] = false;
    }
    Collections.reverse(this.jarjestys);
    int hakuja = 0;
    for (int i = 0; i < this.jarjestys.size(); i++) {
      if (this.vieraillut[this.jarjestys.get(i)] == false) {
        hakuja++;
        if (hakuja > 1) return false;
        syvyysHaku(this.jarjestys.get(i), this.kaannetyt);
      }
    }
    return true;
  }

  void syvyysHaku(int x, ArrayList<Integer>[] lista) {
    if (this.vieraillut[x] == true) {
      return;
    }
    this.vieraillut[x] = true;
    for (int naapuri : lista[x]) {
      syvyysHaku(naapuri, lista);
    }
    jarjestys.add(x);
  }

  void tulostaVieruslista(ArrayList<Integer>[] lista) {
    for (int i = 1; i <= this.n; i++) {
      System.out.print(String.format("solmu %s:", i));
      for (Integer x : lista[i]) {
        System.out.print(String.format(" [%s]", x));
      }
      System.out.println(String.format("%s", ""));
    }
  }

}