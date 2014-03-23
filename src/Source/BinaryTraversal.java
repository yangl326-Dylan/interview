package Source;

import java.util.LinkedList;

/**
 * ����ʵ�ֶ������ı�������Ҫ�Լ�ά��һ��ջ
 * @author Echo
 *
 */
public class BinaryTraversal {

	public static void preOrder(Node root){
		LinkedList<Node> stack  = new LinkedList<Node>();
		Node pointer = root;
		stack.push(root);
		while(!stack.isEmpty()){
			pointer = stack.pop();
			System.out.print(pointer.data+", ");
			if(pointer.rightChild!=null)
				stack.push(pointer.rightChild);
			if(pointer.leftChild!=null)
				stack.push(pointer.leftChild);
		}
		System.out.println();
	}
	
	public static void postOrder(Node root){
		LinkedList<Node> stack = new LinkedList<Node>();
		Node pointer = null; //��ջͷ��һ��ָ��
		Node out = null; //��ջ�Ľڵ�
		stack.push(root);
		while(!stack.isEmpty()){
			pointer = stack.peek();//ֻ�ǿ���ջͷ�Ľڵ�
			if(pointer.rightChild!=null && 
					!(out!=null && out==pointer.rightChild ) &&
					!(out!=null && out==pointer.leftChild )) //��Ҫע���Ѿ�����ջ�Ľڵ㲻���ٽ�
				stack.push(pointer.rightChild);
			if(pointer.leftChild!=null && !(out!=null && out==pointer.rightChild) &&
					!(out!=null && out==pointer.leftChild )) //ͬ�ϣ���Ҫע���Ѿ�����ջ�Ľڵ㲻���ٽ�
				stack.push(pointer.leftChild);
			if(pointer.rightChild == null && pointer.leftChild ==null || //��˵������Ҷ�ӽڵ�
					out!=null&&pointer.rightChild == out ||
					out!=null &&pointer.leftChild == out ) //��˵�������ӽڵ��Ѿ�����������
			{
				out = stack.pop();
				System.out.print(out.data+",");
			}
		}
		System.out.println();
	}
	/**
	 * ����ÿ���ڵ��мٵ������浯���������
	 * stack��ͷ�����ᱻ�ȼٵ���һ��
	 * ֮����Ҫ�жϵ�ǰ�ڵ�����ҽڵ��Ƿ��Ѿ���push��ջһ�Σ����û����ѵ�ǰ�ڵ���ҽڵ�push��stack���ٰѵ�ǰ�ڵ�push��ȥ�����ѵ�ǰ�ڵ����ڵ�push��ȥ
	 * �����жϵ��������ǵ�ǰ�ڵ���������Ƿ��Ѿ�����������Ҳ�����ϴα���ĵ����Ľڵ��ǵ�ǰ�ڵ����ڵ㣬�����ϴα��浯�����ǵ�ǰ�ڵ�����������󱻱�������һ���ڵ�
	 * 
	 * ��ǰ�ڵ㱻��ĵ��� �������������෴
	 * @author TingHuang
	 * @param root
	 */
	public static void inOrder(Node root){
		LinkedList<Node> stack = new LinkedList<Node>(); //����ʵ����Ҫ�Լ���һ��ջ������Ϣ
		Node pointer = null; //������ڲ鿴ջͷ�Ľڵ㣬�Ұ��������ٳ�ջ
		Node out =null; //������ڱ����ϴα��������ģ�Ҳ������ĳ�ջ�Ľڵ�
		stack.push(root);
		while(!stack.isEmpty())
		{
			pointer = stack.pop();
			if(pointer.rightChild!=null && //1.��ǰ�ڵ�û���ҽڵ�
					!(out!=null && out==pointer.leftChild) && //2.��ǰ�ڵ���������Ѿ�������������������������������һ���ڵ��ǵ�ǰ�ڵ����ڵ�
				    !(out!=null  && out.rightChild ==null)) //3.��ǰ�ڵ���������Ѿ�������������������������������һ���ڵ��ǵ�ǰ�ڵ����ڵ���������ϣ������󱻱����Ľڵ�������ζ�û���ҽڵ�
				stack.push(pointer.rightChild);
			stack.push(pointer);
			if(pointer.leftChild!=null && !(out!=null &&out==pointer.leftChild)
					 && !(out!=null && out.rightChild ==null))
				stack.push(pointer.leftChild);
			if(pointer.leftChild == null || (out!=null && out == pointer.leftChild) || 
				 (out!=null && out.rightChild == null)
					) //
			{
				out = stack.pop();
				System.out.print(out.data+",");
			}
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node root = LCA_Binary.construct();
		preOrder(root);
		//postOrder(root);
		//inOrder(root);
		
	}

}
