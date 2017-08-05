package com.pc.test;


/**
 * 
 * @author asus 彭冲
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
			//从后往前比较
			while(size>start&&numbers[size]>=arrt){
				size--;
				if(numbers[size]<=arrt){
					int temp=numbers[size];
					numbers[size]=numbers[start];
					numbers[start]=temp;
				}
				
			}
			//从前往后比较
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
		 //此时第一次循环比较结束，关键值的位置已经确定了。左边的值都比关键值小，右边的值都比关键值大，
		 //但是两边的顺序还有可能是不一样的，进行下面的递归调用
		 //递归
		if(start>low){
			Sort(numbers, low, end-1);
		}
		if(size<end){
			Sort(numbers, low+1, end);
			
		}
	}
}
