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
	 * 					1 cadena para la tura de la relaciones de las pastillas
	 * Postcondiciones:El fichero quedará sobreescrito con el instrumento  Guitarra
	 */
	
	public void guardaInstrumentoGuitarra(String rutaGuitarra,String rutaRelaciones,GuitarraElectricaImpl guitarra){
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
		
		guardaRelacionPastilla(rutaRelaciones, guitarra);
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
}
