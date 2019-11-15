import java.util.ArrayList;
import java.util.HashMap;

public class Sudoku {

  private int ratkaisuja = 0;

  public static void main(String[] args) {
    int n = 2;
    Sudoku s = new Sudoku();
    int tulos = s.laske(n);
    System.out.println(String.format("%s", tulos));
    /*
     * int[][] tt = new int[][]{ {0,1,0}, {0,0,0}, {3,0,0} }; s.tulostaTaulukko(tt);
     * System.out.println(String.format("%s", s.saaLaittaa(0, 2, 3, tt)));
     */
  }

  int laske(int n) {
    laskeOikeasti(n, new int[n][n], 0, 0, new HashMap<Integer, Boolean>());
    return this.ratkaisuja;
  }

  void laskeOikeasti(int n, int[][] t, int y, int x, HashMap<Integer, Boolean> joukko) {

    if (y == n-1 && x == n-1) {
      tulostaTaulukko(t);
      this.ratkaisuja++;
    } else {
      for (int m = 1; m <= n; m++) {
        if (saaLaittaa(x, y, m, t) && !joukko.containsKey(m)) {
          joukko.put(m, true);
          t[y][x] = m;
          tulostaTaulukko(t);
          System.out.println(String.format("y %s, x %s, m %s", y, x, m));
          x = x < n - 1 ? x + 1 : 0;
          if (x == 0) y++;
          laskeOikeasti(n, t, y, x, joukko);
          joukko.remove(m);
        }
      }
    }

  }

  boolean saaLaittaa(int x, int y, int i, int[][] t) {
    // jos vasemmalla on jo luku
    for (int sarake = 0; sarake < x; sarake++) {
      if (t[y][sarake] == i) {
        return false;
      }
    }
    // jos yläpuolella on jo luku
    for (int rivi = 0; rivi < y; rivi++) {
      if (t[rivi][x] == i) {
        return false;
      }
    }
    return true;
  }

  void tulostaTaulukko(int[][] t) {
    System.out.println(String.format("%s", "----------------"));
    for (int i = 0; i < t.length; i++) {
      for (int j = 0; j < t.length; j++) {
        System.out.print(String.format("%d ", t[i][j]));
      }
      System.out.println(String.format("%s", ""));
    }
  }
}
