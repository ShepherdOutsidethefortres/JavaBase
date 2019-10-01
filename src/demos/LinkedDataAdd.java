package demos;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * <p>
 * 倒序相加，先补位，然后再进位
 * Created by huiweizhao on 2019/8/26.
 */
public class LinkedDataAdd {
    public static void main(String[] args) {
        LinkNode node1 = new LinkNode(2);
        LinkNode val4 = new LinkNode(4);
        LinkNode val3 = new LinkNode(3);
        LinkNode val7 = new LinkNode(7);

        node1.next = val4;
        val4.next = val3;
        val3.next = val7;

        LinkNode node2 = new LinkNode(5);
        LinkNode val6 = new LinkNode(6);
        LinkNode val24 = new LinkNode(4);

        node2.next = val6;
        val6.next = val24;


        LinkNode newNode = addTwoNumbers(node1, node2);

        while (newNode != null) {
            System.out.printf(newNode.val + " ");
            newNode = newNode.next;
        }
    }

    public static LinkNode addTwoNumbers(LinkNode node1, LinkNode node2) {
        LinkNode newLN = new LinkNode(0);
        LinkNode result = newLN;
        int carry = 0;

        while (node1 != null || node2 != null) {
            int val1 = (node1 != null) ? node1.val : 0;
            int val2 = (node2 != null) ? node2.val : 0;

            int sum = val1 + val2 + carry;
            //进位
            carry = sum / 10;
            //余数
            int remainder = sum % 10;
            newLN.next = new LinkNode(remainder);

            newLN = newLN.next;
            if (node1 != null) {
                node1 = node1.next;
            }
            if (node2 != null) {
                node2 = node2.next;
            }

        }

        if (carry > 0) {
            newLN.next = new LinkNode(carry);
        }

        return result.next;
    }
}
