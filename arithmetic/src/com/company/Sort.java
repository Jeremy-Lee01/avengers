package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sort {

	public static void main(String[] args) {
		int[] myList =	{181,90,3,3,7,5,6,4,8,2,0};
		radixSort(myList);
		print(myList);
//		shellSort(myList);
//		print(myList);
//		countSort(myList,10);
//		print(myList);
//		Sort.bubbleSort(myList);
//		print(myList);
//
//		selectSort(myList);
//		print(myList);
//
//		insertSort(myList);
//		print(myList);

//		quickSort(myList,0,myList.length-1);
//		heapSort(myList);
//		print(myList);

//		int[] myList = {7,9,2,8,5,1,3,4,6};
//		mergeSort(myList,0,8);
//		print(myList);
//		bucketSort(myList,0,10,5);
//		print(myList);
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
	 * 冒泡排序
	 * @param myList
	 */
	public static void bubbleSort(int[] myList) {
		boolean exchange = false;    			//冒泡排序 优化
		for(int i =0 ;i<myList.length-1;i++) {  //表示 趟数
			for(int j=0;j<myList.length-i-1;j++) {  //表示 有序区和无序区的边界
				if(myList[j]>myList[j+1]) {
					change(myList,j,j+1);
					exchange = true;
				}
			}
			if (exchange == false){
				return ;
			}
		}
	}

	/**
	 * 选择排序
	 * @param myList
	 */
	public static void selectSort(int[] myList) {
		int min_pos;  //最小值的位置
		for (int i = 0;i<myList.length-1;i++) {		//表示	趟数
			min_pos = i;
			for (int j = i+1;j<myList.length;j++) {//表示	找到无序区最小值，保存最小值的位置
				if(myList[j] < myList[min_pos]) {
					min_pos = j;
				}
			}
			change(myList,min_pos,i);
		}
	}

	/**
	 * 插入排序	--扑克牌方式
	 * @param myList
	 */
	public static void insertSort(int[] myList) {
		int tempElement;
		for(int i= 1;i < myList.length;i++) {	//i是摸到牌的下标
			tempElement = myList[i];
			for(int j=i-1;j>=0;j--) {
				if(myList[j] > tempElement) {
					change(myList,j,j+1);
				} else {
					break;
				}
			}
		}
	}

	/**
	 * 快速排序
	 * @param myList
	 * @param left
	 * @param right
	 */
	public static void quickSort(int[] myList,int left,int right) {
		int mid ;
		if(left<right) {		//至少两个元素
			mid = partition(myList,left,right);  	  //mid中间元素下标
			quickSort(myList,left,mid-1);   //左边的部分
			quickSort(myList,mid+1,right);	 //右边的部分
		}
	}

	/**
	 * 堆排序
	 * @param myList 排序的列表
	 */
	public static void heapSort(int[] myList){
		//1.建堆
		//i 从最后一个非叶子节点到根节点，每一次调整这颗子树
		int n =  myList.length;
		//n/2-1 的含义:堆最后一个节点的父节点，堆的最后一个元素为n-1,最后一个元素的父亲节点为（n-1-1）/2
		for (int i = n/2 -1; i>=0 ;i--) {
			//第二个参数n-1，没有实际的含义，只是防止数组越界
			sift(myList,i,n-1);
		}

		//2.挨个出数(将根节点和最后一个叶子节点交换，并将最后一个叶子节点排除树后，重新进行调整)
		int listLength = myList.length;
		for (int j=listLength-1;j>0;j--) {
			change(myList,0,j);
			sift(myList,0,j-1);
		}
	}

	public static void mergeSort(int[] myList,int low,int high) {
		//撞到南墙才回头，回头后有墙接着撞，左边排序OK，右边排序OK，然后左右两边在合并
		if(low < high){
			int mid = (low+high)/2;
			mergeSort(myList,low,mid);//对左边序列进行归并排序
			mergeSort(myList,mid+1, high);//对右边序列进行归并排序
			merge(myList,low,mid,high);//合并两个有序序列
		}
	}



	/**
	 * 归并排序的一次排序
	 * low~mid 有序   mid+1~high 有序
	 * @param myList
	 * @param low
	 * @param mid
	 * @param high
	 */
	public static void merge(int[] myList,int low,int mid,int high) {
		int i =  low;
		int j =  mid + 1;
		//数组的长度问题
		int[] newList =  new int[high-low+1];
		int position = 0;
		while(i<=mid && j<=high) {
			if (myList[i]<myList[j]) {
				newList[position] = myList[i];
				i++;
			}else {
				newList[position] = myList[j];
				j++;
			}
			position++;
		}
		while(i<=mid) {
			newList[position] =  myList[i];
			i++;
			position++;
		}
		while(j<=high) {
			newList[position] = myList[j];
			j++;
			position++;
		}
		for(int k=0;k<newList.length;k++) {
			//数组的下标问题
			myList[k+low] = newList[k];
		}
	}
	/**
	 * 堆排序中的交换
	 * @param myList 列表
	 * @param low    这棵树的开始位置
	 * @param high  这棵树的最后位置
	 */
	public static  void sift(int[] myList,int low,int high) {
		int tmp =myList[low];
		int i =  low;
		int j =  2 * i+1;
		while (j <= high) {  //退出条件2:当前i位置是叶子节点，j位置超过了high
			//比较左右的孩子，找出最大的孩子
			if( (j+1 <= high) && myList[j+1] > myList[j]) {
				j = j + 1;  //如果右孩子存在并且更大，j指向右孩子
			}
			//父节点和孩子节点比较
			if(tmp < myList[j]) {
				myList[i] = myList[j];
				i = j;
				j = 2 * i +1;
			} else {		//退出条件1:tmp的值大于两个孩子的值
				break;
			}
		}
		myList[i] = tmp;
	}

	public static int partition(int[] myList,int left,int right) {
		//避免出现最坏的情况
		int random = getRandom(left, right);
		change(myList,random,left);
		int tempElement = myList[left] ;
		//temp要么是myList[left],temp要么是myList[right]
		//所以才有后续的myList[right]和myList[left]之间相互交换
		while (left < right) {
			while(left < right && myList[right] >= tempElement) {
				right --;
			}
			change(myList,right,left);
			while (left < right && myList[left] <= tempElement) {
				left ++;
			}
			change(myList,left,right);
		}
		myList[left] =  tempElement;
		return left;
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

	public static  int getRandom(int start,int end){
		return (int)(Math.random()*(end-start+1)+start);
	}


	private static void print(int[] myList) {
		for(int i =0;i<myList.length;i++) {
			System.out.print(myList[i]);
		}
	}
}
