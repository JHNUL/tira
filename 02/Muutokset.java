public class Muutokset {
    public static void main(String[] args) {
        Muutokset m = new Muutokset();
        System.out.println(m.laske(new int[] {1,1,2,2,2})); // 2
        System.out.println(m.laske(new int[] {1,2,3,4,5})); // 0
        System.out.println(m.laske(new int[] {1,1,1,1,1})); // 2
    }

    public int laske(int[] t) {
        int vaihtoja = 0;
        for (int i = 1; i < t.length - 1; i++) {
            if (t[i - 1] == t[i]) {
                int uusi;
                if (t[i] + 1 == t[i + 1] && t[i + 1] == 106) {
                    uusi = 1; 
                } else if (t[i] + 1 == t[i] && t[i + 1] < 106) {
                    uusi = t[i + 1] + 1;
                } else if (t[i] == 106 && t[i + 1] != 1) {
                    uusi = 1;
                } else if (t[i] == 106 && t[i + 1] == 1) {
                    uusi = 2;
                } else {
                    uusi = t[i] + 1;
                }
                t[i] = uusi;
                vaihtoja++;
            }
        }

        return vaihtoja;
    }
}
