package ficheros;

import java.io.*;
import java.util.Vector;

import compartidas.UtilidadesCompartidas;
import datos.*;
import enums.*;

public class FicheroGuitarra {

	/* 
	 * Interfaz 
	 * Cabecera:public void guardaInstrumentoGuitarra(String ruta,GuitarraImpl Guitarra)
	 * Proceso:Guarda un isntrumento Guitarra en el fichero indicado
	 * Precondiciones:Ninguna
	 * Entrada:1 instrumento Guitarra
	 * Salida:Nada
	 * Entrada/Salida:Una cadena para la ruta del fichero guitarra
	 * Postcondiciones:El fichero quedará sobreescrito con el instrumento  Guitarra
	 */
	
	public void guardaInstrumentoGuitarra(String rutaGuitarra,GuitarraElectricaImpl guitarra){
		File fichero=new File(rutaGuitarra);
		RandomAccessFile out=null;
		String tipo=UtilidadesCompartidas.completaCadena(guitarra.getTipo().toString(), 20);
		
		try{
			out=new RandomAccessFile(fichero, "rw");
			out.seek(guitarra.getId()*49);
			out.writeInt(guitarra.getId());
			out.writeChars(tipo);
			out.writeBoolean(guitarra.getPuenteFlotante());
			out.writeInt(guitarra.getControles());
		}catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}finally{
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
	}
	
	/* 
	 * Interfaz 
	 * Cabecera:public GuitarraElectricaImpl devuelveInstrumentoGuitarra(String rutaGuitarra,
	 * 					String rutaCuerda,String rutaRelaciones,String rutaInst,int id)
	 * Proceso:devuelve un instrumento Guitarra de un fichero dado, según el id que introduzca.
	 * Precondiciones:Ninguna
	 * Entrada:1 cadena para la ruta de Guitarra
	 * 			1 cadena para la ruta de instrumentos de cuerda
	 * 			1 cadena para la ruta de relaciones
	 * 			1 cadena para ruta de instrumentos
	 * 			1 entero para el id
	 * Salida:1 instrumento Guitarra
	 * Entrada/Salida:Nada
	 * Postcondiciones:instrumento Guitarra asociado al nombre, null si el instrumento no está en el fichero.
	 */
	
	public GuitarraElectricaImpl devuelveInstrumentoGuitarra(String rutaGuitarra,
			  					String rutaCuerda,String rutaRelaciones,String rutaInst,int id){
		GuitarraElectricaImpl devolver=null;
		CuerdaImpl c=null;
		RandomAccessFile in=null;
		FicheroCuerda fc=new FicheroCuerda();
		
		int idLeido,i,controles;
		String tipo;
		int[] pastillas;
		boolean puente;
		
		c=fc.devuelveInstrumentoCuerda(rutaCuerda, rutaInst, id);
		
		if(c!=null){
			try {
				in=new RandomAccessFile(rutaGuitarra, "r");
				in.seek(id*49);
				
				idLeido=in.readInt();
				if(idLeido!=0){
					//tipo
					tipo="";
					for(i=0;i<20;i++){
						tipo=tipo+in.readChar();
					}
					tipo=UtilidadesCompartidas.quitaAsterisco(tipo);
					
					//punete
					puente=in.readBoolean();
					
					
					//controles
					controles=in.readInt();
					
					pastillas=devuelveRelacionesPastillas(rutaRelaciones, id);
				
					devolver=new GuitarraElectricaImpl(c,Tipo.valueOf(tipo),pastillas,puente,controles);
				}
			} catch (FileNotFoundException e) {
				System.out.println(e);
			} catch (IOException e) {
				System.out.println(e);
			}finally{
				if(in!=null){
					try {
						in.close();
					} catch (IOException e) {
						System.out.println(e);
					}
				}
			}
		
		}
		return devolver;
	}
	
	/* 
	 * Interfaz 
	 * Cabecera:public void guardaPastilla(String ruta,String rutaPastilla,GuitarraElectricaImpl g)
	 * Proceso:Guarda las pastillas en un fichero primitivo, de tal forma que a continuación
	 * 			de un id de una guitarra, viene el id de la pastilla.
	 * Precondiciones:Ninguna
	 * Entrada:1 guitarra
	 * Salida:Nada
	 * Entrada/Salida:1 cadena con la ruta donde guardar la relación de las pastillas.
	 * Postcondiciones:Las relaciones quedarán guardadas.
	 */
	
