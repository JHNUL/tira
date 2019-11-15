import java.util.Arrays;
import java.util.stream.IntStream;

public class Circle {
  public static void main(String[] args) {
    Circle c = new Circle();
    // System.out.println(c.viimeinen(7)); // 7
    // System.out.println(c.viimeinen(4)); // 1
    System.out.println(c.viimeinen(123)); // 119
  }

  public int viimeinen(int maara) {
    int[] t = IntStream
      .iterate(1, n -> n + 1)
      .limit(maara)
      .toArray();
    // System.out.println(String.format("taulukko alussa %s", Arrays.toString(t)));
    return laske(t, 0);
  }

  public int laske(int[] t, int aloitusIndeksi) {
    if (t.length == 1) {
      return t[0];
    }
    
    int koko = 0;
    boolean parillinenPituus = t.length % 2 == 0;
    if (parillinenPituus) {
      koko = t.length / 2; 
    } else {
      koko = aloitusIndeksi == 0 ? t.length / 2 + 1 : t.length / 2;
    }

    int[] p = new int[koko];
    for (int i = 0; i < koko; i++) {
      p[i] = t[aloitusIndeksi];
      aloitusIndeksi += 2;
    }

    int uusiAloitusIndeksi = p[p.length - 1] == t[t.length - 1] ? 1 : 0;
    
    // System.out.println(String.format("taulukko miksailun jälkeen %s", Arrays.toString(p)));
    return laske(p, uusiAloitusIndeksi);
  }
}