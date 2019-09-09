import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.HashMap;
import java.util.Map;

public class Alitaulukot {
  public static void main(String[] args) {
    Alitaulukot a = new Alitaulukot();
    int suorita = Integer.valueOf(args[0]);
    if (suorita == 0) {
      System.out.println(a.laske(new int[] { 1, 2, 1, 3, 2 }, false)); // 10
      System.out.println(a.laske(new int[] { 1, 1, 1, 1, 1 }, false)); // 15
      System.out.println(a.laske(new int[] { 1, 2, 3, 4, 5 }, false)); // 9
    }
    
  }

  public long laske(int[] t, boolean debug) {
    int m = -1; // muisti
    int v = -1; // vaihto
    int L = 0; // Left
    int R = -1; // Right
    long pit = 0;

    for (int i = 1; i < t.length - 1; i++) {
      
      R = i + 1;

      if (m == -1 && t[i] != t[L]) {
        m = i;
      }

      if (t[i] != t[i - 1]) {
        v = i;
      }

      if (m > -1 && t[R] != t[m] && t[R] != t[L]) {
        int lisaysPituus = i - L + 1;
        long lisays = ((long) lisaysPituus * (long) lisaysPituus + (long) lisaysPituus) / 2;
        int olap = i - v + 1;
        long olapVahennys = ((long) olap * (long) olap + (long) olap) / 2;
        lisays -= olapVahennys;
        pit += lisays;
        L = v > -1 ? v : 0;
        m = -1;
      }
      if (R == t.length - 1) {
        int lisaysPituus = R - L + 1;
        long lisays = ((long) lisaysPituus * (long) lisaysPituus + (long) lisaysPituus) / 2;
        pit += lisays;
      }

    }
    return pit;
  }
  
}