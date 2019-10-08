package demos;

import java.util.*;

/**
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 */
public class Solution {

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> lists = new Solution().threeSum(nums);

        Iterator<List<Integer>> iterator = lists.iterator();
        while (iterator.hasNext()) {
            List<Integer> next = iterator.next();

            for (int n :
                    next) {
                System.out.print(n + ",");
            }

            System.out.println("");
        }

        ListNode l1 =new ListNode(2);
        ListNode l2 = new ListNode(5);
        ListNode l3 = new ListNode(4);
        ListNode l4 = new ListNode(6);
        ListNode l5 = new ListNode(3);
        ListNode l6 =new ListNode(4);

        l1.next = l3;
        l3.next = l5;

        l2.next = l4;
        l4.next = l6;

        ListNode ret = new Solution().addTwoNumbers(l1, l2);

        while (ret != null) {
            System.out.print(ret.val);
            ret = ret.next;
        }
    }

    /**
     * 暴力法
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {

        ArrayList<List<Integer>> list = new ArrayList<>();


        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] == -nums[k]) {
                        ArrayList<Integer> sub = new ArrayList<>();
                        sub.add(nums[i]);
                        sub.add(nums[j]);
                        sub.add(nums[k]);
                        list.add(sub);
                    }
                }
            }
        }

        return list;
    }

    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * <p>
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     * <p>
     * 为了对运行时间复杂度进行优化，我们需要一种更有效的方法来检查数组中是否存在目标元素。如果存在，我们需要找出它的索引。保持数组中的每个元素与其索引相互对应的最好方法是什么？哈希表。
     * <p>
     * 两遍哈希法
     *
     * @param nums
     * @param target
     * @return
     * @throws IllegalAccessException
     */
    public int[] twoSum(int[] nums, int target) throws IllegalAccessException {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }

        throw new IllegalAccessException("Now two sum solution");
    }

    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * <p>
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     * <p>
     * 一遍哈希法
     *
     * @param nums
     * @param target
     * @return
     * @throws IllegalAccessException
     */
    public int[] twoSum2(int[] nums, int target) throws IllegalAccessException {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }

            map.put(nums[i], i);
        }

        throw new IllegalAccessException("No two sum Solution");
    }

    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * <p>
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * <p>
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l3 = new ListNode(0);
        ListNode p = l1, q = l2, curr = l3;

        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;

            int sum = x + y + carry;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;

            if (p != null) {
                p = p.next;
            }

            if (q != null) {
                q = q.next;
            }

        }

        if (carry > 0) {
            curr.next = new ListNode(carry);
        }

        return l3.next;
    }

    static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
