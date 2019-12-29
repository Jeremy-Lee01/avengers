package com.avengers.example;

public class Search {

	public static void main(String[] args) {
		int[] array =	{1,2,3,4,5,6,7,8,9};
		int position = binarySearch(array, 4);
		System.out.println(position);
	}

	/**
	 * 二分法查找
	 * @param array   查找的数组
	 * @param number  查找的数字
	 * @return		 查找数字对应下标
	 */
	public static int binarySearch(int[] array,int number) {
		int low  = 0;
		int high = array.length-1;
		while(low <=  high) {
			int mid = (low + high)/2;
			if(array[mid] < number) {
				low = mid + 1;
			} else if(array[mid] > number) {
				high =  mid - 1;
			} else {
				return mid;
			}
		}
		return -1;
	}

}
