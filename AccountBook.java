import java.io.*;
import java.util.*;

public class AccountBook {
    public static void main(String[] args) throws IOException {
        File file = new File("records.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] fields = line.split("\t");
            String date = fields[0];
            String type = fields[1];
            double amount = Double.parseDouble(fields[2]);
            String note = fields[3];

            System.out.printf("%s\t%s\t%.2f\t%s\n", date, type, amount, note);
        }
        scanner.close();

        PrintWriter writer = new PrintWriter(new FileWriter(file, true));
        Scanner input = new Scanner(System.in);
        System.out.println("请输入日期（例如2023-05-25）：");
        String date = input.nextLine();
        System.out.println("请输入收支（支出为-negative，收入为positive）：");
        String type = input.nextLine();
        System.out.println("请输入金额：");
        double amount = input.nextDouble();
        input.nextLine(); // consume the remaining newline character
        System.out.println("请输入说明：");
        String note = input.nextLine();

        Record record = new Record(date, type, amount, note);
        writer.println(record);
        writer.close();
    }
}

class Record {
    private String date;
    private String type;
    private double amount;
    private String note;

    public Record(String date, String type, double amount, String note) {
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.note = note;
    }

    public String toString() {
        return String.format("%s\t%s\t%.2f\t%s", date, type, amount, note);
    }
}

