package com.avengers.example;

import java.util.ArrayList;
import java.util.List;

public class OtherSort {

	public static void main(String[] args) {
		int[] array =	{181,90,3,3,7,5,6,4,8,2,0};
//		radixSort(array);
//		Common.print(array);
//
//		shellSort(array);
//		Common.print(array);
//
//		countSort(array,10);
//		Common.print(array);

		bucketSort(array,0,181,5);
		Common.print(array);
	}


	/**
	 * 基数排序：
	 * 关键点：如何取出一个数的个位，十位，百位。先除10，后求余数
	 * @param array
	 */
	public static void radixSort(int[] array) {
		//找出最大的数
		int maxNum = array[0];
		for(int i = 1;i<array.length;i++){
			if(maxNum < array[i]) {
				maxNum =  array[i];
			}
		}
		//有多少位，循环多少次，100
		int i = 0;
		while (Math.pow(10,i) <= maxNum) {
			List<Integer>[] lists = listToBucket(array, i);
			Common.copytoArray(Common.bucketToArray(lists, array.length),array);
			i+=1;
		}
	}

	public static List<Integer>[] listToBucket(int[] array,int place) {
		//创建10个桶
		List<Integer>[] bucket =  new ArrayList[10];
		for(int i =0 ;i<bucket.length;i++) {
			bucket[i] = new ArrayList<Integer>();
		}

		for(int i=0;i<array.length;i++) {
			//按位进行找桶
			int j = array[i]/((int)Math.pow(10,place))%10;
			bucket[j].add(array[i]);
		}
		return bucket;
	}


	/**
	 * 希尔排序:分组插入排序
	 * 保持数组中tempElement之前的有序
	 * @param array
	 */
	public static void shellSort(int[] array) {
		int d = array.length / 2;
		while(d > 0){
			shellSortGap(array,d);
			d = d / 2;
		}
	}

	public static void shellSortGap(int[] array,int gap) {
		int tempElement;
		for(int i= 1;i < array.length;i++) {	//i是摸到牌的下标
			tempElement = array[i];
			for(int j=i-gap;j>=0;j=j-gap) {
				if(array[j] > tempElement) {
					Common.change(array,j,j+gap);
				} else {
					break;
				}
			}
		}
	}

	/**
	 * 计数排序:
	 * @param array
	 * @param maxNum
	 */
	public static void countSort(int[] array,int maxNum) {
		int[] count =  new int[maxNum];
		for(int num = 0;num < array.length;num++) {
			count[array[num]] +=1;
		}
		int position = 0;
		for(int i = 0;i<count.length;i++) {
			for(int j = count[i];j>0;j--){
				array[position] = i;
				position ++;
			}
		}
	}

	/**
	 * 桶排序：
	 * @param array 列表
	 * @param minNum 最小值
	 * @param maxNum 最大值
	 * @param bucketNum 桶的个数
	 */
	public static void bucketSort(int[] array,int minNum,int maxNum,int bucketNum) {
		int n =  (maxNum - minNum + 1)/bucketNum;   //每个桶中有多少数字
		List<Integer>[] bucket = new ArrayList[bucketNum];
		for(int i =0 ;i<bucket.length;i++) {
			bucket[i] = new ArrayList<Integer>();
		}
		for(int i = 0;i<array.length;i++) {
			int num = array[i];
			int bucketPostition = ((num -minNum)/n)==0?0:((num -minNum)/n)-1;
			bucket[bucketPostition].add(num); //将数据放在桶中
			//维护桶有序
			int k = bucket[bucketPostition].size() -1; //桶内最后元素的坐标
			int tmp = bucket[bucketPostition].get(k);
			int j =k -1;
			while (j>=0 && tmp<bucket[bucketPostition].get(j)) {
//				change(bucket[num-minNum/n],j,j+1);
				Integer changeNum = bucket[(num -minNum) / n].get(j);
				bucket[(num -minNum) / n].set(j,bucket[(num -minNum) / n].get(j+1));
				bucket[(num -minNum) / n].set(j+1,changeNum);
				j = j -1;
			}
		}
		//循环遍历
		int[] resultArray = Common.bucketToArray(bucket,array.length);
		Common.copytoArray(resultArray,array);
	}
}
