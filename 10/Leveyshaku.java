import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class Leveyshaku {

  public static void main(String[] args) {
    Leveyshaku l = new Leveyshaku();
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
    int[] etaisyys = new int[n + 1];
    l.haku(1, verkko, vierailtu, etaisyys);
    System.out.println(String.format("%s", etaisyys[5]));
  }

  void haku(int solmu, ArrayList<Integer>[] verkko, int[] vierailtu, int[] etaisyys) {
    ArrayDeque<Integer> j = new ArrayDeque<>();
    j.addLast(solmu);
    vierailtu[solmu] = 1;
    etaisyys[solmu] = 0;
    while(!j.isEmpty()) {
      solmu = j.removeFirst();
      for (Integer naapuri : verkko[solmu]) {
        if (vierailtu[naapuri] == 0) {
          j.addLast(naapuri);
          vierailtu[naapuri] = 1;
          etaisyys[naapuri] = etaisyys[solmu]+1;
        }
      }
    }
  }
}
/* 
jono.enqueue(alku)
vierailtu[alku] = true
etaisyys[alku] = 0
while not jono.empty()
  solmu = jono.dequeue()
  for naapuri in verkko[solmu]
    if vierailtu[naapuri]
      continue
    jono.enqueue(naapuri)
    vierailtu[naapuri] = true
    etaisyys[naapuri] = etaisyys[solmu]+1
*/