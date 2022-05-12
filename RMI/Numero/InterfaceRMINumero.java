import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceRMINumero extends Remote
{
	public int quadrado(int num) throws RemoteException;
	public int cubo(int num) throws RemoteException;
}
