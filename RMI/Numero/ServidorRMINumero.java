import java.rmi.RemoteException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*;
import java.io.Serializable;

public class ServidorRMINumero implements InterfaceRMINumero, Serializable
{
	
	static public void main (String rmi[])
	{
		try 
		{
			ServidorRMINumero objetoServidor = new ServidorRMINumero();
			UnicastRemoteObject.exportObject(objetoServidor);
			Registry registry = LocateRegistry.getRegistry(1099);
			registry.bind("numero", objetoServidor);
		} 
		catch (Exception exc) 
		{
			System.err.println (exc.toString());
		}
	}	

	public int quadrado(int num)
	{
		return num*num;
	}

	public int cubo(int num)
	{
		return num*num*num;
	}
}
