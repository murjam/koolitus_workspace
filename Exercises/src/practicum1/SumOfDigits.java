package practicum1;

import java.util.Scanner;

public class SumOfDigits {
	
	public static int sum(int number) {
		int sum = 0;
		String line = String.valueOf(number);
		int length = line.length();
		
		for (int i = 0; i < length; i++) {
			char c = line.charAt(i);
			
//			sum += Character.digit(c, 10);
			
			String digit = Character.toString(c);
			sum += Integer.parseInt(digit);
		}
		return sum;
	}

	public static void main(String[] args) {
		
		System.out.println("Insert a number");
		
		Scanner scanner = new Scanner(System.in);
		int num = scanner.nextInt();
		scanner.close();
		
	
		System.out.format("The sum of digits: %d", sum(num));
		
	}
	
}
