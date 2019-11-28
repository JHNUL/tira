import java.util.ArrayList;
import java.util.Random;

public class BellmanFord {

  class Kaari {
    private int alku, loppu, paino;

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

  public static void main(String[] args) {
    int n = 1000000;
    BellmanFord bf = new BellmanFord(n);
    for (int i = 1; i <= n - 1; i++) {
      bf.lisaaYhteys(i, i + 1, 1);
    }
    long alku = System.nanoTime();
    System.out.println(String.format("%s", bf.laske(1, 159814)));
    long loppu = System.nanoTime();
    System.out.println("Aikaa kului "+((loppu-alku)/1e9)+" s");
  }

  private ArrayList<Kaari> kaaret;
  private int n;

  public BellmanFord(int n) {
    this.kaaret = new ArrayList<>();
    this.n = n;
  }

  void lisaaYhteys(int a, int b, int p) {
    this.kaaret.add(new Kaari(a, b, p));
    this.kaaret.add(new Kaari(b, a, p));
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