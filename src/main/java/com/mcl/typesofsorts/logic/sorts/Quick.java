package com.mcl.typesofsorts.logic.sorts;

import com.mcl.typesofsorts.Visualization.View;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.Arrays;

public class Quick extends View {
    Timeline timeline = new Timeline();

    //sort the input array
    public static String quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
        return Arrays.toString(array);
    }

    private static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    //visualization
    @Override
    public void start() {
        super.start();
        quickSortView(0, arraySize - 1);
    }


    private void quickSortView(int low, int high) {
        if (low < high) {
            int pivotIndex = partition(low, high);

            KeyFrame keyFrame = new KeyFrame(Duration.millis(speedChart + 1000), event -> {
                quickSortView(low, pivotIndex - 1);
                quickSortView(pivotIndex + 1, high);
            });

            timeline = new Timeline(keyFrame);
            timeline.play();
        }
    }

    private int partition(int low, int high) {
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                swapElements(i, j);
            }
        }

        swapElements(i + 1, high);

        return i + 1;
    }

    private void swapElements(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;

        double tempHeight = rectangles[i].getHeight();
        rectangles[i].setHeight(rectangles[j].getHeight());
        rectangles[j].setHeight(tempHeight);
    }
}