//Link to problem : https://leetcode.com/problems/remove-duplicates-from-sorted-list/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode currNode = head;
        while(currNode != null){
           ListNode current1 = currNode.next;
            while(current1 != null && current1.val == currNode.val){
                currNode.next = current1.next;
                current1 = current1.next;
            }
            currNode = current1;
        }
        return head;
    }
}
