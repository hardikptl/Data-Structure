package comp10205_lab02_hardikkumar;

import java.util.Arrays;

/* Comp10205 - Sorting Assignment
 
 Some of the sort code from courseWare textbook - Copyright, All rights reserved.

 Additional code added by C. Mark Yendt in May 2014 August 2019

 Answers to Questions here :
 _________________________________________________
 Que1: Identify the type of sorts for each of the methods provided.
 Ans:    A:Quick Sort
 B:Selection Sort
 C:Insertion Sort
 D:Merge Sort
 E:Bubble Sort

 Que2:List in order (fastest to slowest) your selection of algorithm to use when the
 array to be sorted contains 25 elements
 Ans:    1)Insertion Sort
 2)Selection Sort
 3)Merge Sort
 4)Bubble Sort.
 5)Quick Sort

 Que3:List in order (fastest to slowest) your selection of algorithm to use when the
 array to be sorted contains 25000 elements. 
 Ans:    1)Merge Sort
 2)Quick Sort
 3)Insertion Sort
 4)Selection Sort.
 5)Bubble Sort

 Que4: List the algorithm and the BIG O notation for that algorithm.
 Ans:    1)Merge Sort        = BigO(n log(n))
 2)Quick Sort        = BigO(n log(n))
 3)Insertion Sort    = BigO(n^2)
 4)Selection Sort    = BigO(n^2)
 5)Bubble Sort       = BigO(n^2)

 Que5:Which algorithm has the best performance of the basic step? Does this have any
 impact on your selection of fastest algorithm when sorting an array of 25000
 elements? Why?
 Ans:Merge sort algortihm has best performance of basic step. It impact alot when you sorting element of 25000 elementss because 
 number elemnts increase then number of basic step decrease as nlogn. as per bigO Notaion in average case.

 */
/**
 * Authorship Statement Student id : 000765796 Date : 2019/10/05
 *
 * @author hardikkumar I, Hardikkumar Patel clearify this assignment done by
 * myself and not copied from anyone.
 */
public class Comp10205_Lab02_Hardikkumar {

    static int sortaCompares = 0;  // Left in for comparison purposes only
    static final boolean SHOW_SOLUTION = true;

