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
        }

        for (int i = 0; i < number; i++) {
            Master master = new Master();
            master.startConnection(ip[i], 58888);
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
