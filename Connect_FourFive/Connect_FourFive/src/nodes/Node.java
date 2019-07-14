package nodes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Random;

import bootstrap.Bootstrap;
import other.Connection;
import sun.rmi.runtime.Log;

public class Node implements Runnable{

//	String bootstrapAddress = String.valueOf(InetAddress.getLocalHost());
	int bootstrapPort=Bootstrap.LISTENER_PORT;
	private int nodePort;
	private Connection predecessor;

	private String id;
	private String nodeAdress = InetAddress.getLocalHost().toString().substring(InetAddress.getLocalHost().toString().lastIndexOf("/")+1);
	private boolean rootNode = false;
	private boolean isConnected = false;
	HashMap<String , Connection> neighbours;

	public Node() throws UnknownHostException {
		neighbours = new HashMap<>();
	}

	public void startNode(){
		Thread node=new Thread(this);
		node.start();
	}

	 public boolean addNeighbour(String id, String address, int port) {
        if (neighbours.containsKey(id)) {
            return false;
        } else {
            neighbours.put(id, new Connection(id, address, port));
            return true;
        }
    }

	public static void create(Node node) throws Exception
	{
		try
		{
			if (node.isConnected())
			{
				throw new Exception("Node Already connected");
			}
			else if (node.getId() == null)
			{
				throw new Exception("Unable to create ring, ID not defined");
			}

			node.setConnected(true);
			node.addNeighbour(node.getId(), node.getNodeAdress(), node.getNodePort());
			node.setPredecessor(null);
		}
		catch (RemoteException e)
		{
			System.out.println("A problem occurred when creating the ring. Please try again later!");
		}
	}


//    private int findLocation(String searchingAdress, int searchingPort, String senderAdress, int senderPort) throws IOException {
//		if (Integer.parseInt(id) % 3 == 0) {
//			if (!neighbours.containsKey(id.substring(0, id.length() - 1).concat("1"))) {
//				addNeighbour(id.substring(0, id.length() - 1).concat("1"), id, searchingAdress, searchingPort, 0);
//			} else if (!neighbours.containsKey(id.substring(0, id.length() - 1).concat("2"))) {
//				addNeighbour(id.substring(0, id.length() - 1).concat("2"), id, searchingAdress, searchingPort, 0);
//			} else {
//				findLocation(searchingAdress, searchingPort, nodeAdress, nodePort);
//			}
//		} else {
//			Connection tempConnecion = neighbours.get(id.substring(0, id.length() - 1).concat("0"));
//			// kontaktirati drugi root node
//			ServerSocket listenerSocket = new ServerSocket(0);
//			nodePort = listenerSocket.getLocalPort();
//			Socket nodeSocket = new Socket(tempConnecion.getIp(), tempConnecion.getPort());
//			PrintWriter out = new PrintWriter(nodeSocket.getOutputStream(), true);
//			BufferedReader in = new BufferedReader(new InputStreamReader(nodeSocket.getInputStream()));
//			out.write("Ping");
//			out.write("\n");
//			out.flush();
//			String message = in.readLine();
//			if (message.equals("Pong")) {
//				out.write("Add node to network");
//				out.write("" + searchingAdress);
//				out.write("" + searchingPort);
//				out.write("\n");
//				out.flush();
//			}
//		}
//		return 0;
//	}
		@Override
		public void run () {
			ServerSocket listenerSocket;

			try {
			/*---------Communication with Bootstrap-------*/
				listenerSocket = new ServerSocket(0);
				nodePort = listenerSocket.getLocalPort();
				Socket bootstrapSocket = new Socket("localhost", bootstrapPort);
				PrintWriter out = new PrintWriter(bootstrapSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(bootstrapSocket.getInputStream()));
				Random rand = new Random();
				//id = "" + rand.nextInt(9999);
				out.write("Ping");
				out.write("\n");
				out.flush();
				String message = in.readLine();
//				System.out.println("ID: " + id + " - " + message);
				if (message.equals("Pong")) {
					out.write("Report");
					out.write("\n");
					out.flush();
					message = in.readLine();
//					System.out.println("ID: " + id + " - " + message);
					System.out.println("IP adress: " + InetAddress.getLocalHost().toString().substring(InetAddress.getLocalHost().toString().lastIndexOf("/") + 1));
					if (message.equals("Port number")) {
						out.write("" + nodePort);
						out.write("\n");
						out.flush();
						out.write(nodeAdress);
						out.write("\n");
						out.flush();
						message = in.readLine();
//						System.out.println("ID: " + id + " - " + message);
						if (message.equals("Initial node")) {
							rootNode = true;
							id = "0";
							System.out.println("ID: " + id);
							System.out.println();
							bootstrapSocket.close();
							NodeThread nodeListenerThread = new NodeThread(this, listenerSocket);
							nodeListenerThread.start();
							isConnected = true;
						}
						if (message.equals("Add node to network")) {
							message = in.readLine();
							String hostAddress=message;
							message = in.readLine();
							int hostPort = Integer.parseInt(message);
							System.out.println("Address: "+hostAddress);
							System.out.println("Port: "+hostPort);
							bootstrapSocket.close();
							NodeThread nodeListenerThread = new NodeThread(this, listenerSocket);
							nodeListenerThread.startNodeThread();
							Socket nodeSenderSocket = new Socket("localhost", hostPort);
							out = new PrintWriter(nodeSenderSocket.getOutputStream(), true);
							in = new BufferedReader(new InputStreamReader(nodeSenderSocket.getInputStream()));

							System.out.println("vicem HEY");
							out.write("hey");
							out.write("\n");
							out.flush();
							isConnected = true;

//							findLocation(hostAddress,hostPort,hostAddress,hostPort);
//							System.out.println("ID node-a na koji treba da se konektujes je: " + message);
//							System.out.println("ID: " + id + " - Added to architecture");
//							System.out.println("ID: " + id + " - Working on Connect 5");
							//bootstrapSocket.close();
				/*--------Communication with Bootstrap--------*/
						}
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}


	public boolean isConnected() {
		return isConnected;
	}

	public void setConnected(boolean connected) {
		isConnected = connected;
	}

	public int getBootstrapPort() {
		return bootstrapPort;
	}

	public void setBootstrapPort(int bootstrapPort) {
		this.bootstrapPort = bootstrapPort;
	}

	public int getNodePort() {
		return nodePort;
	}

	public void setNodePort(int nodePort) {
		this.nodePort = nodePort;
	}

	public Connection getPredecessor() {
		return predecessor;
	}

	public void setPredecessor(Connection predecessor) {
		this.predecessor = predecessor;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNodeAdress() {
		return nodeAdress;
	}

	public void setNodeAdress(String nodeAdress) {
		this.nodeAdress = nodeAdress;
	}

	public boolean isRootNode() {
		return rootNode;
	}

	public void setRootNode(boolean rootNode) {
		this.rootNode = rootNode;
	}

	public HashMap<String, Connection> getNeighbours() {
		return neighbours;
	}

	public void setNeighbours(HashMap<String, Connection> neighbours) {
		this.neighbours = neighbours;
	}

	public String getId() {
		return id;
	}
}