    /**
     * The swap method swaps the contents of two elements in an i array.
     *
     * @param array containing the two elements.
     * @param a The subscript of the first element.
     * @param b The subscript of the second element.
     */
    private static void swap(int[] array, int a, int b) {
        int temp;

        temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    /**
     * The ___Non-recursive Quick__ sort - manages first call
     *
     * @param array an unsorted array that will be sorted upon method completion
     * @return count
     */
    public static int sortA(int array[]) {
        //sortaCompares = 0;
        int count = 0;
        count = doASort(array, 0, array.length - 1, count);
        return count;
    }

    /**
     * The doASort method uses the ___QuickSort__ algorithm to sort
     *
     * @param array The array to sort.
     * @param start The starting subscript of the list to sort
     * @param end The ending subscript of the list to sort
     */
    private static int doASort(int array[], int start, int end, int count) {
        int pivotPoint;

        if (start < end) {
            // Get the pivot point.
            pivotPoint = part(array, start, end);
            //counting subscript for next recursion
            count = count + (end - start);
            // Note - only one +/=
            // Sort the first sub list.
            count = doASort(array, start, pivotPoint - 1, count);

            // Sort the second sub list.
            count = doASort(array, pivotPoint + 1, end, count);
        }
        return count;
    }

    /**
     * The partition method selects a pivot value in an array and arranges the
     * array into two sub lists. All the values less than the pivot will be
     * stored in the left sub list and all the values greater than or equal to
     * the pivot will be stored in the right sub list.
     *
     * @param array The array to partition.
     * @param start The starting subscript of the area to partition.
     * @param end The ending subscript of the area to partition.
     * @return The subscript of the pivot value.
     */
    private static int part(int array[], int start, int end) {
        int pivotValue;    // To hold the pivot value
        int endOfLeftList; // Last element in the left sub list.
        int mid;           // To hold the mid-point subscript

        // see http://www.cs.cmu.edu/~fp/courses/15122-s11/lectures/08-qsort.pdf
        // for discussion of middle point 
        // Find the subscript of the middle element.
        // This will be our pivot value.
        mid = (start + end) / 2;

        // Swap the middle element with the first element.
        // This moves the pivot value to the start of 
        // the list.
        swap(array, start, mid);

        // Save the pivot value for comparisons.
        pivotValue = array[start];

        // For now, the end of the left sub list is
        // the first element.
        endOfLeftList = start;

        // Scan the entire list and move any values that
        // are less than the pivot value to the left
        // sub list.
        for (int scan = start + 1; scan <= end; scan++) {
            sortaCompares++;
            if (array[scan] < pivotValue) {
                endOfLeftList++;
                swap(array, endOfLeftList, scan);
            }
        }

        // Move the pivot value to end of the
        // left sub list.
        swap(array, start, endOfLeftList);

        // Return the subscript of the pivot value.
        return endOfLeftList;
    }

    /**
     * An implementation of the __Selection_ Sort Algorithm
     *
     * @param array an unsorted array that will be sorted upon method completion
     * @return count
     */
    public static int sortB(int[] array) {
        int count = 0;     //counter set
        int startScan;   // Starting position of the scan
        int index;       // To hold a subscript value
        int minIndex;    // Element with smallest value in the scan
        int minValue;    // The smallest value found in the scan

        // The outer loop iterates once for each element in the
        // array. The startScan variable marks the position where
        // the scan should begin.
        for (startScan = 0; startScan < (array.length - 1); startScan++) {
            // Assume the first element in the scannable area
            // is the smallest value.
            minIndex = startScan;
            minValue = array[startScan];

            // Scan the array, starting at the 2nd element in
            // the scannable area. We are looking for the smallest
            // value in the scannable area. 
            for (index = startScan + 1; index < array.length; index++) {
                count++;
                if (array[index] < minValue) {
                    minValue = array[index];
                    minIndex = index;
                }
            }

            // Swap the element with the smallest value 
            // with the first element in the scannable area.
            array[minIndex] = array[startScan];
            array[startScan] = minValue;
        }

        return count;
    }

    /**
     * An implementation of the _Insertion__ Sort algorithm
     *
     * @param array an unsorted array that will be sorted upon method completion
     * @return count
     */
    public static int SortC(int[] array) {
        int count = 0;         //counter set
        int unsortedValue;  // The first unsorted value
        int scan;           // Used to scan the array

        // The outer loop steps the index variable through 
        // each subscript in the array, starting at 1. The portion of
        // the array containing element 0  by itself is already sorted.
        for (int index = 1; index < array.length; index++) {
            // The first element outside the sorted portion is
            // array[index]. Store the value of this element
            // in unsortedValue.
            unsortedValue = array[index];

            // Start scan at the subscript of the first element
            // outside the sorted part.
            scan = index;
             count++;
            // Move the first element in the still unsorted part
            // into its proper position within the sorted part.
            while (scan > 0 && array[scan - 1] > unsortedValue) {
               
                array[scan] = array[scan - 1];
                scan--;
            }

            // Insert the unsorted value in its proper position
            // within the sorted subset.
            array[scan] = unsortedValue;
        }
        return count;
    }

    /**
     * completes a __merge __ sort on an array
     *
     * @param array the unsorted elements - will be sorted on completion
     * @return count
     */
    public static int SortD(int array[]) {
        int length = array.length;
        int count = 0; //count set
        count = doDSort(array, 0, length - 1, count);
        return count;
    }

    /**
     * private recursive method that splits array until we start at array sizes
     * of 1
     *
     * @param array starting array
     * @param lowerIndex lower bound of array to sort
     * @param higherIndex upper bound of array to sort
     */
    private static int doDSort(int[] array, int lowerIndex, int higherIndex, int count) {
        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            // Below step sorts the left side of the array
            count = doDSort(array, lowerIndex, middle, count);
            // Below step sorts the right side of the array
            count = doDSort(array, middle + 1, higherIndex, count);
            // Now put both parts together
            count += part1(array, lowerIndex, middle, higherIndex);
        }
        return count;
    }

    /**
     * Puts two smaller sorted arrays into one sorted array
     *
     * @param array provided in two sorted parts (1,4,9,2,3,11)
     * @param lowerIndex the location of the first index
     * @param middle - the middle element
     * @param higherIndex - the upper bound of the sorted elements
     * @return count
     */
    private static int part1(int[] array, int lowerIndex, int middle, int higherIndex) {
        int count = 0; //set count
        int[] mArray = new int[higherIndex - lowerIndex + 1];
        for (int i = lowerIndex; i <= higherIndex; i++) {
            mArray[i - lowerIndex] = array[i];
        }
        // Part A - from lowerIndex to middle
        // Part B - from middle + 1 to higherIndex
        int partAIndex = lowerIndex;
        int partBIndex = middle + 1;
        int mergeIndex = lowerIndex;
        while (partAIndex <= middle && partBIndex <= higherIndex) {
            // Place items back into Array in order
            // Select the lowestest of the two elements
            count++;
            if (mArray[partAIndex - lowerIndex] <= mArray[partBIndex - lowerIndex]) {
                array[mergeIndex] = mArray[partAIndex - lowerIndex];
                partAIndex++;
            } else {
                array[mergeIndex] = mArray[partBIndex - lowerIndex];
                partBIndex++;
            }
            mergeIndex++;
        }
        // Copy the remainder of PartA array (if required)
        while (partAIndex <= middle) {
            array[mergeIndex] = mArray[partAIndex - lowerIndex];
            mergeIndex++;
            partAIndex++;
        }
        return count;
    }

