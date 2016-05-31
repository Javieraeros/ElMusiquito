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
	 * 		y las compras que ha realizado en otro.
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
	 * Cabecera:public void guardaClienteSinCompras(String ruta,ClienteImpl c)
	 * Proceso:Método que te guarda el dni de un cliente, su correoe y su direccion en un fichero
	 * Precondiciones:Ninguna
	 * Entrada:1 cliente
	 * Salida:Nada
	 * Entrada/Salida:1 cadena para la ruta de los clientes
	 * Postcondiciones:El fichero quedará escrito
	 */
	
	public void guardaClienteSinCompras(String ruta,ClienteImpl c){
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
			} finally {
				if(dis!=null){
					try {
						dis.close();
					} catch (IOException e) {
						System.out.println(e);
					}
				}
				if(fis!=null){
					try {
						fis.close();
					} catch (IOException e) {
						System.out.println(e);
					}
				}
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
	 * Postcondiciones:Vector asociado al nombre, en caso de no existir compras, devuelve un vector
	 * con 0 instrumentos
	 */
	
	public Vector<InstrumentoImpl> devuelveCompras(String rutaCompras, String rutaInstrumento, long dni) {
		Vector<InstrumentoImpl> v = new Vector<InstrumentoImpl>(0, 1);
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
	
	
	/*
	 * Interfaz 
	 * Cabecera:public int cuentaclientes(String ruta) 
	 * Proceso:Cuenta el numero de clientes que hay en un fichero.
	 * Precondiciones:Ninguna 
	 * Entrada:1 cadena para el fichero 
	 * Salida:1 entero con el número de clientes
	 * Entrada/Salida:Nada
	 * Postcondiciones:Entero asociado al nombre
	 */

	public int cuentaClientes(String ruta) {
		int numeroClientes = 0;
		FileInputStream fis = null;
		DataInputStream dis = null;
		try {
			fis = new FileInputStream(ruta);
			dis = new DataInputStream(fis);
			while (dis.available()>0) {
				dis.skipBytes(108); //Me salto el número de Bytes que ocupa un cliente 8+60+40
				numeroClientes++;
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (EOFException e) {

		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if(dis!=null){
				try {
					dis.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if(fis!=null){
				try {
					fis.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
		return numeroClientes;
	}
	
	/*
	 * Interfaz 
	 * Cabecera:public void muestraClientes(String rutaCliente,Stirnt rutaPersona)
	 * Proceso:Muestra toda la infomación de un cliente
	 * Precondiciones:Fichero con clientes 
	 * Entrada:1 cadena con la ruta del fichero de clientes
	 * 			1 cadena con la ruta del fichero de personas
	 * Salida:Nada, pinta en pantalla 
	 * Entrada/Salida:Nada
	 * Postcondiciones:Pintará en pantalla todas los clientes
	 */

	public void muestraClientes(String ruta,String rutaPersona) {
		PersonaImpl p;
		ClienteImpl c;
		FicheroPersona fp=new FicheroPersona();
		FileInputStream fis = null;
		DataInputStream dis = null;
		int contador, numeroClientes = cuentaClientes(ruta);
		long dni;
		String correo,direccion;
		try {
			fis = new FileInputStream(ruta);
			dis = new DataInputStream(fis);
			for (contador = 0; contador < numeroClientes; contador++) {
				dni=dis.readLong();
				p=fp.devuelvePersona(rutaPersona, dni);
				//Lee correo
				correo="";
				for(int i=0;i<30;i++){
					correo=correo+dis.readChar();
				}
				correo=UtilidadesCompartidas.quitaAsterisco(correo);
				
				//lee direccion
				direccion="";
				for(int i=0;i<20;i++){
					direccion=direccion+dis.readChar();
				}
				direccion=UtilidadesCompartidas.quitaAsterisco(direccion);
				c=new ClienteImpl(p, correo, direccion,null);
				System.out.println(c.toString());
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (EOFException e) {

		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if(dis!=null){
				try {
					dis.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if(fis!=null){
				try {
					fis.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
	}

	/*
	 * Interfaz 
	 * Cabecera:public int cuentaCompras(String ruta) 
	 * Proceso:Cuenta el numero de compras que hay en un fichero.
	 * Precondiciones:Ninguna 
	 * Entrada:1 cadena para el fichero 
	 * Salida:1 entero con el número de compras
	 * Entrada/Salida:Nada
	 * Postcondiciones:Entero asociado al nombre
	 */

	public int cuentaCompras(String ruta) {
		int numeroCompras = 0;
		FileInputStream fis = null;
		DataInputStream dis = null;
		try {
			fis = new FileInputStream(ruta);
			dis = new DataInputStream(fis);
			while (dis.available()>0) {
				dis.skipBytes(12); //Me salto el número de Bytes que ocupa una compra
				numeroCompras++;
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (EOFException e) {

		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if(dis!=null){
				try {
					dis.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if(fis!=null){
				try {
					fis.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
		return numeroCompras;
	}

	/*
	 * Interfaz 
	 * Cabecera:public void muestraCompras(String ruta)
	 * Proceso:Muestra la información de todas las compras
	 * Precondiciones:Fichero con compras 
	 * Entrada:1 cadena con la ruta del fichero de compras
	 * Salida:Nada, pinta en pantalla 
	 * Entrada/Salida:Nada
	 * Postcondiciones:Pintará en pantalla todas las compras
	 */

	public void muestraCompras(String ruta) {
		PersonaImpl p;
		ClienteImpl c;
		FicheroPersona fp=new FicheroPersona();
		FileInputStream fis = null;
		DataInputStream dis = null;
		int contador, numeroCompras=cuentaCompras(ruta);
		long dni;
		int id;
		System.out.println("Dni      Id   ");
		try {
			fis = new FileInputStream(ruta);
			dis = new DataInputStream(fis);
			for (contador = 0; contador < numeroCompras; contador++) {
				dni=dis.readLong();
				id=dis.readInt();
				System.out.println(dni+" "+id);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (EOFException e) {

		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if(dis!=null){
				try {
					dis.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if(fis!=null){
				try {
					fis.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
	}
}
