import java.util.Arrays;

public class Eval {
  public static void main(String[] args) {
    Eval e = new Eval();
    String lasku = "1+1";
    for (int i = 0; i < 249; i++) lasku = "("+lasku+")+1";
    System.out.println(e.laske(lasku)); // 251
  }

  long laske(String lauseke) {

    /* Parseri */
    StringBuilder palikka = new StringBuilder("(" + lauseke + ")");

    /* Käydään läpi kaikki merkit */
    int i = 0;
    while (true) {
      /*
       * Tullaan kiinnimenevään sulkeeseen, mennään takaisin avaavaan saakka
       */
      if (palikka.charAt(i) == ")".charAt(0)) {
        int j = i - 1;
        while (true) {

          /* j ja i välissä on sulkeeton jono */
          if (palikka.charAt(j) == "(".charAt(0)) {
            String pala = palikka.subSequence(j + 1, i).toString();
            String luku = String.valueOf(arvioi(pala));
            palikka.delete(j, i + 1);
            palikka.replace(j, j, luku);
            i -= pala.length() + 2;
            break;
          }

          j--;

        }
      }

      if (i == palikka.length() - 1) {
        break;
      }
      i++;
    }

    return Long.valueOf(palikka.toString());
  }

  long arvioi(String alilauseke) {

    /* Ensin plusmerkillä */
    String[] pluspalat = alilauseke.split("\\+");

    for (int i = 0; i < pluspalat.length; i++) {

      /* jos on kertolaskuja, arvioidaan ne ensin */
      String[] tulopalat = pluspalat[i].split("\\*");
      if (tulopalat.length > 1) {
        long alitulos = 1;
        for (String tulopala : tulopalat) {
          alitulos *= Long.valueOf(tulopala);
        }
        pluspalat[i] = String.valueOf(alitulos);
      }

    }

    return Arrays.stream(pluspalat).map(s -> Long.valueOf(s)).reduce((a, b) -> a + b).orElse(0L);
  }
}
