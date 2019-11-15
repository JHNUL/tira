import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class Lukujenmaara {
  public static void main(String[] args) {
    int n = 10000000;
    int[] a = new int[n];
    Random r = new Random(1337);
    for (int i = 0; i < n; i++) {
      a[i] = r.nextInt(1000000);
    }
    long alku = System.nanoTime();
    // HashSet<Integer> luvut = new HashSet<>();
    // for (int i = 0; i < n; i++) {
    //   luvut.add(a[i]);
    // }
    long luvut = Arrays.stream(a).distinct().count();
    System.out.println(String.format("eri lukuja %d", luvut));
    long loppu = System.nanoTime();
    System.out.println("Aikaa kului " + ((loppu - alku) / 1e9) + " s");

  }
}


/* Annettuna on taulukko, jossa on n satunnaista kokonaislukua. Haluamme laskea, montako eri lukua taulukossa on.

Yksi tapa ratkaista tehtävä on käyttää hajautustaulua (HashSet), mutta jos luvut ovat sopivan pieniä,
hajautustaulun sijasta voi käyttää myös tavallista taulukkoa. Tehtäväsi on verrata näiden kahden menetelmän tehokkuutta. */