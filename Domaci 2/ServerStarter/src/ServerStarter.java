import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServerStarter {

	private static final String LOKACIJA_SERVERA = "E:\\bane\\java\\MojServer\\bin";
	
	public static void main(String[] args) throws IOException {
		int prviPort = 9000;
		int brojServera = 3;
		
		for (int i = prviPort; i < prviPort + brojServera; i++) {
			Process p = Runtime.getRuntime().exec(
			"java MojServer " + i, 		//komanda
			null,						//promenljive okruzenja
			new File(LOKACIJA_SERVERA) //aktivni direktorijum
					);
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(
						p.getInputStream()));
			String line = reader.readLine();
			System.out.println(line);
		}
	}

}
