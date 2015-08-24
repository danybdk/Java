import java.io.Serializable;
import java.util.Date;

public class Cuenta implements Serializable {

		private String NCuenta;
		private double Saldo;
		private TCuentas TipoCuenta;
		private String FApertura;
		private String DNI_owner;
		
		
		
		
		public Cuenta(String nCuenta, double saldo, TCuentas tipoCuenta, String fApertura, String dni_owner)
		{
			super();
			NCuenta = nCuenta;
			Saldo = saldo;
			TipoCuenta = tipoCuenta;
			FApertura = fApertura;
			DNI_owner = dni_owner;
		}
		public String getNCuenta() {return NCuenta;}
		public void setNCuenta(String nCuenta) {NCuenta = nCuenta;}
		public double getSaldo() {return Saldo;}
		public void setSaldo(double saldo) {Saldo = saldo;}
		public TCuentas getTipoCuenta() {return TipoCuenta;	}
		public void setTipoCuenta(TCuentas tipoCuenta) {TipoCuenta = tipoCuenta;}
		public String getFApertura() {return FApertura;}
		public void setFApertura(String fApertura) {FApertura = fApertura;}
		public String getDNI_owner() {return DNI_owner;}
		public void setDNI_owner(String dNI_owner) {DNI_owner = dNI_owner;}		
		
	
}
