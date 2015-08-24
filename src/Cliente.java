import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Cliente implements Serializable {
	
	private String DNI;
	private String Nombre;
	private ArrayList<Cuenta> Ccli;
	private String Password;
	private String Fnacimiento;
	private String Direccion;
	private String Telefono;
	private String Pass;

	public Cliente() 
	{	
		this.DNI = DNI;
		this.Nombre = Nombre;
		this.Ccli = Ccli;
		this.Password = Password;
		this.Fnacimiento = Fnacimiento;
		this.Direccion = Direccion;
		this.Telefono = Telefono;	
		this.Pass=Pass;
		
	}

	public String getDNI() {return DNI;	}
	public void setDNI(String dNI) {DNI = dNI;}
	public String getNombre() {return Nombre;}
	public void setNombre(String nombre) {Nombre = nombre;}
	public ArrayList<Cuenta> getCcli() {return Ccli;}
	public void setCcli(ArrayList<Cuenta> ccli) {Ccli = ccli;}
	public String getPassword() {return Password;}
	public void setPassword(String password) {Password = password;}
	public String getFnacimiento() {return Fnacimiento;}
	public void setFnacimiento(String fnacimiento) {Fnacimiento = fnacimiento;}
	public String getDireccion() {return Direccion;}
	public void setDireccion(String direccion) {Direccion = direccion;}
	public String getTelefono() {return Telefono;}
	public void setTelefono(String telefono) {Telefono = telefono;}
	public String getPass() {return Pass;}
	public void setPass(String pass) {Pass = pass;}	
	

}

