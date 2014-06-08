package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution16 {
	/**
	 * Given a linked list, remove the nth node from the end of list and return its head.
	 * @param head
	 * @param n
	 * @return
	 */
	 public ListNode removeNthFromEnd(ListNode head, int n) {
		 if(n<=0)
			 return head;
	     ListNode t1 = null;
	     ListNode t2 = head;
	     int i = 0;
	     for(i=0;t2.next!=null && i<n;i++)
	    	 t2 = t2.next;
	     //in this case, n is equal to the length of the list, so remove the first one
	     if(i<n)
	     {
	    	 t1 = head.next;
	    	 head.next = null;
	    	 return t1;
	     }
	     t1 = head;
	     while(t2.next!=null)
	     {
	    	 t2 = t2.next;
	    	 t1 = t1.next;
	     }
	     t1.next = t1.next.next;
	     return head;
	 }
	 /**
	  * Solution : by stack
	  * Given a string containing just the characters
	  * '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
	  * @param s
	  * @return
	  */
	 public boolean isValid(String s) {
	     LinkedList<Character> stack = new LinkedList<Character>();
	     for(int i=0;i<s.length();i++){
	    	 char top = ' ';
	    	 if(stack.size()>0)
	    		 top = stack.peek();
	    	 char t = s.charAt(i);
	    	 boolean flag = false; // if the top of the stack is matched with the current parentheses
	    	 switch(top){
	    	 case '(' : 
	    		 if(t == ')')
	    			 flag = true;
	    		 break;
	    	 case '{' :
	    		 if(t == '}')
	    			 flag = true;
	    		 break;
	    	 case '[' :
	    		 if(t == ']')
	    			 flag = true;
	    		 break;
	    		 
	    	 }
	    	 if(flag == true)
	    		 stack.pop();
	    	 else
	    		 stack.push(t);
	     }
	     if(stack.size()>0)
	    	 return false;
		 return true;
	 }
	 /**
	  * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
	  * @param n
	  * @return
	  */
	 public List<String> generateParenthesis(int n) {

	        List<List<String>> fn = new ArrayList<List<String>>();
	        ArrayList<String> f0 = new ArrayList<String>();
	        f0.add("");
	        ArrayList<String> f1 = new ArrayList<String>();
	        f1.add("()");
	        fn.add(f0);
	        fn.add(f1);
	        int i = 2;
	        while(i<=n){
	        	 ArrayList<String> fi = new ArrayList<String>();
	        	for(int k=0;k<=i-1;k++){
	        		StringBuilder sb = null;
	        		for(int x=0;x<fn.get(k).size();x++){
	        			sb = new StringBuilder();
	        			sb.append("(");
	        			sb.append(fn.get(k).get(x));
	        			sb.append(")");
	        			for(int y = 0;y<fn.get(i-k-1).size(); y++){
	        				fi.add(sb.toString()+fn.get(i-k-1).get(y));
	        			}
	        		}
	        	}
	        	fn.add(fi);
	        	i++;
	        }
	        return fn.get(n);
	 }
	 public ListNode mergeKLists(List<ListNode> lists) {
		   if(lists==null || lists.size()<=0)
			   return null;
		   if(lists.size() == 1)
	           return lists.get(0);
		   //check the null cases
		   for(int i=0;i<lists.size();i++){
			   if(lists.get(i) == null)
			   {
				   lists.remove(i);
				   i--;
			   }
		   }
		   if(lists.size() == 0)
			   return null;
		   if(lists.size() == 1)
	           return lists.get(0);
		   
	       ListNode head = null;
	       ListNode point = null;
	       int minIndex = 0;
	       int minValue = 0;
	       while(true){
	    	  if(lists.size() == 1){
	    		  point.next = lists.get(0);
	    		  return head;
	    	  }
	    	  minValue = lists.get(0).val;
	    	  minIndex = 0;
	    	  //find the minimum node from the k Nodes
	    	  for(int i=1;i<lists.size();i++){
	    		  if(lists.get(i).val<minValue){
	    			  minValue = lists.get(i).val;
	    			  minIndex = i;
	    		  }
	    	  }
	    	  //add the current minimum node to the global sorted list
	    	  if(head == null){
    			  head = lists.get(minIndex);
    			  point = head;
    		  }else {
    			  point.next = lists.get(minIndex);
    			  point = point.next;
    		  }
	    	  //update the ListNode with the minimum node
	    	  if(lists.get(minIndex).next == null){  
	    		  lists.remove(minIndex);	  
	    	  }else{
	    		  ListNode temp = lists.get(minIndex);
	    		  lists.remove(minIndex);
	    		  lists.add(temp.next);
	    	  }  
	       }
	 }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution16 s = new Solution16();
		
		System.out.println(s.generateParenthesis(3));
		/*ListNode list = new ListNode(1);
		list.next = new ListNode(2);
		list.next.next = new ListNode(3);
		s.removeNthFromEnd(list, 2);*/
		//list.next
	}

}
