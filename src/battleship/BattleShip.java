package battleship;

import java.util.Scanner;

public class BattleShip {

	public static void main(String[] args) {
		int[][] fieldUser12 = new int[12][12];
		int[][] fieldComp12 = new int[12][12];
		Scanner sc = new Scanner(System.in);
		int[] ships = { 4, 3, 3, 2, 2, 2, 1, 1, 1, 1 };
		for (int i = 0; i < ships.length; i++) {
			drawShip(fieldComp12, ships[i]);
			drawShip(fieldUser12, ships[i]);
		}
		System.out.println("Кораблі компа:");
		int[][] fieldComp = matrixTo10(fieldComp12);
		print(fieldComp);
		System.out.println("\nВаші кораблі:");
		int[][] fieldUser = matrixTo10(fieldUser12);
		print(fieldUser);

		while (true) {
			shootingUser(fieldComp, sc);
			System.out.println("Кораблі компа:");
			printHide(fieldComp);
			if (whoWins(fieldComp)) {
				System.out.println("Ви виграли!");
				break;
			}
			shootingComp(fieldUser);
			System.out.println("\nВаші кораблі:");
			printHide(fieldUser);
			if (whoWins(fieldUser)) {
				System.out.println("Комп виграв!");
				break;
			}
		}
	}

	static void print(int[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if (array[i][j] == 0)
					System.out.print("· ");
				if (array[i][j] == 1)
					System.out.print("■ ");
			}
			System.out.println();
		}
	}

	static void printHide(int[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if (array[i][j] == 0)
					System.out.print("· ");
				if (array[i][j] == 1)
					System.out.print("· ");
				if (array[i][j] == 2)
					System.out.print("* ");
				if (array[i][j] == 3)
					System.out.print("x ");
			}
			System.out.println();
		}
	}

	static int random(int min, int max) {
		return (int) Math.round(Math.random() * (max - min) + min);
	}

	static void drawShip(int[][] arr, int length) {
		int x = 0;
		int y = 0;
		int n = 0;
		int z = random(0, 1);
		if (z == 0) {
			x = random(1, 10);
			y = random(1, 11 - length);
			for (int i = -1; i < length + 1; i++) {
				if (arr[x - 1][y + i] == 0 && arr[x][y + i] == 0
						&& arr[x + 1][y + i] == 0)
					n++;
			}
		} else {
			x = random(1, 11 - length);
			y = random(1, 10);
			for (int i = -1; i < length + 1; i++) {
				if (arr[x + i][y - 1] == 0 && arr[x + i][y] == 0
						&& arr[x + i][y + 1] == 0)
					n++;
			}
		}
		if (n == length + 2) {
			for (int i = 0; i < length; i++) {
				arr[x][y] = 1;
				if (z == 1)
					x++;
				else
					y++;
			}
		} else
			drawShip(arr, length);
	}

	static void shootingUser(int[][] arr, Scanner sc) {
		System.out.println("\nВведіть рядок для стрільби:");
		int x = sc.nextInt() - 1;
		System.out.println("Введіть стовбець для стрільби:");
		int y = sc.nextInt() - 1;
		if (x < 0 || x > 9 || y < 0 || y > 9) {
			System.out.println("Вводіть допустимі значення!");
			shootingUser(arr, sc);
		} else {
			if (arr[x][y] == 0) {
				arr[x][y] = 2; // мимо
			} else if (arr[x][y] == 1)
				arr[x][y] = 3; // попав
			else if (arr[x][y] == 2 || arr[x][y] == 3) {
				System.out.println("Ви вже стріляли по цьому місцю!");
				shootingUser(arr, sc);
			}
		}
	}

	static void shootingComp(int[][] arr) {
		int x = random(0, 9);
		int y = random(0, 9);
		if (arr[x][y] == 0) {
			arr[x][y] = 2; // мимо
		} else if (arr[x][y] == 1)
			arr[x][y] = 3; // попав
		else {
			shootingComp(arr);
		}
	}

	static boolean whoWins(int[][] array) {
		int n = 0;
		boolean b = false;
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if (array[i][j] == 3) {
					n++;
				}
			}
		}
		System.out.println("Очки: " + n);
		if (n == 20)
			b = true;
		return b;
	}

	static int[][] matrixTo10(int[][] array) {
		int[][] arr = new int[10][10];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				arr[i][j] = array[i + 1][j + 1];
			}
		}
		return arr;
	}
}
