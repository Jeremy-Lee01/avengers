package com.avengers.example;

public class Hanoi {

	public static void main(String[] args) {
		hanoi(3,"A","B","C");
	}

	/**
	 1.把n-1个圆盘从A经过C移动到B
	 2.把第n个圆盘从A移动到C
	 3.把n-1个小圆盘从B经过A移动到C
	 * @param n： 问题规模
	 * @param A: 起始盘子
	 * @param B：路过盘子
	 * @param C：目标盘子
	 */
	public static void hanoi(int n,String A,String B,String C) {
		if(n>0) {
			hanoi(n-1,A,C,B);
			System.out.println(A+"->"+C);
			hanoi(n-1,B,A,C);
		}
	}


}
