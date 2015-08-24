import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {
	public static ArrayList<Cliente> listadoClientes;
	public static ArrayList<Cuenta> listadoCuentas;
	static int opc;
	
	

	public static void main(String[] args) throws IOException, SQLException {
				
		do{
			
			System.out.println("Selecciona la opción deseada:");
			System.out.println("===============================================");
			System.out.println("1. Insertar todos los datos.");
			System.out.println("2. Consultar datos por DNI.");
			System.out.println("3. Consultar saldo.");
			System.out.println("4. Realizar transferencia.");
			System.out.println("5. Sacar  dinero.");
			System.out.println("6. Ingresar dinero.");
			System.out.println("0. Salir.");
			
	
			//Leo la opción seleccionada del menú.
			Scanner in=new Scanner(System.in);
			opc=in.nextInt();
		
			
			switch (opc) {
			
			case 0:
				  System.out.println("Programa finalizado.");
			    break;
			
			  case 1:
				  CrearDatos();
				  Statement c1=conn();
				  GuardarBD(c1);
			    break;
			  case 2:
				  Statement c2=conn();
				  Listar(c2);
			    break;
			  case 3:
				  Statement c3=conn();
				  ConsultarSaldo(c3);
				  break;
			  case 4:		
				  Statement c4=conn();
				  Statement c7=conn();
				 Transferencias(c4);
				  break;
				  
			 case 5:
				 Statement c5=conn();
				 SacarDinero(c5);
				  break;
				  
			 case 6:
				 Statement c6=conn();
				 IngresarDinero(c6);
				  break;
			    
			  default:
			    break;
			}
		}while(opc!=0);
		CrearDatos();
		//GuardarFichero();
		//GuardarBD();
		
	}
	
	static void CrearDatos()
	{		 
		listadoClientes=new ArrayList<Cliente>();
		listadoCuentas=new ArrayList<Cuenta>();
		
		Cuenta cuent1=new Cuenta("3453245234542", 1247.3, TCuentas.AHORRO , "2003-10-03", "86235962M");
		Cuenta cuent2=new Cuenta("5764562443252", 36235, TCuentas.JOVEN , "2001-08-12", "45333333A");
		Cuenta cuent3=new Cuenta("6786545763423", 7634, TCuentas.AHORRO , "2000-01-30", "1532675X");
		Cuenta cuent4=new Cuenta("3246547536754", 600, TCuentas.CORRIENTE , "2010-01-21", "1532675X");
		Cuenta cuent5=new Cuenta("3214534642664", 1247, TCuentas.CORRIENTE , "2005-06-17", "86235962M");
		Cuenta cuent6=new Cuenta("3457567235234", 30005, TCuentas.AHORRO , "2005-11-25", "2265972N");
		Cuenta cuent7=new Cuenta("1767987667787", 380, TCuentas.JOVEN , "2005-01-22", "79681686K");
		
		listadoCuentas.add(cuent1);
		listadoCuentas.add(cuent2);
		listadoCuentas.add(cuent3);
		listadoCuentas.add(cuent4);
		listadoCuentas.add(cuent5);
		listadoCuentas.add(cuent6);
		listadoCuentas.add(cuent7);
		
		//********************Cliente 1*********************
		
		Cliente c1=new Cliente();
		c1.setDNI("86235962M");
		c1.setNombre("Juan Manuel Herrera");
		c1.setDireccion("Avenida Maniano N3 1A");
		c1.setFnacimiento("1990-04-20");
		c1.setTelefono("696352115");
		c1.setPassword("4321");
		c1.setCcli(listadoCuentas);
		
		
		
		//******************* Cliente 2***********************

		Cliente c2=new Cliente();
		c2.setDNI("45333333A");
		c2.setNombre("María Sanchez Carrión");
		c2.setDireccion("Calle Juan Rico N6");
		c2.setFnacimiento("1976-05-10");
		c2.setTelefono("696352115");
		c2.setPassword("4564");
		c2.setCcli(listadoCuentas);
		
		//********************* Cliente 3**********************

		Cliente c3=new Cliente();
		c3.setDNI("79681686K");
		c3.setNombre("Manuela Maria Perez");
		c3.setDireccion("Plaza de las acacias N7");
		c3.setFnacimiento("1960-10-10");
		c3.setTelefono("696352115");
		c3.setPassword("7987");
		c3.setCcli(listadoCuentas);
		
		//************************Cliente 4**********************

		Cliente c4=new Cliente();
		c4.setDNI("2265972N");
		c4.setNombre("Luisa Perez Pastor");
		c4.setDireccion("Calle Bartolico N20 1E");
		c4.setFnacimiento("1982-01-18");
		c4.setTelefono("696352115");
		c4.setPassword("8876");
		c4.setCcli(listadoCuentas);
		
		//********************* Cliente 5****************************

		Cliente c5=new Cliente();
		c5.setDNI("1532675X");
		c5.setNombre("Alberto Gonzalez Mariano");
		c5.setDireccion("Calle Moncada N8 8A");
		c5.setFnacimiento("1990-01-20");
		c5.setTelefono("696352115");
		c5.setPassword("1234");		
		c5.setCcli(listadoCuentas);
		
		
		listadoClientes.add(c1);
		listadoClientes.add(c2);
		listadoClientes.add(c3);
		listadoClientes.add(c4);
		listadoClientes.add(c5);
					
	}	
	
	public static Statement conn()
	{
		Statement comando=null;		
		try
	    {
			//Creo la conexión a la Base de Datos
			final String DRIVER = "com.mysql.jdbc.Driver";
			Class.forName(DRIVER).newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/banco","root" ,"");
			//Statement comando=conn.createStatement();
			comando=conn.createStatement();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception!");
	      System.err.println(e.getMessage());
	    }
		
		return comando;
	}
	
	
	static void GuardarBD(Statement c1) throws SQLException
	{

		
		
		for(int i=0;i<Main.listadoClientes.size();i++)
		{
			c1.executeUpdate("insert into clientes (dni_cliente, nombre, direccion, telefono , fecha_nac, pass)"
					+ " values (" + "'"+ Main.listadoClientes.get(i).getDNI()+"'" + ", " + "'"+ Main.listadoClientes.get(i).getNombre()+"'" + ", " + "'"+ Main.listadoClientes.get(i).getDireccion()+"'" + " , " + "'"+ Main.listadoClientes.get(i).getTelefono()+"'" + ", " + "'"+ Main.listadoClientes.get(i).getFnacimiento()+"'" + ", " + "'"+ Main.listadoClientes.get(i).getPassword()+"'" + ")");
		}
			//System.out.println(Main.listadoCuentas.get(0).getNCuenta());
		for(int i=0;i<Main.listadoCuentas.size();i++)
		{
			c1.executeUpdate("insert into cuenta (num_cuenta, saldo, fecha_apertura, tipo_cuenta , dni_owner)"
					+ " values (" + "'"+ Main.listadoCuentas.get(i).getNCuenta()+"'" + ", " + "'"+ Main.listadoCuentas.get(i).getSaldo()+"'" + ", " + "'"+ Main.listadoCuentas.get(i).getFApertura()+"'" + " , " + "'"+ Main.listadoCuentas.get(i).getTipoCuenta()+"'" + ", " + "'"+ Main.listadoCuentas.get(i).getDNI_owner()+"'" + ")");
		}
		c1.getConnection().close();
		
	}
	
	static void Listar(Statement c2) throws SQLException
	{
		//Statement comando=c2.createStatement();
		System.out.println("Introduce el DNI, para consultar datos: ");
		Scanner t=new Scanner(System.in);
		String dato=t.nextLine();
		ResultSet rs1=c2.executeQuery("select dni_cliente, nombre, direccion, telefono, fecha_nac from clientes where dni_cliente='"+dato+"'");
		rs1.next();
		
		System.out.println("\nDatos del usuario: " + rs1.getString("nombre"));
		System.out.println("==============================================");
		System.out.println("DNI:\t\t " + rs1.getString("dni_cliente"));
		System.out.println("Dirección:\t " + rs1.getString("direccion"));
		System.out.println("Teléfono:\t " + rs1.getString("telefono"));
		System.out.println("Fecha Nacimiento: " + rs1.getString("fecha_nac"));
		
		ResultSet rs2=c2.executeQuery("select num_cuenta, saldo, fecha_apertura, tipo_cuenta from cuenta where dni_owner='"+dato+"'");
		
		System.out.println("\n\n------------- Cuentas asignadas -----------");
		while(rs2.next())
		{
			System.out.println("Número de cuenta: " + rs2.getString("num_cuenta") + "\t Tipo de Cuenta: " + rs2.getString("tipo_cuenta")+ "\t Saldo: " + rs2.getString("saldo")+ "€");
			System.out.println("Fecha de apertura: " + rs2.getString("fecha_apertura") + "\n\n" );
		}
		c2.getConnection().close();
	}
	
	
	/*public static boolean GuardarFichero() throws IOException 
	{
		FileOutputStream fis=new FileOutputStream("datos.txt");
		ObjectOutputStream out=new ObjectOutputStream(fis);
		out.writeObject(listadoClientes);
		out.close();
		fis.close();
		return true;
	}

		public static  <listarclientes> ArrayList<Cliente> CargarClientes() throws IOException 
		{
			ArrayList<Cliente> listarclientes =new ArrayList<Cliente>();
			FileInputStream fis=new FileInputStream("datos.txt");
			ObjectInputStream out=new ObjectInputStream(fis);
			
			return Cliente Arraylist<listarclientes>.fis.;
		}*/


	public static void SacarDinero(Statement c5) throws SQLException
	{
		System.out.println("Introduce el DNI: ");
		Scanner t=new Scanner(System.in);
		String dato=t.nextLine();// Primera datos DNI
		
		System.out.println("Introduce el Número de cuenta: ");
		Scanner t1=new Scanner(System.in);
		String dato1=t1.nextLine();// Segundo dato Numero de cuenta
		
		System.out.println("Introduce la cantidad a extraer: ");
		Scanner t3=new Scanner(System.in);
		double dato3=t3.nextDouble();// Segundo dato Numero de cuenta
		
		ResultSet a=c5.executeQuery("select num_cuenta, saldo, fecha_apertura, tipo_cuenta from cuenta where dni_owner='"+dato+"' AND num_cuenta='"+dato1+"'");
		a.next();
		
		double saldocuenta=Double.parseDouble(a.getString("saldo"));
		
		if(dato3>saldocuenta)
		{
			System.out.println("Tu cuenta no tiene suficiente saldo.");
		}
		else
		{
			saldocuenta-=dato3;//resto la cantidad a la cuenta de origen
			c5.executeUpdate("UPDATE cuenta SET saldo='"+saldocuenta+"' WHERE num_cuenta='"+ a.getString("num_cuenta") +"'");
			
			//System.out.println("Retirados" +dato3+ "€ de la cuenta," + a.getString("num_cuenta") );
		}
	}
	public static void IngresarDinero(Statement c6) throws SQLException
	{
		System.out.println("Introduce el DNI: ");
		Scanner t=new Scanner(System.in);
		String dato=t.nextLine();// Primera datos DNI
		
		System.out.println("Introduce el Número de cuenta: ");
		Scanner t1=new Scanner(System.in);
		String dato1=t1.nextLine();// Segundo dato Numero de cuenta
		
		System.out.println("Introduce la cantidad a ingresar: ");
		Scanner t3=new Scanner(System.in);
		double dato3=t3.nextDouble();// Segundo dato Numero de cuenta
		
		ResultSet a=c6.executeQuery("select num_cuenta, saldo, fecha_apertura, tipo_cuenta from cuenta where dni_owner='"+dato+"' AND num_cuenta='"+dato1+"'");
		a.next();
		
		double saldocuenta=Double.parseDouble(a.getString("saldo"));

		saldocuenta+=dato3;//resto la cantidad a la cuenta de origen
		c6.executeUpdate("UPDATE cuenta SET saldo='"+saldocuenta+"' WHERE num_cuenta='"+ a.getString("num_cuenta") +"'");
			
		System.out.println("Ingresados, " +dato3+ "€ de la cuenta," + " a la cuenta, '" + a.getString("num_cuenta")+"'");

	}
	public static void Transferencias(Statement c4) throws SQLException
	{

		System.out.println("Introduce el DNI: ");
		Scanner t=new Scanner(System.in);
		String dato=t.nextLine();// Primera datos DNI
		
		System.out.println("Introduce el Número de cuenta de origen: ");
		Scanner t1=new Scanner(System.in);
		String dato1=t1.nextLine();// Segundo dato Numero de cuenta
		
		System.out.println("Introduce el Número de cuenta de destino: ");
		Scanner t2=new Scanner(System.in);
		String dato2=t2.nextLine();// Segundo dato Numero de cuenta
		
		System.out.println("Introduce la cantidad a transferir: ");
		Scanner t3=new Scanner(System.in);
		double dato3=t3.nextDouble();// Segundo dato Numero de cuenta
		
		//Saco número de cuenta origen y su saldo
		ResultSet rs1=c4.executeQuery("select num_cuenta, saldo, from cuenta where dni_owner='"+dato+"'AND num_cuenta= '"+dato1+"'");
		//ResultSet a=c4.executeQuery("select num_cuenta, saldo, fecha_apertura, tipo_cuenta from cuenta where dni_owner='"+dato+"' AND num_cuenta='"+dato1+"'");

		//Saco número de cuenta de destino y su saldo
		ResultSet b=c4.executeQuery("select num_cuenta, saldo, fecha_apertura, tipo_cuenta from cuenta where num_cuenta='"+dato2+"'");
		


		double saldoOrigen= Double.parseDouble((rs1.getString("saldo")));
		
		double saldoDestino= Double.parseDouble((b.getString("saldo")));
		
		if(dato3>saldoOrigen)
		{
			System.out.println("Tu cuenta no tiene suficiente saldo.");
		}
		else
		{
			saldoOrigen-=dato3;//resto la cantidad a la cuenta de origen
			saldoDestino+=dato3;//sumo la cantidad a la cuenta destino
			c4.executeUpdate("UPDATE cuenta SET saldo='"+saldoOrigen+"' WHERE num_cuenta='"+ rs1.getString("num_cuenta") +"'");
			c4.executeUpdate("UPDATE cuenta SET saldo='"+saldoDestino+"' WHERE num_cuenta='"+ b.getString("num_cuenta") +"'");	
			//System.out.println("Transferencia de" +dato3+ "€ realizada con éxito desde la cuenta," + a.getString("num_cuenta") + " a la cuenta, " + b.getString("num_cuenta") );
		}
	}
	
	static void ConsultarSaldo(Statement c3) throws SQLException
	{
		System.out.println("Introduce el DNI: ");
		Scanner t=new Scanner(System.in);
		String dato1=t.nextLine();// Primera datos DNI
		
		System.out.println("Introduce el Número de cuenta a consultar: ");
		Scanner t2=new Scanner(System.in);
		String dato2=t2.nextLine();// Segundo dato Numero de cuenta
		
		ResultSet rs1=c3.executeQuery("select num_cuenta, saldo, fecha_apertura, tipo_cuenta from cuenta where dni_owner='"+dato1+"' AND num_cuenta='"+dato2+"'");
		rs1.next();
		
		System.out.println("\nDatos de la cuenta: " + rs1.getString("num_cuenta"));
		System.out.println("==============================================");
		System.out.println("Saldo:\t\t " + rs1.getString("saldo") + "€");
		System.out.println("Fecha apertura:\t " + rs1.getString("fecha_apertura"));
		System.out.println("Tipo de cuenta:\t " + rs1.getString("tipo_cuenta") + "\n\n");
		
		c3.getConnection().close();
	}
	
	/*void Ver_datos(String DNI)
	{
		Cliente cli=new Cliente();
		
		
		String vd=("Documento identificación cliente: " + cli.getDNI() +".\n" 
					+ "Nombre: "+ cli.getNombre() +".\n" 
					+ "Fecha nacimiento: "+ cli.getFnacimiento() +".\n" 
					+ "Dirección: "+ cli.getDireccion() +".\n" 
					+ "Teléfono: "+ cli.getTelefono() +".\n" 				
					+ "Número de cuenta: " + cli.getCcli() + ".\n");
	}	*/
	

}
