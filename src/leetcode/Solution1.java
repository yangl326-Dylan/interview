package leetcode;

class ListNode {
	     int val;
	     ListNode next;
	     ListNode(int x) {
	         val = x;
	          next = null;
	     }
	     public String toString()
	     {
	    	 if(next!=null)
	    		 return val + "," + next.toString();
	    	 else
	    		 return val +".";
	     }
}
public class Solution1 {
	/**
	 * Given an input string, reverse the string word by word.
	 * ������java��spilt()������������ʽ�ͺ����ף���Ҫע���ȵð��ַ���ͷ��β�Ŀհ�ȥ��
	 * @param s
	 * @return
	 */
	public String reverseWords(String s)
	{
		String regex = "\\s+";//����������ʽ����ʾ�ָ�����һ�������հ׷�
		s = s.trim();//ȥ����β�հ׷�
		String [] array = s.split(regex);
		StringBuilder sb = new StringBuilder();
		for(int i = array.length-1;i>0;i--)
		{
			sb.append(array[i] +" ");
		}
		sb.append(array[0]);
		return "\'"+sb.toString()+"\'";
	}
	/**
	 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
	 * ������ǽ�ջ��ջ
	 * @param tokens
	 * @return
	 */
	public int evalRPN(String[] tokens){
		 final int LENGTH = 100;
		 int[] stack = new int[LENGTH];
		 int top = -1;
		 int temp = 0;
		 for(int i=0;i<tokens.length;i++)
		 { 
			 if(tokens[i].length() == 1)
			 {
				 switch(tokens[i].charAt(0)) //���壺����ֱ����tokens[i].charAt(0) ��Ϊ���ֿ��Ը���'-1',��ʾ�����ķ��źͼ��Ż��ͻ
				 {
				 case '+':
					 temp = stack[top-1] + stack[top];
					 stack[top-1] = temp;
					 top--; break;
				 case '-':
					 temp = stack[top-1] - stack[top];
					 stack[top-1] = temp;
					 top--; break;
				 case '*':
					 temp = stack[top-1] * stack[top];
					 stack[top-1] = temp;
					 top--; break;
				 case '/':
					 temp = stack[top-1] / stack[top];
					 stack[top-1] = temp;
					 top--; break;
				 default:
					 stack[++top] = Integer.parseInt(tokens[i]);
				 }
			 }
			 else
				 stack[++top] = Integer.parseInt(tokens[i]);
		 } 
		 return stack[0]; 
	}
	/**
	 * �����
	 * @param head
	 * @return
	 */
	public ListNode sortList(ListNode head) {
        ListNode pointer = head;
        int len = 0;
        if(head == null || head.next == null)
        	return head;
        while(pointer!=null)
        {
        	len++;
        	pointer = pointer.next;
        }
    	return sort(head, len);
    }
	/**
	 * �����Ե����Ϲ鲢����
	 * @param head 
	 * @param len
	 * @return
	 */
	private ListNode sort(ListNode head,int len)
	{
		int width = 1;
		ListNode r = null;
		for(width = 1;width<=len;width = 2*width)
		{
			ListNode left = head;
			ListNode pLeft = null;
			ListNode pmid = left, mid=left.next, end = null, endNext = null;
			int cnt = 0;
			for(left = head;left!=null;left =endNext)
			{
				pmid = left;
				cnt = 0;
				while(++cnt<width && pmid.next!=null)
				{
					pmid = pmid.next;
				}
				mid = pmid.next;//if pmid.next == null ��û����������
				if(mid == null)
					break;
				end = mid;
				cnt = 0;
				while(++cnt<width && end.next!=null)
				{
					end = end.next;
				}
				endNext = end.next;
				ListNode l = bottomUpSort(pLeft,left,pmid,mid,end,endNext);
				if(end.next!=endNext)
					end = pmid;
				if(l!=null)
				{
					r = l;
					head = r;
				}
				pLeft = end;
			}
		}
		if(r == null)
			return head;
		return r;
	}
	/**
	 * bottomUpSort �������Ӧ�øĳ�inPlaceSort��ǡ��
	 * @param pLeft left��ǰһ��nodeָ�룬Ϊnull��ʾleftΪ����ͷ
	 * @param left ��ǰ��������ĵ�һ��ָ��
	 * @param pmid ��ǰ������������һ��ָ��
	 * @param mid ��ǰ��������ĵ�һ��ָ��
	 * @param end ��ǰ������������һ��ָ��
	 * @param endNext end��next Node
	 */
	private ListNode bottomUpSort(ListNode pLeft, ListNode left, ListNode pmid, ListNode mid, ListNode end, ListNode endNext)
	{
		ListNode p1 = left, pp1 = pLeft ;
		ListNode p2 = mid, pp2 = pmid ;
		ListNode t = null;
		//pp1 = (left.val <= mid.val) ? left: null;
		while(p1!=p2 && p2!=endNext )
		{
			while(p1!=pmid.next && p1.val<=p2.val )
			{
				pp1 = p1;
				p1 = p1.next;
			}
			if(p1 == mid)
				break;
			while(p2!= endNext && p2.val<=p1.val )
			{
				pp2 = p2;
				p2 = p2.next;
			}
			ListNode ln = exchange(pp1,p1,pp2,p2,pmid,mid);
			if(ln != null)
				t = ln;
			//end = pmid;
			mid = pmid.next;
		}
		end = pmid;
		if(pLeft == null)
			return t;
		return null;
	}
	/**
	 * ��С�Ĳ����Ƶ�ǰ��  ֮���Բ����Ƚ϶࣬��Ϊ���������ݵĲ���
	 * @param pp1 
	 * @param p1 
	 * @param pp2
	 * @param p2
	 * @param pmid
	 * @param mid
	 * @return
	 */
	private ListNode exchange( ListNode pp1,ListNode p1, ListNode pp2,ListNode p2, ListNode pmid, ListNode mid)
	{
		if(pp1 == null)
		{
			pp2.next = p1;
			pmid.next = p2;
			return mid;
		}
		pp1.next = mid;
		pp2.next = p1;
		pmid.next = p2;
		return null;
	}
	/**
	 * ����������㷨�Ƚϼ򵥣��ҿ�wiki�ϣ���Ҫ���������б��в���һ��Ԫ��ʱ���ǴӺ�ǰ������
	 * ��������ֻ�ܴ�ǰ��������������Ҳ��ôӺ�ǰ��
	 * Ҫע��ĵط������ҵ�λ��ʱ�����������Ԫ�ز���ľ���ϸ��
	 * @param head
	 * @return
	 */
	public ListNode insertionSortList(ListNode head){
		//������Ϊ�ջ���ֻ��һ���ڵ�����
		if(head == null || head.next == null)
			return head;
		
		ListNode newHead = head;//��¼�µ�����ͷ
		ListNode p1 = head, pp1= null, pointer,pPointer = head, temp = null;
		for( pointer = head.next;pointer!=null;pointer = temp)
		{
			pp1 = null;
			p1 = newHead;
			while(p1!= pointer && p1.val<=pointer.val)
			{
				pp1 = p1;
				p1 = p1.next;
			}
			temp = pointer.next;
			
			if(pp1 == null)
			{
				pointer.next = p1;
				newHead = pointer;
				pPointer.next = temp;
			}else if(p1 != pointer){
				pPointer.next = pointer.next;
				pp1.next = pointer;
				pointer.next = p1;
			}
			else
				pPointer = pPointer.next;
			
		}
		return newHead;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution1 s1 = new Solution1();
		//System.out.println(s1.reverseWords(" 1"));
		//String [] tokens = {"3","-4","+"};
		//System.out.println(s1.evalRPN(tokens));
		ListNode head = new ListNode(4);
		head.next = new ListNode(19);
		head.next.next = new ListNode(14);
		head.next.next.next = new ListNode(5);
		head.next.next.next.next = new ListNode(-3);
		head.next.next.next.next.next = new ListNode(1);
		head.next.next.next.next.next.next = new ListNode(8);
		head.next.next.next.next.next.next.next = new ListNode(11);
		//head.next.next.next.next.next.next.next.next = new ListNode(15);
		//head.next.next.next.next.next.next.next.next.next  = new ListNode(-3);
		//head.next.next.next.next.next.next.next.next.next.next = new ListNode(-54);
		//head.next.next.next.next.next.next.next.next.next.next.next = new ListNode(89);
		//4,19,14,5,-3,1,8,5,11,15
		System.out.println(head.toString());
		//ListNode r = s1.sortList(head);
		ListNode r = s1.insertionSortList(head);
		System.out.println(r.toString());
	}

}
