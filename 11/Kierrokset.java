import java.util.ArrayList;

public class Kierrokset {

  class Kaari {
    int alku; // mistä solmusta kaari alkaa
    int loppu; // mihin solmuun kaari päättyy
    int pituus;

    public Kaari(int alku, int loppu, int pituus) {
      this.alku = alku;
      this.loppu = loppu;
      this.pituus = pituus;
    }

    public String toString() {
      return "(" + alku + "," + loppu + "," + pituus + ")";
    }

    public int getAlku() {
      return alku;
    }

    public int getLoppu() {
      return loppu;
    }

    public int getPituus() {
      return pituus;
    }
  }

  public static void main(String[] args) {
    Kierrokset k = new Kierrokset();
    ArrayList<Kaari> a = k.muodosta(Integer.parseInt(args[0]));
    System.out.println(k.laske(100, a)); // [(1,2,5), (2,3,3), (3,4,4), (1,3,7)]
  }

  public Kierrokset() {}

  ArrayList<Kaari> muodosta(int x) {
    ArrayList<Kaari> kr = new ArrayList<>();
    if (x == 1) return kr;
    for (int i = x; i > 1; i--) {
      kr.add(new Kaari(i-1, i, 100));
    }
    // int lisaKierroksia = x-2;
    // int vika = 99;
    // int c = 0;
    // for (int i = 0; i < lisaKierroksia; i++) {
    //   if (i < 52) {
    //     kr.add(new Kaari(vika - i * 2 - 2, vika - i * 2, 999));
    //   }/*  else {
    //     kr.add(new Kaari((vika-1) - c * 2 - 2, (vika-1) - c * 2, 1));
    //     c++;
    //   } */
    // }
    // kr.add(new Kaari(6, 8, 100));
    // kr.add(new Kaari(5, 8, 100)); 
    // kr.add(new Kaari(2, 4, 1));
    System.out.println(String.format("%s", kr.toString()));
    return kr;
  }

  int laske(int n, ArrayList<Kaari> kaaret) {
    int[] etaisyys = new int[n+1];
    for (int i = 1; i <= n; i++) {
      etaisyys[i] = 999999999;
    }
    etaisyys[1] = 0;
    int kierros = 0;
    while (true) {
      kierros++;
      boolean muutos = false;
      for (Kaari k : kaaret) {
        try {
          if (etaisyys[k.getAlku()] + k.getPituus() < etaisyys[k.getLoppu()]) {
            etaisyys[k.getLoppu()] = etaisyys[k.getAlku()] + k.getPituus();
            muutos = true;
          }
        } catch (Exception e) {
          System.out.println(String.format("%s", k.toString()));
        }
      }
      if (!muutos) break;
    }
    return kierros;
  }
}