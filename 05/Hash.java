public class Hash {
  public static void main(String[] args) {
    String sana = "saippuakauppias";
    long h = 0;
    for (int i = 0; i < sana.length(); i++) {
      h = (h*17+(int)sana.charAt(i)) % 931663099;
    }
    System.out.println(String.format("%s", h));
  }
}