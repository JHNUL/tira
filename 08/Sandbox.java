import java.util.Arrays;
import java.util.HashMap;

public class Sandbox {

  private static int counter = 0;

  public static void main(String[] args) {
    Sandbox s = new Sandbox();
    int[] t = new int[3];
    s.haku(0, t, 3);
    // int[] joukko = new int[] { 4, 4, 4, 4 };
    // s.osajoukko(0, t, joukko);
    // System.out.println(String.format("%s", counter));
    // s.permutaatiot(0, t, new HashMap<Integer, Boolean>());
    // s.testeri(0);
  }

  void testeri(int k) {
    if (k == 3) {
      counter++;
      System.out.println(String.format("%s", counter));
    } else {
      for (int i = 0; i < 4; i++) {
        testeri(k+1);
        System.out.println(String.format("%s", "mitä vittua"));
      }
    }
  }

  void haku(int k, int[] t, int m) {
    if (k == t.length) {
      System.out.println(String.format("%d %s", ++counter, Arrays.toString(t)));
    } else {
      for (int i = 1; i <= m; i++) {
        t[k] = i;
        haku(k + 1, t, m);
      }
    }
  }

  void osajoukko(int k, int[] t, int[] joukko) {
    if (k == t.length) {
      int sum = 0;
      System.out.print("{ ");
      for (int i = 0; i < t.length; i++) {
        if (t[i] == 1) {
          System.out.print(String.format("%d ", joukko[i]));
          sum += joukko[i];
        }
      }
      System.out.println("}");
      if (sum == 4) {
        ++counter;
      }
    } else {
      for (int i = 0; i <= 1; i++) {
        t[k] = i;
        osajoukko(k + 1, t, joukko);
      }
    }
  }

  void permutaatiot(int k, int[] t, HashMap<Integer, Boolean> mukana) {
    if (k == t.length) {
      boolean sopii = true;
      for (int i = 1; i < t.length; i++) {
        if (Math.abs(t[i] - t[i-1]) == 1) {
          sopii = false;
          break;
        }
      }
      if (sopii) {
        System.out.println(String.format("%d %s", ++counter, Arrays.toString(t)));
      }
    } else {
      for (int i = 1; i <= t.length; i++) {
        if (!mukana.containsKey(i)) {
          mukana.put(i, true);
          t[k] = i;
          permutaatiot(k+1, t, mukana);
          mukana.remove(i);
        }
      }
    }
  }
}