package com.example.biz.practice;

/**
 * Function:
 *
 * @author xingche
 * @date 2023/3/24
 */
public class PracticeTest {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args) {
        //yuesefu(new int[]{5, 2, 3, 4, 8, 6}, 4);
        ListNode head = new ListNode(1);
        ListNode h2 = new ListNode(2);
        ListNode h3 = new ListNode(1);
        /*ListNode h3 = new ListNode(0);
        ListNode h4 = new ListNode(-1);
        ListNode h5 = new ListNode(4);*/
        head.next = h2;
        h2.next = h3;
        /*h3.next = h4;
        h4.next = h5;*/
        System.out.print(hasCycle(head));
    }

    public static boolean hasCycle(ListNode head) {
        // node.next.next = node?
        while(head != null && head.next != null){
            ListNode node = head.next.next;
            if(node != null && head.val == node.val) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    /**
     * 约瑟夫问题简单版
     * n个人围成圆圈，每次数到M个杀掉，下一个从头开始数；直到只剩一个人
     */
    private static void yuesefu(int[] array, int m) {
        boolean[] flagArray = new boolean[array.length];
        int countFalse = array.length;
        int num = 0;
        int i = 0;
        while (countFalse > 1) {
            if (!flagArray[i]) {
                num++;
            }
            if (num == m) {
                countFalse--;
                System.out.println(array[i]);
                flagArray[i] = true;
                num = 0;
            }
            i = (i + 1) % array.length;
        }
    }
}
