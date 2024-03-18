package MarchMonth;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day12_LC1171 {
    public static void main(String[] args) {
       List<Integer> list = Arrays.asList(1, 2, 3, 4, -10, 1, 3, -3);
       ListNode head = construct(list);
      // ListNode res = removeZeroSumSublists(head);
       ListNode res = removeZeroSumSublists1(head);
       while (res != null){
           System.out.print(res.val+" ");
           res = res.next;
       }
    }
    public static ListNode removeZeroSumSublists(ListNode head) {
        int sum = 0;
        ListNode temp = head;
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        HashMap<Integer, ListNode> map = new HashMap<>();

        while(temp != null){
            sum += temp.val;
            if(sum == 0){
                curr = dummy;
                dummy.next = null;
                map = new HashMap<>();
                temp = temp.next;
                continue;
            }

            if(map.containsKey(sum)){
                ListNode node = map.get(sum);
                ListNode temp2 = node.next;
                int curVal = sum + temp2.val;
                while(temp2 != null){
                    curVal += temp2.val;
                    if(curVal == sum) break;
                    map.remove(curVal);
                    temp2 = temp2.next;
                }
                node.next = null;
                curr = node;
                temp = temp.next;
                continue;
            }

            map.put(sum, temp);
            curr.next = temp;
            curr = curr.next;
            temp = temp.next;
        }

        return dummy.next;
    }
    public static ListNode removeZeroSumSublists1(ListNode head) {
        ListNode startnode=new ListNode(0);
        startnode.next=head;
        int prefixsum=0;
        HashMap<Integer,ListNode> map=new HashMap<>();
        map.put(prefixsum, startnode);
        while(head!=null) {
            prefixsum+=head.val;
            map.put(prefixsum, head);
            head=head.next;
        }
        head=startnode;
        prefixsum=0;
        while(head!=null) {
            prefixsum+=head.val;
            head.next=map.get(prefixsum).next;
            head=head.next;
        }
        return startnode.next;
    }
    public static ListNode construct(List<Integer> list){
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        for(int val : list){
            ListNode node = new ListNode(val);
            curr.next = node;
            curr = curr.next;
        }
        return dummy.next;
    }

}
class ListNode{
    int val;
    ListNode next;
    ListNode(){ }
    ListNode(int val){
        this.val = val;
    }
    ListNode(int val, ListNode next){
        this.val = val;
        this.next = next;
    }
}

/*
Test Case :
[1,2,-3,3,1]
[1,2,3,-3,4]
[1,2,3,-3,-2]
[1,2,4,1,2,-7,4]
[2,2,-2,1,-1,-1]
[1, 2, 3, 4, -10, 1, 3, -3]
 */