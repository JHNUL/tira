import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class PrimeFactors {
  public static void main(String[] args) {
    // 2^i 3^j 5^k
    long[] taulu = new long[101];
    int c2 = 0, c3 = 0, c5 = 0;
    taulu[0] = 1;
    int ind = 1; // vapaa indeksi
    while (ind < taulu.length) {
      long next = Math.min(2*taulu[c2], Math.min(3*taulu[c3], 5*taulu[c5]));
      taulu[ind] = next;
      ind++;
      if (next == 2*taulu[c2]) c2 += 1;
      if (next == 3*taulu[c3]) c3 += 1;
      if (next == 5*taulu[c5]) c5 += 1;
    }
    System.out.println(String.format("%s", Arrays.toString(taulu)));
  }
}