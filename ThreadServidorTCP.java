
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
/**
 * 
 * @author danifrutos
 *
 */
public class ThreadServidorTCP extends Thread {
	
	Socket s;
	
	public ThreadServidorTCP(Socket s) {
		this.s=s;
	}
	public String descargar(String nombre) {
		File archivo=null; 
		FileReader fr=null;
		BufferedReader br=null;
		String archivoTexto="";
		try{
            archivo = new File("~/"+nombre);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;
            while((linea=br.readLine())!=null){
            	archivoTexto=archivoTexto+linea;
            }
        }catch(Exception e){
                e.printStackTrace();
        }finally{
            try{
                if(null != fr){
                    fr.close();
                }
            }catch(Exception e2){
                e2.printStackTrace();
            }
        }
		return archivoTexto;
	}
	
	
	public void run() {	
		DataInputStream dis;
		DataOutputStream dos;
		try {
			//Leo lo que me ha mandado el cliente
			dis = new DataInputStream(s.getInputStream());
			String nombre=dis.readUTF();
			System.out.println("He recibido el: "+nombre);
			String archivo=descargar(nombre); 
			dos = new DataOutputStream(s.getOutputStream());
			dos.writeUTF(archivo);
			dos.close();
			dis.close();
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

}
