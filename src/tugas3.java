package Tugas3Final;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class EmployeeSalary {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		double[] salaries = {5_000_000, 6_500_000, 9_500_000};
		int[] percentages = {30, 32, 34, 36, 38};
		
		String repeat= "Y";
		
		do {
			System.out.print("Input Golongan ( " + getValidGroups(salaries) + " ): ");
			String group = scanner.next().toUpperCase();
			
			if(group.length() != 1 || group.charAt(0) < 'A' ||  group.charAt(0) >= ('A') + salaries.length) {
				System.out.println("Input golongan tidak valid, nilai yang diinput harus ( " + getValidGroups(salaries) +" )");
				continue;
			}
			
			double basicSalary = employeeGroup(group, salaries);
			
			System.out.println("Golongan anda tersedia, yaitu " + group);
			
			int overtime;
			
			while(true) {
				System.out.print("Input Jam lembur: ");
				
				if(!scanner.hasNextInt()) {
					System.out.println("Input tidak valid, jam lembur harus berupa angka");
					scanner.next();
					continue;
				}
				
				overtime = scanner.nextInt();
				
				
				if(overtime < 0) {
					System.out.println("Input tidak valid, jam lembur tidak boleh minus");
				} else {
					break;
				}
			}
			
			double overtimePay = basicSalary * overtimePercentage(overtime, percentages);
			
			printSalary(group, overtime, basicSalary, overtimePay);
			
			System.out.print("Apakah kamu mau menghitung ulang (Y/N):");
			repeat = scanner.next().toUpperCase();
			
		}while(repeat.equals("Y"));
		
		scanner.close();
		System.out.println("Terima kasih sudah menggunakan aplikasi ini");
	}
	
	static double employeeGroup(String group, double[] salaries) {
		
		int index = group.charAt(0) - 'A';
		
		return salaries[index];
	}
	
	static double overtimePercentage(int hour, int[] percentages) {
		int index;
		
			if(hour <= 0) {
				return 0;
			} else if(hour >= percentages.length ) {
				index = percentages.length - 1;
			} else {
				index = hour - 1;
			}
			
			return (double) percentages[index]/100;
	}
	
	static void printSalary(String group, int overtime, double basicSalary, double overtimePay) {
		
		NumberFormat formater = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("id-ID"));
		
		System.out.println("\n======  Rincian Gaji Karyawan  ======");
		System.out.println("Golongan: " + group);
		System.out.println("Gaji Pokok: " + formater.format(basicSalary));
		System.out.println("Jam Lembur: " + overtime);
		System.out.println("Bayaran Lembur: " + formater.format(overtimePay));
		System.out.println("Total Gaji: " + formater.format(basicSalary + overtimePay));
	}
	
	static String getValidGroups(double[] salaries) {
		
		String result = "";
		
		for(int i=0; i < salaries.length; i++) {
			char groupChar = (char) ('A' + i);
			result += groupChar;
			
			if(i < salaries.length - 1) {
				result += "/";
			}
		}
		
		
		return result;
	}
	
	

}