    /**
     * Sorting using the ___Bubble___ Sort algorithm
     *
     * @param array an unsorted array that will be sorted upon method completion
     * @return count
     */
    public static int sortE(int[] array) {
        int count = 0;      //count set
        int lastPos;     // Position of last element to compare
        int index;       // Index of an element to compare

        // The outer loop positions lastPos at the last element
        // to compare during each pass through the array. Initially
        // lastPos is the index of the last element in the array.
        // During each iteration, it is decreased by one.
        for (lastPos = array.length - 1; lastPos >= 0; lastPos--) {
            // The inner loop steps through the array, comparing
            // each element with its neighbor. All of the elements
            // from index 0 thrugh lastPos are involved in the
            // comparison. If two elements are out of order, they
            // are swapped.
            for (index = 0; index <= lastPos - 1; index++) {
                count++;
                // Compare an element with its neighbor.
                if (array[index] > array[index + 1]) {
                    // Swap the two elements.

                    swap(array, index, index + 1);
                }
            }
        }
        return count;
    }

    /**
     * Print an array to the Console
     *
     * @param A array to be printed
     */
    public static void printArray(int[] A) {
        for (int i = 0; i < A.length; i++) {
            System.out.printf("%10d ", A[i]);
        }
        System.out.println();
    }

    /*
     *   private method for calling and print output of all sort
     */
    private static void displaySort(int size) {
        int[] B;
        int[] A = new int[size];

        // Create random array with elements in the range of 0 to SIZE - 1;
        for (int i = 0; i < size; i++) {
            A[i] = (int) (Math.random() * size);
        }

        System.out.printf("\nComparison for array size of %d\n\n", size);
        //Quick Sort Results
        B = Arrays.copyOf(A, A.length);
        long startTime = System.nanoTime();
        int countOfQuickSort = sortA(B);
        long elapsedTime = System.nanoTime() - startTime;
        double basicStepTime = (double) elapsedTime / countOfQuickSort;

        System.out.printf("Number of compares for sortA = %10d  , Time = %10d ns  , Basic Step = %10.1f ns \n", countOfQuickSort, elapsedTime, basicStepTime);

        //Selection Sort Result
        B = Arrays.copyOf(A, A.length);
        startTime = System.nanoTime();
        int countOfSelectionSort = sortB(B);
        elapsedTime = System.nanoTime() - startTime;
        basicStepTime = (double) elapsedTime / countOfQuickSort;

        System.out.printf("Number of compares for sortB = %10d  , Time = %10d ns  , Basic Step = %10.1f ns \n", countOfSelectionSort, elapsedTime, basicStepTime);

        //Insertion Sort Result
        B = Arrays.copyOf(A, A.length);
        startTime = System.nanoTime();
        int countOfInsertionSort = SortC(B);
        elapsedTime = System.nanoTime() - startTime;
        basicStepTime = (double) elapsedTime / countOfQuickSort;

        System.out.printf("Number of compares for sortC = %10d  , Time = %10d ns  , Basic Step = %10.1f ns \n", countOfInsertionSort, elapsedTime, basicStepTime);

        //Merge Sort Result
        B = Arrays.copyOf(A, A.length);
        startTime = System.nanoTime();
        int countOfMergeSort = SortD(B);
        elapsedTime = System.nanoTime() - startTime;
        basicStepTime = (double) elapsedTime / countOfQuickSort;

        System.out.printf("Number of compares for sortD = %10d  , Time = %10d ns  , Basic Step = %10.1f ns \n", countOfMergeSort, elapsedTime, basicStepTime);

        //Bubble Sort Result
        B = Arrays.copyOf(A, A.length);
        startTime = System.nanoTime();
        int countOfBubbleSort = sortE(B);
        elapsedTime = System.nanoTime() - startTime;
        basicStepTime = (double) elapsedTime / countOfQuickSort;

        System.out.printf("Number of compares for sortE = %10d  , Time = %10d ns  , Basic Step = %10.1f ns \n", countOfBubbleSort, elapsedTime, basicStepTime);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.printf("Lab#2 Sorting Algorithm Performance Analysis\n");
        System.out.println("============================================\n");
        
        displaySort(25); // calling private method with array size 25
        displaySort(250); // calling private method with array size 250
        displaySort(25000); // calling private method with array size 25000
    }

}
