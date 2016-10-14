package skyline;

import java.util.Scanner;

public class SkylineTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int size = sc.nextInt();
		sc.nextLine();
		Skyline skyline = new Skyline(size);

		sc.useDelimiter("\\D+");
		for(int i = 0; i < size; i++){
			int left = sc.nextInt();
			int height = sc.nextInt();
			int right = sc.nextInt();
			skyline.addBuilding(left, height, right);
		}

		skyline.printSkyline();
		sc.close();
	}

}
