package ficheros;

import java.io.*;

import compartidas.UtilidadesCompartidas;
import datos.*;

public class FicheroCuerda {
	
	/* 
	 * Interfaz 
	 * Cabecera:public void guardaInstrumentoCuerda(String ruta,CuerdaImpl cuerda)
	 * Proceso:Guarda un isntrumento de cuerda en el fichero indicado
	 * Precondiciones:Ninguna
	 * Entrada:1 instrumento de cuerda
	 * Salida:Nada
	 * Entrada/Salida:Una cadena para la ruta del fichero
	 * Postcondiciones:El fichero quedará sobreescrito con el instrumento de cuerda
	 */
	
	public void guardaInstrumentoCuerda(String ruta,CuerdaImpl cuerda){
		File fichero=new File(ruta);
		RandomAccessFile out=null;
		String registro=UtilidadesCompartidas.completaCadena(cuerda.getRegistro(), 20);
		
		try{
			out=new RandomAccessFile(fichero, "rw");
			out.seek(cuerda.getId()*49);
			out.writeInt(cuerda.getId());
			out.writeInt(cuerda.getCuerdas());
			out.writeChars(registro);
			out.writeByte(cuerda.getTipoCuerda());
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
	 * Cabecera:public cuerdaImpl devuelveInstrumentocuerda(String rutacuerda,String rutaInst,int id)
	 * Proceso:devuelve un instrumento de cuerda de un fichero dado, según el id que introduzca.
	 * Precondiciones:Ninguna
	 * Entrada:1 cadena para la ruta de cuerda
	 * 			1 cadena para tura de instrumentos
	 * 			1 entero para el id
	 * Salida:1 instrumento de cuerda
	 * Entrada/Salida:Nada
	 * Postcondiciones:instrumento de cuerda asociado al nombre, null si el instrumento no está en el fichero.
	 */
	
	public CuerdaImpl devuelveInstrumentoCuerda(String rutaCuerda,String rutaInst,int id){
		CuerdaImpl devolver=null;
		InstrumentoImpl ii=null;
		RandomAccessFile in=null;
		FicheroInstrumento fi=new FicheroInstrumento();
		
		int idLeido,i;
		String registro;
		char afinacion;
		byte tipoCuerda;
		
		ii=fi.devuelveInstrumento(rutaInst, id);
		
		if(ii!=null){
			try {
				in=new RandomAccessFile(rutaCuerda, "r");
				in.seek(id*49);
				
				idLeido=in.readInt();
				if(idLeido!=0){
					//Afinacion
					afinacion=in.readChar();
					
					
					//Registro
					registro="";
					for(i=0;i<20;i++){
						registro=registro+in.readChar();
					}
					registro=UtilidadesCompartidas.quitaAsterisco(registro);
					
					//Tienda
					tipoCuerda=in.readByte();
				
				
					devolver=new CuerdaImpl(ii,afinacion,registro,tipoCuerda);
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
