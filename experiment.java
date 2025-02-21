package experiment;
import java.util.Random;

public class experiment {

    // Method to generate an array filled with random integers
    public static int[] generateRandomArray(int size) {
        Random rand = new Random(); // Random number generator
        int[] arr = new int[size]; // Create an array of the specified size
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(1000000); // Fill the array with random integers between 0 and 999,999
        }
        return arr; // Return the generated array
    }

    // Insertion Sort Algorithm
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) { // Start from the second element
            int key = arr[i]; // Current element to be inserted
            int j = i - 1; // Compare with elements to the left
            // Shift elements that are greater than the key to the right
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j]; // Move the larger element one position to the right
                j = j - 1; // Move to the next element on the left
            }
            arr[j + 1] = key; // Place the key in its correct position
        }
    }

    // Merge Sort Algorithm
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) { // Check if there is more than one element
            int mid = (left + right) / 2; // Find the midpoint
            mergeSort(arr, left, mid); // Recursively sort the left half
            mergeSort(arr, mid + 1, right); // Recursively sort the right half
            merge(arr, left, mid, right); // Merge the sorted halves
        }
    }

    // Merging two sorted subarrays
    public static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1; // Size of the left subarray
        int n2 = right - mid; // Size of the right subarray
        int[] L = new int[n1]; // Temporary array for left subarray
        int[] R = new int[n2]; // Temporary array for right subarray

        // Copy data to temporary arrays L[] and R[]
        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i]; // Copy left subarray
        for (int i = 0; i < n2; i++)
            R[i] = arr[mid + 1 + i]; // Copy right subarray

        int i = 0, j = 0, k = left; // Initialize indices for L[], R[], and arr[]
        // Merge the temporary arrays back into arr[]
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) // Compare elements of L and R
                arr[k++] = L[i++]; // If L[i] is smaller, add it to arr
            else
                arr[k++] = R[j++]; // Otherwise, add R[j] to arr
        }
        // Copy any remaining elements of L[]
        while (i < n1)
            arr[k++] = L[i++];
        // Copy any remaining elements of R[]
        while (j < n2)
            arr[k++] = R[j++];
    }

    // Quick Sort Algorithm (using a random pivot)
    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) { // Check if there is more than one element
            int pivot = partition(arr, left, right); // Partition the array
            quickSort(arr, left, pivot - 1); // Recursively sort the left subarray
            quickSort(arr, pivot + 1, right); // Recursively sort the right subarray
        }
    }

    // Partitioning the array around a pivot
    public static int partition(int[] arr, int left, int right) {
        // Select a random pivot
        int pivotIndex = left + (int) (Math.random() * (right - left + 1));
        int pivot = arr[pivotIndex]; // Store the pivot value
        swap(arr, pivotIndex, right); // Move pivot to the end
        int i = left - 1; // Pointer for the smaller element
        // Rearrange the array based on the pivot
        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) { // If the current element is less than or equal to the pivot
                i++; // Increment the pointer for the smaller element
                swap(arr, i, j); // Swap the current element with the smaller element
            }
        }
        swap(arr, i + 1, right); // Move the pivot to its correct position
        return i + 1; // Return the index of the pivot
    }

    // Swap two elements in the array
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i]; // Temporarily store one element
        arr[i] = arr[j]; // Swap the elements
        arr[j] = temp; // Complete the swap
    }

    // Method to measure the execution time of sorting algorithms
    public static void measureSortingTime(int[] arr, String algorithm) {
        long startTime, endTime; // Variables to store the start and end time

        int[] arrCopy = arr.clone(); // Create a copy of the array to sort (to keep the original intact)

        // Measure execution time based on the sorting algorithm
        switch (algorithm) {
            case "InsertionSort":
                startTime = System.currentTimeMillis(); // Start timing
                insertionSort(arrCopy); // Sort the array using Insertion Sort
                endTime = System.currentTimeMillis(); // End timing
                System.out.println("Insertion Sort took " + (endTime - startTime) + " ms"); // Print time taken
                break;

            case "MergeSort":
                startTime = System.currentTimeMillis(); // Start timing
                mergeSort(arrCopy, 0, arrCopy.length - 1); // Sort the array using Merge Sort
                endTime = System.currentTimeMillis(); // End timing
                System.out.println("Merge Sort took " + (endTime - startTime) + " ms"); // Print time taken
                break;

            case "QuickSort":
                startTime = System.currentTimeMillis(); // Start timing
                quickSort(arrCopy, 0, arrCopy.length - 1); // Sort the array using Quick Sort
                endTime = System.currentTimeMillis(); // End timing
                System.out.println("Quick Sort took " + (endTime - startTime) + " ms"); // Print time taken
                break;
        }
    }

    // Main method to test the sorting algorithms
    public static void main(String[] args) {
        // Array sizes for testing
        int[] sizes = {10000, 20000, 40000, 80000, 160000, 320000};

        // Test each algorithm on random arrays of increasing sizes
        for (int size : sizes) {
            System.out.println("\nArray Size: " + size);
            int[] randomArray = generateRandomArray(size); // Generate a random array

            // Measure the sorting times for each algorithm
            measureSortingTime(randomArray, "InsertionSort");
            measureSortingTime(randomArray, "MergeSort");
            measureSortingTime(randomArray, "QuickSort");
        }
    }
}
