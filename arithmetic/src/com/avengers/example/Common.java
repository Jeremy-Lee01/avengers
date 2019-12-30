package com.avengers.example;

import java.util.List;

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
			System.out.println(array[i]);
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

	/**
	 * 数组之间的copy
	 * @param from
	 * @param to
	 */
	public static void copytoArray(int[] from,int[] to) {
		for (int i =0 ;i<from.length;i++){
			to[i] = from[i];
		}
	}

	/**
	 * 将桶转换为数组
	 * @param bucket
	 * @param length
	 * @return
	 */
	public static int[] bucketToArray(List<Integer>[] bucket,int length) {
		int[] array = new int[length];
		int position = 0;
		for (int i =0;i<bucket.length;i++) {
			for(int j=0;j<bucket[i].size();j++){
				array[position] = bucket[i].get(j);
				position ++;
			}
		}
		return array;
	}
}
