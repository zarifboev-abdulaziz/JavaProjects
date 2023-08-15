import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;

public class MyPriorityQueue {
    int[] nums = new int[100];
    TreeSet<Integer> set = new TreeSet<>();
    int size;
    int back;

    public void add(int value) {
        if (isFull()){
            throw new RuntimeException();
        }
        int i;
        for( i = size - 1; i >= 0; i--){
            if (nums[i] > value){
                nums[i + 1] = nums[i];
            } else {
                break;
            }
        }
        nums[i+1] = value;
        size++;
    }




    public void remove(){
        for (int i = size - 1; i > 0; i--) {
            nums[i] = nums[i - 1];
        }
    }

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i = 0; i < nums.length; i++) {
            stringBuilder.append(nums[i]).append(", ");
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private boolean isFull() {
        return size == nums.length;
    }

    public int poll() {
        Integer first = set.first();
        set.remove(first);
        return first;
    }

    public boolean isEmpty() {
        return size==0;
    }


}
