public class Summat {
  public static void main(String[] args) {
    Summat s = new Summat();
    System.out.println(String.format("%s", s.laskeDynaamisesti(Integer.valueOf(args[0]))));
  }

  long laskeDynaamisesti(int n) {
    long[] t = new long[n+5];
    t[0] = 1;
    t[1] = 2;
    t[2] = 4;
    t[3] = 8;
    t[4] = 16;
    t[5] = 32;
    for (int i = 6; i < n; i++) {
      t[i] = t[i-1]+t[i-2]+t[i-3]+t[i-4]+t[i-5]+t[i-6];
    }
    return t[n-1];
  }

  long laske(int n) {

    /**
     * Jos summa voidaan laskea luvuista 1-6, kun ensimmäinen luku on 1, on summa
     * n-1; kun 2 niin summa on n-2 jne.
     */
    if (n <= 0)
      return 0; // kun mennään negatiiviseen, ei tule yhtään lisäystä
    if (n == 1)
      return 1; // vain luku 1
    if (n == 2)
      return 2; // [1,1], [2]
    if (n == 3)
      return 4; // [1,1,1], [1,2], [2,1], [3]
    if (n == 4)
      return 8; // [1,1,1,1], [1,1,2], [1,2,1], [2,1,1], [2,2], [1,3], [3,1], [4]
    if (n == 5)
      return 16;
    if (n == 6)
      return 32;

    return laske(n - 1) + laske(n - 2) + laske(n - 3) + laske(n - 4) + laske(n - 5) + laske(n - 6);
  }
}