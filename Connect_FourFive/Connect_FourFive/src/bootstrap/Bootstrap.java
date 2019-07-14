package bootstrap;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import other.Connection;

public class Bootstrap implements Runnable{

	public static int LISTENER_PORT=9001;
	//public static ArrayList<Integer> listNodes=new ArrayList<>();
	public static HashMap<Integer,String> listNodes= new HashMap<>();
    ConcurrentHashMap<String, Connection> nodes = new ConcurrentHashMap<>();

	public Bootstrap() {

	}

	public void startBootstap(){
		Thread listenerThread= new Thread(this);
		listenerThread.start();
	}

//	public synchronized Connection getDefferToAgent() {
//		if(nodes.size() == 0) {
//            System.out.println("Bootstrap: nodes null");
//            return null;
//        } else {
//            int nodesSize = nodes.size();
//            Random rand  = new Random();
//            int ind = rand.nextInt(nodesSize);
//            int i = 0;
//            for (String key : nodes.keySet()) {
//                if(i==ind)
//                    return nodes.get(key);
//                i++;
//            }
//        }
//        System.out.println("Bootstrap: keySet null");
//        return null;
//    }
//
//	 public synchronized boolean addNode(Connection newConnection) {
//	        if (nodes.containsKey(newConnection.getAddress())) {
//	            return false;
//	        } else {
//	            System.out.println("[INFO] ADD NODE: " + newConnection.getAddress());
//	            nodes.put(newConnection.getAddress(), newConnection);
//	            return true;
//	        }
//	 }

	@Override
	public void run() {

		ServerSocket listenerSocket;
		try {

			listenerSocket= new ServerSocket(LISTENER_PORT);
			System.out.println("Listening on: "+LISTENER_PORT);

			while (true) {

				Socket clientSocket=listenerSocket.accept();
				Responder r= new Responder(clientSocket);
				r.startResponder();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int getLISTENER_PORT() {
		return LISTENER_PORT;
	}

	public static void setLISTENER_PORT(int lISTENER_PORT) {
		LISTENER_PORT = lISTENER_PORT;
	}

//	public static ArrayList<Integer> getListNodes() {
//		return listNodes;
//	}
//
//	public static void setListNodes(ArrayList<Integer> listNodes) {
//		Bootstrap.listNodes = listNodes;
//	}

	public static HashMap<Integer, String> getListNodes() {
		return listNodes;
	}

	public static void setListNodes(HashMap<Integer, String> listNodes) {
		Bootstrap.listNodes = listNodes;
	}
}
