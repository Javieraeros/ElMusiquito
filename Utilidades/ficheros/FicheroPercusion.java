package ficheros;

import java.io.*;

import compartidas.UtilidadesCompartidas;
import datos.*;

public class FicheroPercusion {
	/* 
	 * Interfaz 
	 * Cabecera:public void guardaInstrumentoperc(String ruta,PercusionImpl viento)
	 * Proceso:Guarda un isntrumento de percusion en el fichero indicado
	 * Precondiciones:Ninguna
	 * Entrada:1 instrumento de percusion
	 * Salida:Nada
	 * Entrada/Salida:Una cadena para la ruta del fichero
	 * Postcondiciones:El fichero quedará sobreescrito con el instrumento de percusion
	 */
	
	public void guardaInstrumentoPerc(String ruta,PercusionImpl perc){
		File fichero=new File(ruta);
		RandomAccessFile out=null;
		String material=UtilidadesCompartidas.completaCadena(perc.getMaterial(), 20);
		
		try{
			out=new RandomAccessFile(fichero, "rw");
			out.seek(perc.getId()*47);
			out.writeInt(perc.getId());
			out.writeChar(perc.getAfinacion());
			out.writeChars(material);
			out.writeBoolean(perc.getAccesorios());
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
	 * Cabecera:public PercusionImpl devuelveInstrumentoPerc(String rutaPerc,String rutaInst,int id)
	 * Proceso:devuelve un instrumento de percusion de un fichero dado, según el id que introduzca.
	 * Precondiciones:Ninguna
	 * Entrada:1 cadena para la ruta de percusion
	 * 			1 cadena para tura de instrumentos
	 * 			1 entero para el id
	 * Salida:1 instrumento de percusion
	 * Entrada/Salida:Nada
	 * Postcondiciones:instrumento de percusion asociado al nombre, null si el instrumento no está en el fichero.
	 */
	
	public PercusionImpl devuelveInstrumentoPercusion(String rutaPerc,String rutaInst,int id){
		PercusionImpl devolver=null;
		InstrumentoImpl ii=null;
		RandomAccessFile in=null;
		FicheroInstrumento fi=new FicheroInstrumento();
		
		int idLeido,i;
		String material;
		char afinacion;
		boolean accesorios;
		
		ii=fi.devuelveInstrumento(rutaInst, id);
		
		if(ii!=null){
			try {
				in=new RandomAccessFile(rutaPerc, "r");
				in.seek(id*47);
				
				idLeido=in.readInt();
				if(idLeido!=0){
					//Afinacion
					afinacion=in.readChar();
					
					
					//Material
					material="";
					for(i=0;i<20;i++){
						material=material+in.readChar();
					}
					material=UtilidadesCompartidas.quitaAsterisco(material);
					
					//Accesorios
					accesorios=in.readBoolean();
				
				
					devolver=new PercusionImpl(ii,afinacion,material,accesorios);
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
	 * Cabecera:public void borraInstrumentoperc(String ruta,PercusionImpl viento)
	 * Proceso:Borra un isntrumento de percusion en el fichero indicado
	 * Precondiciones:Ninguna
	 * Entrada:1 instrumento de percusion
	 * Salida:Nada
	 * Entrada/Salida:Una cadena para la ruta del fichero
	 * Postcondiciones:El fichero quedará sobreescrito con el instrumento de percusion
	 */
	
	public void borraInstrumentoPerc(String ruta,PercusionImpl perc){
		File fichero=new File(ruta);
		RandomAccessFile out=null;
		String material=UtilidadesCompartidas.completaCadena(perc.getMaterial(), 20);
		
		try{
			out=new RandomAccessFile(fichero, "rw");
			out.seek(perc.getId()*47);
			out.writeInt(0);
			out.writeChar(perc.getAfinacion());
			out.writeChars(material);
			out.writeBoolean(perc.getAccesorios());
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
}
