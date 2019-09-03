public class PisinToisto {
    public static void main(String[] args) {
        PisinToisto pt = new PisinToisto();
        System.out.println(pt.laske(new int[] {1,2,1,1,2})); // 2
        System.out.println(pt.laske(new int[] {1,2,3,4,5})); // 1
        System.out.println(pt.laske(new int[] {1,1,1,1,1})); // 5
    }

    public int laske(int[] t) {
        int pisin = 0;
        int montako = 1;
        for (int i = 1; i < t.length; i++) {
            if (t[i - 1] == t[i]) {
                montako++;
            } else {
                montako = 1;
            }
            pisin = montako > pisin ? montako : pisin;
        }
        return pisin;
    }
}

// Annettuna on taulukko, jossa on n kokonaislukua. Tehtäväsi on laskea, kuinka pitkä on pisin samaa lukua toistava osuus taulukossa.

// Tee luokka PisinToisto, jossa on seuraavat metodit:

// int laske(int[] t): palauttaa pisimmän toiston pituuden
// Rajat:

// 1 ≤ n ≤ 106
// jokainen alkio on välillä 1...106