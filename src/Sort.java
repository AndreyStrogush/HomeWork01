import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//1.5 Каждую из сортировок испытать на массиве величиной в 1000000 и подсчитать время выполнения
public class Sort{

    //создаем массив, в данном случае заранее заданного размера
    public static int[] create() {
        int n = 1000000;
        int array[] = new int[n];
        for (int i = 0; i < n; i++)
            array[i] = (int) (Math.random() * n);

        return array;
    }

    //измеряем время сортировок
    // принимаем номер сортировки по списку  и выполняем
    // время выводим  в консоль
    public static void measure() {
        int[] numbers = Sort.create();              // создаем массив
        int low = 0, high = numbers.length - 1;     //значения для некоторых сортировок - 1й и последний элементы
        Scanner sc = new Scanner(System.in);        //сканнер для выбора метода сортировки
        Sort.printArray(numbers,100);           //просто посмотреть как сработал Рандом

        System.out.println("Выберите метод сортировки: \n" +
                "\t 1. Быстрая сортировка \n" +
                "\t 2. Сортировка слиянием \n" +
                "\t 3. Пирамидальная сортировка \n" +
                "\t 4. Пузырьковая сортировка \n" +
                "\t 5. Сортировка вставками \n" +
                "\t 6. Сортировка выбором \n" +
                "\t 7. Блочная сортировка \n" +
                "\t 8. Поразрядная сортировка \n");
        int n = sc.nextInt();

        long startTime = System.currentTimeMillis();            // фиксируем начало выполнения
        switch (n) {
            //      1. Быстрая сортировка
            case 1:
                System.out.println("Выполняется быстрая сортировка: ");         //165 ms
                Sort.quickSort(numbers, low, high);
                break;
            //      2. Сортировка слиянием
            case 2:
                System.out.println("Выполняется  сортировка слиянием: ");       //312 ms
                Sort.mergeSort(numbers, low, high);
                break;
            //      3. Пирамидальная сортировка
            case 3:
                System.out.println("Выполняется пирамидальная сортировка: ");   //234 ms
                Sort.heapSort(numbers);
                break;
            //      4. Пузырьковая сортировка
            case 4:
                System.out.println("Выполняется пузырьковая сортировка: ");     // ???
                Sort.bubbleSort(numbers);
                break;
            //      5. Сортировка вставками
            case 5:
                System.out.println("Выполняется  сортировка вставками: ");      //298 ms
                Sort.shellSort(numbers);
                break;
            //      6. Сортировка выбором
            case 6:
                System.out.println("Выполняется сортировка выбором: ");         //64 ms
                Sort.sort(numbers, numbers.length);
                break;
            //      7. Блочная сортировка
            case 7:
                System.out.println("Выполняется блочная сортировка: ");         //31 ms
                Sort.bucketSort(numbers);
                break;
            //      8. Поразрядная сортировка
            case 8:
                System.out.println("Выполняется поразрядная сортировка: ");     //607 ms
                Sort.radixSort(numbers);
                break;
            default:
                System.out.println("Неправильный ввод");
        }

        long endTime = System.currentTimeMillis();                  //окончание процесса

        System.out.println("Полное время выполнения составило: "
                + (endTime - startTime) + " ms");                   //выводим потраченное время в миллисекундах
    }

    //собственно алгоритмы сортировок

    //      1. Быстрая сортировка
    public static void quickSort(int[] numbers, int low, int high) {
        if (low < high) {
            int dp = partition(numbers, low, high);
            quickSort(numbers, low, dp - 1);
            quickSort(numbers, dp + 1, high);
        }
    } //end quickSort

    //      2. Сортировка слиянием
    public static void mergeSort(int[] numbers, int low, int high) {
        if (low < high) { // list contains at least 2 elements
            int mid = (low + high) / 2;
            mergeSort(numbers, low, mid); // recursion : sort first half
            mergeSort(numbers, mid + 1, high); // recursion : sort second half
            merge(numbers, low, mid, high); // merge both sorted halves
        }
    } // end margeSort

    //      3. Пирамидальная сортировка
    public static void heapSort(int[] a) {
        for (int i = a.length / 2 - 1; i >= 0; i--)
            // convert the array to a heap
            shiftDown(a, i, a.length);
        for (int i = a.length - 1; i > 0; i--) {
            swap(a, 0, i); /* deleteMax */
            shiftDown(a, 0, i);
        }
    } // end heapSort

    //      4. Пузырьковая сортировка
    public static void bubbleSort(int[] numbers) {

        int n = numbers.length;
        int temp;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                if (numbers[j - 1] > numbers[j]) {
                    //swap elements
                    temp = numbers[j - 1];
                    numbers[j - 1] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }
    }//end bubbleSort

