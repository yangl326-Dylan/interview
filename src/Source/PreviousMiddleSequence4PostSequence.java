package Source;

public class PreviousMiddleSequence4PostSequence {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		previousMiddleSequence4PostSequence("abcdef","badcef");
	}
	/**
	 * ������������� ��������ĺ�������
	 * @param preStr
	 * @param midStr
	 */
	public static void previousMiddleSequence4PostSequence(String preStr, String midStr)
	{
		//�򵥵���֤�������Ƿ���Ϲ���
		if(preStr.length()!=midStr.length())
		{
			System.out.println("Input Error !");
			return ;
		}
		//��������֮һ
		if(preStr.length()<=0 || midStr.length()<=0)
			return ;
		//���������
		if(preStr.length()==1 && midStr.length()==1 && preStr.equals(midStr) )
		{
			System.out.print(preStr);
			return ;
		}
		char root=preStr.charAt(0);
		int index=midStr.indexOf(root);
		//�ҵ���Ӧ�����������������������
		String leftStr=midStr.substring(0, index);
		String preSubStr1=preStr.substring(1, leftStr.length()+1);
		//�ҵ���Ӧ�����������������������
		String rightStr=midStr.substring(index+1, midStr.length());
		String preSubStr2=preStr.substring(1+leftStr.length());
		//�ݹ���ã�ע��˳���ȵ�����������
		previousMiddleSequence4PostSequence(preSubStr2,rightStr);
		previousMiddleSequence4PostSequence(preSubStr1,leftStr);
		System.out.print(root);
	}
}
