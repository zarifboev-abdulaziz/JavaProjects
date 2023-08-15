package uz.pdp.exadel;

import java.io.*;


public class Test1 {

    public static void main(String[] args) {
        try {
            String[] StringArr1 = read("src/main/resources/input1.txt").trim().split(",");
            String[] StringArr2 = read("src/main/resources/input2.txt").trim().split("\\s+");
            int x = Integer.parseInt(StringArr1[0]);
            int y = Integer.parseInt(StringArr1[1]);
            String result = "";
            for (String string : StringArr2) {
                int curr = Integer.parseInt(string);
                if (curr % x == 0 && curr % y == 0) {
                    result += " HotDog";
                } else if (curr % x == 0) {
                    result += " Hot";
                } else if (curr % y == 0) {
                    result += " Dog";
                } else
                    result += " " + String.valueOf(curr);
            }
            write(result.trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean write(String textToWrite) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("output.txt", false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(textToWrite);
            bufferedWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String read(String path) throws Exception {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(path));
            String res = bufferedReader.readLine();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bufferedReader.close();
        }
        return null;
    }


}
