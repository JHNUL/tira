import java.util.HashMap;
import java.util.Map;

public class Matching {
  public static void main(String[] args) {
    Matching m = new Matching();
    String[] tulos = m.luoSyotteet(new String[]{"a", "b"}, 18);
    Map<Integer, String> hakemisto = new HashMap<>();
    for (String s : tulos) {
      if (hakemisto.containsKey(s.hashCode())) {
        System.out.println(String.format("duplikaatit %s : %s", hakemisto.get(s.hashCode()), s));
      }
      hakemisto.put(s.hashCode(), s);
    }
  }

  public String[] luoSyotteet(String[] syote, int pituus) {

    if (syote[syote.length - 1].length() == pituus) {
      return syote;
    }

    String[] uusi = new String[syote.length * 2];

    for (int i = 0; i < syote.length; i++) {
      uusi[i] = "a" + syote[i];
    }

    for (int i = syote.length; i < syote.length * 2; i++) {
      uusi[i] = "b" + syote[i - syote.length];
    }
    
    String[] uudet = luoSyotteet(uusi, pituus);
    String[] yhdistetty = new String[uudet.length + syote.length];
    int ind = 0;
    while(ind < syote.length) {
      yhdistetty[ind] = syote[ind];
      ind++;
    }
    while(ind < yhdistetty.length) {
      yhdistetty[ind] = uudet[ind - syote.length];
      ind++;
    }
    return yhdistetty;
  }

  

  public int hajautusArvo(String s) {
    int h = 0;
    for (int i = 0; i < s.length(); i++) {
      h += (int)s.charAt(i)*(Math.pow(31, s.length() - (i + 1)));
    }
    
    return h;
    // s[0]*31^(n - 1) + s[1]*31^(n - 2) + ... + s[n - 1]
    /* sometimes the hashcode calculation itself goes beyond the Integer.MAX_VALUE,
    i.e 2147483647. what happens then is that we get a negative integer after the overflow.
    Negative hashcode is perfectly valid! */
  }

}

/* 
Etsi kaksi eri merkkijonoa, jotka muodostuvat merkeistä a ja b 
a joille Javan metodi hashCode antaa saman hajautusarvon.

Tee luokka Tormays, jossa on seuraavat metodit:

String merkkijono1(): palauttaa ensimmäisen merkkijonon
String merkkijono2(): palauttaa toisen merkkijonon
Seuraava koodi esittelee luokan käyttämistä:

Tormays t = new Tormays();
String x = t.merkkijono1();
String y = t.merkkijono2();
if (x.matches("[ab]+") && y.matches("[ab]+") && !x.equals(y) && x.hashCode() == y.hashCode()) {
    System.out.println("Hyvää työtä :)");
}
 */