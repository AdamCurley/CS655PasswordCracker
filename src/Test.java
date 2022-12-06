import java.util.Scanner;

public class Test {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String args[]) throws Exception {

        System.out.println("Please enter your password: ");
        String input = scanner.nextLine();
        System.out.println("Please enter the number of workers: (The range is in 1 - 5)");
        int number = scanner.nextInt();

        input = Md5.hashMd5(input) + " " + number;

        for (int i = 0; i < number; i++) {
            MasterHandler masterHandler = new MasterHandler(i, input);
            masterHandler.start();
        }
    }
}
