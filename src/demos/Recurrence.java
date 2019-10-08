package demos;

import org.testng.annotations.Test;

public class Recurrence {

    @Test
    public void testPrintReverse(){
        char[] str={'a','c','d','e','x'};

//        printReverse(str);
//        reverseString(str);

        Solution.ListNode l1 = new Solution.ListNode(1);
        Solution.ListNode l2 = new Solution.ListNode(2);
        l1.next=l2;

        swapPairs(l1);

        while(l1!=null){
            System.out.println(l1.val);
            l1=l1.next;
        }

    }

    private static void printReverse(char[] str){
        helper(0,str);
    }

    /**
     * 以相反的顺序打印字符串
     */
    private static void helper(int index,char[] str){
        if (str==null||index>=str.length){
            return;
        }

        helper(index+1,str);

        System.out.print(str[index]);
    }

    /**
     * 将输入的字符串反转过来，不给另外的数组分配额外的空间，必须原地修改输入数组
     * O(1)空间要求！
     * @param s
     */
    private static void reverseString(char[] s){
        //法一
        for (int i = 0; i < s.length / 2; i++) {
            char temp=s[i];
            s[i]=s[s.length-i-1];
            s[s.length-i-1]=temp;
        }

        //法二
        int i=0,j=s.length-1;
        while(i<j){
            char c=s[i];
            s[i]=s[j];
            s[j]=c;

            i++;
            j--;
        }
    }

    public Solution.ListNode swapPairs(Solution.ListNode head){
        if (head==null||head.next==null){
            return head;
        }

        Solution.ListNode first=head;
        Solution.ListNode second=head.next;

        Solution.ListNode next=second.next;

        second.next=first;

        first.next=swapPairs(next);

        return second;
    }
}
