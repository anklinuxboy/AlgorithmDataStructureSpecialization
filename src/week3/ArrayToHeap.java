package week3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by ankit on 7/17/17.
 */
public class ArrayToHeap {

    private static int swaps = 0;
    private List<Integer> swapPairs = new ArrayList<>();

    public static int getSwaps() {
        return swaps;
    }

    public List<Integer> getSwapPairs() {
        return swapPairs;
    }

    public void buildMinHeap(List<Integer> array) {
        int heapSize = array.size() / 2 - 1;
        for (int i = heapSize; i >= 0; i--) {
            minHeapify(array, array.size()-1, i);
        }
    }

    private void swap(List<Integer> array, int index1, int index2) {
        int temp = array.get(index1);
        swapPairs.add(index2);
        swapPairs.add(index1);
        array.set(index1, array.get(index2));
        array.set(index2, temp);
    }

    public void minHeapify(List<Integer> array, int min, int index) {
        int smallest;
        int leftChildIndex = getLeftChildIndex(index);
        int rightChildIndex = getRightChildIndex(index);

        if (leftChildIndex <= min && array.get(leftChildIndex) < array.get(index)) {
            smallest = leftChildIndex;
        } else {
            smallest = index;
        }

        if (rightChildIndex <= min && array.get(rightChildIndex) < array.get(smallest)) {
            smallest = rightChildIndex;
        }

        if (smallest != index) {
            swap(array, smallest, index);
            swaps += 1;
            minHeapify(array, min, smallest);
        }
    }

    public int getParentIndex(int index) {
        return index / 2;
    }

    // Extra 1 because of 0 index
    public int getLeftChildIndex(int index) {
        return index * 2 + 1;
    }

    public int getRightChildIndex(int index) {
        return index * 2 + 2;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        List<Integer> array = new ArrayList<>(count);

        for (int i = 0; i < count; i++) {
            array.add(sc.nextInt());
        }

        ArrayToHeap arrayToHeap = new ArrayToHeap();
        arrayToHeap.buildMinHeap(array);
        System.out.println(ArrayToHeap.getSwaps());
        List<Integer> swapPairs = arrayToHeap.getSwapPairs();
        for (int i = 0; i <swapPairs.size(); i += 2) {
            System.out.println(swapPairs.get(i) + " " + swapPairs.get(i+1));
        }

        sc.close();
    }
}
