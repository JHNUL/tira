import java.util.ArrayList;

public class Polkujenmaara {

  class Kaari {
    int alku;
    int loppu;
    int paino;

    public Kaari(int a, int b, int p) {
      this.alku = a;
      this.loppu = b;
      this.paino = p;
    }
  }

  public static void main(String[] args) {
    Polkujenmaara p = new Polkujenmaara();
    System.out.println(String.format("%s", p.muodosta(10)));
  }

  ArrayList<Kaari> muodosta(int x) {
    ArrayList<Kaari> kaaret = new ArrayList<>();
    for (int k = 1; k <= 100; k++) {
      for (int i = 1; i <= 100; i++) {
        for (int j = 1; j <= 100; j++) {
          
        }
      }
    }


    return null;
  }
}