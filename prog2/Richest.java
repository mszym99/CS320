/*
 * Matthew Szymanski
 * 2/27/2002
 * Dr. Xu
 * CSCD320 Prog2
 * 
 */

import java.util.*;
import java.io.*;
import java.util.Scanner;


import static java.lang.Math.floor;

public class Richest {
    public static void main(String[] args) throws FileNotFoundException{
        
    	
            
            File inf =new File(args[0]); 
            Scanner scan = new Scanner(inf);

            int count = 1;
            
            int[] A = new int[10000];
            Scanner scan2 = new Scanner(inf);
            while (count < 10000 && scan2.hasNext()) {
                A[count] = scan2.nextInt();
                count++;
            }
            MinHeap MinHeap = new MinHeap(A);
            
            MinHeap.HeapSort(A);
            
            while (scan2.hasNextInt()) {
                int temp = scan2.nextInt();
                
                if (A[0] < temp) {
                    A[0] = temp;
                    MinHeap.HeapSort(A);
                }
            }
            PrintStream file = new PrintStream("richest.output.txt");
            
            for (int i = 0; i < A.length; i++)
                 file.println(A[i]);
            file.close();
    }
            
            
            public static void printFile(MinHeap MinHeap, PrintStream file) {
            	int oldSize = MinHeap.size;
            	for(int i = MinHeap.size - 1; i >= 0; i--) {
            		MinHeap.swap(0,i);
            		MinHeap.sizeDec();
            		MinHeap.min_Heapify(i);
            		
            	}
            	for(int i = 0; i < oldSize; i++) {
            		file.println(MinHeap.retIndex(i));
            	}
            

    }
    public static class MinHeap {
        int[] heap;
        int size;
        int max;

        public MinHeap(int[] A) {
            heap = A;
            this.size = A.length;
            max = A.length - 1;
        }

        private void swap(int x, int y) {
			int temp;
			temp = heap[x];
			this.heap[x] = this.heap[y];
			this.heap[y] = temp;
		}

        private void Build_Min_Heap(int[] A) {
            this.size = A.length; //A.length = n
            for (int i = (int) floor(A.length / 2); i >= 1; i--) {
                min_Heapify(1);
            }
        }
        
        public  int retIndex(int i) {
        	return heap[i];
        }
        private void min_Heapify(int i) {
            int s = i;
            int left = (2 * i) + 1;
            int right = (2 * i) + 2;
            if (left < size && heap[left] < heap[s])
                s = left;
            if (right < size && heap[right] < heap[s])
                s = right;
            if (s != i) {
            	swap(i,s);
                min_Heapify(s);
            }
        }

        public void sizeDec() {
        	this.size--;
        }
        public void HeapSort(int[] A) {
            Build_Min_Heap(A);
            int size = A.length;
            for (int i = size-1; i >= 2; i--) {
                swap(0, i);
                size--;
                min_Heapify(0);
            }

        }

    }


}