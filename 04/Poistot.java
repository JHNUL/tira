import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Poistot {
  static int[] t2;
  public static void main(String[] args) {
    Poistot p = new Poistot();
    int n = 1000000;
        t2 = new int[n];
        Random r = new Random(1337);
        for (int i = 0; i < n; i++) {
            t2[i] = r.nextInt(2)+1;
        }
    System.out.println(String.format("%s", p.laske(t2))); // 1006
    // System.out.println(String.format("%s", p.laske(new int[] {2, 2, 2, 1, 1, 2, 1}))); // 1
  }

  int laske(int[] t) {
    if (t.length == 1)
      return 1;

    int[] pino = new int[t.length];
    int pinoIndeksi = 0;
    for (int i = 0; i < t.length; i++) {
      // System.out.println(String.format("luku %s", t[i]));
      // System.out.println(String.format("pinossa päällä %s", pino[pinoIndeksi]));
      if (t[i] == pino[pinoIndeksi]) {
        pino[pinoIndeksi] = 0;
        pinoIndeksi = pinoIndeksi > 0 ? pinoIndeksi-1 : 0;
      } else {
        if (pino[pinoIndeksi] == 0) {
          pino[pinoIndeksi] = t[i];
        } else {
          pinoIndeksi++;
          pino[pinoIndeksi] = t[i];
        }
      }
      // System.out.println(String.format("%s", Arrays.toString(pino)));
      // System.out.println(String.format("pinoIndeksi %s", pinoIndeksi));
      // System.out.println(String.format("%s", ""));
    }

    return (int) IntStream.of(pino).filter(n -> n > 0).count();
  }
}