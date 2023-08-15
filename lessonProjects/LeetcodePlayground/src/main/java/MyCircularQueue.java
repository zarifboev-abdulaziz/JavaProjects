class MyCircularQueue {
    private int[] nums;
    private int size;
    private int front;
    private int back;

    public MyCircularQueue(int k) {
        nums = new int[k];
    }

    public boolean enQueue(int value) {
        nums[front++] = value;
        size++;
        return true;
    }

    public boolean deQueue() {
        nums[back++] = 0;
        size--;
        return true;
    }

    public int Front() {
        return nums[front];
    }

    public int Rear() {
        return nums[back];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == nums.length;
    }

}