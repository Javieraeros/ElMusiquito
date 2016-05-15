package ficheros;

import java.io.*;
import java.util.Vector;

import compartidas.UtilidadesCompartidas;
import datos.*;

public class FicheroCliente {
	
	/* 
	 * Interfaz 
	 * Cabecera:public void guardaCliente(String ruta,ClienteImpl c)
	 * Proceso:Método que te guarda el dni de un cliente, su correoe y su direccion en un fichero
	 * Precondiciones:Ninguna
	 * Entrada:1 cliente
	 * Salida:Nada
	 * Entrada/Salida:1 cadena para la ruta
	 * Postcondiciones:El fichero quedará escrito
	 */
	
	public void guardaCliente(String ruta,ClienteImpl c){
		File ficheroCliente=new File(ruta);
		FileOutputStream fos = null;
		DataOutputStream dos = null;
		String direccion,correoe;
		
		try {
			fos=new FileOutputStream(ficheroCliente,true);
			dos=new DataOutputStream(fos);
			//Guardamos el dni
			dos.writeLong(c.getDNI());
			
			//Guardamos el correo
			correoe=UtilidadesCompartidas.completaCadena(c.getCorreoe(), 30);
			dos.writeChars(correoe);
			
			//Guardamos la direccion
			direccion=UtilidadesCompartidas.completaCadena(c.getDireccion(), 20);
			dos.writeChars(direccion);
			
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}finally{
			if(dos!=null){
				try {
					dos.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
	}
	
	/* 
	 * Interfaz 
	 * Cabecera:ClienteImpl devuelveCliente(String rutaCliente,String rutaPersona,long dni);
	 * Proceso:Devuelve un cliente alojado en el fichero persona y cliente
	 * Precondiciones:El cliente debe exsitir en ambos ficheros
	 * Entrada:1 entero largo con el dni
	 * Salida:1 cliente
	 * Entrada/Salida:1 cadena para la ruta del fichero cliente
	 * 					1 cadena para la ruta del ficherp persona
	 * Postcondiciones:cliente asociado al nombre.Null si no existe.
	 */
	
	public ClienteImpl devuelveCliente(String rutaCliente,String rutaPersona,long dni){
		ClienteImpl c=null;
		PersonaImpl p=null;
		Vector<InstrumentoImpl> v=new Vector<InstrumentoImpl>(1,1);
		
		File ficheroCliente=new File(rutaCliente);
		FileInputStream fis = null;
		DataInputStream dis = null;
		long dniLeido;
		String direccion="",correoe="";
		
		FicheroPersona fp=new FicheroPersona();
		p=fp.devuelvePersona(rutaPersona, dni);
		
		if(p!=null){
			try {
				fis=new FileInputStream(ficheroCliente);
				dis=new DataInputStream(fis);
				dniLeido=dis.readLong();
				while(dis.available()>0 && dniLeido!=dni){
					dis.skip(50);
					dniLeido=dis.readLong();
				}
				
				if(dniLeido==dni){
					//Lee correo
					for(int i=0;i<30;i++){
						correoe=correoe+dis.readChar();
					}
					correoe=UtilidadesCompartidas.quitaAsterisco(correoe);
					//lee direccion
					for(int i=0;i<20;i++){
						direccion=direccion+dis.readChar();
					}
					direccion=UtilidadesCompartidas.quitaAsterisco(direccion);
					c=new ClienteImpl(p,correoe,direccion,v);
				}
			} catch (FileNotFoundException e) {
				System.out.println(e);
			} catch (IOException e) {
				System.out.println(e);
			}
			
		}
		return c;
	}
	
}
