package Source;

import java.util.ArrayList;

public class CTCI8_4_Permutations {

	/**
	 * ��ȫ�������⣬Ϊ�˼���������������0-n��n+1�����ֵ�����
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int array[]={1,2,3};
		ArrayList<ArrayList<Integer>> results=permutations(array);
		for(int i=0;i<results.size();i++)
		{
			System.out.println(results.get(i));
		}
	}
	public static ArrayList<ArrayList<Integer>> permutations(int []array)
	{
		int len=array.length;
		//���Ϸ�����
		if(len<=0)
			return null;
		//������������
		if(len == 1)
		{
			ArrayList<ArrayList<Integer>> unitPermu= new ArrayList<ArrayList<Integer>>();
			ArrayList<Integer> unit = new ArrayList<Integer>();
			unit.add(array[0]);
			unitPermu.add(unit);
			return unitPermu;
		}
		int []subArray=new int[len-1];
		for(int i=0;i<len-1;i++)
		{
			subArray[i] = array[i];
		}
		//�õ��ӹ�ģ����Ľ�
		ArrayList<ArrayList<Integer>> subPermu=permutations(subArray);
		ArrayList<ArrayList<Integer>> permu = new ArrayList<ArrayList<Integer>>();
		int last = array[len-1];//��ǰ��Ҫ����������һ��Ԫ��
		//�õ���ǰ��ģ����Ľ�
		for(int i=0;i<subPermu.size();i++)
		{
			ArrayList<Integer> aSubPermu = subPermu.get(i);
			//��ÿ�����������������ӵ�ǰ���lastԪ��
			for(int j=0;j<aSubPermu.size()+1;j++)
			{
				ArrayList<Integer> aPermu = new ArrayList<Integer>();
				aPermu.addAll(aSubPermu);
				aPermu.add(j, last);
				permu.add(aPermu);
			}
			
		}
		return permu;
	}
}
