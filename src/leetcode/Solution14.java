package leetcode;

import java.util.Arrays;
import java.util.HashMap;

class valIndex implements Comparable<valIndex>{
	int val;
	int index;
	public valIndex(int v, int i){
		val = v;
		index = i;
	}
	public int compareTo(valIndex vi){
		return val<vi.val? -1 : (val == vi.val? 0 : 1);
	}
}
public class Solution14 {
	/**
	 * Given a sorted linked list, delete all duplicates such that each element appear only once.
	 * @param head
	 * @return
	 */
	public ListNode deleteDuplicates(ListNode head){
		if(head == null || head.next == null)
			return head;
		ListNode dupB = head;
		ListNode temp = head.next;
		while(temp!=null){
			if(temp.val  == dupB.val){
				temp = temp.next;
			}else{
				dupB.next = temp;
				dupB = temp;
				temp = temp.next;
			}
		}
		dupB.next = null;
		return head;
	}
	/**
	 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
	 * @param head
	 * @return
	 */
	public ListNode deleteDuplicates2(ListNode head) {
		if(head == null || head.next == null)
			return head;
		ListNode pre  =  null;
		ListNode dupB = head;
		ListNode headN = null;
		int cnt = 1;
		ListNode temp = head.next;
		while(temp != null){
			if(temp.val == dupB.val)
			{
				temp = temp.next;
				cnt++;
			}
			else{
				if(cnt == 1)
				{
					if(pre == null)
					{
						pre = dupB;
						headN = pre;
					}
					else
					{
						pre.next = dupB;
						pre = dupB;
					}
				}
				dupB = temp;
				temp = temp.next;
				cnt = 1;
			}
		}
		//��һ���ֵĴ������Ҫ��
		if(pre!=null && cnt == 1)
			pre.next = dupB;
		else if(pre!=null)
			pre.next = null;
		else if(pre == null && cnt == 1)
			return dupB;
		
		return headN;
	}
	
	public boolean exist(char[][] board, String word){
		if(word == null || word.length() <= 0)
			return true;
		int wid = board.length;
		int len = board[0].length;
		//use to mark whether [i,j] has been visited
		HashMap<Integer, Integer> visited;
		for(int i=0;i<wid;i++){
			for(int j =0;j<len;j++){
				if(board[i][j] == word.charAt(0)){
					visited =  new HashMap<Integer,Integer>();
					visited.put(i, j);
					if(dfs(i,j,1,word,board, visited,wid,len))
						return true;
				}
			}
		}
		return false;
	}
	
