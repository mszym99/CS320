
/*Matthew Szymanski
 * CSCD320
 * Professor Xu
 * 1/24/22
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class OS_Finding {

	
   public static void main(String[] args) throws FileNotFoundException {
   	
   	
      String infile = args[0];
   	
      File inf = new File(infile);
      Scanner scan = new Scanner(inf);
      int size = count(scan);
      scan.close();
      scan = new Scanner(inf);
      int[] A = new int[size];
      transferIntoArray(A, scan);
      	if (Integer.parseInt(args[1]) <= 0 || Integer.parseInt(args[1]) >= size) {
      		System.out.println("Null");
      	}
      	else {
         System.out.println(RandomizedSelect(A, 0, A.length - 1, Integer.parseInt(args[1])));
      	}
   }
	
   public static int count(Scanner scan) {
      int count = 0;
      while(scan.hasNext()) {
      	
         scan.nextLine();
         count++;
      }
      return count;
   }
	
   public static void transferIntoArray(int[] A, Scanner scan) {
      int counter = 0;
   	
      while(scan.hasNextLine()) {
         int scan1 = scan.nextInt();
         A[counter] = scan1;
         counter++;
      }
   	
   }
	
	
   @SuppressWarnings("unused")
   public static final void swap(int[] A, int a, int b) {
      int temp = A[a];
      A[a] = A[b];
      A[b] = temp;
   }
	
   public static int partition(int[] A,int left,int right) {
      int pivot = A[right];
      int index = left;
      for(int i = left; i <= right - 1; i++) {
         if (A[i] <= pivot) {
            swap(A, index, i);
            index++;
         }
      }
      swap(A, index, right);
      return index;
   }
	
   public static int randomized_partition(int[] A, int left, int right) {
      int i = (int) ((((right - left + 1) * (Math.random())) + left));
   	
      swap(A, i, right);
      return partition(A, left, right);
   }
	
   
   public static int RandomizedSelect(int[] A, int p, int r, int i) {
      if(A == null) {
         System.out.println("null");
      }
      if (p == r) {
         return A[p];
      }
      int q = randomized_partition(A, p, r);
      int k = q - p + 1;
      if(i == k) {
         return A[q];
      }
      else if (i<k) {
         return RandomizedSelect(A, p, q-1, i);
      }
      else {
         return RandomizedSelect(A, q+1, r, i-k);
      }
   }
	
}
	

