import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[] array = {1, 9, -10, 7, -56, 45, 2, 13, 100};

        MyInterface operation = (int[] nums) -> {

            int sum = 0;
            int count = 0;

            for (int i = 0; i < nums.length; i++) {

                count = 0;
                for (int j = 1; j <= nums[i]; j++) {
                    if(nums[i] % j == 0) count++;
                }
                if(count == 2){sum += nums[i];}
            }
            System.out.println("Arraydagi Tub elementlar yig'indisi: " + sum);
        };


        operation.sumNums(array);


    }
}
