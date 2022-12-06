/*reference: https://www.baeldung.com/a-guide-to-java-sockets*/
/*
Documentation
Type "javac Manager.java" in the terminal to compile the file.
Type "java Manager <port_number> <number_of_workers>" in the terminal to run the file.
Manager takes in two command line arguments: port number and the number of workers.
Manager will be hosted on the specified port.
Manager waits for number_of_workers workers to join.
After number_of_workers workers joined, Manager assigns jobs evenly to the workers.
*/
import java.io.*;
import java.net.*;

public class Manager {

    private static class WorkerHandler extends Thread {
        private Socket clientSocket;
        private String workRange;
        private PrintWriter outToClient;
        private BufferedReader inFromClient;

        public WorkerHandler(Socket socket, String workRange) {
            this.clientSocket = socket;
            this.workRange = workRange;
        }

        public void run() {
            outToClient = new PrintWriter(clientSocket.getOutputStream(), true);
            inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
            //manager sends each worker "x,y", where x and y are integers between 1 and 52^5
            //a worker needs to take care of x'th to y'th passwords between "aaaaa" and "ZZZZZ"
            outToClient.println(workRange);
            String str = inFromClient.readLine();
            System.out.println("RECEIVED: " + str);

            //Closing connection
            inFromClient.close();
            outToClient.close();
            clientSocket.close();
            System.out.println("A client left");
        }
    }

    public static void main(String[] args) throws IOException {
        
        //Reading command line arguments
        if (args.length < 2) {
            throw new IllegalArgumentException("Port number or number of workers missing.");
        }
        int port = Integer.parseInt(args[0]);
        int num_workers = Integer.parseInt(args[1]);
        
        //Establishing connection
        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {
            //preparing to assign jobs
            int total_password_num = (int)Math.pow(52, 5);
            int[] workload = new int[num_workers]; 
            //workload[i] stores the number of passwords worker i needs to try
            int avg_workload = total_password_num/num_workers;
            int remainder = total_password_num%num_workers;
            for (int i=0; i<num_workers; i++) {
                workload[i] = avg_workload;
            }
            for (int i=0; i<remainder; i++) {
                workload[i]++;
            }

            //a worker's work range is represented with "x,y", 
            //where x and y are integers between 1 and 52^5
            //If workRange[i] is "x,y", 
            //then worker i needs to take care of x'th to y'th passwords between "aaaaa" and "ZZZZZ"
            String[] workRange = new String[num_workers];
            int accum = 0;
            for (int i=0; i < num_workers; i++) {
                String start = "" + (accum + 1);
                accum += workload[i];
                String end = "" + accum;
                workRange[i] = start + "," + end;
            }

            WorkerHandler[] workerThreads = new WorkerHandler[num_workers];
            for (int i=0; i < num_workers; i++) {
                workerThreads[i] = new WorkerHandler(serverSocket.accept(), workRange[i]).start();
                System.out.println("A client joined");
            }
        }
        //serverSocket.close();
    }
}
