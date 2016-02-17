package practicum1;

public class Table {
	
	public static void main(String[] args) {
	
		int size = 10;

		for (int row = 0; row < size; row++) {

			for (int col = 0; col < size; col++) {
				
				int number = Math.min(size - col - 1, size - row - 1);
				
				System.out.format("%2d", number);
			}
			System.out.println();
		}
	}
	
}
