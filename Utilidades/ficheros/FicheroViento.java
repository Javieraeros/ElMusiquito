package ficheros;

import java.io.*;

import compartidas.UtilidadesCompartidas;
import datos.*;
import enums.Marca;

public class FicheroViento {
	
	/* 
	 * Interfaz 
	 * Cabecera:public void guardaInstrumentoViento(String ruta,VientoImpl viento)
	 * Proceso:Guarda un isntrumento de viento en el fichero indicado
	 * Precondiciones:Ninguna
	 * Entrada:1 instrumento de viento
	 * Salida:Nada
	 * Entrada/Salida:Una cadena para la ruta del fichero
	 * Postcondiciones:El fichero quedará sobreescrito con el instrumento de viento
	 */
	
	public void guardaInstrumentoViento(String ruta,VientoImpl viento){
		File fichero=new File(ruta);
		RandomAccessFile out=null;
		String tesitura=UtilidadesCompartidas.completaCadena(viento.getTesitura(), 20);
		
		try{
			out=new RandomAccessFile(fichero, "rw");
			out.seek(viento.getId()*47);
			out.writeInt(viento.getId());
			out.writeChar(viento.getAfinacion());
			out.writeChars(tesitura);
			out.writeByte(viento.getBoquilla());
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
	 * Cabecera:public VientoImpl devuelveInstrumentoViento(String rutaViento,String rutaInst,int id)
	 * Proceso:devuelve un instrumento de viento de un fichero dado, según el id que introduzca.
	 * Precondiciones:Ninguna
	 * Entrada:1 cadena para la ruta de viento
	 * 			1 cadena para tura de instrumentos
	 * 			1 entero para el id
	 * Salida:1 instrumento de viento
	 * Entrada/Salida:Nada
	 * Postcondiciones:instrumento de viento asociado al nombre, null si el instrumento no está en el fichero.
	 */
	
	public VientoImpl devuelveInstrumentoViento(String rutaViento,String rutaInst,int id){
		VientoImpl devolver=null;
		InstrumentoImpl ii=null;
		RandomAccessFile in=null;
		FicheroInstrumento fi=new FicheroInstrumento();
		
		int idLeido,i;
		String tesitura;
		char afinacion;
		byte boquilla;
		
		ii=fi.devuelveInstrumento(rutaInst, id);
		
		if(ii!=null){
			try {
				in=new RandomAccessFile(rutaViento, "r");
				in.seek(id*47);
				
				idLeido=in.readInt();
				if(idLeido!=0){
					//Afinacion
					afinacion=in.readChar();
					
					
					//Tesitura
					tesitura="";
					for(i=0;i<20;i++){
						tesitura=tesitura+in.readChar();
					}
					tesitura=UtilidadesCompartidas.quitaAsterisco(tesitura);
					
					//Boquilla
					boquilla=in.readByte();
				
				
					devolver=new VientoImpl(ii,afinacion,tesitura,boquilla);
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
}
