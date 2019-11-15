public class Kuningatar {
  public static void main(String[] args) {
    int n = 6;
    int[] t = new int[n];
    Kuningatar k = new Kuningatar();
    k.haku(0, n, t);
  }

  void haku(int y, int n, int[] taulukko) {
    if (y == n) {
      tulostaTaulukko(taulukko);
    } else {
      for (int x = 0; x < n; x++) {
        if (voiSijoittaa(y, x, taulukko)) {
          taulukko[y] = x;
          haku(y+1, n, taulukko);
        }
      }
    }
  }

  boolean voiSijoittaa(int y, int x, int[] t) {
    for (int i = 0; i < y; i++) {
      if (t[i] == x) {
        return false;
      }
      if (Math.abs(i-y) == Math.abs(t[i] - x)) {
        return false;
      }
    }
    return true;
  }

  void tulostaTaulukko(int[] t) {
    for (int y = 0; y < t.length; y++) {
      System.out.println(String.format("Rivillä %d kuningatar sarakkeessa %d", y+1, t[y]+1));
    }
    System.out.println(String.format("%s", "----------------------"));
  }
}