	public void guardaRelacionPastilla(String ruta,GuitarraElectricaImpl g){
		File ficheroRelaciones=new File(ruta);
		FileOutputStream fos = null;
		DataOutputStream dos = null;
		
		//Guardar relaciones
		try {
			fos=new FileOutputStream(ficheroRelaciones,true);
			dos=new DataOutputStream(fos);
			for(int i=0;i<g.getIdPastillas().length;i++){
				dos.writeInt(g.getId());
				dos.writeInt(g.getIdPastillas()[i]);
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
	 * Cabecera:int[] devuelveRelacionesPastillas(String rutaRelaciones,int idGuitarra);
	 * Proceso:Devuelve un array con lso ids de las pastillas de una guitarra
	 * Precondiciones:Ninguna
	 * Entrada:1 entero  con el id de la guitarra
	 * 			1 cadena para la tura de las relaciones
	 * Salida:1 array de enteros
	 * Entrada/Salida:
	 * Postcondiciones:array asociado al nombre.
	 */
	
	public int[] devuelveRelacionesPastillas(String rutaRelaciones,int id){
		int[] devolver=new int[3];
		int idLeido;
		File ficheroRelaciones=new File(rutaRelaciones);
		FileInputStream fis = null;
		DataInputStream dis = null;
		
			try {
				fis=new FileInputStream(ficheroRelaciones);
				dis=new DataInputStream(fis);
				idLeido=dis.readInt();
				while(dis.available()>0 && id!=idLeido){
					//Me salto el id de la pastilla
					dis.readInt();
					
					idLeido=dis.readInt();
				}
				if(dis.available()>0){
					devolver[0]=dis.readInt();
					
					//Si existen más pastillas en la misma guitarra
					idLeido=dis.readInt();
					if(id==idLeido){
						devolver[1]=dis.readInt();
						idLeido=dis.readInt();
						if(id==idLeido){
							devolver[2]=dis.readInt();
						}
					}
				}else{
					System.out.println("Error, no se encuentran las pastillas");
				}
			} catch (FileNotFoundException e) {
				System.out.println(e);
			} catch (IOException e) {
				System.out.println(e);
			}
			
		return devolver;
	}
	
	/*
	 * Interfaz 
	 * Cabecera:public void muestraRelaciones(String ruta)
	 * Proceso:Muestra todas las relaciones de guitarras con pastillas
	 * Precondiciones:Fichero con relaciones (2 entero)
	 * Entrada:1 cadena con la ruta del fichero de relaciones
	 * Salida:Nada, pinta en pantalla 
	 * Entrada/Salida:Nada
	 * Postcondiciones:Pintará en pantalla todas las relaciones (id de la guitarra   id de la pastilla)
	 */

	public void muestraCompras(String ruta) {
		FileInputStream fis = null;
		DataInputStream dis = null;
		int idGuitarra,idPastilla;
		System.out.println("IdGuitarra \tIdPastilla");
		try {
			fis = new FileInputStream(ruta);
			dis = new DataInputStream(fis);
			while(dis.available()>0) {
				idGuitarra=dis.readInt();
				idPastilla=dis.readInt();
				System.out.println(idGuitarra+" \t"+idPastilla);
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
	 * Cabecera:public int cuentaRelaciones(String ruta) 
	 * Proceso:Cuenta el numero de relaciones que hay en un fichero.
	 * Precondiciones:Ninguna 
	 * Entrada:1 cadena para el fichero 
	 * Salida:1 entero con el número de relaciones
	 * Entrada/Salida:Nada
	 * Postcondiciones:Entero asociado al nombre
	 */

	public int cuentaRelaciones(String ruta) {
		int numeroRelaciones = 0;
		FileInputStream fis = null;
		DataInputStream dis = null;
		try {
			fis = new FileInputStream(ruta);
			dis = new DataInputStream(fis);
			while (dis.available()>0) {
				dis.skipBytes(8); //Me salto el número de Bytes que ocupa una relacion
				numeroRelaciones++;
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
		return numeroRelaciones;
	}

}
