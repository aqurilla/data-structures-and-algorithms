package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseBits {
  @EpiTest(testDataFile = "reverse_bits.tsv")
  public static long reverseBits(long x) {

    String bitsBase2 = padLeft(Long.toString(x,2), 64);

    String reversedBits = new StringBuilder(bitsBase2).reverse().toString();

    return Long.valueOf(reversedBits,2);
  }

  public static String padLeft(String s, int n) {
    return String.format("%"+n+"s", s).replace(' ', '0');
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
