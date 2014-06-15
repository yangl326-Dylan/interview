package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Solution17 {
	/**
	 * Substring with Concatenation of All Words 
	 * permutation will get TLE
	 * two complex
	 * @param S
	 * @param L
	 * @return
	 */
	 public List<Integer> findSubstring(String S, String[] L) {
         List<Integer> ret = new ArrayList<Integer>();
	     if(L == null || L[0].length()<=0)
	    	 return ret;
	     int n = L[0].length();
	     int k = L.length;
	     int m = S.length();
	     
	     System.out.println(permutations(L));
	     List<String> per = permutations(L);
	     for(int i =0;i<per.size();i++){
	    	 if(S.indexOf(per.get(i))==-1)
	    		 continue;
	    	 int temp = 0;
	    	 while(temp!=-1 && temp<m){
	    		 temp = S.indexOf(per.get(i), temp);
	    		 if(temp != -1 && !ret.contains(temp))
	    		 {
	    			 ret.add(temp);
	    			 temp++;
	    		 }
	    		 if(temp != -1 && ret.contains(temp))
	    			 temp++;
	    	 }
	     }
	     System.out.println(ret);
		    
	     return ret;
   }
	 /**
	  * return all permutations of the L
	  * @param L
	  * @return
	  */
	private List<String> permutations(String[] L){
		List<List<String>> rets = new ArrayList<List<String>>();
		int k = L.length;
		int i=1;
		List<String> temp = new ArrayList<String>();
		temp.add(L[0]);
		rets.add(temp);
		while(i<k){
			int size = rets.size();
			for(int j=0;j<size;j++){
				temp = rets.get(j);
				//temp.add(L[i]);
				for(int x=0;x<temp.size();x++){
					List<String> tmp = new ArrayList<String>();
					tmp.addAll(temp);
					tmp.add(x, L[i]);
					rets.add(tmp);
				}
				temp.add(L[i]);
			}
			i++;
		}
		
		List<String> ret = new ArrayList<String>();
		for(i=0;i<rets.size();i++){
			StringBuilder sb = new StringBuilder();
			for(int j=0;j<rets.get(i).size();j++)
				sb.append(rets.get(i).get(j));
			ret.add(sb.toString());
		}
		return ret;
	}
	public List<Integer> findSubstring2BruteFource(String S, String[] L) {
		 List<Integer> ret = new ArrayList<Integer>();
	     if(L == null || L[0].length()<=0)
	    	 return ret;
	     int w = L[0].length();
	     int k = L.length;
	     int m = S.length();
	     //store the counts of each word in L
	     HashMap<String, Integer> cn = new HashMap<String, Integer>(k);
	     //store the number of each word met in S
	     HashMap<String, Integer> cnt = new HashMap<String, Integer>(k);
	     int count = 0;
	     int begin = 0;
	     //caculate the counts of each word in L
	     for(int i=0;i<k;i++){
	    	 if(cn.containsKey(L[i]))
	    		 cn.put(L[i], cn.get(L[i])+1);
	    	 else
	    		 cn.put(L[i], 1);
	     }
	     
	     for(int i=0;i<w;i++){
	    	 begin = i;
	    	 count = 0;
	    	 cnt.clear();
	    	 for(int j=i;j<m;j=j+w){
	    		 String tar = S.substring(j,j+w<m? j+w:m);
	    		 //if reach to a word that's not in the L, reset the begin , count and cnt;
	    		 if(!cn.containsKey(tar)){
	    			 begin = j+w;
	    			 count = 0;
	    			 cnt.clear();
	    			 continue;
	    		 }
	    		 if(!cnt.containsKey(tar) || cnt.get(tar) == 0){
	    			 cnt.put(tar, 1);
	    			 count++;
	    		 }else if(cnt.get(tar)<cn.get(tar)){
	    			 cnt.put(tar, cnt.get(tar)+1);
	    			 count++;
	    		 }else{
	    			 if(count < k){//reset all the variables
	    				 begin = j+w;
		    			 count = 0;
		    			 cnt.clear();
	    			 }else{
	    				 ret.add(begin);
	    				 count --;
	    				 String rm = S.substring(begin,begin+w);
	    				 cnt.put(rm, cnt.get(rm)-1);
	    				 begin=begin+w;
	    				 continue;
	    			 }
	    		 }
	    		 if(count == k){
	    			 ret.add(begin);
    				 count --;
    				 String rm = S.substring(begin,begin+w);
    				 cnt.put(rm, cnt.get(rm)-1);
    				 begin=begin+w;
	    		 }
	    	 }
	     }
	     return ret;
	}
	/**
	 * Given a string S and a string T, find the minimum window in S
	 *  which will contain all the characters in T in complexity O(n).
	 *  Got TLE
	 * @param S
	 * @param T
	 * @return
	 */
	public String minWindow(String S, String T) {
		if(S.length()<T.length())
			return "";
		char[] tc = T.toCharArray();
		char[] sc = S.toCharArray();
		int tL = T.length();
		int sL = S.length();
        //store the char in T and its Corresponding number
		//HashMap<Character, Integer> cn = new HashMap<Character, Integer>(T.length());
		int[] cn = new int[256];
        //store the index of each char in S
		HashMap<Character, LinkedList<Integer>> cnt = new HashMap<Character, LinkedList<Integer>>(T.length());
		//store the indexes of all chars
		LinkedList<Integer> inds = new LinkedList<Integer>();
		int ct = 0;//mark the chars in current window
		int gLen = sL+1;
		int gMin = 0;
		int gMax = 0;
		//caculate the number of each difference
		for(int i=0;i<tL;i++){
			char tmp = tc[i];
			cn[tmp]++;
		}
		
		//traverse the String S
		for(int i=0;i<sL;i++){
			char tmp = sc[i];
			//if tmp is one of the char in T, else do nothing
			if(cn[tmp]>0){
				int num = cn[tmp]; // the total time of occurrence
				//update cnt map
				if(!cnt.containsKey(tmp)){
					LinkedList<Integer> indice = new LinkedList<Integer>();
					indice.offer(i);
					cnt.put(tmp, indice);
					ct++;
					inds.offer(i);
				}else if(cnt.get(tmp).size()<num){
					cnt.get(tmp).offer(i);
					ct++;
					inds.offer(i);
				}else {
					Integer r = cnt.get(tmp).remove();//remove the oldest one
					cnt.get(tmp).offer(i);
					inds.remove((Object)r);
					inds.offer(i);
				}
				//has contain all the chars
				if(ct == tL){
					int min = inds.get(0);
					int max = i;
					if( max-min+1<gLen){
						gLen = max-min+1;
						gMin = min;
						gMax = max;
					}
				}
			}
		}
		return gLen == sL+1 ? "" : S.substring(gMin, gMax+1);
    }
	/**
	 * the Algorithm is according to the http://leetcode.com/2010/11/finding-minimum-window-in-s-which.html  
	 * @param S
	 * @param T
	 * @return
	 */
	public String minWindow2(String S, String T) {
		if(T == null || T.length()<=0 || T.length()>S.length())
			return "";
		int[] needsToFind = new int[256]; //stores the total count of a character in T
		int[] hasFound = new int[256]; //stores the total count of a character met so far. 
		int count = 0;
		int begin=0; //the start index of the window
		int end = 0; //the end index of the window
		int gLen = S.length()+1;
		int gMin = 0;
		int gMax = 0;
		//first count the occurrence for each char in T
		for(int i=0;i<T.length();i++)
			needsToFind[T.charAt(i)]++;
		
		for(int i=0;i<S.length();i++){
			char tmp = S.charAt(i);
			if(needsToFind[tmp]>0){
				end = i;
				hasFound[tmp] ++ ; 
				if(hasFound[tmp]<=needsToFind[tmp]){
					count++;
				}
				//a window has all the chars in T
				if(count == T.length()){
					//check whether end can advance
					while(hasFound[S.charAt(begin)]>needsToFind[S.charAt(begin)] || 
							needsToFind[S.charAt(begin)]<=0){
						if(hasFound[S.charAt(begin)]>needsToFind[S.charAt(begin)] )
							hasFound[S.charAt(begin)]--;
						begin++;
						
					}
					if(end-begin+1<gLen){
						gLen = end-begin+1;
						gMin = begin;
						gMax = end +1;
					}	
				}
			}
		}
		return count == T.length()?  S.substring(gMin, gMax):"";
	}
	/**
	 * Substring with Concatenation of All Words 
	 * @param S
	 * @param L
	 * @return
	 */
	 public List<Integer> findSubstring2(String S, String[] L) {
            List<Integer> ret = new ArrayList<Integer>();
	     if(L == null || L[0].length()<=0)
	    	 return ret;
	     int w = L[0].length();
	     int k = L.length;
	     int m = S.length();
	     //store the counts of each word in L
	     HashMap<String, Integer> cn = new HashMap<String, Integer>(k);
	     //store the number of each word met in S
	     HashMap<String, Integer> cnt = new HashMap<String, Integer>(k);
	     int count = 0;
	     int begin = 0;
	     //caculate the counts of each word in L
	     for(int i=0;i<k;i++){
	    	 if(cn.containsKey(L[i]))
	    		 cn.put(L[i], cn.get(L[i])+1);
	    	 else
	    		 cn.put(L[i], 1);
	     }
	     
	     for(int i=0;i<w;i++){
	    	 begin = i;
	    	 count = 0;
	    	 cnt.clear();
	    	 for(int j=i;j<m;j=j+w){
	    		 String tar = S.substring(j,j+w<m? j+w:m);
	    		 //if reach to a word that's not in the L, reset the begin , count and cnt;
	    		 if(!cn.containsKey(tar)){
	    			 begin = j+w;
	    			 count = 0;
	    			 cnt.clear();
	    			 continue;
	    		 }
	    		 if(!cnt.containsKey(tar) || cnt.get(tar) == 0){
	    			 cnt.put(tar, 1);
	    			 count++;
	    		 }else if(cnt.get(tar)<cn.get(tar)){
	    			 cnt.put(tar, cnt.get(tar)+1);
	    			 count++;
	    		 }else{
	    			 if(count < k){//reset all the variables
	    				 while(true){
	    					 String rm = S.substring(begin,begin+w);
	    					 begin = begin + w;
	    					 if(rm.equals(tar))
	    						 break;
	    					 else
	    						 cnt.put(rm, cnt.get(rm)-1);
	    				 }
	    			 }else{
	    				 ret.add(begin);
	    				 count --;
	    				 String rm = S.substring(begin,begin+w);
	    				 cnt.put(rm, cnt.get(rm)-1);
	    				 begin=begin+w;
	    				 continue;
	    			 }
	    		 }
	    		 if(count == k){
	    			 ret.add(begin);
    				 count --;
    				 String rm = S.substring(begin,begin+w);
    				 cnt.put(rm, cnt.get(rm)-1);
    				 begin=begin+w;
	    		 }
	    	 }
	     }
	     return ret;
	}
	 public void nextPermutation(int[] num) {
         if(num == null || num.length <=1)
	        	return ;
	        int i = num.length-2;
	        int last = num.length -1;
	        while(i>=0){
	        	if(num[i]>=num[last]){//keep looking, and put the current num to the end of the array
	        		int tmp = num[i];
	        		int ind = i+1;
	        		while(ind<=last)
	        		{
	        			num[ind-1] = num[ind];
	        			ind++;
	        		}
	        		num[last] = tmp;
	        	}else{
	        		int ind = i+1;
	        		while(num[i]>=num[ind])
	        			ind++;
	        		int tmp = num[i];
	        		num[i] = num[ind];
	        		num[ind] = tmp;
	        		break;
	        	}
	        	i--;
	        }
    }
	 /**
	  * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
	  * 
	  * @param s
	  * @return
	  */
	 public int longestValidParentheses(String s) {
	        if(s == null || s.length()<=0)
	            return 0;
	        int max = 0;
	        int cnt = 0;
	        LinkedList< Integer > stack = new LinkedList<Integer>();//only push the index of '('
	        int[] f = new int[s.length()];//'(' : 0 ; ')' : the longest length ended with this ')'
	        if(s.charAt(0) == '(')
	        	stack.push(0);
	        for(int i=1;i<s.length();i++)
	        {
	        	if(s.charAt(i)==')')
	        	{
	        		if (stack.size() > 0) {
	        			int beg = stack.pop();
	        			cnt = i - beg + 1;
	        			if(beg>0 && f[beg-1]>0){
	        				cnt += f[beg-1];		
	        			}
	        			f[i] = cnt;
	        			max = max>cnt?max:cnt;
	        		}
				}	
	        	else {
					stack.push(i);
				}
	        }
	        return max;
	  }
	 /**
	  * Search in Rotated Sorted Array
	  * @param A
	  * @param target
	  * @return
	  */
	 public int search(int[] A, int target) {
	      if(A == null || A.length<=0)
	    	  return -1;
	      //boolean flag1 = A[0]>target ? true: false; // whether the first one in the array is bigger than the target
	      //boolean flag2 = A[A.length-1]>=target ? true:false; // whether the last one in the array is bigger than the target
	      //binary search
	      int left = 0;
	      int right = A.length-1;
	      int mid = (left+right)/2;
	      while(left<=right){
	    	  if(A[mid] == target)
	    		  return mid;
	    	  if(A[mid]<target){
	    		  if(A[mid]>=A[right]){
	    			  left = mid+1;
	    			  mid = (left+right)/2;
	    		  }else{
	    			  if(A[right]<target){
	    				  right = mid-1;
	    				  mid = (left+right)/2;
	    			  }else{
	    				  left = mid+1;
	    				  mid = (left+right)/2;
	    			  }
	    		  }
	    	  }
	    	  if(A[mid]>target){
	    		  if(A[mid]>=A[right]){
	    			  if(A[left]>target){
	    				  left = mid+1;
	    				  mid = (left+right)/2;
	    			  }else{
	    				  right = mid-1;
	    				  mid = (left+right)/2;
	    			  }
	    		  }else{
	    			  right = mid-1;
	    			  mid = (left+right)/2;
	    		  }
	    	  }
	    	  if(left >= A.length || right<0)
	    		  break;
	      }
	      return -1;
	  }
	 /**
	  * search for a range
	  * @param A
	  * @param target
	  * @return
	  */
	 public int[] searchRange(int[] A, int target) {
		 int[] ret = {-1,-1};
		 if(A == null || A.length == 0)
			 return ret;
		 int left = 0;
		 int right = A.length -1;
		 int mid = (left+ right)/2;
		 while(left<=right){
			 if(A[mid] == target)
				 break;
			 if(A[mid]<target){
				 left = mid+1;
				 mid = (left+right)/2;
			 }
			 if(A[mid]>target){
				 right = mid-1;
				 mid = (left+right)/2;
			 }
			 if(left>A.length-1 || right <0 )
				 break;
		 }
		 if(mid<A.length && mid>=0 && A[mid] == target){
			 int low = mid;
			 int hig = mid;
			 while(low >=0 && A[low] == target)
				 low--;
			 while(hig<A.length && A[hig] == target)
				 hig ++ ;

			 ret[0] = low == 0 ? (A[low]==target? low : low+1) :low+1;
			 ret[1] = hig == A.length-1 ? (A[hig] == target ? hig : hig-1):hig-1;
		 }
		 
		 return ret;
	 }
	 public int searchInsert(int[] A, int target) {
		 int left = 0;
		 int right = A.length-1;
		 int mid = (left+right)/2;
		 while(left<=right){
			 //case 1 
			 if(left == right && A[left]!=target){
				 if(A[left]<target)
					 return left+1;
				 else {
					return left;
				}
			 }
			 if(A[mid] == target)
				 return mid;
			 if(A[mid]<target){
				 left = mid+1;
				 mid = (left+right)/2;
			 }else {
				right = mid-1;
				mid = (left+right)/2;
			}	 
		 }
		 //case 2
		 if(right<left)
			 return left;
		 return 0 ;
	 }
	 /**
	  * Determine if a Sudoku is valid
	  * @param board
	  * @return
	  */
	 public boolean isValidSudoku(char[][] board) {
	       int[][][] map = new int[3][9][10];
	       for(int i=0;i<9;i++)
	       {
	    	   for(int j=0;j<9;j++){
	    		   if(board[i][j] == '.')
	    			   continue;
	    		   int val = board[i][j]-'0';
	    		   //valid the rows
	    		   if(map[0][i][val] == 1)
	    			   return false;
	    		   else 
	    			   map[0][i][val] = 1;
	    		   //valid the column
	    		   if(map[1][j][val] == 1)
	    			   return false;
	    		   else 
	    			   map[1][j][val] = 1;
	    		   //valid the sub-group
	    		   if (map[2][3*(i/3)+j/3][val] == 1) 
	    			   return false;
	    		   else {
					   map[2][3*(i/3)+j/3][val] = 1;
				}
	    	   }
	       }
	       return true;
	 }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution17 s = new Solution17();
		int[] A ={3,5,7,9,10};
		int target = 8;
		System.out.print(s.searchInsert(A, target));
		//String[] L ={"foo", "bar"};
		//System.out.println(s.findSubstring2BruteFource("barfoothefoobarman",L));
		//String[] L = {"dhvf","sind","ffsl","yekr","zwzq","kpeo","cila","tfty","modg","ztjg","ybty","heqg","cpwo","gdcj","lnle","sefg","vimw","bxcb"};
		//s.findSubstring("pjzkrkevzztxductzzxmxsvwjkxpvukmfjywwetvfnujhweiybwvvsrfequzkhossmootkmyxgjgfordrpapjuunmqnxxdrqrfgkrsjqbszgiqlcfnrpjlcwdrvbumtotzylshdvccdmsqoadfrpsvnwpizlwszrtyclhgilklydbmfhuywotjmktnwrfvizvnmfvvqfiokkdprznnnjycttprkxpuykhmpchiksyucbmtabiqkisgbhxngmhezrrqvayfsxauampdpxtafniiwfvdufhtwajrbkxtjzqjnfocdhekumttuqwovfjrgulhekcpjszyynadxhnttgmnxkduqmmyhzfnjhducesctufqbumxbamalqudeibljgbspeotkgvddcwgxidaiqcvgwykhbysjzlzfbupkqunuqtraxrlptivshhbihtsigtpipguhbhctcvubnhqipncyxfjebdnjyetnlnvmuxhzsdahkrscewabejifmxombiamxvauuitoltyymsarqcuuoezcbqpdaprxmsrickwpgwpsoplhugbikbkotzrtqkscekkgwjycfnvwfgdzogjzjvpcvixnsqsxacfwndzvrwrycwxrcismdhqapoojegggkocyrdtkzmiekhxoppctytvphjynrhtcvxcobxbcjjivtfjiwmduhzjokkbctweqtigwfhzorjlkpuuliaipbtfldinyetoybvugevwvhhhweejogrghllsouipabfafcxnhukcbtmxzshoyyufjhzadhrelweszbfgwpkzlwxkogyogutscvuhcllphshivnoteztpxsaoaacgxyaztuixhunrowzljqfqrahosheukhahhbiaxqzfmmwcjxountkevsvpbzjnilwpoermxrtlfroqoclexxisrdhvfsindffslyekrzwzqkpeocilatftymodgztjgybtyheqgcpwogdcjlnlesefgvimwbxcbzvaibspdjnrpqtyeilkcspknyylbwndvkffmzuriilxagyerjptbgeqgebiaqnvdubrtxibhvakcyotkfonmseszhczapxdlauexehhaireihxsplgdgmxfvaevrbadbwjbdrkfbbjjkgcztkcbwagtcnrtqryuqixtzhaakjlurnumzyovawrcjiwabuwretmdamfkxrgqgcdgbrdbnugzecbgyxxdqmisaqcyjkqrntxqmdrczxbebemcblftxplafnyoxqimkhcykwamvdsxjezkpgdpvopddptdfbprjustquhlazkjfluxrzopqdstulybnqvyknrchbphcarknnhhovweaqawdyxsqsqahkepluypwrzjegqtdoxfgzdkydeoxvrfhxusrujnmjzqrrlxglcmkiykldbiasnhrjbjekystzilrwkzhontwmehrfsrzfaqrbbxncphbzuuxeteshyrveamjsfiaharkcqxefghgceeixkdgkuboupxnwhnfigpkwnqdvzlydpidcljmflbccarbiegsmweklwngvygbqpescpeichmfidgsjmkvkofvkuehsmkkbocgejoiqcnafvuokelwuqsgkyoekaroptuvekfvmtxtqshcwsztkrzwrpabqrrhnlerxjojemcxel", L);
		//System.out.println(s.minWindow2("acbbaca", "aba"));
	}

}
