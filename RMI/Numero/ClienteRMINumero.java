import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.Naming;

public class ClienteRMINumero
{
	static public void main (String rmi[])
	{
		try 
		{
			String localizacao = "//127.0.0.1/numero";
			InterfaceRMINumero objeto = (InterfaceRMINumero) Naming.lookup (localizacao);
			
			System.out.println ("2 ao quadrado eh: " + objeto.quadrado(2));
			System.out.println ("2 ao cubo eh: " + objeto.cubo(2));
		} 
		catch (Exception exc) 
		{
			System.err.println (exc.toString());
		}
	}	
}
