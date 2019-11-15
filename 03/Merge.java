import java.util.Arrays;
import java.util.Random;

public class Merge {
  public static void main(String[] args) {
    Merge m = new Merge();
    int n = 10000000;
    int[] a = new int[n];
    Random r = new Random(1337);
    for (int i = 0; i < n; i++) {
      a[i] = r.nextInt(1000000);
    }
    long alku = System.nanoTime();
    m.mergeSort(a, a.length);
    Arrays.sort(a);
    long loppu = System.nanoTime();
    System.out.println("Aikaa kului " + ((loppu - alku) / 1e9) + " s");
  }
  public void mergeSort(int[] a, int n) {
    if (n == 1) {
      return;
    }
    int mid = n / 2;
    int[] l = new int[mid];
    int[] r = new int[n - mid];
    for (int i = 0; i < mid; i++) {
      l[i] = a[i];
    }
    for (int i = mid; i < n; i++) {
      r[i - mid] = a[i];
    }
    mergeSort(l, mid);
    mergeSort(r, n - mid);
    merge(a, l, r, mid, n - mid);
  }

  public void merge(int[] a, int[] l, int[] r, int left, int right) {
    int i = 0, j = 0, k = 0;
    while (i < left && j < right) {
      if (l[i] <= r[j]) {
        a[k++] = l[i++];
      } else {
        a[k++] = r[j++];
      }
    }
    while (i < left) {
      a[k++] = l[i++];
    }
    while (j < right) {
      a[k++] = r[j++];
    }
  }

}
