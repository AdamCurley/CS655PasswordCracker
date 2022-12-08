import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MasterHandler extends Thread{
    private int number;
    private String input;
    private Master master;
    public static int rest;
    private int total;
    private Lock lock = new ReentrantLock();

//    public static int[] port = {37211, 37212, 37213, 37214, 37215};
    public MasterHandler(int number, String input, Master master, int total) {
        this.number = number;
        this.input = input;
        this.master = master;
        this.total = total;
        rest = total;
    }

    public void run() {
        try {

            double startTime = System.currentTimeMillis();

            master.sendMessage(input);
            String response = master.receiveMessage();

            while (response.length() != 5) {
                lock.lock();
                rest--;
                lock.unlock();
            }
            System.out.println("Password is: " + response);

            double elapsedTime = System.currentTimeMillis() - startTime;
            double elapsedSeconds = elapsedTime / 1000.0;

            System.out.println("RTT: " + elapsedSeconds + "s");
            System.out.println("Throughput: " + (float)(input + response).getBytes().length * 8L / elapsedSeconds + "bps");

            //send msg to inform all the workers that found the password
            for (int i = 0; i < Test.list.size(); i++) {
                if (i != number) {
                    Test.list.get(i).sendMessage("stop");
                }
            }
//            master.stopConnection();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
