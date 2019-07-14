package bootstrap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static bootstrap.Bootstrap.listNodes;

public class Responder implements Runnable{

	private Socket clientSocket;

	public Responder(Socket socket) {
		clientSocket=socket;
	}

	public void startResponder(){
		Thread responderThread= new Thread(this);
		responderThread.start();
	}



	@Override
	public void run() {
		PrintWriter out;
		try {
			out= new PrintWriter(clientSocket.getOutputStream(),true);
			BufferedReader in= new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String message=in.readLine();
			System.out.println(message);
			if(message.equals("Off")){
				message=in.readLine();
				Integer remove=Integer.parseInt(message);
				synchronized(listNodes){
					listNodes.remove(remove);
				}
			}
			if(message.equals("Ping")){
				out.write("Pong");
				out.write("\n");
				out.flush();
				message=in.readLine();
				System.out.println(message);
				if(message.equals("Report")){
					out.write("Port number");
					out.write("\n");
					out.flush();
					message=in.readLine();
					System.out.println(message);
					synchronized(listNodes) {
						if(listNodes.isEmpty()){
//						System.out.println("initial node has appeared!");
							int port=Integer.parseInt(message);
							String address=in.readLine();
							listNodes.put(port,address);
							System.out.println("initial node has appeared! " + address + " : " + port);
							out.write("Initial node");
							out.write("\n");
							out.flush();
							System.out.println("Network initialised");
						}
						else{
							int port=Integer.parseInt(message);
							String address=in.readLine();
							List<Integer> keysAsArray = new ArrayList<Integer>(listNodes.keySet());
							Random r = new Random();
							int temp = keysAsArray.get(r.nextInt(keysAsArray.size()));
							listNodes.put(port,address);
							out.write("Add node to network");
							out.write("\n");
							out.flush();
							System.out.println("stigao je novi node u mrezu! " + address + " : " + port);
//						out.write(""+address);
//						out.write(""+port);
//						out.write("\n");
//						out.flush();
							String tempA=listNodes.get(temp);
							System.out.println(temp + " : " +  tempA);
							out.write("" + tempA);
							out.write("\n");
							out.flush();
							out.write(""+temp);
							out.write("\n");
							out.flush();
						}
					}
//					System.out.println("LISTA: " + listNodes.size());
				}
			}
			if(message.equals("Tvoje putovanje pocinje sa kodovima")){

			}
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
