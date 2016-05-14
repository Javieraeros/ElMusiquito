/*
 * Restricciones:
 * **************
 * BoquillaSAxo menos de 20 caracteres
 * Material menos de 20 caracteres
 * 
 * Métods añadidos/Heredados:
 * **************************
 * String toString();
 */

package datos;

import enums.Acabado;
import enums.FamiliaSaxo;
import enums.Marca;
import interfaces.Saxofon;

public class SaxofonImpl extends VientoImpl implements Saxofon {
	
	private static final long serialVersionUID = 1L;

	
	private FamiliaSaxo familia;
	private String boquillaSaxo;
	private String material;
	private Acabado acabado;
	
	public SaxofonImpl() {
		super();
		this.familia=FamiliaSaxo.Alto;
		this.boquillaSaxo="S80 C*";
		this.material="Latón";
		this.acabado=Acabado.DoradoB;
	}

	public SaxofonImpl(String nombre, Marca marca, String descripcion, String modelo, double precio,
			char afinacion,String tesitura, byte boquilla,
			FamiliaSaxo familia,String boquillaSaxo,String material,Acabado acabado) {
		super(nombre, marca, descripcion, modelo, precio, afinacion, tesitura, boquilla);
		this.familia=familia;
		
		if(boquillaSaxo.length()>20){
			System.out.println("Error, el boquillaSaxo no puede contener más de 20 caracteres");
			boquillaSaxo=boquillaSaxo.substring(0,20);
			System.out.println("Se guardará: "+boquillaSaxo);
		}
		this.boquillaSaxo=boquillaSaxo;
		
		if(material.length()>20){
			System.out.println("Error, el material no puede contener más de 20 caracteres");
			material=material.substring(0,20);
			System.out.println("Se guardará: "+material);
		}
		this.material=material;

		this.acabado=acabado;
	}

	public SaxofonImpl(int id, String nombre, Marca marca, String descripcion, String modelo,
			double precio,char afinacion, String tesitura, byte boquilla,
			FamiliaSaxo familia,String boquillaSaxo,String material,Acabado acabado) {
		
		super(id, nombre, marca, descripcion, modelo, precio, afinacion, tesitura, boquilla);
		
		this.familia=familia;
		
		if(boquillaSaxo.length()>20){
			System.out.println("Error, el boquillaSaxo no puede contener más de 20 caracteres");
			boquillaSaxo=boquillaSaxo.substring(0,20);
			System.out.println("Se guardará: "+boquillaSaxo);
		}
		this.boquillaSaxo=boquillaSaxo;
		
		if(material.length()>20){
			System.out.println("Error, el material no puede contener más de 20 caracteres");
			material=material.substring(0,20);
			System.out.println("Se guardará: "+material);
		}
		this.material=material;

		this.acabado=acabado;
	}

	public SaxofonImpl(VientoImpl viento,FamiliaSaxo familia,String boquillaSaxo,String material,Acabado acabado) {
		super(viento);

		this.familia=familia;
		
		if(boquillaSaxo.length()>20){
			System.out.println("Error, el boquillaSaxo no puede contener más de 20 caracteres");
			boquillaSaxo=boquillaSaxo.substring(0,20);
			System.out.println("Se guardará: "+boquillaSaxo);
		}
		this.boquillaSaxo=boquillaSaxo;
		
		if(material.length()>20){
			System.out.println("Error, el material no puede contener más de 20 caracteres");
			material=material.substring(0,20);
			System.out.println("Se guardará: "+material);
		}
		this.material=material;

		this.acabado=acabado;
	}

	//Copia
	public SaxofonImpl(SaxofonImpl saxo){
		super(saxo.getId(), saxo.getNombre(), saxo.getMarca(), 
				saxo.getDescripcion(), saxo.getModelo(), saxo.getPrecioVenta(),
				saxo.getAfinacion(),saxo.getTesitura(),saxo.getBoquilla());
		this.familia=saxo.getFamilia();
		this.boquillaSaxo=saxo.getBoquillaSaxo();
		this.material=saxo.getMaterial();
		this.acabado=saxo.getAcabado();
	}
	
	@Override
	public FamiliaSaxo getFamilia() {
		return familia;
	}

	@Override
	public String getBoquillaSaxo() {
		return boquillaSaxo;
	}

	@Override
	public String getMaterial() {
		return material;
	}

	@Override
	public Acabado getAcabado() {
		return acabado;
	}

	@Override
	public void setFamilia(FamiliaSaxo familia) {
		this.familia=familia;
	}

	@Override
	public void setBoquillaSaxo(String boquillaSaxo) {
		if(boquillaSaxo.length()>20){
			System.out.println("Error, el boquillaSaxo no puede contener más de 20 caracteres");
			boquillaSaxo=boquillaSaxo.substring(0,20);
			System.out.println("Se guardará: "+boquillaSaxo);
		}
		this.boquillaSaxo=boquillaSaxo;
	}

	@Override
	public void setMaterial(String material) {
		if(material.length()>20){
			System.out.println("Error, el material no puede contener más de 20 caracteres");
			material=material.substring(0,20);
			System.out.println("Se guardará: "+material);
		}
		this.material=material;

	}

	@Override
	public void setAcabado(Acabado acabado) {
		this.acabado=acabado;
	}

	@Override
	public String toString(){
		String salida=super.toString();
		salida=salida+"\n"+familia+" "+boquillaSaxo+" "+material+" "+acabado;
		return salida;
	}
}
