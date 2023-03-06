
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * 
 * @author danifrutos
 *
 */
public class ServidorTCP {

	public static void main(String[] args) {
		try {
			ServerSocket ss= new ServerSocket(5555);
			while (true) {
			Socket s = ss.accept();
			ThreadServidorTCP ts = new ThreadServidorTCP(s);
			ts.start();
			
			
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

