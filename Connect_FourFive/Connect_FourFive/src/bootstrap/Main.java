package bootstrap;

import nodes.Node;

import java.net.UnknownHostException;

public class Main {

	public static void main(String[] args) throws UnknownHostException {

		Bootstrap bootstrap= new Bootstrap();
		bootstrap.startBootstap();
		Node node = new Node();
		node.startNode();
		Node node1 = new Node();
		node1.startNode();
	}

}
