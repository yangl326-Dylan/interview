package leetcode;

public class Solution17 {
	 public List<Integer> findSubstring(String S, String[] L) {
         List<Integer> ret = new ArrayList<Integer>();
	     if(L == null || L[0].length()<=0)
	    	 return ret;
	     int n = L[0].length();
	     int k = L.length;
	     int m = S.length();
	     boolean[][] f = new boolean[n*k][m];
	     
	     //fullfil the matrix of f
	     for(int i=0;i<m;i++)
	    	 f[0][i] = L[0].charAt(0) == S.charAt(i) ? true : false;
	     for(int j=1;j<n*k;j++)
	    	 f[j][0] = false;
	     for(int x = 0;x< k;x++){
	    	 int j= x==0 ? 1 : 0;
	    	 for( ;j<n;j++){
	    		  for(int i=1;i<m;i++){
	    			  if(j==0)
	    				  
	    			  if(f[j-1][i-1] == true && )
	    		  }
	    	 }
	     }
	    
	     return ret;
   }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