    //      5. Сортировка вставками
    public static void shellSort(int[] numbers) {
        int j;
        for (int gap = numbers.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < numbers.length; i++) {
                int temp = numbers[i];
                for (j = i; j >= gap && numbers[j - gap] > temp; j -= gap) {
                    numbers[j] = numbers[j - gap];
                }
                numbers[j] = temp;
            }
        }
    }//end shellSort

    //      6. Сортировка выбором
    public static void sort(int[] input, int n) {

        int min = 0, max = 0;

        for (int i = 1; i < n; i++) {

            if (input[i] > max)
                max = input[i];

            if (input[i] < min)
                min = input[i];
        }

        int range = max - min + 1;

        int[] count = new int[range];

        //counts frequencies for each element

        for (int i = 0; i < n; i++)

            count[input[i] - min]++;

        // getting positions in final array

        for (int i = 1; i < range; i++)

            count[i] += count[i - 1];


        //copy to output array, preserving order of inputs with equal keys
        int j = 0;

        for (int i = 0; i < range; i++)

            while (j < count[i])

                input[j++] = i + min;
    }//end sort

    //      7. Блочная сортировка
    public static int[] bucketSort(int[] arr) {
        int i, j;
        int[] bucket = new int[arr.length + 1];
        Arrays.fill(bucket, 0);

        for (i = 0; i < arr.length; i++) {
            bucket[arr[i]]++;
        }

        int k = 0;
        for (i = 0; i < bucket.length; i++) {
            for (j = 0; j < bucket[i]; j++) {
                arr[k++] = i;
            }
        }
        return arr;
    }//end bucketSort

    //      8. Поразрядная сортировка
    public static void radixSort(int[] input) {

        List<Integer>[] buckets = new ArrayList[10];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<Integer>();
        }

        // sort
        boolean flag = false;
        int tmp = -1, divisor = 1;
        while (!flag) {
            flag = true;
            // split input between lists
            for (Integer i : input) {
                tmp = i / divisor;
                buckets[tmp % 10].add(i);
                if (flag && tmp > 0) {
                    flag = false;
                }
            }
            // empty lists into input array
            int a = 0;
            for (int b = 0; b < 10; b++) {
                for (Integer i : buckets[b]) {
                    input[a++] = i;
                }
                buckets[b].clear();
            }
            // move to next digit
            divisor *= 10;
        }
    }//end radixSort

    // дополнительные методы

    // partition numbers[low] to numbers[high] using numbers[low] as the pivot
    private static int partition(int[] numbers, int low, int high) {
        int pivot = numbers[low];
        int i = low;
        for (int j = low + 1; j <= high; j++)
            if (numbers[j] < pivot) {
                ++i;
                swap(numbers, i, j);
            }
        //end for
        swap(numbers, low, i);
        return i;
    }//end partition

    /*
     * Merge sorted array of elements from low to mid and mid+1
     * low is the left most position of the subset of elements
     * high is the right most position of the subset of elements
     */
    private static void merge(int[] subset, int low, int mid, int high) {

        int n = high - low + 1;
        int[] Temp = new int[n];

        int i = low, j = mid + 1;
        int k = 0;

        while (i <= mid || j <= high) {
            if (i > mid)
                Temp[k++] = subset[j++];
            else if (j > high)
                Temp[k++] = subset[i++];
            else if (subset[i] < subset[j])
                Temp[k++] = subset[i++];
            else
                Temp[k++] = subset[j++];
        }
        for (j = 0; j < n; j++)
            subset[low + j] = Temp[j];
    } // end merge

    // Exchange list[i] and list[j] values
    // (swap numbers)
    public static void swap(int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

    //heapSort
    private static void shiftDown(int[] a, int i, int n) {
        int child;
        int tmp;

        for (tmp = a[i]; leftChild(i) < n; i = child) {
            child = leftChild(i);
            if (child != n - 1 && (a[child] < a[child + 1]))
                child++;
            if (tmp < a[child])
                a[i] = a[child];
            else
                break;
        }
        a[i] = tmp;
    }//end shiftDown

    //heapSort
    private static int leftChild(int i) {
        return 2 * i + 1;
    }
    public static void printArray(int[] numbers, int n){
        System.out.println("Сформирован массив со следующими значениями: ");
        for (int i = 0; i<n; i++){
            System.out.print(numbers[i] + "; ");
        }
        if(n<numbers.length)
        System.out.println("...");
    }
}
