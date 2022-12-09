
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Worker {
    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static PrintWriter out;
    private static BufferedReader in;
    private static boolean isRunning = true;
    private static Lock locker = new ReentrantLock();
    public static void start(int port, int n) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Worker" + n + " is waiting......");
        clientSocket = serverSocket.accept();
        System.out.println("Client: " + clientSocket.getInetAddress().getHostAddress()+", port: " + clientSocket.getPort() + "is connected!");
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String input;
        while ((input = in.readLine()) != null) {
            if (input.equals("stop")) {
                Stop stop = new Stop();
                Thread t = new Thread(stop);
                t.start();
            } else if (input.equals("q")) {
                stop();
            } else {
                locker.lock();
                isRunning = true;
                locker.unlock();
                Cracker cracker = new Cracker(input, n);
                Thread t = new Thread(cracker);
                t.start();
            }
        }

    }

    public static void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
    public boolean isStop() {
        return false;
    }
    public static class Stop implements Runnable {

        public void run() {
            locker.lock();
            isRunning = false;
            locker.unlock();

        }
    }
    public static class Cracker implements Runnable {
        private final int n;
        private String input;
        public Cracker(String s, int n) {
            input = s;
            this.n = n;
        }
        public void run() {
            try {
                String[] s = input.split(" ", 0);
                String hash = s[0];
                int number = Integer.parseInt(s[1]);

                double startTime = System.currentTimeMillis();
                String password = Md5.cracker(n, hash, number);

                if (password == null) {
                    out.println("Worker" + n + " doesn't crack the password");
                }

                double elapsedTime = System.currentTimeMillis() - startTime;
                double elapsedSeconds = elapsedTime / 1000.0;
//                long elapsedMinutes = elapsedSeconds / 60;

                System.out.println("cracking time: " + elapsedSeconds);
                out.println(password);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static class Md5 {
        public static char[] ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        public static final int PASSWORD_MAX_LENGTH = 5;

        public static String hashMd5(String input) throws IOException {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] messageDigest = md.digest(input.getBytes());
                BigInteger no = new BigInteger(1, messageDigest);

                StringBuilder hashText = new StringBuilder(no.toString(16));

                while (hashText.length() < 32) {
                    hashText.insert(0, "0");
                }
                return hashText.toString();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }

        public static String cracker(int start, String hash, int total) throws IOException {
            String password = "";
            char[] digits = {'a', 'a', 'a', 'a', 'a'};

            for (int i = (ALPHABET.length / total) * (start - 1); start == total ? i < ALPHABET.length : i < (ALPHABET.length / total) * start; i++) {
                for (char element : ALPHABET) {
                    for (char item : ALPHABET) {
                        for (char value : ALPHABET) {
                            for (char c : ALPHABET) {
                                digits[0] = ALPHABET[i];
                                digits[1] = element;
                                digits[2] = item;
                                digits[3] = value;
                                digits[4] = c;

                                password = String.valueOf(digits);
                                //System.out.println("Worker" + start + " is cracking: " + password);
                                if (hash.equals(hashMd5(password))) {
                                    return password;
                                }

                                locker.lock();
                                if (!isRunning) {
                                    locker.unlock();
                                    return "worker" + start + " stopped";
                                }
                                locker.unlock();
                            }
                        }
                    }
                }
            }

            return null; //does nothing but code won't run without it (until complete)
        }
    }
    public static void main(String[] args) throws IOException {
//        WorkerOne.start(58888, 1);
        Worker.start(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    }
}
