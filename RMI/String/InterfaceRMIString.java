import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceRMIString extends Remote {
	public String getData() throws RemoteException;

	public String getNome() throws RemoteException;

	public String bemVindo(String nome) throws RemoteException;
}
