package uz.pdp.exadel;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

public class Test2 {
    public static void main(String[] args) {
        File file1 = new File("src/main/resources/input.txt");
        File file2 = new File("output1.txt");
        File file3 = new File("output2.txt");
        String str = read(file1);
        writeToFile(file2, removeMaxChar(str));
        writeToFile(file3, sortTask(str));
    }

    public static void writeToFile(File file, String str) {
        try (FileWriter fileWriter = new FileWriter(file, false)) {
            fileWriter.write((str));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String read(File file) {
        StringBuilder result = new StringBuilder();
        try (FileReader fileReader = new FileReader(file)) {
            int c;
            while ((c = fileReader.read()) != -1) {
                result.append((char) c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public static String removeMaxChar(String output) {
        String str = output;
        int[] alpha = new int[26];
        for (char c : output.toCharArray()) {
            alpha[c - 'a']++;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < 26; i++) {
            if (alpha[i] > max) {
                max = alpha[i];
            }
        }
        for (int i = 0; i < 26; i++) {
            if (alpha[i] == max) {
                output = output.replaceAll(String.valueOf((char) (i + 'a')), "");
            }
        }
        if (output.equals("")) return str;
        return output;
    }

    public static String sortTask(String str) {
        int[] alpha = new int[26];
        for (char c : str.toCharArray()) {
            alpha[c - 'a']++;
        }

        int[][] arr = new int[26][2];
        for (int i = 0; i < 26; i++) {
            arr[i][0] = i;
            arr[i][1] = alpha[i];
        }

        Arrays.sort(arr, (a, b) -> {
            if (a[1] == b[1]) return a[0] - b[0];
            else return b[1] - a[1];
        });
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (arr[i][1] != 0) {
                stringBuilder.append((char) (arr[i][0] + 'a')).append(":").append(arr[i][1]).append(" ");
            }
        }
        return stringBuilder.toString();
    }

}
