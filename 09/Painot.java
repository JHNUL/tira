import java.util.Arrays;

public class Painot {
  public static void main(String[] args) {
    Painot p = new Painot();
    int t = p.yhteisPainot(new int[] { 1,2,3,4 });
    System.out.println(String.format("[%s]", t));
  }

  int yhteisPainot(int[] t) {
    int s = Arrays.stream(t).sum();
    boolean[] painot = new boolean[s + 1];
    painot[0] = true;
    for (int i = 0; i < t.length; i++) {
      for (int j = s; j >= 0; j--) {
        if (painot[j]) {
          painot[j + t[i]] = true;
        }
      }
    }
    int tosia = 0;
    for (int i = 1; i < painot.length; i++) {
      if (painot[i]) {
        System.out.print(String.format("%s ", i));
        tosia++;
      }
    }
    return tosia;
  }

}
