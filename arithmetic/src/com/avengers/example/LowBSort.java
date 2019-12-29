package com.avengers.example;

public class LowBSort {

	public static void main(String[] args) {

		int[] array = {7, 9, 2, 8, 5, 1, 3, 4, 6};
		bubbleSort(array);
		Common.print(array);

		selectSort(array);
		Common.print(array);

		insertSort(array);
		Common.print(array);
	}

	/**
	 * 冒泡排序
	 * 关键点：趟数和有序边界
	 * @param array
	 */
	public static void bubbleSort(int[] array) {
		boolean exchange = false;                //冒泡排序优化
		for (int i = 0; i < array.length - 1; i++) {  //表示 趟数
			for (int j = 0; j < array.length - i - 1; j++) {  //表示 有序区和无序区的边界
				if (array[j] > array[j + 1]) {
					Common.change(array, j, j + 1);
					exchange = true;
				}
			}
			if (exchange == false) {
				return;
			}
		}
	}

	/**
	 * 选择排序
	 * 关键点：趟数和有序边界
	 * @param array
	 */
	public static void selectSort(int[] array) {
		int min_pos;  //最小值的位置
		for (int i = 0; i < array.length - 1; i++) {        //表示 趟数
			min_pos = i;
			for (int j = i + 1; j < array.length; j++) {//表示	找到无序区最小值，保存最小值的位置
				if (array[j] < array[min_pos]) {
					min_pos = j;
				}
			}
			Common.change(array, min_pos, i);
		}
	}

	/**
	 * 插入排序
	 * 保持数组中tempElement之前的有序
	 * @param array
	 */
	public static void insertSort(int[] array) {
		int tempElement;
		for (int i = 1; i < array.length; i++) {    //i是摸到牌的下标
			tempElement = array[i];
			for (int j = i - 1; j >= 0; j--) {
				if (array[j] > tempElement) {
					Common.change(array, j, j + 1);
				} else {
					break;
				}
			}
		}
	}
}
