import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Pen dasturiga hush kelibsiz");
        System.out.println("Ruchkaning dastlabki siyohining qiymati 100%.\nKatta harfga 6% siyoh, kichik harfga 3% siyoh ketadi. Probel hisoblanmaydi.");
        Scanner scanStr = new Scanner(System.in);
        Scanner scanInt = new Scanner(System.in);
        Pen ruchka = new Pen(100);


        while (true){
            System.out.println("1.Write\n2.Show Ink\n3.Fill Ink\n4.Exit\nChoose one option");
            int option = scanInt.nextInt();
            switch(option){
                case 1:{
                    System.out.print("Yozing: ");
                    String writestr = scanStr.nextLine();
                    ruchka.write(writestr);
                }break;

                case 2:{
                    System.out.println(ruchka.getInk());
                }break;

                case 3:{
                    System.out.println("Qancha miqdordagi siyohni qo'shmoqchisiz? (%da qiymatni kiriting) ");
                    double setInk1 = scanInt.nextDouble();
                    ruchka.setInk(setInk1);
                }break;


                default:{
                    if(option==4){
                        System.out.println("Good Bye");
                    }else
                    System.out.println("Wrong option");
                }break;
            }
           if(option == 4){break;}
        }





    }
}
