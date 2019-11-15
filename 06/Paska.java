public class Paska {
  public static void main(String[] args) {
    Paska p = new Paska();
    int res = p.countTrees(Integer.valueOf(args[0]));
    System.out.println(String.format("%d solmulla mahdollisia puurakenteita: %s", Integer.valueOf(args[0]), res));
  }
  public int countTrees(int numKeys) {
    if (numKeys <=1) {
      return(1);
    }
    else {
      // there will be one value at the root, with whatever remains
      // on the left and right each forming their own subtrees.
      // Iterate through all the values that could be the root...
      int sum = 0;
      int left, right, root;
  
      for (root=1; root<=numKeys; root++) {
        left = countTrees(root-1);
        right = countTrees(numKeys - root);
  
        // number of possible trees with this root == left*right
        sum += left*right;
      }
  
      return(sum);
    }
  }
}