import java.util.Arrays;

public class Tehdas {
  public static void main(String[] args) {
    Tehdas t = new Tehdas();
    int tulos = t.laske(new int[] {2,3,7}, 6);
    System.out.println(String.format("%s", tulos));
  }

  int laske(int[] taulu, int x) {
    Arrays.sort(taulu);
    
    // otetaan pienin
    int pienin = taulu[0];
    int tuotteita = 0;
    
    for (int i = 0; i < taulu.length; i++) {
      if (taulu[i] < pienin * 2) {
        tuotteita++;
        if (tuotteita == x) {
          return taulu[i];
        }
      } else {
        break;
      }
    }
    tuotteita = 0;
    while  (tuotteita < x) {

      // lasketaan kuinka monta konetta nykyisellä pienimmän arvolla tulee valmiiksi
      for (int i = 0; i < taulu.length; i++) {
        // jos pienin on pienempi, ei tule yhtään
        if (pienin >= taulu[i]) {
          tuotteita += Math.floor(pienin / taulu[i]);
        } else {
          if (tuotteita < x) {
            pienin += taulu[0];
          }
          break; // ulos for-silmukasta
        }
      }


    }

    return pienin;
  }
}