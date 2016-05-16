package ficheros;

import java.io.*;

import compartidas.UtilidadesCompartidas;
import datos.*;
import enums.Acabado;
import enums.FamiliaSaxo;

public class FicheroSaxofon {

	/* 
	 * Interfaz 
	 * Cabecera:public void guardaInstrumentoSaxofon(String ruta,SaxofonImpl Saxofon)
	 * Proceso:Guarda un isntrumento Saxofon en el fichero indicado
	 * Precondiciones:Ninguna
	 * Entrada:1 instrumento Saxofon
	 * Salida:Nada
	 * Entrada/Salida:Una cadena para la ruta del fichero
	 * Postcondiciones:El fichero quedará sobreescrito con el instrumento  Saxofon
	 */
	
	public void guardaInstrumentoSaxofon(String ruta,SaxofonImpl saxofon){
		File fichero=new File(ruta);
		RandomAccessFile out=null;
		String familia=UtilidadesCompartidas.completaCadena(saxofon.getFamilia().toString(), 20);
		String boquilla=UtilidadesCompartidas.completaCadena(saxofon.getBoquillaSaxo(), 20);
		String material=UtilidadesCompartidas.completaCadena(saxofon.getMaterial(), 20);
		String acabado=UtilidadesCompartidas.completaCadena(saxofon.getAcabado().toString(), 20);
		
		try{
			out=new RandomAccessFile(fichero, "rw");
			out.seek(saxofon.getId()*164);
			out.writeInt(saxofon.getId());
			out.writeChars(familia);
			out.writeChars(boquilla);
			out.writeChars(material);
			out.writeChars(acabado);
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
	 * Cabecera:public SaxofonImpl devuelveInstrumentoSaxofon(String rutaSaxofon,String rutaInst,int id)
	 * Proceso:devuelve un instrumento de Saxofon de un fichero dado, según el id que introduzca.
	 * Precondiciones:Ninguna
	 * Entrada:1 cadena para la ruta de Saxofon
	 * 			1 cadena para la ruta de instrumentos de viento
	 * 			1 cadena para tura de instrumentos
	 * 			1 entero para el id
	 * Salida:1 instrumento Saxofon
	 * Entrada/Salida:Nada
	 * Postcondiciones:instrumento Saxofon asociado al nombre, null si el instrumento no está en el fichero.
	 */
	
	public SaxofonImpl devuelveInstrumentoSaxofon(String rutaSaxofon,String rutaViento,
				String rutaInst,int id){
		
		SaxofonImpl devolver=null;
		VientoImpl v=null;
		RandomAccessFile in=null;
		FicheroViento fv=new FicheroViento();
		
		int idLeido,i;
		String familia,boquillaSaxo,material,acabado;
		
		v=fv.devuelveInstrumentoViento(rutaViento, rutaInst, id);
		
		
		if(v!=null){
			try {
				in=new RandomAccessFile(rutaSaxofon, "r");
				in.seek(id*164);
				
				idLeido=in.readInt();
				if(idLeido!=0){
					//familia
					familia="";
					for(i=0;i<20;i++){
						familia=familia+in.readChar();
					}
					familia=UtilidadesCompartidas.quitaAsterisco(familia);
					
					//boquillaSaxo
					boquillaSaxo="";
					for(i=0;i<20;i++){
						boquillaSaxo=boquillaSaxo+in.readChar();
					}
					boquillaSaxo=UtilidadesCompartidas.quitaAsterisco(boquillaSaxo);
					
					//material
					material="";
					for(i=0;i<20;i++){
						material=material+in.readChar();
					}
					material=UtilidadesCompartidas.quitaAsterisco(material);
					
					//acabado
					acabado="";
					for(i=0;i<20;i++){
						acabado=acabado+in.readChar();
					}
					acabado=UtilidadesCompartidas.quitaAsterisco(acabado);
					
				
				
					devolver=new SaxofonImpl(v,FamiliaSaxo.valueOf(familia),boquillaSaxo,material,Acabado.valueOf(acabado));
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
