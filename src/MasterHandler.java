import java.io.IOException;

public class MasterHandler extends Thread{
    private int number;
    private String input;
//    public static int[] port = {37211, 37212, 37213, 37214, 37215};
    public static String[] ip = {
            "pcvm3-19.instageni.osu.edu",
            "pcvm3-21.instageni.osu.edu",
            "pcvm3-22.instageni.osu.edu",
            "pcvm3-23.instageni.osu.edu",
            "pcvm3-24.instageni.osu.edu"
    };
    public MasterHandler(int number, String input) {
        this.number = number;
        this.input = input;
    }
    public void run() {
        Master master = new Master();
        try {
            master.startConnection(ip[number], 37211);
//            master.startConnection("csa1.bu.edu", 58888);
            String response = master.sendMessage(input);
            System.out.println(response);
            master.stopConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
