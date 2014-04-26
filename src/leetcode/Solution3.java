package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Solution3 {
	/**
	 * Word break
	 * Given a string s and a dictionary of words dict, 
	 * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
	 * ���ö�̬�滮�㷨��������Db[0...len], Db[i]��ʾstring[0...i]�ǿ�segmented��
	 * ���ö�̬�滮ʱ���ܹؼ���һ���ǵ�Ū�����������ʲô��	
	 * �����ڿ��ǵ�i���ַ�ʱ����Ӧ���Ǵ�ǰ���Db[j]Ϊtrue��j+1��ʼ��i������ִ��Ƿ���dict�У�������٣��������ǰ�Һ��ʵ�j������ĺ���ָ����Db[j]Ϊtrue��
	 * @param s
	 * @param dict
	 * @return
	 */
	public boolean wordBreak1(String s, Set<String> dict)
	{
		//if the dict is empty, return false
		if(dict.size()<=0)
			return false;
		boolean[] Db = new boolean[s.length()];
		//initialize
		if(dict.contains(s.substring(0,1)))
			Db[0] = true;
		else
			Db[0] = false;
		//DP process
		for(int i=1;i<s.length();i++)
		{
			Db[i] = false;
			int j = i-1;
			while(j>=0)
			{
				if(Db[j] && dict.contains(s.substring(j+1,i+1)))
				{
					Db[i] = true;
					break;
				}
				j--;		
			}
			if(!Db[i] && j<0 && dict.contains(s.substring(0,i+1)))
				Db[i] = true;
				
				
		}
		return Db[s.length()-1];
	}
	/**
	 * Given a string s and a dictionary of words dict, add spaces in s to construct a sentence 
	 * where each word is a valid dictionary word. Return all such possible sentences.
	 * �������һ���ݹ����⣬����Ե�һ��word break��DP�ĵ���ʽ���Ļ����Ͳ��ѽ���һ��
	 * ���õݹ�ķ�����һ����ģ�ϴ������ݹ��һ����ģ��С������
	 * @param s
	 * @param dict
	 * @return
	 */
	public ArrayList<String> wordBreak2(String s,Set<String> dict){
		//�Ȳ���dp��ñ�ʶ��Ϣ������
		boolean[] Db = wordB(s,dict);
		//if it is not a breakable string, return empty
		if(Db == null || Db[s.length()-1] == false)
			return new ArrayList<String>();
		ArrayList<String> list = new ArrayList<String>();
		//���õݹ�ķ�������й��ж������ַ����ָ��
		subProblem(list,new String(), Db, s.length()-1,dict, s);
		
		return list;
	}
	/**
	 * 
	 * @param list �൱��ȫ�ֵ�һ������
	 * @param curr �����ĿǰΪֹ���Ѿ��γɵ��ַ���������ε����аѵ�ǰ��һ���Ϸ����ʷŵ�currǰ�棬�����ԡ� ���ָ�
	 * @param Db ��ʶ��Ϣ��һֱ����Ҫ
	 * @param end ��ʾĿǰ����Ĺ�ģ
	 * @param dict 
	 * @param s
	 */
	private void subProblem(ArrayList<String> list, String curr, boolean[] Db, int end,Set<String> dict,String s)
	{
		//���������Ļ�������������д����Ѿ����ʱ���Ͱ����ַָʽ�Ľ���ŵ�ȫ�ֵ�list��
		if(end == -1)
			list.add(curr.substring(0,curr.length()));
		
		int j = end-1;
		while(j>=0){
			if(Db[j] && dict.contains(s.substring(j+1,end+1)) )
			{		
				String cur = s.substring(j+1,end+1) + " "+curr;
				subProblem(list, cur, Db, j, dict, s);
			}
			j--;
		}
		//�����������
		if(dict.contains(s.substring(0,end+1)))
		{
			String cur = s.substring(0,end+1) + " "+curr;
			subProblem(list, cur, Db, -1, dict, s);
		}
	}
	/**
	 * DP����
	 * @param s
	 * @param dict
	 * @return
	 */
	private boolean[] wordB(String s, Set<String> dict){
		// if the dict is empty, return false
		if (dict.size() <= 0)
			return null;
		boolean[] Db = new boolean[s.length()];
		// initialize
		if (dict.contains(s.substring(0, 1)))
			Db[0] = true;
		else
			Db[0] = false;
		// DP process
		for (int i = 1; i < s.length(); i++) {
			Db[i] = false;
			int j = i - 1;
			while (j >= 0) {
				if (Db[j] && dict.contains(s.substring(j + 1, i + 1))) {
					Db[i] = true;
					break;
				}
				j--;
			}
			if (!Db[i] && j < 0 && dict.contains(s.substring(0, i + 1)))
				Db[i] = true;

		}
		return Db;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution3 s3 = new Solution3();
		Set<String> s = new HashSet<String>();
		s.add("cat");
		s.add("cats");
		s.add("and");
		s.add("sand");
		s.add("dog");
		System.out.println(s3.wordBreak2("catsanddog",s));
	}

}
