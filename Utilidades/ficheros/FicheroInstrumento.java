/*
 * Clase encargada de guardar los instrumentos en el fichero correspondiente
 * con acceso aleatorio.
 * Cada instrumento consta de:  4+40+40+2+40+8=134 Bytes
 * Se gurdará la descripción aparte, en otro fichero.
 * 
 */

package ficheros;

import datos.InstrumentoImpl;
import enums.Marca;

import java.io.*;

import compartidas.UtilidadesCompartidas;

public class FicheroInstrumento {
	
	/* 
	 * Interfaz 
	 * Cabecera:public void guardaInstrumento(String ruta,InstrumentoImpl instrumento)
	 * Proceso:Guarda un isntrumento en el fichero indicado
	 * Precondiciones:Ninguna
	 * Entrada:1 instrumento
	 * Salida:Nada
	 * Entrada/Salida:Una cadena para la ruta del fichero
	 * 					Una cadena para la descripcion del fichero
	 * Postcondiciones:El fichero quedará sobreescrito con el instrumento
	 */
	public void guardaInstrumento(String ruta,String desc,InstrumentoImpl instrumento){
		File fichero=new File(ruta);
		RandomAccessFile out=null;
		String nombre=UtilidadesCompartidas.completaCadena(instrumento.getNombre(), 20);
		
		//Pasamos la marca de tipo Marca a tipo String
		String marca=UtilidadesCompartidas.completaCadena(instrumento.getMarca().toString(),20);
		
		String modelo=UtilidadesCompartidas.completaCadena(instrumento.getModelo(), 20);
		
		try{
			out=new RandomAccessFile(fichero, "rw");
			out.seek(instrumento.getId()*134);
			out.writeInt(instrumento.getId());
			out.writeChars(nombre);
			out.writeChars(marca);
			if(instrumento.getDescripcion()!=""){
				out.writeChar('s');
				guardaDescripcion(desc,instrumento);
			}else{
				out.writeChar('n');
			}
			out.writeChars(modelo);
			out.writeDouble(instrumento.getPrecioVenta());
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
	 * Cabecera:public void muestraInstrumentos(String ruta)
	 * Proceso:muestra todos los instrumentos de un fichero
	 * Precondiciones:Ninguna
	 * Entrada:1 cadena para el fichero
	 * Salida:Nada
	 * Entrada/Salida:Nada
	 * Postcondiciones:Pintará en pantalla los instrumentos de dicho fichero.
	 */
	public void muestraInstrumentos(String ruta){
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

	/* 
	 * Interfaz 
	 * Cabecera:public InstrumentoImpl devuelveInstrumento(String ruta,int id)
	 * Proceso:devuelve un instrumento de un fichero dado, según el id que introduzca.
	 * Precondiciones:Ninguna
	 * Entrada:1 cadena para la ruta
	 * 			1 entero para el id
	 * Salida:1 instrumento
	 * Entrada/Salida:Nada
	 * Postcondiciones:instrumento asociado al nombre, null si el instrumento no está en el fichero.
	 */
	public InstrumentoImpl devuelveInstrumento(String ruta,int id){
		InstrumentoImpl devolver=null;
		RandomAccessFile in=null;
		
		int idLeido,i;
		String nombre,marca,descripcion,modelo;
		double precio;
		try {
			in=new RandomAccessFile(ruta, "r");
			in.seek(id*134);
			
			idLeido=in.readInt();
			if(idLeido!=0){
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
			
			
				devolver=new InstrumentoImpl(id,nombre, Marca.valueOf(marca), descripcion, modelo, precio);
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
		
		
		return devolver;
	}
	
	
	/* 
	 * Interfaz 
	 * Cabecera:public void guardaDescripcion(String ruta,InstrumentoImpl instrumento)
	 * Proceso:Guarda el id del instrumento y su descripción, en un fichero de texto. Se usará # como fin de lectura 
	 * 				de la descripción,
	 * Precondiciones:Las descripción del isntrumento no podrá contener #
	 * Entrada:1 instrumento
	 * Salida:Nada
	 * Entrada/Salida:1 cadena para la ruta del fichero.
	 * Postcondiciones:El fichero quedará sobreescrito con el instrumento.
	 */
	
	public void guardaDescripcion(String ruta,InstrumentoImpl instrumento){
		FileWriter out=null;
		BufferedWriter bw=null;
		try {
			out = new FileWriter(ruta,true);
			bw=new BufferedWriter(out);
			bw.write(String.valueOf(instrumento.getId()));
			bw.write("\r\n");
			bw.write(instrumento.getDescripcion());
			bw.write("#\r\n");
			
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}finally{
			if(bw!=null){
				try {
					bw.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
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
	 * Cabecera:public String descripcionInstrumento(String ruta,int id)
	 * Proceso:Método que devuelve la descripción de un instrumento.
	 * Precondiciones:Ninguna
	 * Entrada:1 id para el instrumento
	 * Salida:1 cadena asociada al nombre con la descripción del instrumento
	 * Entrada/Salida:1 cadena para la ruta del fichero
	 * Postcondiciones:Cadena asociada al nombre, vacía si no exsite la descripción.
	 */
	
	public String descripcionInstrumento(String ruta,int id){
		String salida="";
		FileReader in=null;
		BufferedReader br=null;
		int idLeido;
		char leido='1';
		
		try {
			in = new FileReader(ruta);
			br=new BufferedReader(in);
			
			idLeido=Integer.parseInt(br.readLine());
			while(id!=idLeido && idLeido!=-1){
				while(leido!='#'){
					leido=(char) br.read();
				}
				br.readLine();
				idLeido=Integer.parseInt(br.readLine());
				//Volvemos a leer para que no se nso quede con el # anterios
				leido=(char) br.read();
			}
			
			while(leido!='#'){
				salida=salida+leido;
				leido=(char) br.read();
			}
		}catch(FileNotFoundException e){
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}finally{
			if (br!=null){
				try {
					br.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if (in!=null){
				try {
					in.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
		
		return salida;
	}
	
}
