
package com.sqa.ay;

public class DisplayHelper {

	public static void multArray(Object[][] data) {

		for (int i = 0; i < data.length; i++) {
			System.out.print("[");
			for (int j = 0; j < data[i].length; j++) {
				System.out.print(data[i][j]);
				if (j < data[i].length - 1) {
					System.out.print("\t");
				}
			}
			System.out.print("]");
			System.out.print("\n");
		}
	}
}
