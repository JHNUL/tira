import java.util.HashMap;

public class Toistuminen {
  public static void main(String[] args) {
    Toistuminen t = new Toistuminen();
    System.out.println(t.uusiLuku(1)); // -1
    System.out.println(t.uusiLuku(2)); // -1
    System.out.println(t.uusiLuku(3)); // -1
    System.out.println(t.uusiLuku(1)); // 2
    System.out.println(t.uusiLuku(2)); // 2
    System.out.println(t.uusiLuku(1)); // 1
  }

  private HashMap<Integer, Integer> rakenne;
  private int kutsut;

  public Toistuminen() {
    this.rakenne = new HashMap<>();
    this.kutsut = 0;
  }

  int uusiLuku(int i) {
    this.kutsut++;
    if (this.rakenne.get(i) == null) {
      this.rakenne.put(i, this.kutsut);
      return -1;
    }
    int esiintymisKutsukerta = this.rakenne.get(i);
    int lukujaValissa = this.kutsut - (esiintymisKutsukerta + 1);
    this.rakenne.put(i, this.kutsut);
    return lukujaValissa;
  }
}