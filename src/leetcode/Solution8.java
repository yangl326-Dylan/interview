package leetcode;

import java.util.HashMap;



public class Solution8 {
	/**
	 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
	 * @param s
	 * @return
	 */
	public boolean isPalindrome(String s){
		String pre = preProcess(s);
		char[] sw = pre.toCharArray();
		if(pre==null || pre.length()<=1 )
			return true;
		int i=0;
		int j=pre.length()-1;
		while(i<=j){
			if(sw[i]!=sw[j])
				return false;
			i++;
			j--;
		}
		return true;
	}
	/**
	 * pre-processing the string, remove all non-isAlphanumeric
	 * and change all to lower
	 * @param s
	 * @return
	 */
	private String preProcess(String s){
		
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<s.length();i++)
			if(isAlphanumeric(s.charAt(i)))
				sb.append(s.charAt(i));
		return sb.toString().toLowerCase();
	}
	/**
	 * make decision if a character is a alphanumeric
	 * @param c
	 * @return
	 */
	private boolean isAlphanumeric(char c){
		if(c>='a' && c<='z' || c>='A' && c<='Z' || c>='0'&& c<='9')
			return true;
		return false;
	}
	/**
	 * Given a binary tree, find the maximum path sum.
	 * The path may start and end at any node in the tree.
	 * there are negative numbers
	 * {9,6,-3,#,#,-6,2,#,#,2,#,-6,-6,-6}
	 * @param root
	 * @return
	 */
	public int maxPathSum(TreeNode root){
		if(root == null)
			return 0;
		return subProblem(root);
	}
	/**
	 * 
	 * @param root
	 * @return
	 */
	private int subProblem(TreeNode root){
		
		if(root == null)
			return Integer.MIN_VALUE;
		if(root.left == null && root.right == null)
			return root.val;
		//�����������·����
		int left = subProblem(root.left);
		//�����������·����
		int right = subProblem(root.right);
		//����root�ڵ�����·����
		int lc = maxSinglePath2(root.left);
		int rc = maxSinglePath2(root.right);
		int mid = lc>0?lc:0;
		mid = rc>0? mid+rc:mid;
		
		return Math.max(left, right)>mid+root.val?Math.max(left, right):mid+root.val;
	}
	/**
	/**
	 * ���ֵݹ����ĳ���ڵ㵽Ҷ�ڵ�·���Ϻ����ķ��������׳���TLE
	 * ��Ϊ�������Ҫ��subProblem����ʹ��
	 * 
	private int maxSinglePath(TreeNode root){
		if(root==null)
			return 0;
		if(root.left==null && root.right ==null)
			return root.val;
		int lc = maxSinglePath(root.left);
		int lr = maxSinglePath(root.right);
		return Math.max(lc, lr)+root.val;
	}*/
	//ͨ�����⿪��һ��ȫ�ֱ������洢�ӵײ㿪ʼ��ÿ���ڵ㣨key����·��������
	//�������Ա�������Ѿ�������Ľڵ㱻��������
	HashMap<TreeNode,Integer> ns = new HashMap<TreeNode,Integer>();
	private int maxSinglePath2(TreeNode root){
		if(root==null)
			return 0;
		if(root.left==null && root.right ==null)
		{
			ns.put(root, root.val);
			return root.val;
		}
		int lc,rc;//�����ҽڵ�����·����
		if(root.left!=null && ns.containsKey(root.left))
			lc = ns.get(root.left);
		else
			lc = maxSinglePath2(root.left);
		if(root.right!=null && ns.containsKey(root.right))
			rc = ns.get(root.right);
		else
			rc = maxSinglePath2(root.right);
		//this is very important!!!!!! ��Ϊ�ڵ��ֵ����Ϊ������������������ڵ�����·�����Ǹ����Ļ����Ҿ�ֱ�Ӵӵ�ǰ�ڵ���Ϊ��ǰ�ڵ��·�����͵���ʼ��
		if(Math.max(lc, rc)>0)
			ns.put(root, Math.max(lc, rc)+root.val);
		else
			ns.put(root, root.val);
		return ns.get(root);
		//return Math.max(lc, rc)+root.val;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution8 s8 = new Solution8();
	
			TreeNode root = new TreeNode(9);
			TreeNode n2 = new TreeNode(6);
			root.left = n2;
			TreeNode n3 = new TreeNode(-3);
			root.right = n3;
			TreeNode n4 = new TreeNode(-6);
			TreeNode n5 = new TreeNode(2);
			n3.left = n4;
			n3.right = n5;
			TreeNode n6 = new TreeNode(2);
			n5.left = n6;
			TreeNode n7 = new TreeNode(-6);
			TreeNode n8 = new TreeNode(-6);
			n6.left = n7;
			n6.right = n8;
			TreeNode n9 = new TreeNode(-6);
			n7.left = n9;
			/*TreeNode n10 = new TreeNode(6);
			/*
			root.left = n2;
			root.right = n3;
			n2.left=n4;
			n2.right = n5;
			n3.left = n6;
			n3.right = n7;
			n4.left= n8;
			n4.right = n9;
			n5.left = n10;*/
		//	return root;
		
		System.out.println(s8.maxPathSum(root));
	}

}
