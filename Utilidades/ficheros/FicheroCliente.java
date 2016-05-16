package ficheros;

import java.io.*;
import java.util.Vector;

import compartidas.UtilidadesCompartidas;
import datos.*;

public class FicheroCliente {
	
	/* 
	 * Interfaz 
	 * Cabecera:public void guardaCliente(String ruta,String rutaCompras,ClienteImpl c)
	 * Proceso:Método que te guarda el dni de un cliente, su correoe y su direccion en un fichero
	 * Precondiciones:Ninguna
	 * Entrada:1 cliente
	 * Salida:Nada
	 * Entrada/Salida:1 cadena para la ruta de los clientes
	 * 					1 cadena para la ruta de las compras
	 * Postcondiciones:El fichero quedará escrito
	 */
	
	public void guardaCliente(String ruta,String rutaCompras,ClienteImpl c){
		File ficheroCliente=new File(ruta);
		FileOutputStream fos = null;
		DataOutputStream dos = null;
		String direccion,correoe;
		
		int i;
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
			
			//Guardamos las compras
			for(i=0;i<c.getCompras().size();i++){
				guardaCompras(rutaCompras, c, i);
			}
			
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
	 * Cabecera:ClienteImpl devuelveCliente(String rutaCliente,String rutaPersona,long dni
	 * 										String rutaInstrumentos,String rutaComrpas);
	 * Proceso:Devuelve un cliente alojado en el fichero persona y cliente
	 * Precondiciones:El cliente debe exsitir en ambos ficheros
	 * Entrada:1 entero largo con el dni
	 * 			1 cadena para la ruta del fichero cliente
	 * 			1 cadena para la ruta del ficherp persona
	 * 			1 cadena para la ruta de los instrumentos
	 * 			1 cadena para la ruta de las compras
	 * Salida:1 cliente
	 * Entrada/Salida:
	 * Postcondiciones:cliente asociado al nombre.Null si no existe.
	 */
	
	public ClienteImpl devuelveCliente(String rutaCliente,String rutaPersona,long dni,
										String rutaInstrumento,String rutaCompras){
		ClienteImpl c=null;
		PersonaImpl p=null;
		Vector<InstrumentoImpl> v=new Vector<InstrumentoImpl>(1, 1);
		
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
					dis.skip(100);
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
					
					//Recuperamos las compras
					v=devuelveCompras(rutaCompras, rutaInstrumento, dniLeido);
					
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
	
	/* 
	 * Interfaz 
	 * Cabecera:public void guardaCompras(String rutaCompras,ClienteImpl c,int indice)
	 * Proceso:Guarda la compra de un cliente, marcada por el indice.
	 * Precondiciones:Indice mayor que 0
	 * Entrada:Un cliente
	 * 			Un entero que marque que instrumento quiere guardar
	 * Salida:NAda
	 * Entrada/Salida:1 cadena par ala ruta del fichero
	 * Postcondiciones:El fichero quedará guardado con el dni del cliente y el id del instrumento.
	 */
	public void guardaCompras(String rutaCompras,ClienteImpl c,int indice){
		File ficheroCompras=new File(rutaCompras);
		FileOutputStream fos = null;
		DataOutputStream dos = null;
		
		try {
			fos=new FileOutputStream(ficheroCompras,true);
			dos=new DataOutputStream(fos);
			//Guardamos el dni
			dos.writeLong(c.getDNI());
			
			//Guardamos el id del instrumento
			dos.writeInt(c.getCompras().elementAt(indice).getId());
			
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
	 * Cabecera:public Vector<InstrumentoImpl> devuelveCompras(String rutaCompras,long dni)
	 * Proceso:Devuelve un vector con todas las compras realizadas por un cliente
	 * Precondiciones:Ninguna
	 * Entrada:Un entero largo dni
	 * 			Una cadena para la ruta del fichero
	 * 			Una cadena para la ruta de los instrumentos
	 * Salida:Un vector de instrumentos
	 * Entrada/Salida:
	 * Postcondiciones:Vector asociado al nombre
	 */
	
	public Vector<InstrumentoImpl> devuelveCompras(String rutaCompras, String rutaInstrumento, long dni) {
		Vector<InstrumentoImpl> v = new Vector<InstrumentoImpl>(1, 1);
		File ficheroCompras = new File(rutaCompras);
		FileInputStream fis = null;
		DataInputStream dis = null;
		FicheroInstrumento fi = new FicheroInstrumento();
		InstrumentoImpl generico;

		long dniLeido;
		int idInstrumento;
		try {
			fis = new FileInputStream(ficheroCompras);
			dis = new DataInputStream(fis);

			dniLeido = dis.readLong();
			while (dis.available() > 0 && dni != dniLeido) {
				// Me salto el id del instrumento
				dis.readInt();

				dniLeido = dis.readLong();
			}
			while (dis.available()>0 && dniLeido == dni) {
				idInstrumento = dis.readInt();
				generico = fi.devuelveInstrumento(rutaInstrumento, idInstrumento);
				v.add(generico);
				if(dis.available()>0){
					dniLeido=dis.readLong();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if (dis != null) {
				try {
					dis.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}

		return v;
	}
	
}
