import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[] array = {1, 9, -10, 7, -56, 45, 2, 12, 100};

        MyInterface operation = (int[] nums) -> {
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                if(nums[i] % 2 == 1){
                    sum += nums[i];
                }
            }
            System.out.println("Arraydagi Toq elementlar yig'indisi: " + sum);
        };


        operation.sumNums(array);


    }
}
