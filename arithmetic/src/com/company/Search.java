package com.company;

public class Search {

	public static void main(String[] args) {
		int[] myList =	{1,2,3,4,5,6,7,8,9};
		int num = binarySearch(myList, 11);
		System.out.println(num);
	}

	/**
	 二分法查找
	 */
	public static int binarySearch(int[] myList,int val) {
		int low  = 0;
		int high = myList.length-1;
		while(low <=  high) {
			int mid = (low + high)/2;
			if(myList[mid] < val) {
				low = mid + 1;
			} else if(myList[mid] > val) {
				high =  mid - 1;
			} else {
				return mid;
			}
		}
		return -1;
	}


}
