package Source;

public class SplitString2PalindromeSubstring {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//splitString2PalindromeSubstring("habbaffabba");
		splitString2PalindromeSubstring("abcdefg");
		
	}
	/**
	 * ��һ���ܳ����ַ������ָ��һ��һ�ε����ַ��������ַ������ǻ����ַ�����
	 * �л����ַ����������ģ�û�л��ľ����һ��һ�����ַ���
	 * @param str ������ַ���
	 */
	public static void splitString2PalindromeSubstring(String str){
		int size=str.length();
		//S[i]��ʾ��str�ĵڣ�i+1�����ַ�Ϊ��βʱ����Ļ����Ӵ�
		int S[]=new int[size];
		
		S[0]=1;
		for(int i=1;i<size;i++)
		{
			//�Ƚϵ�ǰ�ַ����п��ܳ�Ϊ���ĵĶ�Ӧλ�õ��ַ��������ͬ�����ĳ�������2�������Ե�ǰ�ַ�Ϊ��β�Ļ��ĳ���Ϊ1
			char a=' ';
			if(S[i-1]==1)
				a=str.charAt(i-S[i-1]);
			else
				a=str.charAt(i-S[i-1]-1);
			char b=str.charAt(i);
			if(a==b)
			{
				if(S[i-1]==1)
					S[i]=S[i-1]+1;
				else
					S[i]=S[i-1]+2;
			}else
			{
				S[i]=1;
			}
		}
		//��ӡ���ָ���Ӵ�,�Ӻ���ǰ��ӡ
        int i=size-1;
        while(i>=0)
        {
        	if(S[i]==1)
        		System.out.println(str.charAt(i));
        	else
        		System.out.println(str.substring(i-S[i]+1, i+1));
        	i=i-S[i];
        }
		
	}

}
