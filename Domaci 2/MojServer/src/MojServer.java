import java.io.IOException;
import java.net.ServerSocket;

public class MojServer {

	public static void main(String[] args) throws IOException {
		Integer brojPorta = Integer.parseInt(args[0]);
		
		
		ServerSocket ss = new ServerSocket(brojPorta);
		System.out.println("Slusam na portu " + brojPorta);
		
		ss.accept();
	}

}
