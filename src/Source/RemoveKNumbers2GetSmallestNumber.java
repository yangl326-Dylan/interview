package Source;

import java.math.MathContext;

public class RemoveKNumbers2GetSmallestNumber {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int array[]={9,5,1,2,4,6,8,7,3};
		removeKNumbers2GetSmallestNumber(array,5);
	}
	/**
	 * ����һ��Nλ��������12345��������ȥ��k�����֣��õ�һ��N-kλ������
	 * ����ȥ��2��4���õ�135��ȥ��1��5���õ�234������㷨��������еõ���
     * N-kλ��������С����һ��
	 * @param array ���������
	 * @param K �û�ָ����
	 * �㷨���Ӷ���O(m*(n-K))
	 */
	public static void removeKNumbers2GetSmallestNumber(int [] array, int K){
		int len = array.length;
		//flag�����ʼʱ���ᱻ��Ϊ0
		int flag [] =new int[len];
		//M�����ɵ�����λ��
		int M=len-K;
		
		int beg=0;
		int end=len-M;
		int min=10000;
		int ind=-1;
		
		for(int i=0;i<M;i++)
		{
			//�ҵ�ÿһλ�п������ڵķ�Χ��С����
			for(int j=beg;j<=end;j++)
			{
				if(array[j]<min)
				{
					min=array[j];
					ind=j;
				}
			}
			//���±���
			flag[ind]=1;//�ѱ�����λ��1
			beg=ind+1;
			end=len-M+i+1;
			min=10000;
			ind=-1;
			
		}
		//��ӡ����������
		for(int i=0;i<len;i++)
		{
			if(flag[i]==1)
			{
				System.out.print(array[i]);
			}
		}
	}
}
