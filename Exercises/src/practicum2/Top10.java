package practicum2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Top10 {
	
	public static final String FILE_NAME = "data.txt";
	
	public static ArrayList<Athlete> readData() throws Exception {
		try {
			ObjectInputStream input = new ObjectInputStream(
					new FileInputStream(new File(FILE_NAME)));
			ArrayList<Athlete> data = (ArrayList<Athlete>) input.readObject();
			input.close();
			System.out.format("Read %s\n", FILE_NAME);
			return data;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		throw new Exception("Could not read data");
	}
	
	public static void writeData(ArrayList<Athlete> data) {
		try {
			ObjectOutputStream output = new ObjectOutputStream(
					new FileOutputStream(new File(FILE_NAME)));
			output.writeObject(data);
			output.close();
			System.out.format("Wrote %s\n", FILE_NAME);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Athlete readAthlete() {
		Scanner scanner = new Scanner(System.in);
		
		Athlete athlete = new Athlete();
		System.out.println("Insert the athete's result");
		athlete.result = scanner.nextDouble();

		System.out.println("Insert the athete's name");	
		athlete.name = scanner.next();
		
		scanner.close();
		
		return athlete;
	}

	public static void main(String[] args) throws IOException {
		
		ArrayList<Athlete> athletes;
		try {
			athletes = readData();
		}
		catch (Exception e) {
			athletes = new ArrayList<Athlete>();
		}
		
		for (Athlete athlete : athletes) {
			System.out.println(athlete);
		}
		
		Athlete athlete = readAthlete();
		
		athletes.add(athlete);
		
		Collections.sort(athletes, Collections.reverseOrder());
		
		writeData(athletes);
	}
	
}