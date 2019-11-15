import java.util.ArrayList;
import java.util.Arrays;

public class MerkkijonotI {

  ArrayList<String> lista;
  public static void main(String[] args) {
    MerkkijonotI mI = new MerkkijonotI();
    ArrayList<String> l = mI.muodosta(3, 5);
    System.out.println(String.format("%s", l.toString()));
  }

  void haku(String s, int n, int k) {
    if (s.length() == n) {
      lista.add(s);
    } else {
      for (int i = 0; i < k; i++) {
        haku(s+(char)('A'+i), n, k);
      }
    }
  }

  ArrayList<String> muodosta(int n, int k) {
    lista = new ArrayList<>();
    haku("", n, k);
    return lista;
  }
}
