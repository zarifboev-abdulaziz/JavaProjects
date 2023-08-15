public class Savol4 {
    public static void main(String[] args) {

        int[] nums1 = {1, 1, 5, 5, -10, -8, 7};
        int[] nums2 = {1, -1, 5, -5, -10, 8, 7};
        int[] nums3 = {12, -85, -1, 3};


        System.out.println(findAbsolyuteDifferent(nums3));

    }

    public static int findAbsolyuteDifferent(int[] nums){
        int sumPositive = 0, sumNegative = 0;

        for (int i = 0; i < nums.length; i++) {
            if(nums[i] > 0){
                sumPositive += nums[i];
            }else{
                sumNegative += nums[i];
            }

        }

        return Math.abs(sumPositive) - Math.abs(sumNegative);
    }

}
