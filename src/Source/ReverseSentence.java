package Source;

public class ReverseSentence {

	/**
	 * ��תһ��char������[start,end)����
	 * @param str
	 * @param start
	 * @param end
	 */
	public static void reverse(char[] str, int start, int end){
		
		for(int i=start;i<(start+end)/2;i++)
		{
			char temp = str[i];
			str[i] = str[end-(i-start)-1];
			str[end-(i-start)-1] = temp;
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str="I am a student.";
		char[] array = str.toCharArray();
		//�Ȱ��������ӵ����е�����ת
		reverse(array,0,array.length);
		System.out.println(array);
		//����ת��ľ��ӵ�ÿ����������ת
		for(int i=0;i<array.length;i++)
		{
			int mark=i;
			while(i<array.length && array[i]!=' '  )
			{
				i++;
			}
			reverse(array,mark,i);			
		}
		System.out.println(array);
	}

}