	private boolean dfs(int x, int y, int index, String word, char[][] board,
			HashMap<Integer,Integer> visited,int wid, int len){
		int[] xdelta = {-1,0,1,0};//up, left, down , right
		int[] ydelta = {0,1,0,-1};
		
		if(index>=word.length())
			return true;
		
		for(int i=0;i<4;i++){
			int xn = x + xdelta[i];
			int yn = y + ydelta[i];
			if(xn>=0 && xn< wid && yn>=0 && yn < len && !(visited.containsKey(xn)&&visited.get(xn).equals(yn)) && board[xn][yn] == word.charAt(index)){
				
				HashMap<Integer,Integer> vis = new HashMap<Integer,Integer>();
				vis.putAll(visited);
				vis.put(xn, yn);
				//recursively dfs
				if(dfs(xn,yn, index+1, word, board, vis, wid, len))
					return true;
			}
		}
		
		return false;
	}
	/**
	 * ͨ������ʱ�临�Ӷ�ΪO(nlogn)������Ҫ����ռ临�Ӷ�O(n)
	 * accept
	 * @param numbers
	 * @param target
	 * @return
	 */
	public int[] twoSum(int[] numbers, int target){
		valIndex[] vis = new valIndex[numbers.length];
		for(int i=0;i<numbers.length;i++){
			vis[i] = new valIndex(numbers[i],i+1);
		}
		int[] r = new int[2];// for storing result
		Arrays.sort(vis);
		//Arrays.sort
		int i=0;
		int j=numbers.length-1;
		while(i<j){
			if(vis[i].val + vis[j].val == target){
				r[0] = vis[i].index < vis[j].index ? vis[i].index: vis[j].index;
				r[1] = vis[i].index > vis[j].index ? vis[i].index: vis[j].index;
				break;
			}
			if(vis[i].val + vis[j].val < target )
				i++;
			else
				j--;
		}
		return r;
	}
	/**
	 * �����㷨��ʱ�临�Ӷ�ΪO(n^2) TLE
	 * 
	 * @param numbers
	 * @param target
	 * @return
	 */
	public int[] twoSum2(int[] numbers, int target){
		int[] r = new int[2];
		for(int i=0;i<numbers.length;i++){
			for(int j=i+1;j<numbers.length;j++){
				if(numbers[i] + numbers[j] == target)
				{
					r[0] = i+1;
					r[1] = j+1;
					break;
				}
				
			}
		}
		return r;
	}
	/**
	 * ���㷨̫���ˣ���������
	 * ����ռ任ʱ���ֵ
	 * @param numbers
	 * @param target
	 * @return
	 */
	public int[] twoSum3(int[] numbers, int target){
		HashMap<Integer, Integer> need = new HashMap<Integer,Integer>();
		int[] result = new int[2];
		for(int i=0;i<numbers.length;i++){
			if(need.containsKey(numbers[i])){
				int index = need.get(numbers[i]);
				result[0] = index+1;
				result[1] = i+1;
			}else
				need.put(target-numbers[i], i);
		}
		return result;
	}
	/**
	 *  here are two sorted arrays A and B of size m and n respectively. 
	 * Find the median of the two sorted arrays. 
	 * The overall run time complexity should be O(log (m+n)).
	 * @param A
	 * @param B
	 * @return
	 */
	public double findMedianSortedArrays(int A[], int B[]) {
		if(A.length<=B.length)
			return subRecur(A,0,A.length-1, B, 0, B.length-1);
		else
			return subRecur(B,0,B.length-1, A, 0, A.length-1);
	}
	/**
	 * A is smaller than B
	 * @param A
	 * @param start
	 * @param end
	 * @param B
	 * @param start2
	 * @param end2
	 * @return
	 */
	private double subRecur(int A[], int start, int end, int B[], int start2, int end2){
		int n = end2-start2+1;// the larger length
		//when A has no element
		if(start>end)
		{
			if(n%2 == 0){
				return 1.0*(B[start2+(end2-start2-1)/2] + B[start2+(end2-start2+1)/2])/2;
			}else
				return 1.0*B[start2+(end2-start2+1)/2];
		}
		//when the smaller array has only one element
		if(start == end){
			//B has only one element case
			if(n == 1)
				return 1.0*(A[start] + B[start2])/2;
			//otherwise
			if(A[start] < B[start2])
				return subRecur(A, start+1, end, B, start2,end2-1);
			else if(A[start] > B[end2] )
				return subRecur(A,start+1,end, B, start2+1, end2);
			else{
				if(n%2 == 0){
					if(A[start]<B[start2+(end2-start2-1)/2])
						return 1.0*B[start2+(end2-start2-1)/2];
					if(A[start]>B[start2+(end2-start2+1)/2])
						return B[start2+(end2-start2+1)/2];
					return A[start];
				}else{
					if(A[start]< B[start2+(end2-start2-1)/2])
						return 1.0*(B[start2+(end2-start2-1)/2] + B[start2+(end2-start2+1)/2])/2;
					if(A[start]> B[start2+(end2-start2+1)/2  +1])
						return 1.0*(B[start2+(end2-start2+1)/2+1] + B[start2+(end2-start2+1)/2])/2;
					return 1.0*(A[start]+B[start2+(end2-start2+1)/2])/2;
				}
			}
		}
		
		int na = end-start+1;
		double am = na%2==0? (1.0*(A[start + na/2 -1] + A[start + na/2])/2) : 1.0 * A[start+na/2];
		double bm = n%2==0? (1.0*(B[start2 + n/2 -1] + B[start2 + n/2])/2) : 1.0 * B[start2+n/2];
		int lefta = A[start + na/2 -1];
		int righta = A[start+na/2]; // median
		int leftb = B[start2 + n/2-1];
		int rightb = B[start2 + n/2]; // median
		//many special cases need to be considered
		if(na%2 == 1 && n%2 == 0){
			if(righta > leftb && righta < rightb)
				return righta;
		}else if(n%2 == 1 && na%2 == 0){
			if(rightb > lefta && rightb < righta)
				return rightb;
		}if(na%2 == 0 && n%2 == 0){
			if(lefta<leftb && righta>rightb)
				return bm;
			if(lefta>leftb && righta<rightb)
				return am;
		}
		if(am<bm)
			return subRecur(A, start+na/2,end, B, start2, end2-na/2);
		if(am>bm)
			return subRecur(A, start, end-na/2, B, start2+na/2, end2);
		//when a's median equals to b's median
		return am;
	}
	/**
	 * Given a string, find the length of the longest substring without repeating characters. 
	 * For example, the longest substring without repeating letters for ��abcabcbb�� is ��abc��, 
	 * which the length is 3. For ��bbbbb�� the longest substring is ��b��, with the length of 1.
	 * @param s
	 * @return
	 */
	public int lengthOfLongestSubString(String s){
		if(s == null || s.length() <= 0)
			return 0;
		char[] c = s.toCharArray();
		int[] a = new int[c.length];
		
		int label = 0;
		a[0] = 1;
		int max = 1;
		boolean f = false;
		for(int i=1;i<c.length;i++){
			f = false;
			for(int j=label;j<i;j++){
				if(c[i] == c[j])
				{
					//note here , the new label should be from the repeated character's next element, and a[i] should include all elements from the label to i
					//this is very important!!!!
					label = j+1;
					a[i] = i-label+1;
					f = true;
					break;
				}
			}
			if(f == false)//does not appear a same char
			{
				a[i] = a[i-1]+1;
			}
			if(a[i]>max)
				max = a[i];
		}
		return max;
	}
	/**
	 * You are given two linked lists representing two non-negative numbers. 
	 * The digits are stored in reverse order and each of their nodes contain 
	 * a single digit. Add the two numbers and return it as a linked list.
	 * @param l1
	 * @param l2
	 * @return
	 */
	 public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		 ListNode r = null;
		 //base case, 
		 if(l1 == null && l2 == null)
			 return r;
		 if(l1 == null )
			 return l2;
		 if(l2 == null)
			 return l1;
		 
