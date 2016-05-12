package ejecutables;

import java.io.*;
import java.util.Scanner;

public class CreaID {

	public static void main(String[] args) throws IOException { //Pongo aquí la excepción porque se que no
																//me va a aparecer
		File idFichero=new File("Ficheros//Id.dat");
		FileOutputStream escribirID=null;
		DataOutputStream out=null;
		Scanner teclado=new Scanner(System.in);
		if(!idFichero.exists()){
			idFichero.createNewFile();
			try {
				escribirID = new FileOutputStream(idFichero);
				out = new DataOutputStream(escribirID);
				System.out.println("Introduzca el id: ");
				int id=teclado.nextInt();
				out.writeInt(id);
				
			} catch (FileNotFoundException e) {
				System.out.println(e);
			}finally{
				if(out!=null){
					out.close();
				}
				if(escribirID!=null){
					escribirID.close();
				}
			}
		}else{
			System.out.println("El fichero ya existe, desea sobreescribirlo?");
			char respuesta=teclado.next().charAt(0);
			if(respuesta=='s' || respuesta=='S'){
				try {
					escribirID = new FileOutputStream(idFichero);
					out = new DataOutputStream(escribirID);
					System.out.println("Introduzca el id: ");
					int id=teclado.nextInt();
					out.writeInt(id);
					
				} catch (FileNotFoundException e) {
					System.out.println(e);
				}finally{
					if(out!=null){
						out.close();
					}
					if(escribirID!=null){
						escribirID.close();
					}
				}
			}
		}

	}

}
