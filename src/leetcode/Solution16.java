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
	 /**
		 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
		 * merge 2 sorted list. If we merge every adjacent 2 lists per iteration, we needs log2(k) iterations,
		 *  where merge 2 sorted list costs O(n) per iteration. Thus the run time is O(log(k)n).
		 * @param lists
		 * @return
		 * @author Administrator
		 * @date 2014-6-9
		 */
		public ListNode mergeKLists(List<ListNode> lists) {
	        if(lists.isEmpty() || lists.size()<=0)
	        	return null;
	        if(lists.size() == 1)
	        	return lists.get(0);
	        
	        int iter = (int)(Math.log(lists.size())/Math.log(2));
	        iter = iter < Math.log(lists.size())/Math.log(2) ? iter+1:iter;
	        
	        for(int i=0;i<iter;i++){
	        	for(int j = 0; j<lists.size(); j += Math.pow(2, i+1)){
	        		 int offset = j+(int)Math.pow(2,i);
	        		 ListNode temp = merger2Lists(lists.get(j), offset>=lists.size()? null:lists.get(offset));
	        		 lists.set(j, temp);
	        	}
	        }
	        return lists.get(0);
	    }
		
		private ListNode merger2Lists(ListNode a, ListNode b){
			if(b == null )
				return a;
			if(a == null )
				return b;
			ListNode head = null;
			ListNode point = null;
			ListNode t1 = a;
			ListNode t2 = b;
			while(t1!=null && t2!=null){
				if(head == null)
				{
					head = t1.val<=t2.val? t1: t2;
					//point = head;
				}
				while(t1!=null && t2!=null && t1.val <= t2.val){
					if(point != null)
					{
						point.next = t1;
						point = point.next;
					}else 
						point = t1;
					
					t1 = t1.next;
				}
				while(t2!=null && t1!=null && t2.val < t1.val){
					if(point != null)
					{
						point.next = t2;
						point = point.next;
					}else
						point = t2;
					
					t2 = t2.next;
				}
			}
			
			if(t1!=null){
				point.next = t1;
			}else if(t2!=null){
				point.next = t2;
			}
			return head;
		}
		
		 public ListNode swapPairs(ListNode head) {
	          if(head == null || head.next == null)
		    	   return head;
		       ListNode t1 = head;
		       ListNode t2 = null;
		       ListNode newHead = t1.next;
		       ListNode last = null;
		       while(t1!=null && t1.next!=null){
		    	   t2 = t1.next;
		    	   t1.next = t1.next.next;
		    	   t2.next = t1;
		    	  
		    	   if(last !=null){
		    	       last.next = t2;
		    	   }
		    	   last = t1;
		    	    t1 = t1.next;
		       }
		       return newHead;
	    }
		 
		 public int divide(int dividend, int divisor) {
		      long[] rets = new long[32]; // keep all the temps
				int ret = 0;
				long temp = divisor>0 ? divisor : Math.abs(divisor);
				long absDividend = Math.abs((long)dividend);
				long absDivisor = Math.abs((long)divisor);
				boolean flag = true;
				if( dividend<0 && divisor>0 || divisor<0 &&dividend>0)
					flag = false;
				//special case 1
				if(divisor == 0)
					return -1;
				//special case 2
				if(temp == 1)
					return flag == false ? 0-Math.abs(dividend):Math.abs(dividend);
				//special case 3
				if(dividend == 0)
					return 0;
				//special case 4
				if(absDividend == absDivisor)
					return flag == true ? 1 : -1;
				int i = 0;
				ret = 0;
				rets[0] = 0;
				while(temp > 0 && temp<=absDividend){
					ret = (ret == 0 ? 1 : ret+ret);
					rets[++i] = temp;
					temp += temp;
				}
				temp = rets[i]; //recover it
				for(int j=i-1;j>=1;j--)
				{
					if(temp+rets[j]<=absDividend)
					{
						ret+= (int)Math.pow(2, j-1);
						temp += rets[j];
					}
				}
				return flag==false ? 0-ret:ret;
		    }	
		 
		 public String strStr(String haystack, String needle) {
		       
		        if(haystack == null || haystack.length()<needle.length() || needle == null )
		        	return null;
		        if(needle.length() == 0 )
		        	return haystack;
		        if(haystack.length() == needle.length())
		        {	for(int i=needle.length()-1;i>=0;i--)
		        		if(haystack.charAt(i) != needle.charAt(i))
		        			return null;
		        	return haystack;
		        }
		        char[] ch = haystack.toCharArray();
		        char[] cn = needle.toCharArray();
		        boolean[][] memo = new boolean[cn.length][ch.length];
		        for(int i=0;i<ch.length;i++)
		        {
		        	memo[0][i]= (cn[0] == ch[i] ? true : false);
		        	if(cn.length == 1 && memo[0][i])
		        		return haystack.substring(i-cn.length+1);
		        }
		        for(int i=1;i<cn.length;i++)
		        	memo[i][0] = false;
		        
		        for(int i=1;i<cn.length;i++){
		        	for(int j=1;j<ch.length;j++){
		        		memo[i][j] = cn[i] == ch[j] && memo[i-1][j-1]? true : false;
		        		if(i==cn.length-1 && memo[i][j] == true)
		        			return haystack.substring(j-cn.length+1);
		        	}
		        }
		        return null; 
		    }
		 public int removeElement(int[] A, int elem) {
	          if(A == null || A.length == 0)
	        	return 0;
	        int i = A.length;//from the end to the beginning
	        int j = 0;
	        for(j=0;j<i;j++){
	        	if(A[j] == elem){
	        		i--;
	        		//find the one that is not the instance of elem
	        		while(i>=0 && A[i]==elem)
	        			i--;
	        		if(j<i)
	        		{
	        			A[j] = A[i];
	        			A[i] = elem;
	        		}
	        	}
	        }
	        return j<i+1? j:i+1;
	    }
		 public int removeDuplicates(int[] A) {
		        if(A == null || A.length<=1)
					return A!=null?A.length:0;
				int i=0;
				for(int j=1;j<A.length;j++){
					if(A[j] == A[i])
						continue;
					else{
						i++;
						if(i!=j)
							A[i] = A[j];
					}
				}
				return i+1;
		    }
		 /**
		  * Solution : reverse a list with K nodes(a group), I achieve it by log2(K) (ceil). 
		  * in every iteration, I reverse two subGroup, after all iterations, the K nodes are totally reversed.
		  * Example :
		  * 1->2->3->4
		  * iter1 : 2->1->4->3
		  * iter2 : 4->3->2->1
		  * @param head
		  * @param k
		  * @return
		  * @author Administrator
		  * @date 2014-6-11
		  */
		  public ListNode reverseKGroup(ListNode head, int k) {
		      if(head == null || head.next == null || k == 1)
		                return head;
		            ListNode newHead = null;
		            ListNode ts = head; // the beginning node of each group 
		            ListNode te = ts; // the end node of each group
		            int iter = (int)(Math.log(k)/Math.log(2)); 
		            iter = iter < Math.log(k)/Math.log(2)? iter + 1 : iter;

		            ListNode eLast = null; //the last node of the last group
		            while(te!=null && te.next!=null){
		                int i=1;
		                //determine the start and end of the some group
		                while(i++<k){
		                    te = te.next;
		                    if(te == null)
		                        return newHead==null? head : newHead;
		                }

		                //te is the first node of the next group
		                te = te.next;

		                for(int j=0;j<iter;j++){
		                    int size = (int)Math.pow(2, j+1);
		                    ListNode last = null;//the last node
		                    for(int m=0;m<(k%size==0?k/size:k/size+1);m++){
		                        ListNode s1 = null; //the first node of the first subgroup
		                        ListNode e1 = null; //the end node of the first subgroup
		                        ListNode s2 = null; //the first node of the second subgroup
		                        ListNode e2 = null; //the end node of the second subgroup
		                        if(last == null)
		                            s1 = ts;
		                        else
		                            s1 = last.next;
		                        e1 = s1;
		                        int n=1;
		                        while(e1.next!=te && n++<size/2)
		                            e1 = e1.next;
		                        if(e1.next == te)
		                            break;
		                        s2 = e1.next;
		                        e2 = s2;
		                        n=1;
		                        while(e2.next!= te && n++<size/2)
		                            e2 = e2.next;

		                        if(last!=null)
		                            last.next = s2;
		                        else
		                            ts = s2;
		                        last = e1;
		                        //swap the two subgroups
		                        ListNode temp = e2.next;
		                        e2.next = s1;
		                        e1.next = temp;

		                    }
		                    if(j==iter-1 && newHead == null)
		                        newHead = ts;
		                    else  if(j==iter-1 && eLast!=null)
		                        eLast.next = ts;
		                    if(j==iter-1)
		                        eLast = last;
		                }
		                if(eLast != null)
		                    eLast.next = te;
		                ts = te;
		            }
		            return newHead;
		}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution16 s = new Solution16();
		
		//System.out.println(s.generateParenthesis(3));
		ListNode list = new ListNode(1);
		list.next = new ListNode(2);
		list.next.next = new ListNode(3);
		list.next.next.next = new ListNode(4);
		list.next.next.next.next = new ListNode(5);
		list.next.next.next.next.next = new ListNode(6);
		System.out.println(s.reverseKGroup(list, 3));
		//s.removeNthFromEnd(list, 2);*/
		//list.next
	}

}
