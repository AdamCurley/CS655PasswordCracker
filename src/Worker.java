import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Worker {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port, int n) throws IOException{
        serverSocket = new ServerSocket(port);
        System.out.println("Worker" + n + " is waiting......");
        clientSocket = serverSocket.accept();
        System.out.println("Client: " + clientSocket.getInetAddress().getHostAddress()+", port: " + clientSocket.getPort() + "is connected!");
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String[] input = in.readLine().split(" ", 0);
        String hash = input[0];
        int number = Integer.parseInt(input[1]);
        String password = Md5.cracker(n, hash, number);
        if (password != null) {
            out.println(password);
        } else {
            out.println("Worker" + n + " doesn't crack the password");
        }
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }

    public static void main(String[] args) throws IOException {
        Worker worker = new Worker();
        worker.start(37211, 1);
        worker.stop();
    }
}