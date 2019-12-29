package com.avengers.example;

import java.util.ArrayList;
import java.util.List;

public class Sort {

	public static void main(String[] args) {
		int[] myList =	{181,90,3,3,7,5,6,4,8,2,0};
		radixSort(myList);
		print(myList);
		shellSort(myList);
		print(myList);
		countSort(myList,10);
		print(myList);



		print(myList);
		bucketSort(myList,0,10,5);
		print(myList);
	}


	/**
	 * 基数排序
	 * @param myList
	 */
	public static void radixSort(int[] myList) {
		//找出最大的数
		int maxNum = myList[0];
		for(int i = 1;i<myList.length;i++){
			if(maxNum < myList[i]) {
				maxNum =  myList[i];
			}
		}
		//有多少位，循环多少次，100
		int i = 0;
		while (Math.pow(10,i) <= maxNum) {
			List<Integer>[] lists = listToBucket(myList, i);
			copytoArray(bucketToArray(lists, myList.length),myList);
			i+=1;
		}
	}

	public static List<Integer>[] listToBucket(int[] myList,int place) {
		//创建10个桶
		List<Integer>[] bucket =  new ArrayList[10];
		for(int i =0 ;i<bucket.length;i++) {
			bucket[i] = new ArrayList<Integer>();
		}

		for(int i=0;i<myList.length;i++) {
			//按位进行找桶
			int j = myList[i]/((int)Math.pow(10,place))%10;
			bucket[j].add(myList[i]);
		}
		return bucket;
	}

	public static int[] bucketToArray(List<Integer>[] bucket,int length) {
		int[] mylist = new int[length];
		int position = 0;
		for (int i =0;i<bucket.length;i++) {
			for(int j=0;j<bucket[i].size();j++){
				mylist[position] = bucket[i].get(j);
				position ++;
			}
		}
		return mylist;
	}

	public static void copytoArray(int[] from,int[] to) {
		for (int i =0 ;i<from.length;i++){
			to[i] = from[i];
		}
	}


	/**
	 * 希尔排序
	 * @param myList
	 */
	public static void shellSort(int[] myList) {
		int d = myList.length / 2;
		while(d > 0){
			shellSortGap(myList,d);
			d = d / 2;
		}
	}


	public static void shellSortGap(int[] myList,int gap) {
		int tempElement;
		for(int i= 1;i < myList.length;i++) {	//i是摸到牌的下标
			tempElement = myList[i];
			for(int j=i-gap;j>=0;j=j-gap) {
				if(myList[j] > tempElement) {
					change(myList,j,j+gap);
				} else {
					break;
				}
			}
		}
	}

	/**
	 * 计数排序
	 * @param myList
	 * @param maxNum
	 */
	public static void countSort(int[] myList,int maxNum) {
		int[] count =  new int[maxNum];
		for(int num = 0;num < myList.length;num++) {
			count[myList[num]] +=1;
		}
		int position = 0;
		for(int i = 0;i<count.length;i++) {
			for(int j = count[i];j>0;j--){
				myList[position] = i;
				position ++;
			}
		}
	}

	/**
	 *
	 * @param myList 列表
	 * @param minNum 最小值
	 * @param maxNum 最大值
	 * @param bucketNum 桶的个数
	 */
	public static void bucketSort(int[] myList,int minNum,int maxNum,int bucketNum) {
		int n =  (maxNum - minNum + 1)/bucketNum;
		List<Integer>[] bucket = new ArrayList[bucketNum];
		for(int i =0 ;i<bucket.length;i++) {
			bucket[i] = new ArrayList<Integer>();
		}
		for(int i = 0;i<myList.length;i++) {
			int num = myList[i];
			bucket[(num -minNum)/n ].add(num); //将数据放在桶中
			//维护桶有序
			int k = bucket[(num -minNum)/n].size() -1; //桶内最后元素的坐标
			int tmp = bucket[(num -minNum)/n].get(k);
			int j =k -1;
			while (j>=0 && tmp<bucket[(num -minNum)/n].get(j)) {
//				change(bucket[num-minNum/n],j,j+1);
				Integer changeNum = bucket[(num -minNum) / n].get(j);
				bucket[(num -minNum) / n].set(j,bucket[(num -minNum) / n].get(j+1));
				bucket[(num -minNum) / n].set(j+1,changeNum);
				j = j -1;
			}
		}
		//循环遍历
		int position = 0;
		for(int i =0;i<bucket.length;i++) {
			for(int j = 0;j<bucket[i].size();j++) {
				myList[position] = bucket[i].get(j);
				position++;
			}
		}
	}







	/**
	 * 数组之间进行交换
	 * @param myList
	 * @param before
	 * @param end
	 */
	public static void change(int[] myList,int before,int end) {
		int temp ;
		temp = myList[before];
		myList[before] = myList[end];
		myList[end] = temp;
	}




	private static void print(int[] myList) {
		for(int i =0;i<myList.length;i++) {
			System.out.print(myList[i]);
		}
	}
}
