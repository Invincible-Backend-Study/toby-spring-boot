import java.util.Arrays;
import java.util.OptionalInt;

public class Main {
    public static void main(String[] args) {
        prinMaxAndMin();
        bubbleSort();
        serch();

    }

    private static void serch() {
        int[] nums= {3, 1, 6, 9, 0, 8, 7, 2};

        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == 7){
                System.out.println(i);
            }
        }
    }

    private static void bubbleSort() {
        int[] nums = {64, 34, 25, 12, 22, 11, 90};
        for (int i = 0; i < nums.length; i++) {
            if ( i != nums.length-1 && nums[i] > nums[i + 1]) {
                swap(nums, i);
            }
            for (int j = 0; j < nums.length; j++) {
                if ( j != nums.length-1 && nums[j] > nums[j + 1]) {
                    swap(nums, j);
                }
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    private static void swap(int[] nums, int i) {
        int temp = nums[i];
        nums[i] = nums[i + 1];
        nums[i + 1] = temp;
    }

    private static void prinMaxAndMin() {
        int[] arr = {3, 5, 2, 7, 1, 9};
        OptionalInt max = Arrays.stream(arr).max();
        OptionalInt min = Arrays.stream(arr).min();
        System.out.println(max.getAsInt());
        System.out.println(min.getAsInt());
    }
}