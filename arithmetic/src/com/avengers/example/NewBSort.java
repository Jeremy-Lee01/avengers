package com.avengers.example;

public class NewBSort {

	public static void main(String[] args) {
		int[] array = {7, 9, 2, 8, 5, 1, 3, 4, 6};

		quickSort(array, 0, array.length - 1);
		Common.print(array);

		heapSort(array);
		Common.print(array);

		mergeSort(array, 0, 8);
		Common.print(array);
	}

	/**
	 * 快速排序
	 * 算法关键: temp要么是array[left],temp要么是array[right]
	 * @param array
	 * @param left
	 * @param right
	 */
	public static void quickSort(int[] array, int left, int right) {
		int mid;
		if (left < right) {        //至少两个元素
			mid = findMidPlace(array, left, right);      //mid中间元素下标
			quickSort(array, left, mid - 1);   //左边的部分
			quickSort(array, mid + 1, right);     //右边的部分
		}
	}

	/**
	 * 堆排序
	 * @param array 排序的列表
	 */
	public static void heapSort(int[] array) {
		//1.建堆
		//i 从最后一个非叶子节点到根节点，每一次调整这颗子树
		int n = array.length;
		//n/2-1 的含义:堆最后一个节点的父节点，堆的最后一个元素为n-1,最后一个元素的父亲节点为（n-1-1）/2
		for (int i = n / 2 - 1; i >= 0; i--) {
			//第二个参数n-1，没有实际的含义，只是防止数组越界
			sift(array, i, n - 1);
		}

		//2.挨个出数(将根节点和最后一个叶子节点交换，并将最后一个叶子节点排除树后，重新进行调整)
		int listLength = array.length;
		for (int j = listLength - 1; j > 0; j--) {
			Common.change(array, 0, j);
			sift(array, 0, j - 1);
		}
	}

	public static void mergeSort(int[] array, int low, int high) {
		//撞到南墙才回头，回头后有墙接着撞，左边排序OK，右边排序OK，然后左右两边在合并
		if (low < high) {
			int mid = (low + high) / 2;
			mergeSort(array, low, mid);//对左边序列进行归并排序
			mergeSort(array, mid + 1, high);//对右边序列进行归并排序
			merge(array, low, mid, high);//合并两个有序序列
		}
	}


	/**
	 * 归并排序的一次排序
	 * low~mid 有序   mid+1~high 有序
	 * @param array
	 * @param low
	 * @param mid
	 * @param high
	 */
	public static void merge(int[] array, int low, int mid, int high) {
		int i = low;
		int j = mid + 1;
		//数组的长度问题
		int[] newList = new int[high - low + 1];
		int position = 0;
		while (i <= mid && j <= high) {
			if (array[i] < array[j]) {
				newList[position] = array[i];
				i++;
			} else {
				newList[position] = array[j];
				j++;
			}
			position++;
		}
		while (i <= mid) {
			newList[position] = array[i];
			i++;
			position++;
		}
		while (j <= high) {
			newList[position] = array[j];
			j++;
			position++;
		}
		for (int k = 0; k < newList.length; k++) {
			//数组的下标问题
			array[k + low] = newList[k];
		}
	}

	/**
	 * 堆排序中的交换
	 * @param array 列表
	 * @param low    这棵树的开始位置
	 * @param high  这棵树的最后位置
	 */
	public static void sift(int[] array, int low, int high) {
		int tmp = array[low];
		int i = low;
		int j = 2 * i + 1;
		while (j <= high) {  //退出条件2:当前i位置是叶子节点，j位置超过了high
			//比较左右的孩子，找出最大的孩子
			if ((j + 1 <= high) && array[j + 1] > array[j]) {
				j = j + 1;  //如果右孩子存在并且更大，j指向右孩子
			}
			//父节点和孩子节点比较
			if (tmp < array[j]) {
				array[i] = array[j];
				i = j;
				j = 2 * i + 1;
			} else {        //退出条件1:tmp的值大于两个孩子的值
				break;
			}
		}
		array[i] = tmp;
	}

	public static int findMidPlace(int[] array, int left, int right) {
		//避免出现最坏的情况
		int random = Common.getRandom(left, right);
		Common.change(array, random, left);

		//temp要么是array[left],temp要么是array[right]
		//所以才有后续的array[right]和array[left]之间相互交换
		int tempElement = array[left];
		while (left < right) {
			while (left < right && array[right] >= tempElement) {
				right--;
			}
			Common.change(array, right, left);
			while (left < right && array[left] <= tempElement) {
				left++;
			}
			Common.change(array, left, right);
		}
		array[left] = tempElement;
		return left;
	}
}
