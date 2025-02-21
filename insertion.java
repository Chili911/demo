package experiment;

import java.util.Random;

public class insertionsort {

    // Insertion Sort Algorithm
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) { // Start at the second element
            int key = arr[i]; // Store the current element
            int j = i - 1; // Compare with previous elements
            while (j >= 0 && arr[j] > key) { // Shift elements larger than key to the right
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key; // Insert the key in its correct position
        }
    }

    // Main method to test Insertion Sort with different array sizes
    public static void main(String[] args) {
        int[] sizes = {10_000, 40_000, 80_000, 160_000, 320_000}; // Array sizes

        for (int size : sizes) {
            int[] arr = generateRandomArray(size); // Generate random array
            System.out.println("Unsorted array of size " + size + ":");
            // printArray(arr); // Uncomment to print the unsorted array

            long startTime = System.nanoTime(); // Start timing
            insertionSort(arr); // Sort the array
            long endTime = System.nanoTime(); // End timing

            System.out.println("Sorted array of size " + size + " sorted in " + (endTime - startTime) + " nanoseconds.");
            // printArray(arr); // Uncomment to print the sorted array
        }
    }

    // Utility function to print an array
    public static void printArray(int[] arr) {
        for (int j : arr) {
            System.out.print(j + " ");
        }
        System.out.println();
    }

    // Function to generate an array filled with random integers
    public static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(100_000); // Random integers from 0 to 99,999
        }
        return arr;
    }
}
