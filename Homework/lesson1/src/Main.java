import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Son kiriting: ");
        long n = scan.nextLong();
       String num =  String.valueOf(n);
       String number = "";
        for (int i = num.length()-1; i >= 0; i--) {
            number = number + num.charAt(i);
        }

        String[] sArray = new String[21];

        for (int i = 0; i < number.length(); i = i + 3) {
            switch(Integer.parseInt(number.substring(i,i+1))){
                case 0: sArray[i]=" ";break;
                case 1: sArray[i]="bir ";break;
                case 2: sArray[i]="ikki ";break;
                case 3: sArray[i]="uch ";break;
                case 4: sArray[i]="to'rt ";break;
                case 5: sArray[i]="besh ";break;
                case 6: sArray[i]="olti ";break;
                case 7: sArray[i]="yetti ";break;
                case 8: sArray[i]="sakkiz ";break;
                case 9: sArray[i]="to'qqiz ";break;
            }

            if(i < number.length()-1){
            switch(Integer.parseInt(number.substring(i+1,i+2))){
                case 0: sArray[i+1]=" ";break;
                case 1: sArray[i+1]="o'n ";break;
                case 2: sArray[i+1]="yigirma ";break;
                case 3: sArray[i+1]="o'ttiz ";break;
                case 4: sArray[i+1]="qirq ";break;
                case 5: sArray[i+1]="ellik ";break;
                case 6: sArray[i+1]="oltmish ";break;
                case 7: sArray[i+1]="yetmish ";break;
                case 8: sArray[i+1]="sakson ";break;
                case 9: sArray[i+1]="to'qson ";break;
            }
            }

            if(i < number.length()-2){
            switch(Integer.parseInt(number.substring(i+2,i+3))){
                case 0: sArray[i+2]=" ";break;
                case 1: sArray[i+2]="bir yuz ";break;
                case 2: sArray[i+2]="ikki yuz ";break;
                case 3: sArray[i+2]="uch yuz ";break;
                case 4: sArray[i+2]="to'rt yuz ";break;
                case 5: sArray[i+2]="besh yuz ";break;
                case 6: sArray[i+2]="olti yuz ";break;
                case 7: sArray[i+2]="yetti yuz ";break;
                case 8: sArray[i+2]="sakkiz yuz ";break;
                case 9: sArray[i+2]="to'qqiz yuz ";break;
            }
            }

            if(i==3){sArray[i-1] = "Ming " + sArray[i-1];}
            if(i==6){sArray[i-1] = "Million " + sArray[i-1];}
            if(i==9){sArray[i-1] = "Milliard " + sArray[i-1];}
            if(i==12){sArray[i-1] = "Trillion " + sArray[i-1];}



        }

        for (int i = sArray.length-1; i>=0 ; i--) {
            if(sArray[i] != null) {
                System.out.print(sArray[i]);
            }
        }




}
}
