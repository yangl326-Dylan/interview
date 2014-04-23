package Source;

import java.util.Arrays;

public class MyMaxHeap {

	static int k = 0;
	//���ѷ���
	public static void buildMaxHeap(int [] A){
		k = A.length;
		for(int i= k/2-1;i>=0;i--)
		{
			heapify(A,i);
		}
	}
	//���������ڵ�
	public static void heapify(int [] A, int i)
	{
		int larger = i;
		int left = 2*i+1;
		int right = 2*i+2;
		if(left<=k-1)
		{
			if(A[left]>A[larger])
				larger = left;
		}
		if(right<=k-1)
		{
			if(A[right]>A[larger])
				larger = right;
		}
		if(larger == i)
			return;
		else
		{
			int temp = A[larger];
			A[larger] = A[i];
			A[i] = temp;
			heapify(A,larger);
		}
	}
	
	/**
	 * �������ѣ�����k����С��Ԫ��
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] array={1,3,5,7,2,4,6,8};
		int[] heap = new int[4];
		
		for(int i=0;i<4;i++)
		{
			heap[i] = array[i];
		}
		buildMaxHeap(heap);
		
		for(int i=4;i<array.length;i++)
		{
			if(array[i]<heap[0])
			{
				heap[0] = array[i];
				heapify(heap,0);
			}
		}
		System.out.println(Arrays.toString(heap));
	}

}
