/*Matthew Szymanski
 * CSCD320
 * 3/12/2022
 */



//package prog3pak;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class FastMatrixMulti {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		File infile = new File(args[0]);
        Scanner scanLine = new Scanner(infile);
        Scanner scanInt = new Scanner(infile);
        
        
        int lines = 0;
 
        while (scanLine.hasNextLine()) {
            lines++;
            scanLine.nextLine();
         }
        
        int[] Arr = new int[lines + 1]; // no. of lines in array dep on line scanner

        int count = 0;
        while (scanInt.hasNextLine()) {	
          int num = scanInt.nextInt();
          Arr[count] = num;
          count++; // count++ when num encountered
          Arr[count] = num;
        }
        scanLine.close();
        scanInt.close();
        
		matrix_chain_order(Arr);
		
	}
	
	
	public static int matrix_chain_order(int[] p) {
		int n = p.length - 1;
		
		int[][] m = new int[n][n]; // storing the optimal time cost
		int[][] s = new int[n][n]; // storing the position of the optimal tcost
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				m[i][i] = 0;
				s[i][i] = 0;
			}
		}
		
		for(int h = 2; h < n; h++) { // h is length of the sub chain
			for(int i = 1; i < n - h + 1; i++) {
			int j = i + h - 1; // position of sub chain
			m[i][j] = Integer.MAX_VALUE; // infinitely high value
				for(int k = i; k <= j - 1; k++) { 
					int q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
					if(q < m[i][j]) {
						m[i][j] = q; // stores the optimal time cost for Ai Aj
						s[i][j] = k; // stores the position of the outmost parantheses
					}
				}
			}
		}
		
		print_optimal_parens(s, 1, n - 1);
		
		return m[1][n-1];		
		
	}
	
	public static void print_optimal_parens(int[][] s, int i, int j){
		if(i == j) {
			System.out.print("A" + i);
			
		}
		else {
			System.out.print("(");
			print_optimal_parens(s, i, s[i][j]); //recursive... Ran into problem here because I wasnt in correct indexing //Fixed
			print_optimal_parens(s, (s[i][j]) + 1, j);
			System.out.print(")");
		}
	}
	
	
}
