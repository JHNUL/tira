import java.util.Arrays;

public class Pysakit {
  public static void main(String[] args) {
    Pysakit p = new Pysakit();
    System.out.println(p.laske(new int[] { 3, 7, 1, 5 }, 1)); // 2
    System.out.println(p.laske(new int[] { 3, 7, 1, 5 }, 2)); // 2
    System.out.println(p.laske(new int[] { 3, 7, 1, 5 }, 3)); // 1
  }

  int laske(int[] t, int k) {
    Arrays.sort(t);
    int L = t[0];
    int R = -1;
    int diff = -1;
    int pysakit = 0;

    int i = 0;
    int next = 1;
    while (true) {
      L = t[i];
      R = t[i + next];
      diff = R - L;

      /* Jos pysäkillä yhdistetään kaksi asuntoa */
      if (diff == 2 * k) {
        pysakit++;
        i = i + next + 1;
        next = 1;
        if (i > t.length - 1)
          break;
        if (i == t.length - 1) {
          pysakit++;
          break;
        }
      }
      /* Jos kahden talon etäisyys on liian suuri molempien yhdistämiseen */
      else if (diff > 2 * k) {
        pysakit++;
        i = i + next;
        next = 1;
        if (i > t.length - 1) {
          break;
        }
        if (i == t.length - 1) {
          pysakit++;
          break;
        }
      }
      /*
       * Jos kahden talon etäisyys on pienempi kuin 2 * sallittu pysäkkietäisyys
       * Kasvatetaan oikeaa
       */
      else {
        next++;
        if (i + next > t.length - 1) {
          pysakit++;
          break;
        }
      }
    }

    return pysakit;
  }
}