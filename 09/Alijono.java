import java.util.Arrays;

public class Alijono {
  public static void main(String[] args) {
    Alijono a = new Alijono();
    System.out.println(String.format("%s", a.pisin(new int[]{1,1,1,1,1,1}))); // 1
    System.out.println(String.format("%s", a.pisin(new int[]{1,2,3,4,5,6}))); // 6
    System.out.println(String.format("%s", a.pisin(new int[]{6,5,4,3,2,1}))); // 6
    System.out.println(String.format("%s", a.pisin(new int[]{1,8,2,7,3,6,4}))); // 4
  }

  int pisin(int[] t) {
    int[] pisin = new int[t.length];
    for (int i = 0; i < t.length; i++) {
      pisin[i] = 1;
      for (int j = 0; j < i; j++) {
        if (Math.abs(t[j] - t[i]) == 1 && pisin[j]+1 > pisin[i]) {
          pisin[i] = pisin[j]+1;
        }
      }
    }
    return Arrays.stream(pisin).max().orElse(-1);
  }

  /* 
  for k = 0 to n-1
    pisin[k] = 1
    for x = 0 to k-1
      if taulu[x] < taulu[k] and pisin[x]+1 > pisin[k]
        pisin[k] = pisin[x]+1 
  */
}