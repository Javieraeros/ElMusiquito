package ficherosGes;

import java.io.*;

import datos.*;
import ficheros.*;
public class Actualizador {
	
	/* 
	 * Interfaz 
	 * Cabecera:public void actualizaPersonas(String maestro,String temporal)
	 * Proceso:Actualiza un fichero maestro con otro de actualización
	 * Precondiciones:LOS FICHEROS DEBEN ESTAR ORDENADOS
	 * Entrada:Nada
	 * Salida:Nada
	 * Entrada/Salida:1 cadena para la ruta del fichero maestro
	 * 					1 cadena para la ruta del fichero temporal
	 * Postcondiciones:el maestro quedará sobreescrito con todos los cambios
	 * 					el temporal quedará borrado.
	 */
	
	public void actualizaPersonas(String maestro,String temporal){
		File maestroF=new File(maestro);
		File tempF=new File(temporal);
		File maestroAct=new File(maestro+"act");
		
		FileInputStream fisMaestro=null;
		ObjectInputStream oisMaestro=null;
		
		FileInputStream fisTemp=null;
		ObjectInputStream oisTemp=null;
		
		FileOutputStream fosMactu=null;
		ObjectOutputStream oosMactu=null;
		
		FicheroPersona fp=new FicheroPersona();
		
		int registrosMaestro,registrosTemp;
		PersonaImpl p,paux;
		
		registrosMaestro=fp.cuentaPersonas(maestro);
		registrosTemp=fp.cuentaPersonas(temporal);
		
		try {
			fisMaestro=new FileInputStream(maestro);
			oisMaestro=new ObjectInputStream(fisMaestro);
			
			fisTemp=new FileInputStream(temporal);
			oisTemp=new ObjectInputStream(fisTemp);
			
			fosMactu=new FileOutputStream(maestro+"act");
			oosMactu=new ObjectOutputStream(fosMactu);
			//Lectura anticipada
			p=(PersonaImpl) oisMaestro.readObject();
			paux=(PersonaImpl) oisTemp.readObject();
			
			while(registrosMaestro>0 && registrosTemp>0){
				//Modificación o eliminación
				if(p.getDNI()==paux.getDNI()){
					//Si no una persona que vayamos a borrar
					//La guardamos, en caso contrario, no la guardamos, pero pasamos a la siguiente
					if(paux.getNombre()!=""){
						oosMactu.writeObject(paux);
					}
					registrosTemp--;
					paux=(PersonaImpl) oisTemp.readObject();
				}else{
					//Nada
					if(p.getDNI()<=paux.getDNI()){
						oosMactu.writeObject(p);
						registrosMaestro--;
						p=(PersonaImpl) oisMaestro.readObject();
					}else{
						//Alta
						oosMactu.writeObject(paux);
						registrosTemp--;
						paux=(PersonaImpl) oisTemp.readObject();
					}
				}
			}
			if(registrosMaestro==0){
				while(registrosTemp>0){
					oosMactu.writeObject((PersonaImpl) oisTemp.readObject());
					registrosTemp--;
				}
			}
			if(registrosTemp==0){
				while(registrosMaestro>0){
					oosMactu.writeObject((PersonaImpl) oisMaestro.readObject());
					registrosMaestro--;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally{
			if(oisMaestro!=null){
				try {
					oisMaestro.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if(fisMaestro!=null){
				try {
					fisMaestro.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if(oisTemp!=null){
				try {
					oisTemp.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if(fisTemp!=null){
				try {
					fisTemp.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if(oosMactu!=null){
				try {
					oosMactu.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if(fosMactu!=null){
				try {
					fosMactu.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			maestroF.delete();
			tempF.delete();
			maestroAct.renameTo(maestroF);
		}
	}
}