		 int x = 0; // jin wei biaozhi
		 ListNode t1 = l1;
		 ListNode t2 = l2;
		 ListNode pointer = null;
		 ListNode temp = null;
		 while(t1 != null && t2!=null){
			 int b = t1.val + t2.val + x;
			 x = b/10;
			 b = b%10;
			 temp = new ListNode(b);
			 if(pointer == null)
				 r = temp;
			 else
				 pointer.next = temp;
			
			 pointer = temp;
			 t1 =t1.next;
			 t2 =t2.next;
			 
		 }

		while (t1 != null) {
			int b = t1.val + x;
			x = b / 10;
			b = b % 10;
			temp = new ListNode(b);
			pointer.next = temp;
			pointer = temp;
			t1 = t1.next;
		}

		while (t2 != null) {
			int b = t2.val + x;
			x = b / 10;
			b = b % 10;
			temp = new ListNode(b);
			pointer.next = temp;
			pointer = temp;
			t2 = t2.next;
		}

		 if(x !=0)
		 {
			 temp = new ListNode(x);
			 pointer.next = temp;
		 }
		 return r;
	 }
	 /**
	  * DP 
	  * 
	  * @param s
	  * @return
	  */
	 public String longestPalindrome(String s) {
		 if(s == null ||s.length()<=0)
			 return null;
		 char[] c  =  s.toCharArray();
		 boolean [][] flag = new boolean[c.length][c.length]; // to mark s[i...j] is palindrome
		 //each is a palindrome
		 for(int i=0;i<c.length;i++)
			 flag[i][i] = true;
		 int start = 0;
		 int end = 0;
		 int max = 1;
		 //from end to start
		 for(int i=c.length-1;i>=0;i--){
			 for(int j=c.length-1;j>i;j--){
				 if(j-i==1){
					if(c[i] == c[j])
					{
						flag[i][j] = true;
						if(max < 2){
							max = 2;
							start = i;
							end = j;
						}
					}
				 }else{
						if(c[i] == c[j] && flag[i+1][j-1] == true){
							flag[i][j] = true;
							if(j-i+1 > max){
								max = j-i+1;
								start = i;
								end = j;
							}
						}
					}
				 }
			 }
		 
		 //return null;
		return s.substring(start,end+1);
	 }
	 /**
	  * Accepted 
	  * O(n^3) why ????
	  * @param s
	  * @return
	  */
	 public String longestPalindrome2(String s) {
		 if(s == null ||s.length()<=0)
			 return null;
		 char[] c  =  s.toCharArray();
		 for(int len = c.length ;len>1;len--){
			 for(int start=0;start<=c.length-len;start++){
				 int i=start,j = start+len-1;
				 while(i<=j && c[i] == c[j])
				{
					 i++;
					 j--;
				}
				 if(i>=j)
					 return s.substring(start,start+len);
			 }
		 }
		 return s.substring(0,1);
	 }
	 /**
	  * ZigZag Conversion
	  * @param s
	  * @param nRows
	  * @return
	  */
	 public String convert(String s, int nRows) {
		 if(s == null || s.length() <= 1 || nRows == 1)
			 return s;
		 //the total segments
		 int seg = s.length()%(nRows+nRows-2) == 0 ? s.length()/(nRows+nRows-2):
			 s.length()/(nRows+nRows-2)+1;
		 char[] c = s.toCharArray();
		 int sz = nRows+nRows-2;//the size of each segment
		 
		 StringBuilder sBuilder = new StringBuilder();
		 //for(int i=0;i<seg;i++){
			 for(int j=0;j<nRows;j++){
				 for(int i=0;i<seg;i++){
					int index = i*sz + j > s.length()-1 ? -1: i*sz + j;
					if(index >= 0)
						sBuilder.append(c[index]);
					if(j>0 && j<nRows-1){
						int index2 = i*sz +	(sz-j)> s.length() -1? -1:i*sz +	(sz-j);
						if(index2 >= 0)
							sBuilder.append(c[index2]);
					 }
				}
			 }
		  return sBuilder.toString();
	  }
	 /**
	  * reverse an integer
	  * overflow?
	  * 00000?
	  * 
	  * @param x
	  * @return
	  */
	 public int reverse(int x){
		 char[] c = Integer.toString(x).toCharArray();
		// if(string.length() == 1)
		 char pn = '+';
		 int start = 0;  
		 if(c[0] == '+' || c[0] == '-')
		 {
			 start = 1;
			 pn = c[0];
		 }
		 int result = 0;
		 boolean flag = false;
		 for(int i=c.length-1;i>=start;i--){
			 if(c[i] == '0' && flag == false)
				 continue;
			 if(flag == false)
				 flag = true;
			 if(result< (0x7fffffff-c[i]-'0')/10)
				 result = result*10 + c[i]-'0';
			 else 
				return 0;// overflow	 
		 }
		 return pn=='+'?result:result*(-1);
	 }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution14 s = new Solution14();
		/*ListNode r = new ListNode(1);
		r.next = new ListNode(1);
		r.next.next = new ListNode(2);
		//s.deleteDuplicates2(r);
		char[][] a = new char[3][4];
		a[0] = "ABCE".toCharArray();
		a[1] = "SFCS".toCharArray();
		a[2] = "ADEE".toCharArray();
		System.out.println(s.exist(a, "ABCB"));*/
		//int[] r = {3,2,4};
		//s.twoSum(r, 6);
		//int[] a = {1,2,6};
		//int[] b = {3,4,5};
		//System.out.println(s.findMedianSortedArrays(a, b));
		//System.out.println(s.lengthOfLongestSubString("qopubjguxhxdipfzwswybgfylqvjzhar"));
		ListNode r =new ListNode(9);
		r.next = new ListNode(9);
		//r.next.next = new ListNode(3);
		
		//ListNode b =new ListNode(9);
		//b.next = new ListNode(9);
		//b.next.next = new ListNode(4);		
		//System.out.println(s.longestPalindrome("ccc"));
		//s.addTwoNumbers(r, b);
		System.out.println(s.reverse(0));
	}

}
