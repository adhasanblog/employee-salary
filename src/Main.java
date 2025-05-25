import java.util.Scanner;
import java.text.NumberFormat;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String repeat = "Y";

        do {

            System.out.print("Input Golongan (A/B/C): ");
            String group = scanner.next().toUpperCase();

            if(!group.equals("A") && !group.equals("B") && !group.equals("C")){
                System.out.println("Input golongan tidak valid, nilai harus (A/B/C)");
                continue;
            }

            double basicSalary = employeeGroup(group);

            System.out.println("Golongan anda ditemukan, yaitu " + group);

            int overtime;

            while (true){
                System.out.print("Input jam lembur: ");

                if(!scanner.hasNextInt()){
                    System.out.println("Input tidak valid, jam lembur harus berupa angka");
                    scanner.next();
                    continue;
                }

                overtime = scanner.nextInt();

                if(overtime < 0){
                    System.out.println("Input tidak valid, jam lembur tidak boleh minus");
                } else {
                    break;
                }
            }

            double overtimePay = basicSalary * overtimePercentage(overtime);
            NumberFormat formatter = NumberFormat.getNumberInstance(Locale.forLanguageTag("id-ID"));

            System.out.println("\n====== Rincian Gaji Karyawan ======");
            System.out.println("Golongan: " + group);
            System.out.println("Gaji Pokok: Rp. " + formatter.format(basicSalary));
            System.out.println("Jam Lembur: " + overtime);
            System.out.println("Bayaran Lembur: Rp. " + formatter.format(overtimePay));
            System.out.println("Total Gaji: Rp. " + formatter.format( basicSalary + overtimePay));

            System.out.print("Apakah kamu ingin menghitung ulang (Y/N): ");
            repeat = scanner.next().toUpperCase();

        } while (repeat.equals("Y"));

    }

    static double employeeGroup(String group){
        double salary;

        if(group.equals("A")){
            salary = 5_000_000;
        }else if(group.equals("B")){
            salary = 6_500_000;
        } else {
            salary = 9_500_000;
        }

        return  salary;
    }

    static  double overtimePercentage(int hour){
        double percentage;

        if(hour == 1){
            percentage = (double) 30/100;
        } else if(hour == 2){
            percentage = (double) 32/100;
        } else if(hour == 3) {
            percentage = (double) 34 / 100;
        }else if(hour == 4) {
            percentage = (double) 36 / 100;
        }else if(hour >= 5) {
            percentage = (double) 38 / 100;
        } else {
            percentage = 0;
        }

        return percentage;
    }
}