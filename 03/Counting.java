import java.util.Arrays;
import java.util.Random;

public class Counting {
  public static void main(String[] args) {
    Counting c = new Counting();
    int n = 10000000;
    int[] a = new int[n];
    Random r = new Random(1337);
    for (int i = 0; i < n; i++) {
      a[i] = r.nextInt(1000000);
    }
    long alku = System.nanoTime();
    int[] arr = c.countingSort(a, a.length);
    // Arrays.sort(a);
    long loppu = System.nanoTime();
    System.out.println("Aikaa kului " + ((loppu - alku) / 1e9) + " s");
    for (int i = 0; i < arr.length -1; i++) {
      if (arr[i] > arr[i + 1]) {
        throw new IllegalStateException("Array not in order");
      }
    }
  }

  int[] countingSort(int[] input, int k) {
    int[] c = countElements(input, k);
 
    int[] sorted = new int[input.length];
    for (int i = input.length - 1; i >= 0; i--) {
        int current = input[i];
    sorted[c[current] - 1] = current;
    c[current] -= 1;
    }
 
    return sorted;
  }

  int[] countElements(int[] input, int k) {
    int[] c = new int[k + 1];
    Arrays.fill(c, 0);

    for (int i : input) {
      c[i] += 1;
    }

    for (int i = 1; i < c.length; i++) {
      c[i] += c[i - 1];
    }

    return c;
  }

}
