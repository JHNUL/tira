import java.util.ArrayDeque;
import java.util.ArrayList;

public class Labyrintti {
  public static void main(String[] args) {
    Labyrintti l = new Labyrintti();
    char[][] t = {
      {'#','#','#','#','#','#','#'},
      {'#','x','#','.','y','b','#'},
      {'#','.','#','A','#','#','#'},
      {'#','b','B','.','B','a','#'},
      {'#','#','#','#','#','#','#'}
    };
    System.out.println(l.etsi(t)); // AAOOOOVVYYO
  }

  String[][] polut;
  int[][] vierailtu;
  boolean aKey = false;
  boolean bKey = false;

  String etsi(char[][] laby) {
    int ix = 0, iy = 0;
    int fx = 0, fy = 0;
    vierailtu = new int[laby.length][laby[0].length][3];
    polut = new String[laby.length][laby[0].length];
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
    return polut[fy][fx];
  }

  void haku(char[][] m, Integer[] solmu) {
    ArrayDeque<Integer[]> solmut = new ArrayDeque<>();
    solmut.add(solmu);
    vierailtu[solmu[0]][solmu[1]] = 1;
    polut[solmu[0]][solmu[1]] = "";
    while (!solmut.isEmpty()) {
      solmu = solmut.removeFirst();
      if (vierailtu[solmu[0]-1][solmu[1]] == 0) {
        if (m[solmu[0]-1][solmu[1]] == 'A' && aKey || m[solmu[0]-1][solmu[1]] == 'B' && bKey) {
          solmut.add(new Integer[]{solmu[0]-1, solmu[1]});
          vierailtu[solmu[0]-1][solmu[1]] = 1;
          polut[solmu[0]-1][solmu[1]] = polut[solmu[0]][solmu[1]] + "Y";
        } else if (m[solmu[0]-1][solmu[1]] != '#' && m[solmu[0]-1][solmu[1]] != 'A' && m[solmu[0]-1][solmu[1]] != 'B') {
          if (m[solmu[0]-1][solmu[1]] == 'a') aKey = true; // poimi avain
          if (m[solmu[0]-1][solmu[1]] == 'b') bKey = true; // poimi avain
          solmut.add(new Integer[]{solmu[0]-1, solmu[1]});
          vierailtu[solmu[0]-1][solmu[1]] = 1;
          polut[solmu[0]-1][solmu[1]] = polut[solmu[0]][solmu[1]] + "Y";
        }
      }
      if (vierailtu[solmu[0]+1][solmu[1]] == 0) {        
        if (m[solmu[0]+1][solmu[1]] == 'A' && aKey || m[solmu[0]+1][solmu[1]] == 'B' && bKey) {
          solmut.add(new Integer[]{solmu[0]+1, solmu[1]});
          vierailtu[solmu[0]+1][solmu[1]] = 1;
          polut[solmu[0]+1][solmu[1]] = polut[solmu[0]][solmu[1]] + "A";
        } else if (m[solmu[0]+1][solmu[1]] != '#' && m[solmu[0]+1][solmu[1]] != 'A' && m[solmu[0]+1][solmu[1]] != 'B') {
          if (m[solmu[0]+1][solmu[1]] == 'a') aKey = true; // poimi avain
          if (m[solmu[0]+1][solmu[1]] == 'b') bKey = true; // poimi avain
          solmut.add(new Integer[]{solmu[0]+1, solmu[1]});
          vierailtu[solmu[0]+1][solmu[1]] = 1;
          polut[solmu[0]+1][solmu[1]] = polut[solmu[0]][solmu[1]] + "A";
        }
      }
      if (vierailtu[solmu[0]][solmu[1]+1] == 0) {        
        if (m[solmu[0]][solmu[1]+1] == 'A' && aKey || m[solmu[0]][solmu[1]+1] == 'B' && bKey) {
          solmut.add(new Integer[]{solmu[0], solmu[1]+1});
          vierailtu[solmu[0]][solmu[1]+1] = 1;
          polut[solmu[0]][solmu[1]+1] = polut[solmu[0]][solmu[1]] + "O";
        } else if (m[solmu[0]][solmu[1]+1] != '#' && m[solmu[0]][solmu[1]+1] != 'A' && m[solmu[0]][solmu[1]+1] != 'B') {
          if (m[solmu[0]][solmu[1]+1] == 'a') aKey = true; // poimi avain
          if (m[solmu[0]][solmu[1]+1] == 'b') bKey = true; // poimi avain
          solmut.add(new Integer[]{solmu[0], solmu[1]+1});
          vierailtu[solmu[0]][solmu[1]+1] = 1;
          polut[solmu[0]][solmu[1]+1] = polut[solmu[0]][solmu[1]] + "O";
        }
      }
      if (vierailtu[solmu[0]][solmu[1]-1] == 0) {        
        if (m[solmu[0]][solmu[1]-1] == 'A' && aKey || m[solmu[0]][solmu[1]-1] == 'B' && bKey) {
          solmut.add(new Integer[]{solmu[0], solmu[1]-1});
          vierailtu[solmu[0]][solmu[1]-1] = 1;
          polut[solmu[0]][solmu[1]-1] = polut[solmu[0]][solmu[1]] + "V";
        } else if (m[solmu[0]][solmu[1]-1] != '#' && m[solmu[0]][solmu[1]-1] != 'A' && m[solmu[0]][solmu[1]-1] != 'B') {
          if (m[solmu[0]][solmu[1]-1] == 'a') aKey = true; // poimi avain
          if (m[solmu[0]][solmu[1]-1] == 'b') bKey = true; // poimi avain
          solmut.add(new Integer[]{solmu[0], solmu[1]-1});
          vierailtu[solmu[0]][solmu[1]-1] = 1;
          polut[solmu[0]][solmu[1]-1] = polut[solmu[0]][solmu[1]] + "V";
        }
      }
      // System.out.println(String.format("sijainti %s:%s %s", solmu[0],solmu[1], m[solmu[0]][solmu[1]]));
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