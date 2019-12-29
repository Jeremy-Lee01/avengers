package com.avengers.example;

public class Common {

	/**
	 * 数组之间进行交换
	 * @param array
	 * @param before
	 * @param end
	 */
	public static void change(int[] array, int before, int end) {
		int temp;
		temp = array[before];
		array[before] = array[end];
		array[end] = temp;
	}

	/**
	 * 打印 array
	 * @param array
	 */
	public static void print(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
		}
	}

	/**
	 *
	 * @param start 开始数字
	 * @param end   结束数字
	 * @return 开始数字~结束数字之间的数字
	 */
	public static int getRandom(int start, int end) {
		return (int) (Math.random() * (end - start + 1) + start);
	}
}
