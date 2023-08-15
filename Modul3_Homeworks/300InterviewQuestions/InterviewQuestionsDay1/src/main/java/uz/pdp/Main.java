package uz.pdp;

import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTrueFalse;
import uz.pdp.model.Animal;
import uz.pdp.model.Dog;

import java.io.*;
import java.security.PermissionCollection;
import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {


    public static void main(String[] args){
//        int[] nums = {10, 40, 8, 10, 20, 24, 28, 32, 1};
//
//        quickSort(nums,0, nums.length-1);
//        System.out.println(Arrays.toString(nums));

        Animal animal = new Animal("monkey");

//        String animal  = new String("monkey");
//        String animal1 = new String("monkey");

        System.out.println(animal.hashCode());

    }



    public static void quickSort(int[] nums, int lowIndex, int highIndex) {

        if (lowIndex >= highIndex){
            return;
        }

        int pivot = nums[highIndex];
        int leftPointer = lowIndex;
        int rightPointer = highIndex;


        while (leftPointer < rightPointer){
            while (nums[leftPointer] <= pivot && leftPointer < rightPointer){
                leftPointer++;
            }
            while (nums[rightPointer] >= pivot && leftPointer < rightPointer){
                rightPointer--;
            }
            swap(nums, leftPointer, rightPointer);
        }

        swap(nums, leftPointer, highIndex);
        quickSort(nums, lowIndex, leftPointer - 1);
        quickSort(nums, leftPointer + 1, highIndex);
    }

    private static void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }







    private static void doSelectionSort() {
        int[] nums = {10, 40, 8, 12, 16, 20};

        int maxValue = Integer.MIN_VALUE;
        int maxValueAtIndex = 0;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (nums[j] > maxValue){
                    maxValue = nums[j];
                    maxValueAtIndex = j;
                }
            }

            int temp = nums[i];
            nums[i] = nums[maxValueAtIndex];
            nums[maxValueAtIndex] = temp;

            maxValue = Integer.MIN_VALUE;
        }

        System.out.println(Arrays.toString(nums));
    }

}








