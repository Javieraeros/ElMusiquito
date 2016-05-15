/*
 * Restricciones:
 * **************
 * Máximo 3 pastillas
 * Mínimo 1 control
 * 
 * Métods añadidos/heredados:
 * String toString();
 * 
 */

package datos;

import enums.Marca;
import enums.Tipo;
import interfaces.GuitarraElectrica;

public class GuitarraElectricaImpl extends CuerdaImpl implements GuitarraElectrica {
	private static final long serialVersionUID = 1L;

	private Tipo tipo;
	private Pastilla[] pastillas;
	private boolean puenteFlotante;
	private int controles;
	
	
	public GuitarraElectricaImpl() {
		super();
		this.tipo=Tipo.ES;
		Pastilla defecto=new Pastilla();
		Pastilla[] aPastillas={defecto,defecto,defecto};
		this.pastillas=aPastillas;
		this.puenteFlotante=false;
		this.controles=2;
	}

	public GuitarraElectricaImpl(String nombre, Marca marca, String descripcion, String modelo, double precio,
			int cuerdas, String registro, byte tipoCuerda,
			Tipo tipo,Pastilla[] pastillas,boolean puenteFlotante,int controles) {
		super(nombre, marca, descripcion, modelo, precio, cuerdas, registro, tipoCuerda);
		
		this.tipo=tipo;
		
		if(pastillas.length>3){
			System.out.println("Error, solo se pueden poner 3 pastillas. Se guardaran las 3 primeras");
			Pastilla[] aux={pastillas[0],pastillas[1],pastillas[2]};
			this.pastillas=aux;
		}else{
			this.pastillas=pastillas;
		}
		

		this.puenteFlotante=puenteFlotante;

		if(controles<1){
			System.out.println("Error, la guitarra tiene que tener al menos 1 controlador");
			controles=1;
		}
		this.controles=controles;
	}

	public GuitarraElectricaImpl(int id, String nombre, Marca marca, String descripcion, String modelo, double precio,
			int cuerdas, String registro, byte tipoCuerda,
			Tipo tipo,Pastilla[] pastillas,boolean puenteFlotante,int controles) {
		super(id, nombre, marca, descripcion, modelo, precio, cuerdas, registro, tipoCuerda);
		
		this.tipo=tipo;
		
		if(pastillas.length>3){
			System.out.println("Error, solo se pueden poner 3 pastillas. Se guardaran las 3 primeras");
			Pastilla[] aux={pastillas[0],pastillas[1],pastillas[2]};
			this.pastillas=aux;
		}else{
			this.pastillas=pastillas;
		}
		

		this.puenteFlotante=puenteFlotante;

		if(controles<1){
			System.out.println("Error, la guitarra tiene que tener al menos 1 controlador");
			controles=1;
		}
		this.controles=controles;
	}

	public GuitarraElectricaImpl(InstrumentoImpl instrumento, 
			int cuerdas, String registro, byte tipoCuerda,
			Tipo tipo,Pastilla[] pastillas,boolean puenteFlotante,int controles
			) {
		super(instrumento, cuerdas, registro, tipoCuerda);
		
		this.tipo=tipo;
		
		if(pastillas.length>3){
			System.out.println("Error, solo se pueden poner 3 pastillas. Se guardaran las 3 primeras");
			Pastilla[] aux={pastillas[0],pastillas[1],pastillas[2]};
			this.pastillas=aux;
		}else{
			this.pastillas=pastillas;
		}
		

		this.puenteFlotante=puenteFlotante;

		if(controles<1){
			System.out.println("Error, la guitarra tiene que tener al menos 1 controlador");
			controles=1;
		}
		this.controles=controles;
	}

	public GuitarraElectricaImpl(CuerdaImpl cuerda,
			Tipo tipo,Pastilla[] pastillas,boolean puenteFlotante,int controles) {
		super(cuerda);
		
		this.tipo=tipo;
		
		if(pastillas.length>3){
			System.out.println("Error, solo se pueden poner 3 pastillas. Se guardaran las 3 primeras");
			Pastilla[] aux={pastillas[0],pastillas[1],pastillas[2]};
			this.pastillas=aux;
		}else{
			this.pastillas=pastillas;
		}
		

		this.puenteFlotante=puenteFlotante;

		if(controles<1){
			System.out.println("Error, la guitarra tiene que tener al menos 1 controlador");
			controles=1;
		}
		this.controles=controles;
	}

	@Override
	public Tipo getTipo() {
		return tipo;
	}

	@Override
	public Pastilla[] getPastillas() {
		return pastillas;
	}

	@Override
	public boolean getPuenteFlotante() {
		return puenteFlotante;
	}

	@Override
	public int getControles() {
		return controles;
	}

	@Override
	public void setTipo(Tipo tipo) {
		this.tipo=tipo;
	}

	@Override
	public void setPastillas(Pastilla[] pastillas) {
		if(pastillas.length>3){
			System.out.println("Error, solo se pueden poner 3 pastillas. Se guardaran las 3 primeras");
			Pastilla[] aux={pastillas[0],pastillas[1],pastillas[2]};
			this.pastillas=aux;
		}else{
			this.pastillas=pastillas;
		}
	}

	@Override
	public void setPuenteFlotante(boolean puenteFlotante) {
		this.puenteFlotante=puenteFlotante;

	}

	@Override
	public void setControles(int controles) {
		if(controles<1){
			System.out.println("Error, la guitarra tiene que tener al menos 1 controlador");
			controles=1;
		}
		this.controles=controles;
	}

	@Override
	public String toString(){
		String salida=super.toString();
		salida=salida+"\nTipo: "+tipo+"\nNumero de Pastillas: "+pastillas.length+"\nPuenteFlotante: "+puenteFlotante+"\nNumero controles: "+controles;
		return salida;
	}
}
