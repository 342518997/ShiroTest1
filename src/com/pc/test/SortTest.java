package com.pc.test;


/**
 * 
 * @author asus ���
 *  
 */
public class SortTest {
	public static void main(String[] args) {
		int[] numbers = { 9, 5, 8, 6, 8, 4, 25, 69 };
		int low = 0;
		int end = numbers.length - 1;
		Sort(numbers, low, end);
		for (int i = 0; i < numbers.length; i++) {
			System.out.print(numbers[i] + " ");
		}
	}

	public static void Sort(int[] numbers, int low, int end) {
		int start = low;
		int size = end;
		int arrt = numbers[start];
		while(size>start){
			//�Ӻ���ǰ�Ƚ�
			while(size>start&&numbers[size]>=arrt){
				size--;
				if(numbers[size]<=arrt){
					int temp=numbers[size];
					numbers[size]=numbers[start];
					numbers[start]=temp;
				}
				
			}
			//��ǰ����Ƚ�
			while(size>start&&numbers[start]<=arrt){
				start++;
				if(numbers[start]>=arrt){
					int temp=numbers[start];
					numbers[start]=numbers[size];
					numbers[size]=temp;
					
				}
			}
		}
			System.out.println(start+ " " +low);
			System.out.println(size+" "+end);
			System.out.println("");
		 //��ʱ��һ��ѭ���ȽϽ������ؼ�ֵ��λ���Ѿ�ȷ���ˡ���ߵ�ֵ���ȹؼ�ֵС���ұߵ�ֵ���ȹؼ�ֵ��
		 //�������ߵ�˳���п����ǲ�һ���ģ���������ĵݹ����
		 //�ݹ�
		if(start>low){
			Sort(numbers, low, end-1);
		}
		if(size<end){
			Sort(numbers, low+1, end);
			
		}
	}
}
