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
					//Si no es una persona que vayamos a borrar
					//La guardamos, en caso contrario, no la guardamos, pero pasamos a la siguiente
					if(!paux.getNombre().equals("")){
						oosMactu.writeObject(paux);
					}
					registrosTemp--;
					registrosMaestro--;
					if(registrosTemp>0){
						paux=(PersonaImpl) oisTemp.readObject();
					}
					if(registrosMaestro>0){
						p=(PersonaImpl) oisMaestro.readObject();
					}
				}else{
					//Nada
					if(p.getDNI()<=paux.getDNI()){
						oosMactu.writeObject(p);
						registrosMaestro--;
						if(registrosMaestro>0){
							p=(PersonaImpl) oisMaestro.readObject();
						}
					}else{
						//Alta
						oosMactu.writeObject(paux);
						registrosTemp--;
						if(registrosTemp>0){
							paux=(PersonaImpl) oisTemp.readObject();
						}
					}
				}
			}
			if(registrosMaestro==0){
				while(registrosTemp>0){
					oosMactu.writeObject(paux);
					registrosTemp--;
					if(registrosTemp>0){
						paux=(PersonaImpl) oisTemp.readObject();
					}
				}
			}
			if(registrosTemp==0){
				while(registrosMaestro>0){
					oosMactu.writeObject(p);
					registrosMaestro--;
					if(registrosMaestro>0){
						p=(PersonaImpl) oisMaestro.readObject();
					}
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

	/* 
	 * Interfaz 
	 * Cabecera:public void actualizaClientes(String maestro,String temporal)
	 * Proceso:Actualiza un fichero maestro con otro de actualización
	 * Precondiciones:LOS FICHEROS DEBEN ESTAR ORDENADOS
	 * Entrada:Nada
	 * Salida:Nada
	 * Entrada/Salida:1 cadena para la ruta del fichero maestro
	 * 					1 cadena para la ruta del fichero temporal
	 * Postcondiciones:el maestro quedará sobreescrito con todos los cambios
	 * 					el temporal quedará borrado.
	 */
	
	public void actualizaClientes(String maestro,String temporal){
		File maestroF=new File(maestro);
		File tempF=new File(temporal);
		File maestroAct=new File(maestro+"act");
		
		FileInputStream fisMaestro=null;
		DataInputStream disMaestro=null;
		
		FileInputStream fisTemp=null;
		DataInputStream disTemp=null;
		
		FileOutputStream fosMactu=null;
		DataOutputStream dosMactu=null;
		
		FicheroCliente fc=new FicheroCliente();
		
		int registrosMaestro,registrosTemp;
		long dniMaestro,dniAct;
		String correoMaestro,correoAct;
		String direccionMaestro,direccionAct;
		
		registrosMaestro=fc.cuentaClientes(maestro);
		registrosTemp=fc.cuentaClientes(temporal);
		
		try {
			fisMaestro=new FileInputStream(maestro);
			disMaestro=new DataInputStream(fisMaestro);
			
			fisTemp=new FileInputStream(temporal);
			disTemp=new DataInputStream(fisTemp);
			
			fosMactu=new FileOutputStream(maestro+"act");
			dosMactu=new DataOutputStream(fosMactu);
			
			//Lectura anticipada
			dniMaestro=disMaestro.readLong();
			correoMaestro="";
			for(int i=0;i<30;i++){
				correoMaestro=correoMaestro+disMaestro.readChar();
			}
			direccionMaestro="";
			for(int i=0;i<20;i++){
				direccionMaestro=direccionMaestro+disMaestro.readChar();
			}
			
			dniAct=disTemp.readLong();
			correoAct="";
			for(int i=0;i<30;i++){
				correoAct=correoAct+disTemp.readChar();
			}
			
			direccionAct="";
			for(int i=0;i<20;i++){
				direccionAct=direccionAct+disTemp.readChar();
			}
			
			
			while(registrosMaestro>0 && registrosTemp>0){
				//Modificación o eliminación
				if(dniMaestro==dniAct){
					//Si la direccion de la persona actualizada está vacía(todo asteriscos) es una eliminación
					//En caso contrario es una modificación
					
					if(!direccionAct.equals("********************")){
						dosMactu.writeLong(dniAct);
						dosMactu.writeChars(correoAct);
						dosMactu.writeChars(direccionAct);
					}
					registrosTemp--;
					registrosMaestro--;
					//Leemos del Actualizado
					if(registrosTemp>0){
						dniAct=disTemp.readLong();
						correoAct="";
						for(int i=0;i<30;i++){
							correoAct=correoAct+disTemp.readChar();
						}
						
						direccionAct="";
						for(int i=0;i<20;i++){
							direccionAct=direccionAct+disTemp.readChar();
						}
					}
					//Leemos Maestro
					if(registrosMaestro>0){
						dniMaestro=disMaestro.readLong();
						correoMaestro="";
						for(int i=0;i<30;i++){
							correoMaestro=correoMaestro+disMaestro.readChar();
						}
						direccionMaestro="";
						for(int i=0;i<20;i++){
							direccionMaestro=direccionMaestro+disMaestro.readChar();
						}
					}
				}else{
					//Nada
					//Escribimos en Maestro
					if(dniMaestro<=dniAct){
						dosMactu.writeLong(dniMaestro);
						dosMactu.writeChars(correoMaestro);
						dosMactu.writeChars(direccionMaestro);
						registrosMaestro--;
						//Leemos de maestro
						if(registrosMaestro>0){
							dniMaestro=disMaestro.readLong();
							correoMaestro="";
							for(int i=0;i<30;i++){
								correoMaestro=correoMaestro+disMaestro.readChar();
							}
							direccionMaestro="";
							for(int i=0;i<20;i++){
								direccionMaestro=direccionMaestro+disMaestro.readChar();
							}
						}
					}else{
						//Alta
						dosMactu.writeLong(dniAct);
						dosMactu.writeChars(correoAct);
						dosMactu.writeChars(direccionAct);
						registrosTemp--;
						if(registrosTemp>0){
							dniAct=disTemp.readLong();
							correoAct="";
							for(int i=0;i<30;i++){
								correoAct=correoAct+disTemp.readChar();
							}
							
							direccionAct="";
							for(int i=0;i<20;i++){
								direccionAct=direccionAct+disTemp.readChar();
							}
						}
					}
				}
			}
			if(registrosMaestro==0){
				while(registrosTemp>0){
					dosMactu.writeLong(dniAct);
					dosMactu.writeChars(correoAct);
					dosMactu.writeChars(direccionAct);
					registrosTemp--;
					if(registrosTemp>0){
						dniAct=disTemp.readLong();
						correoAct="";
						for(int i=0;i<30;i++){
							correoAct=correoAct+disTemp.readChar();
						}
						
						direccionAct="";
						for(int i=0;i<20;i++){
							direccionAct=direccionAct+disTemp.readChar();
						}
					}
				}
			}
			if(registrosTemp==0){
				while(registrosMaestro>0){
					dosMactu.writeLong(dniMaestro);
					dosMactu.writeChars(correoMaestro);
					dosMactu.writeChars(direccionMaestro);
					registrosMaestro--;
					//Leemos de maestro
					if(registrosMaestro>0){
						dniMaestro=disMaestro.readLong();
						correoMaestro="";
						for(int i=0;i<30;i++){
							correoMaestro=correoMaestro+disMaestro.readChar();
						}
						direccionMaestro="";
						for(int i=0;i<20;i++){
							direccionMaestro=direccionMaestro+disMaestro.readChar();
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally{
			if(disMaestro!=null){
				try {
					disMaestro.close();
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
			if(disTemp!=null){
				try {
					disTemp.close();
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
			if(dosMactu!=null){
				try {
					dosMactu.close();
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
	
	/* 
	 * Interfaz 
	 * Cabecera:public void actualizaEmpleados(String maestro,String temporal)
	 * Proceso:Actualiza un fichero maestro con otro de actualización
	 * Precondiciones:LOS FICHEROS DEBEN ESTAR ORDENADOS
	 * Entrada:Nada
	 * Salida:Nada
	 * Entrada/Salida:1 cadena para la ruta del fichero maestro
	 * 					1 cadena para la ruta del fichero temporal
	 * Postcondiciones:el maestro quedará sobreescrito con todos los cambios
	 * 					el temporal quedará borrado.
	 */
	
	public void actualizaEmpleados(String maestro,String temporal){
		File maestroF=new File(maestro);
		File tempF=new File(temporal);
		File maestroAct=new File(maestro+"act");
		
		FileInputStream fisMaestro=null;
		DataInputStream disMaestro=null;
		
		FileInputStream fisTemp=null;
		DataInputStream disTemp=null;
		
		FileOutputStream fosMactu=null;
		DataOutputStream dosMactu=null;
		
		FicheroEmpleado fe=new FicheroEmpleado();
		
		int registrosMaestro,registrosTemp;
		long dniMaestro,dniAct;
		String cuentaMaestro,cuentaAct;
		double sueldoMaestro,sueldoAct;
		short tiendaMaestro,tiendaAct;
		
		registrosMaestro=fe.cuentaEmpleados(maestro);
		registrosTemp=fe.cuentaEmpleados(temporal);
		
		try {
			fisMaestro=new FileInputStream(maestro);
			disMaestro=new DataInputStream(fisMaestro);
			
			fisTemp=new FileInputStream(temporal);
			disTemp=new DataInputStream(fisTemp);
			
			fosMactu=new FileOutputStream(maestro+"act");
			dosMactu=new DataOutputStream(fosMactu);
			
			//Lectura anticipada
			dniMaestro=disMaestro.readLong();
			cuentaMaestro="";
			for (int i = 0; i < 20; i++) {
				cuentaMaestro = cuentaMaestro + disMaestro.readChar();
			}
			sueldoMaestro=disMaestro.readDouble();
			tiendaMaestro=disMaestro.readShort();
			
			dniAct=disTemp.readLong();
			cuentaAct="";
			for (int i = 0; i < 20; i++) {
				cuentaAct = cuentaAct + disTemp.readChar();
			}
			sueldoAct=disTemp.readDouble();
			tiendaAct=disTemp.readShort();
			
			
			while(registrosMaestro>0 && registrosTemp>0){
				//Modificación o eliminación
				if(dniMaestro==dniAct){
					//Si la tienda es igual a 0 es una eliminación
					//En caso contrario es una modificación
					
					if(tiendaAct!=0){
						dosMactu.writeLong(dniAct);
						dosMactu.writeChars(cuentaAct);
						dosMactu.writeDouble(sueldoAct);
						dosMactu.writeShort(tiendaAct);
					}
					registrosTemp--;
					registrosMaestro--;
					//Leemos del Actualizado
					if(registrosTemp>0){
						dniAct=disTemp.readLong();
						cuentaAct="";
						for (int i = 0; i < 20; i++) {
							cuentaAct = cuentaAct + disTemp.readChar();
						}
						sueldoAct=disTemp.readDouble();
						tiendaAct=disTemp.readShort();
					}
					//Leemos Maestro
					if(registrosMaestro>0){
						dniMaestro=disMaestro.readLong();
						cuentaMaestro="";
						for (int i = 0; i < 20; i++) {
							cuentaMaestro = cuentaMaestro + disMaestro.readChar();
						}
						sueldoMaestro=disMaestro.readDouble();
						tiendaMaestro=disMaestro.readShort();
					}
				}else{
					//Nada
					//Escribimos en Maestro
					if(dniMaestro<=dniAct){
						dosMactu.writeLong(dniMaestro);
						dosMactu.writeChars(cuentaMaestro);
						dosMactu.writeDouble(sueldoMaestro);
						dosMactu.writeShort(tiendaMaestro);
						registrosMaestro--;
						//Leemos de maestro
						if(registrosMaestro>0){
							dniMaestro=disMaestro.readLong();
							cuentaMaestro="";
							for (int i = 0; i < 20; i++) {
								cuentaMaestro = cuentaMaestro + disMaestro.readChar();
							}
							sueldoMaestro=disMaestro.readDouble();
							tiendaMaestro=disMaestro.readShort();
						}
					}else{
						//Alta
						dosMactu.writeLong(dniAct);
						dosMactu.writeChars(cuentaAct);
						dosMactu.writeDouble(sueldoAct);
						dosMactu.writeShort(tiendaAct);
						registrosTemp--;
						if(registrosTemp>0){
							dniAct=disTemp.readLong();
							cuentaAct="";
							for (int i = 0; i < 20; i++) {
								cuentaAct = cuentaAct + disTemp.readChar();
							}
							sueldoAct=disTemp.readDouble();
							tiendaAct=disTemp.readShort();
						}
					}
				}
			}
			if(registrosMaestro==0){
				while(registrosTemp>0){
					dosMactu.writeLong(dniAct);
					dosMactu.writeChars(cuentaAct);
					dosMactu.writeDouble(sueldoAct);
					dosMactu.writeShort(tiendaAct);
					registrosTemp--;
					if(registrosTemp>0){
						dniAct=disTemp.readLong();
						cuentaAct="";
						for (int i = 0; i < 20; i++) {
							cuentaAct = cuentaAct + disTemp.readChar();
						}
						sueldoAct=disTemp.readDouble();
						tiendaAct=disTemp.readShort();
					}
				}
			}
			if(registrosTemp==0){
				while(registrosMaestro>0){
					dosMactu.writeLong(dniMaestro);
					dosMactu.writeChars(cuentaMaestro);
					dosMactu.writeDouble(sueldoMaestro);
					dosMactu.writeShort(tiendaMaestro);
					registrosMaestro--;
					//Leemos de maestro
					if(registrosMaestro>0){
						dniMaestro=disMaestro.readLong();
						cuentaMaestro="";
						for (int i = 0; i < 20; i++) {
							cuentaMaestro = cuentaMaestro + disMaestro.readChar();
						}
						sueldoMaestro=disMaestro.readDouble();
						tiendaMaestro=disMaestro.readShort();
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally{
			if(disMaestro!=null){
				try {
					disMaestro.close();
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
			if(disTemp!=null){
				try {
					disTemp.close();
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
			if(dosMactu!=null){
				try {
					dosMactu.close();
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

	/* 
	 * Interfaz 
	 * Cabecera:public void actualizaPastillas(String maestro,String temporal)
	 * Proceso:Actualiza un fichero maestro con otro de actualización
	 * Precondiciones:LOS FICHEROS DEBEN ESTAR ORDENADOS
	 * Entrada:Nada
	 * Salida:Nada
	 * Entrada/Salida:1 cadena para la ruta del fichero maestro
	 * 					1 cadena para la ruta del fichero temporal
	 * Postcondiciones:el maestro quedará sobreescrito con todos los cambios
	 * 					el temporal quedará borrado.
	 */
	
	public void actualizaPastillas(String maestro,String temporal){
		File maestroF=new File(maestro);
		File tempF=new File(temporal);
		File maestroAct=new File(maestro+"act");
		
		FileInputStream fisMaestro=null;
		ObjectInputStream oisMaestro=null;
		
		FileInputStream fisTemp=null;
		ObjectInputStream oisTemp=null;
		
		FileOutputStream fosMactu=null;
		ObjectOutputStream oosMactu=null;
		
		FicheroPastillas fp=new FicheroPastillas();
		
		int registrosMaestro,registrosTemp;
		Pastilla p,paux;
		
		registrosMaestro=fp.cuentaPastillas(maestro);
		registrosTemp=fp.cuentaPastillas(temporal);
		
		try {
			fisMaestro=new FileInputStream(maestro);
			oisMaestro=new ObjectInputStream(fisMaestro);
			
			fisTemp=new FileInputStream(temporal);
			oisTemp=new ObjectInputStream(fisTemp);
			
			fosMactu=new FileOutputStream(maestro+"act");
			oosMactu=new ObjectOutputStream(fosMactu);
			//Lectura anticipada
			p=(Pastilla) oisMaestro.readObject();
			paux=(Pastilla) oisTemp.readObject();
			
			while(registrosMaestro>0 && registrosTemp>0){
				//Modificación o eliminación
				if(p.getId()==paux.getId()){
					//Si la marca está vacía, significa que vamos a borrar dicha pastilla
					//en caso contrario, la guardamos,puesto que es una modificación
					if(!paux.getMarca().equals("")){
						oosMactu.writeObject(paux);
					}
					registrosTemp--;
					registrosMaestro--;
					if(registrosTemp>0){
						paux=(Pastilla) oisTemp.readObject();
					}
					if(registrosMaestro>0){
						p=(Pastilla) oisMaestro.readObject();
					}
				}else{
					//Nada
					if(p.getId()<=paux.getId()){
						oosMactu.writeObject(p);
						registrosMaestro--;
						if(registrosMaestro>0){
							p=(Pastilla) oisMaestro.readObject();
						}
					}else{
						//Alta
						oosMactu.writeObject(paux);
						registrosTemp--;
						if(registrosTemp>0){
							paux=(Pastilla) oisTemp.readObject();
						}
					}
				}
			}
			if(registrosMaestro==0){
				while(registrosTemp>0){
					oosMactu.writeObject(paux);
					registrosTemp--;
					if(registrosTemp>0){
						paux=(Pastilla) oisTemp.readObject();
					}
				}
			}
			if(registrosTemp==0){
				while(registrosMaestro>0){
					oosMactu.writeObject(p);
					registrosMaestro--;
					if(registrosMaestro>0){
						p=(Pastilla) oisMaestro.readObject();
					}
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

	/* 
	 * Interfaz 
	 * Cabecera:public void actualizaCompras(String maestro,String temporal)
	 * Proceso:Actualiza un fichero maestro con otro de actualización.
	 * 			-Si el registro se encuentra en el maestro y no en el actualizado, nada
	 * 			-Si el registro se encuentra en el maestru y en el actualizado, eliminación
	 * 			-Si el registro no se encuentra en el maestro y si en el actualizado, alta
	 * Precondiciones:LOS FICHEROS DEBEN ESTAR ORDENADOS
	 * Entrada:Nada
	 * Salida:Nada
	 * Entrada/Salida:1 cadena para la ruta del fichero maestro
	 * 					1 cadena para la ruta del fichero temporal
	 * Postcondiciones:el maestro quedará sobreescrito con todos los cambios
	 * 					el temporal quedará borrado.
	 */
	
	public void actualizaCompras(String maestro,String temporal){
		File maestroF=new File(maestro);
		File tempF=new File(temporal);
		File maestroAct=new File(maestro+"act");
		FicheroCliente fc=new FicheroCliente();
		
		FileInputStream fisMaestro=null;
		DataInputStream disMaestro=null;
		
		FileInputStream fisTemp=null;
		DataInputStream disTemp=null;
		
		FileOutputStream fosMactu=null;
		DataOutputStream dosMactu=null;
		
		int registrosMaestro,registrosTemp;
		long dniMaestro,dniAct;
		int idMaestro,idAct;
		
		try {
			fisMaestro=new FileInputStream(maestroF);
			disMaestro=new DataInputStream(fisMaestro);
			
			fisTemp=new FileInputStream(tempF);
			disTemp=new DataInputStream(fisTemp);
			
			fosMactu=new FileOutputStream(maestroAct);
			dosMactu=new DataOutputStream(fosMactu);
			
			registrosMaestro=fc.cuentaCompras(maestro);
			registrosTemp=fc.cuentaCompras(temporal);
			//Lectura anticipada
			dniMaestro=disMaestro.readLong();
			idMaestro=disMaestro.readInt();
			
			dniAct=disTemp.readLong();
			idAct=disTemp.readInt();
			
			while (registrosMaestro > 0 && registrosTemp > 0) {
				if(dniMaestro == dniAct){
					if(idMaestro < idAct){
						//Sobreescritura
						dosMactu.writeLong(dniMaestro);
						dosMactu.writeInt(idMaestro);
						registrosMaestro--;

						if(registrosMaestro > 0){
							dniMaestro = disMaestro.readLong();
							idMaestro = disMaestro.readInt();
						}
					}else{
						//Alta
						if(idMaestro > idAct){
							dosMactu.writeLong(dniAct);
							dosMactu.writeInt(idAct);
							registrosTemp--;

							if(registrosTemp > 0){
								dniAct = disTemp.readLong();
								idAct = disTemp.readInt();
							}
							//Borrado
						}else{
							registrosMaestro--;
							if(registrosMaestro > 0){
								dniMaestro = disMaestro.readLong();
								idMaestro = disMaestro.readInt();
							}
							registrosTemp--;
							if(registrosTemp > 0){
								dniAct = disTemp.readLong();
								idAct = disTemp.readInt();
							}
						}

					}

				} else {
					if (dniMaestro < dniAct) {
						//Sobreescritura
						dosMactu.writeLong(dniMaestro);
						dosMactu.writeInt(idMaestro);
						registrosMaestro--;

						if(registrosMaestro > 0){
							dniMaestro = disMaestro.readLong();
							idMaestro = disMaestro.readInt();
						}
					} else {
						//Alta
						dosMactu.writeLong(dniAct);
						dosMactu.writeInt(idAct);
						registrosTemp--;

						if(registrosTemp > 0){
							dniAct = disTemp.readLong();
							idAct = disTemp.readInt();
						}
					}
				}
			}
			if (registrosMaestro == 0) {
				while (registrosTemp > 0) {
					//Alta
					dosMactu.writeLong(dniAct);
					dosMactu.writeInt(idAct);
					registrosTemp--;

					if(registrosTemp > 0){
						dniAct = disTemp.readLong();
						idAct = disTemp.readInt();
					}
				}
			}
			if (registrosTemp == 0) {
				while (registrosMaestro > 0) {
					//Sobreescritura
					dosMactu.writeLong(dniMaestro);
					dosMactu.writeInt(idMaestro);
					registrosMaestro--;

					if(registrosMaestro > 0){
						dniMaestro = disMaestro.readLong();
						idMaestro = disMaestro.readInt();
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if (disMaestro != null) {
				try {
					disMaestro.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if (fisMaestro != null) {
				try {
					fisMaestro.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if (disTemp != null) {
				try {
					disTemp.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if (fisTemp != null) {
				try {
					fisTemp.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if (dosMactu != null) {
				try {
					dosMactu.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if (fosMactu != null) {
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
