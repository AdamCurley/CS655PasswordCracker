import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
    private static Scanner scanner = new Scanner(System.in);
    public static List<Master> list = new ArrayList<>();
    public static boolean canStart = false;
    private static Lock locker = new ReentrantLock();
    public static String[] ip = {
            "pcvm3-19.instageni.osu.edu",
//            "localhost",
            "pcvm3-21.instageni.osu.edu",
//            "csa1.bu.edu",
            "pcvm3-22.instageni.osu.edu",
            "pcvm3-23.instageni.osu.edu",
            "pcvm3-24.instageni.osu.edu"
    };
    public static int port = 58888;

    public static void start() throws IOException, InterruptedException {
        String input; int number = 5;
        System.out.println("If you want to quit, press [q] or press [c] to continue:");
        if (scanner.nextLine().equals("q")) {
            input = "q";
        } else {
            System.out.println("Please enter your password: ");
            input = scanner.nextLine();
            System.out.println("Please enter the number of workers: (The range is in 1 - 5)");
            number = scanner.nextInt();
            input = Md5.hashMd5(input) + " " + number;
            scanner.nextLine();
            System.out.println("Please enter the first worker ip: ");
            ip[0] = scanner.nextLine();
            System.out.println("You entered: " + ip[0]);
            System.out.println("Please enter the second worker ip: ");
            ip[1] = scanner.nextLine();
            System.out.println("You entered: " + ip[1]);
            System.out.println("Please enter the third worker ip: ");
            ip[2] = scanner.nextLine();
            System.out.println("You entered: " + ip[2]);
            System.out.println("Please enter the fourth worker ip: ");
            ip[3] = scanner.nextLine();
            System.out.println("You entered: " + ip[3]);
            System.out.println("Please enter the fifth worker ip: ");
            ip[4] = scanner.nextLine();
            System.out.println("You entered: " + ip[4]);
            System.out.println("Please enter the port: ");
            port = scanner.nextInt();
            System.out.println("You entered: " + port);
        }

        for (int i = 0; i < number; i++) {
            Master master = new Master();
            master.startConnection(ip[i], port);
            list.add(master);
        }
        for (int i = 0; i < number; i++) {
            MasterHandler masterHandler = new MasterHandler(i, input, list.get(i), number);
            masterHandler.start();
        }
        Thread.sleep(5000);
        if (input.equals("q")) {
            for (Master master : list) {
                master.stopConnection();
            }
        }
        while (MasterHandler.rest > 1) {
            Thread.sleep(1000);
        }
        locker.lock();
        Test.canStart = true;
        locker.unlock();
    }
    public static void main(String args[]) throws Exception {
        start();
        while (true) {
            while(!canStart) {
                Thread.sleep(1000);
            }
            scanner.nextLine();
            start();
//            scanner.nextLine();
        }

    }
}
