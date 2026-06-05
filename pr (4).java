import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class pr {

    public static void bubbleSort(int[] array, boolean ascending) {

        int n = array.length;

        for (int i = 0; i < n - 1; i++) {

            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {

                boolean needSwap = ascending
                        ? array[j] > array[j + 1]
                        : array[j] < array[j + 1];

                if (needSwap) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }

    public static void insertionSort(int[] array, boolean ascending) {

        for (int i = 1; i < array.length; i++) {

            int key = array[i];
            int j = i - 1;

            while (j >= 0 &&
                    (ascending ? array[j] > key : array[j] < key)) {

                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = key;
        }
    }

    public static void selectionSort(int[] array, boolean ascending) {

        for (int i = 0; i < array.length - 1; i++) {

            int selectedIndex = i;

            for (int j = i + 1; j < array.length; j++) {

                if (ascending) {
                    if (array[j] < array[selectedIndex]) {
                        selectedIndex = j;
                    }
                } else {
                    if (array[j] > array[selectedIndex]) {
                        selectedIndex = j;
                    }
                }
            }

            int temp = array[i];
            array[i] = array[selectedIndex];
            array[selectedIndex] = temp;
        }
    }

    public static void mergeSort(int[] array,
                                 int left,
                                 int right,
                                 boolean ascending) {

        if (left < right) {

            int middle = (left + right) / 2;

            mergeSort(array, left, middle, ascending);
            mergeSort(array, middle + 1, right, ascending);

            merge(array, left, middle, right, ascending);
        }
    }

    private static void merge(int[] array,
                              int left,
                              int middle,
                              int right,
                              boolean ascending) {

        int n1 = middle - left + 1;
        int n2 = right - middle;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; i++) {
            leftArray[i] = array[left + i];
        }

        for (int j = 0; j < n2; j++) {
            rightArray[j] = array[middle + 1 + j];
        }

        int i = 0;
        int j = 0;
        int k = left;

        while (i < n1 && j < n2) {

            boolean condition = ascending
                    ? leftArray[i] <= rightArray[j]
                    : leftArray[i] >= rightArray[j];

            if (condition) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }
        }

        while (i < n1) {
            array[k++] = leftArray[i++];
        }

        while (j < n2) {
            array[k++] = rightArray[j++];
        }
    }

    public static void countingSort(int[] array, boolean ascending) {

        int min = array[0];
        int max = array[0];

        for (int value : array) {
            if (value < min) min = value;
            if (value > max) max = value;
        }

        int[] count = new int[max - min + 1];

        for (int value : array) {
            count[value - min]++;
        }

        int index = 0;

        if (ascending) {

            for (int i = 0; i < count.length; i++) {

                while (count[i] > 0) {
                    array[index++] = i + min;
                    count[i]--;
                }
            }

        } else {

            for (int i = count.length - 1; i >= 0; i--) {

                while (count[i] > 0) {
                    array[index++] = i + min;
                    count[i]--;
                }
            }
        }
    }

    public static void quickSort(int[] array,
                                 int low,
                                 int high,
                                 boolean ascending) {

        if (low < high) {

            int pivotIndex =
                    partition(array, low, high, ascending);

            quickSort(array, low, pivotIndex - 1, ascending);
            quickSort(array, pivotIndex + 1, high, ascending);
        }
    }

    private static int partition(int[] array,
                                 int low,
                                 int high,
                                 boolean ascending) {

        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {

            boolean condition = ascending
                    ? array[j] <= pivot
                    : array[j] >= pivot;

            if (condition) {

                i++;

                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Введіть розмір масиву: ");
        int size = scanner.nextInt();

        System.out.print("Введіть мінімальне значення: ");
        int min = scanner.nextInt();

        System.out.print("Введіть максимальне значення: ");
        int max = scanner.nextInt();

        System.out.print("Сортування за зростанням? (true/false): ");
        boolean ascending = scanner.nextBoolean();

        int[] originalArray = new int[size];

        for (int i = 0; i < size; i++) {
            originalArray[i] =
                    random.nextInt(max - min + 1) + min;
        }

        int[] bubbleArray = Arrays.copyOf(originalArray, size);
        int[] insertionArray = Arrays.copyOf(originalArray, size);
        int[] selectionArray = Arrays.copyOf(originalArray, size);
        int[] mergeArray = Arrays.copyOf(originalArray, size);
        int[] countingArray = Arrays.copyOf(originalArray, size);
        int[] quickArray = Arrays.copyOf(originalArray, size);

        System.out.println("\nПочатковий масив:");
        System.out.println(Arrays.toString(originalArray));

        sortAndPrint("Bubble Sort", bubbleArray,
                arr -> bubbleSort(arr, ascending));

        sortAndPrint("Insertion Sort", insertionArray,
                arr -> insertionSort(arr, ascending));

        sortAndPrint("Selection Sort", selectionArray,
                arr -> selectionSort(arr, ascending));

        sortAndPrint("Merge Sort", mergeArray,
                arr -> mergeSort(arr, 0, arr.length - 1, ascending));

        sortAndPrint("Counting Sort", countingArray,
                arr -> countingSort(arr, ascending));

        sortAndPrint("Quick Sort", quickArray,
                arr -> quickSort(arr, 0, arr.length - 1, ascending));

        scanner.close();
    }

    interface SortAlgorithm {
        void sort(int[] array);
    }

    public static void sortAndPrint(String name,
                                    int[] array,
                                    SortAlgorithm algorithm) {

        LocalTime start = LocalTime.now();

        algorithm.sort(array);

        LocalTime end = LocalTime.now();

        Duration duration = Duration.between(start, end);

        System.out.println("\n===== " + name.toUpperCase() + " =====");
        System.out.println(Arrays.toString(array));

        System.out.println(
                "Час виконання: "
                        + duration.toMillis()
                        + " мс ("
                        + duration.toNanos()
                        + " нс)"
        );
    }
}