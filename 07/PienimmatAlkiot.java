import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PienimmatAlkiot {

  public static void main(String[] args) {
    PienimmatAlkiot p = new PienimmatAlkiot();
    int n = 10000000;
        // 1. luo testisyöte
        
        List<Integer> tt = Stream // ei duplikaatteja tässä toteutuksessa
          .iterate(0, l -> l + 1)
          .limit(n)
          .collect(Collectors.toList());
        Collections.shuffle(tt);
        int[] taulu = tt.stream().mapToInt(l -> l).toArray();
        // 2. aloita ajanmittaus
        long alku = System.nanoTime();
        // 3. suorita algoritmi
        long tulos = p.laske3(taulu, 1000); // 499500
        // 4. lopeta ajanmittaus
        long loppu = System.nanoTime();
        // 5. ilmoita tulokset
        System.out.println("Algoritmin tulos: "+tulos);
        System.out.println("Aikaa kului "+((loppu-alku)/1e9)+" s");
  }

  /* O(nk) toteutus */
  public long laske1(int[] t, int k) {

    int edellinenPienin = -1;
    int lukuja = 0;
    int summa = 0;
    while (lukuja < k) {
      int pienin = 10000000;
      for (int i = 0; i < t.length; i++) {
        if (t[i] <= pienin && t[i] > edellinenPienin) {
          pienin = t[i];
        }
      }
      summa += pienin;
      edellinenPienin = pienin;
      lukuja++;
    }
    
    return summa;
  }

  /* O(n log n) toteutus */
  public long laske2(int[] t, int k) {

    Arrays.sort(t);

    int indeksi = 0;
    long summa = 0;
    while (indeksi < k) {
      summa += t[indeksi];
      indeksi++;
    }

    return summa;
  }

  /* O(n + k log n) toteutus */
  public long laske3(int[] t, int k) {

    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int i = 0; i < t.length; i++) {
      pq.add(t[i]);
    }

    int indeksi = 0;
    int summa = 0;
    while (indeksi < k) {
      summa += pq.poll();
      indeksi++;
    }

    return summa;
  }
  
}