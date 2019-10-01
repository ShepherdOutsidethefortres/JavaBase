package demos;

/**
 * Created by huiwei.zhao on 2019/7/2.
 */
public class SelfStack {
    private int maxSize;
    private int[] arr;
    private int top = -1;

    public SelfStack(int size) {
        this.maxSize = size;
        arr=new int[maxSize];
    }

    /**
     * 入栈
     * @param value
     */
    public void push(int value) {
        arr[++top] = value;
    }

    /**
     * 取栈顶元素
     * @return
     */
    public int peek() {
        return arr[top];
    }

    /**
     * 弹出数据
     * @return
     */
    public int pop() {
        return arr[top--];
    }

    public boolean isEmpty() {
        return top == -1;
    }


}
