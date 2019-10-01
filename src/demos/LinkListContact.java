package demos;

/**
 * 有两个有序链表，将他们组成一个有序链表。
 * 递归算法
 * Created by huiwei.zhao on 2019/6/24.
 */
public class LinkListContact {

    public static void main(String[] args) {
        LinkNode node1 = new LinkNode(1);
        LinkNode node2 = new LinkNode(2);
        LinkNode node3 = new LinkNode(3);
        LinkNode node4 = new LinkNode(4);
        LinkNode node5 = new LinkNode(5);
        LinkNode node6 = new LinkNode(6);
        LinkNode node7 = new LinkNode(7);

        node1.next = node3;
        node3.next = node5;
        node5.next = node7;

        node2.next = node4;
        node4.next = node6;

        LinkNode node1_copy = new LinkNode();
        LinkNode node2_copy = new LinkNode();

        node1_copy = node1;
        node2_copy = node2;

        System.out.println("有序链表合并前：");
        System.out.println("链表1：");
        while (node1 != null) {
            System.out.println(node1.val);
            node1 = node1.next;
        }

        System.out.println("链表2：");
        while (node2 != null) {
            System.out.println(node2.val);
            node2 = node2.next;
        }

        LinkNode linkNode = mergeTwoLinkList(node1_copy, node2_copy);

        System.out.println("有序链表合并后：");

        while (linkNode != null) {
            System.out.println(linkNode.val);
            linkNode = linkNode.next;
        }

    }

    public static LinkNode mergeTwoLinkList(LinkNode node1, LinkNode node2) {
        if (node1 == null) {
            return node2;
        }
        if (node2 == null) {
            return node1;
        }

        LinkNode head = null;
        if (node1.val <= node2.val) {
            head = node1;
            head.next = mergeTwoLinkList(node1.next, node2);
        } else {
            head = node2;
            head.next = mergeTwoLinkList(node1, node2.next);
        }

        return head;
    }
}
