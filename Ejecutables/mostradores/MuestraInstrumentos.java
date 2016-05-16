package mostradores;

import java.io.*;

import compartidas.UtilidadesCompartidas;
import datos.InstrumentoImpl;
import enums.Marca;

public class MuestraInstrumentos {

	public static void main(String[] args) {
		String ruta="Ficheros//Tests//Instrumentos.dat";
		File fichero=new File(ruta);
		RandomAccessFile in=null;
		int id,contador=1,i;
		String nombre,marca,modelo;
		String descripcion;
		double precio;
		InstrumentoImpl generico;
		
		try {
			in=new RandomAccessFile(fichero,"r");
			while(in.read()!=-1){
				in.seek(contador*134);
				
				id=in.readInt();
				
				if(id!=0){
					//Nombre
					nombre="";
					for(i=0;i<20;i++){
						nombre=nombre+in.readChar();
					}
					nombre=UtilidadesCompartidas.quitaAsterisco(nombre);
					
					//Marca
					marca="";
					for(i=0;i<20;i++){
						marca=marca+in.readChar();
					}
					marca=UtilidadesCompartidas.quitaAsterisco(marca);
					
					//Descripcion
					descripcion="";
					descripcion=descripcion+in.readChar();
					
					//Modelo
					modelo="";
					for(i=0;i<20;i++){
						modelo=modelo+in.readChar();
					}
					modelo=UtilidadesCompartidas.quitaAsterisco(modelo);
					
					//PrecioVenta
					precio=in.readDouble();
				
				
					generico=new InstrumentoImpl(id,nombre, Marca.valueOf(marca), descripcion, modelo, precio);
					System.out.println(generico);
				}
				contador++;
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

}
