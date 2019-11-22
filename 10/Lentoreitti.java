import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class Lentoreitti {

  ArrayList<Integer>[] verkko;
  int[] vierailtu;
  int[] etaisyys;

  public static void main(String[] args) {
    Lentoreitti l = new Lentoreitti(6);
    l.lisaaYhteys(1, 2);
    l.lisaaYhteys(2, 3);
    l.lisaaYhteys(3, 4);
    l.lisaaYhteys(3, 5);
    l.lisaaYhteys(1, 3);
    l.lisaaYhteys(5, 6);
    System.out.println(l.laske(1, 6)); // 2
  }

  public Lentoreitti(int n) {
    this.vierailtu = new int[n + 1];
    this.etaisyys = new int[n + 1];
    this.verkko = new ArrayList[n + 1];
    for (int i = 1; i <= n; i++) {
      this.verkko[i] = new ArrayList<>();
    }
  }

  void lisaaYhteys(int a, int b) {
    this.verkko[a].add(b);
    this.verkko[b].add(a);
  }

  int laske(int solmu, int kohdesolmu) {
    ArrayDeque<Integer> j = new ArrayDeque<>();
    j.addLast(solmu);
    this.vierailtu[solmu] = 1;
    this.etaisyys[solmu] = 0;
    while (!j.isEmpty()) {
      solmu = j.removeFirst();
      for (Integer naapuri : this.verkko[solmu]) {
        if (this.vierailtu[naapuri] == 0) {
          j.addLast(naapuri);
          this.vierailtu[naapuri] = 1;
          this.etaisyys[naapuri] = this.etaisyys[solmu] + 1;
        }
      }
    }
    return this.etaisyys[kohdesolmu]-1;
  }
}
/*
 * jono.enqueue(alku) vierailtu[alku] = true etaisyys[alku] = 0 while not
 * jono.empty() solmu = jono.dequeue() for naapuri in verkko[solmu] if
 * vierailtu[naapuri] continue jono.enqueue(naapuri) vierailtu[naapuri] = true
 * etaisyys[naapuri] = etaisyys[solmu]+1
 */