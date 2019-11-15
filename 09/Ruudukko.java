public class Ruudukko {
  public static void main(String[] args) {
    Ruudukko r = new Ruudukko();
    int[][] kentta = {
      {0,0,0,0},
      {0,1,2,3},
      {0,1,2,3},
      {0,1,2,3},
    };
    int tls = r.laske(kentta); // 12
    System.out.println(String.format("%s", tls));
  }

  int laske(int[][] r) {
    for (int y = 1; y < r.length; y++) {
      for (int x = 1; x < r.length; x++) {
        r[y][x] = r[y][x] + Math.max(r[y-1][x], r[y][x-1]);
      }
    }
    tulosta(r);
    return r[r.length-1][r.length-1];
  }

  void tulosta(int[][] t) {
    for (int y = 1; y < t.length; y++) {
      for (int x = 1; x < t.length; x++) {
        System.out.print(String.format("%s ", t[y][x]));
      }
      System.out.println("");
    }
  }
}

/* 
for y = 1 to n
  for x = 1 to n
    if este[y][x]
      reitit[y][x] = 0
    else if y == 1 and x == 1
      reitit[y][x] = 1
    else
      reitit[y-1][x]+reitit[y][x-1]
 */