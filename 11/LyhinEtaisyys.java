import java.util.ArrayList;

public class LyhinEtaisyys {
  class Kaari {
    private int alku;
    private int loppu;
    private int paino;

    public Kaari(int alku, int loppu, int paino) {
      this.alku = alku;
      this.loppu = loppu;
      this.paino = paino;
    }

    public int getAlku() {
      return alku;
    }

    public int getLoppu() {
      return loppu;
    }

    public int getPaino() {
      return paino;
    }
  }

  private ArrayList<Kaari> kaaret;
  private int n;

  public static void main(String[] args) {
    LyhinEtaisyys l = new LyhinEtaisyys(5);
    l.lisaaTie(1, 2, 7);
    l.lisaaTie(2, 4, 2);
    l.lisaaTie(1, 3, 6);
    l.lisaaTie(3, 4, 5);
    l.lisaaTie(4, 5, 3);
    System.out.println(l.laske(1, 5)); // 12
  }

  public LyhinEtaisyys(int n) {
    this.n = n;
    this.kaaret = new ArrayList<>();
  }

  void lisaaTie(int a, int b, int p) {
    Kaari k1 = new Kaari(a, b, p);
    Kaari k2 = new Kaari(b, a, p);
    this.kaaret.add(k1);
    this.kaaret.add(k2);
  }

  int laske(int a, int b) {
    int[] etaisyys = new int[this.n + 1];
    for (int i = 1; i <= n; i++) {
      etaisyys[i] = 999999999;
    }
    etaisyys[a] = 0;
    while (true) {
      boolean muutos = false;
      for (Kaari k : this.kaaret) {
        if (etaisyys[k.getAlku()] + k.getPaino() < etaisyys[k.getLoppu()]) {
          etaisyys[k.getLoppu()] = etaisyys[k.getAlku()] + k.getPaino();
          muutos = true;
        }
        if (etaisyys[k.getLoppu()] + k.getPaino() < etaisyys[k.getAlku()]) {
          etaisyys[k.getAlku()] = etaisyys[k.getLoppu()] + k.getPaino();
          muutos = true;
        }
      }
      if (!muutos)
        break;
    }
    return etaisyys[b] == 999999999 ? -1 : etaisyys[b];
  }

}