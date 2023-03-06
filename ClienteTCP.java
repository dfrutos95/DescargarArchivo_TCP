
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
/**
 * 
 * @author danifrutos
 *
 */
public class ClienteTCP {

	//Realiza un programa en el que cliente envía 
	
	public static void main(String[] args) {

		try {
			//Conexion
			Socket s= new Socket("127.0.0.1",5555);	
			//Envio mensaje al cliente
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			System.out.println("Escribe el nombre del archivo que quieres descargar: ");
			String nombre = (new Scanner(System.in)).nextLine();
			dos.writeUTF(nombre);
			//Recibo respuesta del servidor
			DataInputStream dis = new DataInputStream(s.getInputStream());
			String mensaje=dis.readUTF();
			Scanner sc2 = new Scanner(System.in);
			System.out.println("Escribe la ruta donde lo quieres descargar: ");
			String ruta=sc2.nextLine();
			File file = new File(ruta+"/"+nombre);
			file.createNewFile();
			FileWriter fw = new FileWriter(ruta+"/"+nombre);
			PrintWriter pw = new PrintWriter(fw);
			pw.write(mensaje);
			fw.close();
			dis.close();
			dos.close();
		 
			//Cierro socket
			s.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

}