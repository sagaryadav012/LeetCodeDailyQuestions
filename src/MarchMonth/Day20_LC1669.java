package MarchMonth;

import java.util.Arrays;
import java.util.List;

public class Day20_LC1669 {
    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(0,1,2,3,4,5,6);
        List<Integer> list2 = Arrays.asList(1000000,1000001,1000002,1000003,1000004);
        int a = 2;
        int b = 5;

        ListNode list1Node = Day12_LC1171.construct(list1);
        ListNode list2Node = Day12_LC1171.construct(list2);

        ListNode res = mergeInBetween(list1Node, a, b, list2Node);
        while (res != null){
            System.out.print(res.val+" ");
            res = res.next;
        }
    }
    public static ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {

        // head of 2nd list
        ListNode startList2 = list2;
        // Find end of 2nd list
        ListNode endList2 = list2;
        while(endList2.next != null){
            endList2 = endList2.next;
        }

        // Find before node of removal node in list1
        int before = a-1;
        ListNode beforeNode = list1;
        for(int i = 0; i < before; i++){
            beforeNode = beforeNode.next;
        }

        // find next node of removal node in list1
        int next = b-a+1;
        ListNode nextNode = beforeNode;
        for(int i = 0; i <= next; i++){
            nextNode = nextNode.next;
        }

        // Connect nodes
        beforeNode.next = startList2;
        endList2.next = nextNode;

        return list1;
    }
}
