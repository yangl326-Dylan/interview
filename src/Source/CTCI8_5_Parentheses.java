package Source;

import java.util.ArrayList;

public class CTCI8_5_Parentheses {

	/**
	 * implement an algorithm to print all valid combinations of n-pairs of parentheses
	 * ���и�����������ȱ��һ�����
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> strList=parentheses(3);
		for(int i=0;i<strList.size();i++)
		{
			System.out.println(strList.get(i));
		}
	}
	public static ArrayList<String> parentheses(int n)
	{
		ArrayList<String> strList ;
		//���������ǻ������
		if(n==0)
		{
			strList=new ArrayList<String>();
			String zero = new String();
			zero="";
			strList.add(zero);
			return strList;
		}
		if(n==1)
		{
			strList=new ArrayList<String>();
			String one = new String();
					one = "()";
			strList.add(one);
			return strList;
		}
		//�ݹ�õ���ǰ�Ľ�
		ArrayList<String> results = new ArrayList<String>();
		for(int i=0;i<n;i++)
		{
			ArrayList<String> preHalf=parentheses(i);
			ArrayList<String> postHalf=parentheses(n-i-1);
			
			for(int j=0;j<preHalf.size();j++)
			{
				for(int k=0;k<postHalf.size();k++)
				{
					String str= new String();
					str="("+preHalf.get(j)+")"+postHalf.get(k);
					//System.out.println(str);
					results.add(str);
				}
			}
		}
		return results;
	}
}
