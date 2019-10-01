package demos;

/**
 * Created by huiwei.zhao on 2019/7/2.
 */
public class MyStackTest {
    public static void main(String[] args){
        SelfStack stack = new SelfStack(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        System.out.println(stack.peek());
        stack.pop();
        System.out.println(stack.peek());
    }
}
