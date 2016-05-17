package principal;

import java.io.*;
import java.util.Vector;

import compartidas.UtilidadesCompartidas;
import datos.*;
import enums.Marca;
import ficheros.*;

public class GestionadoraPrincipal {
	/* 
	 * Interfaz 
	 * Cabecera:public boolean compruebaExistencia****(String ruta,campoClave o)
	 * Proceso:Los siguientes métodos comprobaron si un objeto existe en un fichero
	 * Precondiciones:Ninguna
	 * Entrada:1 cadena para la ruta y el campo clave del objeto
	 * Salida:1 booleano
	 * Entrada/Salida:Nada
	 * Postcondiciones:True si existe el objeto,flase en caso contrario.
	 */

	public boolean compruebaExistenciaPersona(String ruta, long DNI) {
		boolean resultado=false;
		PersonaImpl p;
		FileInputStream fos = null;
		ObjectInputStream ois = null;

		try {
			fos = new FileInputStream(ruta);
			ois = new ObjectInputStream(fos);
			do{
				p = (PersonaImpl) ois.readObject();
				if(p.getDNI()==DNI){
					resultado=true;
				}
			}while(p!=null && !resultado);
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (EOFException e) {

		} catch (IOException e) {
			System.out.println(e);
		}
		
		return resultado;
	}

	public boolean compruebaExistenciaCliente(String ruta, long dni) {
		boolean resultado = false;

		File ficheroCliente = new File(ruta);
		FileInputStream fis = null;
		DataInputStream dis = null;
		long dniLeido;

		try {
			fis = new FileInputStream(ficheroCliente);
			dis = new DataInputStream(fis);
			dniLeido = dis.readLong();
			while (dis.available() > 0 && dniLeido != dni) {
				dis.skip(100);
				dniLeido = dis.readLong();
			}
			if(dniLeido==dni){
				resultado=true;
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}

		return resultado;
	}

	public boolean compruebaExistenciaEmpleado(String ruta, long dni) {
		boolean resultado = false;

		File ficheroEmpleado = new File(ruta);
		FileInputStream fis = null;
		DataInputStream dis = null;
		long dniLeido;

		try {
			fis = new FileInputStream(ficheroEmpleado);
			dis = new DataInputStream(fis);
			dniLeido = dis.readLong();
			while (dis.available() > 0 && dniLeido != dni) {
				dis.skip(50);
				dniLeido = dis.readLong();
			}
			if(dniLeido==dni){
				resultado=true;
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}

		return resultado;
	}
	
	public boolean compruebaExistenciaInstrumento(String ruta, int id) {
		boolean resultado = false;
		File fichero = new File(ruta);
		RandomAccessFile in = null;
		int idLeido;

		try {
			in = new RandomAccessFile(fichero, "r");
			in.seek(id * 134);
			idLeido = in.readInt();
			if(idLeido==id){
				resultado=true;
			}

		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}

		return resultado;
	}
	
	public boolean compruebaExistenciaViento(String ruta, int id) {
		boolean resultado = false;
		File fichero = new File(ruta);
		RandomAccessFile in = null;
		int idLeido;

		try {
			in = new RandomAccessFile(fichero, "r");
			in.seek(id * 47);
			idLeido = in.readInt();
			if(idLeido==id){
				resultado=true;
			}

		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}

		return resultado;
	}
	
	public boolean compruebaExistenciaPercusion(String ruta, int id) {
		boolean resultado = false;
		File fichero = new File(ruta);
		RandomAccessFile in = null;
		int idLeido;

		try {
			in = new RandomAccessFile(fichero, "r");
			in.seek(id * 47);
			idLeido = in.readInt();
			if(idLeido==id){
				resultado=true;
			}

		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}

		return resultado;
	}
	
	public boolean compruebaExistenciaCuerda(String ruta, int id) {
		boolean resultado = false;
		File fichero = new File(ruta);
		RandomAccessFile in = null;
		int idLeido;

		try {
			in = new RandomAccessFile(fichero, "r");
			in.seek(id * 49);
			idLeido = in.readInt();
			if(idLeido==id){
				resultado=true;
			}

		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}

		return resultado;
	}
	
	public boolean compruebaExistenciaSaxofon(String ruta, int id) {
		boolean resultado = false;
		File fichero = new File(ruta);
		RandomAccessFile in = null;
		int idLeido;

		try {
			in = new RandomAccessFile(fichero, "r");
			in.seek(id * 164);
			idLeido = in.readInt();
			if(idLeido==id){
				resultado=true;
			}

		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}

		return resultado;
	}
	
	public boolean compruebaExistenciaGuitarra(String ruta, int id) {
		boolean resultado = false;
		File fichero = new File(ruta);
		RandomAccessFile in = null;
		int idLeido;

		try {
			in = new RandomAccessFile(fichero, "r");
			in.seek(id * 49);
			idLeido = in.readInt();
			if(idLeido==id){
				resultado=true;
			}

		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}

		return resultado;
	}
	
	public boolean compruebaExistenciaPastilla(String ruta, int id) {
		boolean resultado=false;
		Pastilla p;
		FileInputStream fos = null;
		ObjectInputStream ois = null;

		try {
			fos = new FileInputStream(ruta);
			ois = new ObjectInputStream(fos);
			do{
				p = (Pastilla) ois.readObject();
				if(p.getId()==id){
					resultado=true;
				}
			}while(p!=null && !resultado);
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (EOFException e) {

		} catch (IOException e) {
			System.out.println(e);
		}
		
		return resultado;
	}

}
