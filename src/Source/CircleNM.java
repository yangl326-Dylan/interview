package Source;
class circleNode{
	int data;
	circleNode next;
	public circleNode(int _data, circleNode n){
		data = _data;
		next = n;
	}
}
/**
 * ��0��ʼ��ŵĵ��ƹ�ʽ��f(n,m) = [f(n-1,m)+m]%n ; f(1,m) = 0;
 * ��1��ʼ��ŵĵ��ƹ�ʽ��f(n,m) = [f(n-1,m)+m -1]%n +1; f(1,m) = 1;
 * @author Echo
 *
 */
public class CircleNM {
	/**
	 * Լɪ������Ļ������⣺n����Χ��һȦ����0��ʼ��ŵ�n-1����1������m������m�ĳ��ӣ�Ȼ�����һ����ʼ��1������m��������У�
	 * �����һ���˵ı�š�
	 * ���������ѧ��ʽf(n,m) = [f(n-1,m)+m]%n  O(n)
	 * �������Ǵ�1��ʼ������������ʱ���1
	 * ���õ��Ƶķ������
	 * @param n
	 * @param m
	 * @return
	 */
	public static int firstMathMethod(int n,int m)
	{
		if(n<=0 || m<=0)
			return -1;//�����������
		int lastVal = 0;
		for(int i=2;i<=n;i++)
			lastVal = (lastVal+m)%i;
		return lastVal;
	}
	public static int firstMathMethod2(int n,int m)
	{
		if(n<=0 || m<=0)
			return -1;//�����������
		int lastVal = 1;
		for(int i=2;i<=n;i++)
			lastVal = (lastVal+m-1)%i +1;
		return lastVal;
	}
	/**
	 * ����ͬ�ϣ�����������
	 * ���ַ�������������γ��ӵı�ţ�Ŀǰ�Ҳ�֪������ģ�ⷽ��������ʲô����Ч�ʵķ�����������������˳�򣿣�
	 * ���û�ģ��ɾ������ O(nm)
	 * @param n
	 * @param m
	 * @return
	 */
	public static int secondLinkedListMethod(int n, int m)
	{
		//��������
		circleNode head = new circleNode(0,null);
		circleNode pointer =head;
		for(int i=1;i<n;i++)
		{
			pointer.next = new circleNode(i,null); 
			pointer = pointer.next;
		}
		pointer.next = head;
		//ģ��ɾ������
		pointer = head;
		int index = 1;
		while(pointer.next!=pointer)
		{
			if(index == m-1)
			{
				pointer.next = pointer.next.next;
				pointer = pointer.next;
				index = 1;
			}else
			{
				index++;
				pointer =  pointer.next;
			}
		}
		return pointer.data;
		
	}
	
	/**
	 * ������Լɪ�������һ����չ��n ���˰�˳ʱ��Χ��һȦ��1��ʼ��˳��˳���ţ������Ȱѵ�m�ŵ��˳��ӣ�
	 * Ȼ���m+1�ſ�ʼ��1��2��3 ��....��k��˳ʱ�뱨������ k ���˳�Ȧ�⣬
	 * ��������ٴ�1��2��3 ��....��k �������� k �������˳�Ȧ�⣬�������ơ���������һ���˵�ԭ��š�
	 * ��ʵ����˼ά���̺ͻ���������һ�Ƶġ�
	 * @param n �����Ŵ�1��ʼ���� ����������+1 ����
	 * @param k
	 * @param m
	 * @return
	 */
	public static int genericNMK(int n, int k, int m)
	{
		if(n<=0 || m<=0)
			return -1;//�����������
		int lastValue = 0;
		for(int i=2;i<n;i++)
			lastValue = (lastValue + k)%i;
		return (lastValue +m)%n;
	}
	/**
	 * ����һ����ѧ���ƹ�ʽ
	 * @param n
	 * @param m
	 * @return
	 */
	public static int mn(int n, int m)
	{
		if(n<=0 || m<=0)
			return -1;//�����������
		int lastValue = 1;
		
		for(int i=2;i<=n;i++)
		{	
			int delta = m%i;
			lastValue = (lastValue + delta-1)%i +1;
		}
		return lastValue;
	}
	/**
	 * ����һ�ֵ��ƹ�ʽ ��1��ʼ��ţ���ʵ����f(n,m) = [f(n-1,m)+m-1]%i +1 ;
	 * ͬ firstMathMethod2
	 * @param n
	 * @param m
	 * @return
	 */
	public static int mns(int n, int m)
	{
		if(n<=0 || m<=0)
			return -1;//�����������
		int lastValue = 1;
		
		for(int i=2;i<=n;i++)
		{	
			int delta = m%i;
			lastValue = (lastValue + delta-1)%i +1;
		}
		return lastValue;
	}
	/**
	 * ��չ����2����P�ֳ�Ȧ�ı�ţ����r��ʼ��ʱ��Ϊʲô��0���Ҳ���������
	 * �����Ǵ�1��ʼ���
	 * @param n
	 * @param m
	 * @param p
	 * @return
	 */
	public static int NMP(int n,int m, int p)
	{
		int r = 0;
		for(int i=n-p+1;i<=n;i++)
		{
			r = (r+m-1)%i +1;
		}
		return r;
	}
	/**
	 * ��չ����3���״γ��ӵı�������֮��Ĳ�ͬ
	 * @param n ��1��ʼ��ŵ�n
	 * @param m �����״��ⶼ����
	 * @param k �״γ��ӵı���Ǳ���k��
	 * @param p ��p����Ȧ
	 * @return
	 */
	public static int NMKP(int n,int m,int k, int p)
	{
		int r = 0;
		for(int i=n-p+1;i<n;i++)
		{
			r = (r+m-1)%i +1;
		}
		return (r+k-1)%n+1;
	}
	/**
	 * һ����������£���m=2ʱ�����Եõ�O��logN�����㷨
	 * ����������ֱ�ӵ�����ô������
	 * ���õݹ�ķ�ʽ����������������
	 * @param n
	 * @return
	 */
	public static int N2recu(int n)
	{
		if(n<=0)
			return -1;
		if(n == 1)
			return 1;
		if(n%2 == 0)
			return 2*N2recu(n/2)-1;
		else
			return 2*N2recu(n/2)+1;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println(firstMathMethod(10,11));
		//System.out.println(firstMathMethod2(10,11));
		//System.out.println(secondLinkedListMethod(8,3));
		
		System.out.println(N2recu(11));
	}

}
