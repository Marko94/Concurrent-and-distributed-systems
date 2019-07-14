package nodes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Luna on 28-Aug-17.
 */
public class NodeThread extends Thread{
    private Node node;
    private ServerSocket listenerSocket;

    public NodeThread(Node node, ServerSocket listenerSocket) {
        this.node = node;
        this.listenerSocket = listenerSocket;
    }

    public void startNodeThread(){
        Thread listenerThread= new Thread(this);
        listenerThread.start();
    }

    @Override
    public void run () {

        try {
			/*---------Communication with Bootstrap-------*/

//			Socket nodeRecieveSocket = new Socket("localhost", listenerSocket.getLocalPort());


            System.out.println("Adresa je: " + "localhost" + " Port je: " +listenerSocket.getLocalPort());
            while(true) {
                Socket clientSocket = listenerSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String message = in.readLine();
//                System.out.println("nesto" + message);
                if (message.equals("hey")) {
//                    System.out.println("KONEKTOVAO SE NA NODE!!! :D");

                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
