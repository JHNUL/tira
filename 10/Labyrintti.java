import java.util.ArrayDeque;
import java.util.ArrayList;

public class Labyrintti {
  public static void main(String[] args) {
    Labyrintti l = new Labyrintti();
    char[][] t = { 
      { '#', '#', '#', '#', '#', '#', '#' },
      { '#', 'x', '#', '.', 'y', '.', '#' },
      { '#', '.', '#', '.', '#', '.', '#' },
      { '#', '.', '.', '.', '.', '.', '#' },
      { '#', '#', '#', '#', '#', '#', '#' }
    };
    System.out.println(l.etsi(t)); // AAOOYYO
  }

  String[][] etaisyydet;
  int[][] vierailtu;

  String etsi(char[][] laby) {
    int ix = 0, iy = 0;
    int fx = 0, fy = 0;
    vierailtu = new int[laby.length][laby[0].length];
    etaisyydet = new String[laby.length][laby[0].length];
    for (int y = 0; y < laby.length; y++) {
      for (int x = 0; x < laby[0].length; x++) {
        if (laby[y][x] == 'x') {
          ix = x;
          iy = y;
        }
        if (laby[y][x] == 'y') {
          fx = x;
          fy = y;
        }
      }
    }
    haku(laby, new Integer[]{iy, ix});
    return etaisyydet[fy][fx];
  }

  void haku(char[][] m, Integer[] solmu) {
    ArrayDeque<Integer[]> solmut = new ArrayDeque<>();
    solmut.add(solmu);
    vierailtu[solmu[0]][solmu[1]] = 1;
    etaisyydet[solmu[0]][solmu[1]] = "";
    while (!solmut.isEmpty()) {
      solmu = solmut.removeFirst();
      if (m[solmu[0]-1][solmu[1]] != '#' && vierailtu[solmu[0]-1][solmu[1]] == 0) {
        solmut.add(new Integer[]{solmu[0]-1, solmu[1]});
        vierailtu[solmu[0]-1][solmu[1]] = 1;
        etaisyydet[solmu[0]-1][solmu[1]] = etaisyydet[solmu[0]][solmu[1]] + "Y";
      }
      if (m[solmu[0]+1][solmu[1]] != '#' && vierailtu[solmu[0]+1][solmu[1]] == 0) {
        solmut.add(new Integer[]{solmu[0]+1, solmu[1]});
        vierailtu[solmu[0]+1][solmu[1]] = 1;
        etaisyydet[solmu[0]+1][solmu[1]] = etaisyydet[solmu[0]][solmu[1]] + "A";
      }
      if (m[solmu[0]][solmu[1]-1] != '#' && vierailtu[solmu[0]][solmu[1]-1] == 0) {
        solmut.add(new Integer[]{solmu[0], solmu[1]-1});
        vierailtu[solmu[0]][solmu[1]-1] = 1;
        etaisyydet[solmu[0]][solmu[1]-1] = etaisyydet[solmu[0]][solmu[1]] + "V";
      }
      if (m[solmu[0]][solmu[1]+1] != '#' && vierailtu[solmu[0]][solmu[1]+1] == 0) {
        solmut.add(new Integer[]{solmu[0], solmu[1]+1});
        vierailtu[solmu[0]][solmu[1]+1] = 1;
        etaisyydet[solmu[0]][solmu[1]+1] = etaisyydet[solmu[0]][solmu[1]] + "O";
      }
    }
  }

}
/*
 * jono.enqueue(alku)
 * vierailtu[alku] = true
 * etaisyys[alku] = 0
 *  while not jono.empty()
 *    solmu = jono.dequeue()
 *    for naapuri in verkko[solmu] 
 *      if vierailtu[naapuri]
 *        continue
 *      jono.enqueue(naapuri)
 *      vierailtu[naapuri] = true
 *      etaisyys[naapuri] = etaisyys[solmu]+1
 */