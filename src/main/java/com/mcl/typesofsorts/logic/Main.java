package com.mcl.typesofsorts.logic;

import com.mcl.typesofsorts.HelloApplication;
import com.mcl.typesofsorts.logic.search.Binary;
import com.mcl.typesofsorts.logic.search.Linear;
import com.mcl.typesofsorts.logic.sorts.Bubble;
import com.mcl.typesofsorts.logic.sorts.Insertion;
import com.mcl.typesofsorts.logic.sorts.Merge;
import com.mcl.typesofsorts.logic.sorts.Quick;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    private String inputArrayString;
    private static String[] inputArray;

    private String splitChar;
    private Boolean showGraph;
    private String searchOrSort;
    public static boolean flagResult;
    //if user chose the search
    private String findNumber;

    //constructor
    public Main(String inputArray, String splitChar, Boolean showGraph1, String searchOrSort, String findNumber) {
        this.inputArrayString = inputArray;
        showGraph = showGraph1;
        this.searchOrSort = searchOrSort;
        this.findNumber = findNumber;

        //set the split, default space
        if (splitChar.equals(""))
            this.splitChar = " ";
        else this.splitChar = splitChar;
    }

    //start the program
    public void begin() throws IOException {
        //error check
        flagResult = Errors.checkErrors(searchOrSort, inputArrayString, findNumber);
        if (flagResult){
            //set fields in result window
            String findResult = add();

            SetResultFields resultFields = new SetResultFields(inputArrayString, findResult);

            HelloApplication.openResultWindow();
            //graph view
            if (showGraph)
                graph();
        }
    }

    //gets a ready-made list from the desired method
    private String add() throws IOException {
        //search number
        int target;

        //from an array of strings to an array of numbers
        inputArray = inputArrayString.split(" ");
        int[] array = Arrays.stream(inputArray)
                .mapToInt(Integer::parseInt)
                .toArray();
        //correct display of the sorted list in the final window
        if (searchOrSort.equals("Binary Search")) {
            inputArrayString = Arrays.toString(Arrays.stream(array).sorted().toArray());
            inputArrayString = inputArrayString.substring(1, inputArrayString.length() - 1);
        }
        //start algorithm
        String result;
        switch (searchOrSort) {
            case ("Linear Search"):
                target = Integer.parseInt(findNumber);
                Timer.timeStart();
                result = Linear.linearSearch(array, target);
                Timer.timeEnd();
                break;
            case ("Binary Search"):
                target = Integer.parseInt(findNumber);
                result = Binary.binarySearch(array, target);
                break;
            case ("Bubble Sort"):
                Timer.timeStart();
                result = Bubble.bubbleSort(array);
                Timer.timeEnd();
                break;
            case ("Insertion Sort"):
                Timer.timeStart();
                result = Insertion.insertionSort(array);
                Timer.timeEnd();
                break;
            case "Merge Sort":
                Timer.timeStart();
                result = Merge.mergeSort(array);
                Timer.timeEnd();
                break;
            case ("Quick Sort"):
                Timer.timeStart();
                result = Quick.quickSort(array);
                Timer.timeEnd();
                break;
            default:
                result = "ERROR";
        }
        return result;
    }

    //create a chart
    private void graph() throws IOException {
        switch (searchOrSort) {
            case ("Linear Search"):
                Linear linear = new Linear();
                linear.start();
                break;
            case ("Binary Search"):
                Binary binary = new Binary();
                binary.start();
                break;
            case ("Bubble Sort"):
                Bubble bubble = new Bubble();
                bubble.start();
                break;
            case ("Insertion Sort"):
                Insertion insertion = new Insertion();
                insertion.start();
                break;
            case "Merge Sort":
                Merge merge = new Merge();
                merge.start();
                break;
            case ("Quick Sort"):
                Quick quick = new Quick();
                quick.start();
                break;
            default:
                break;
        }
    }
}
