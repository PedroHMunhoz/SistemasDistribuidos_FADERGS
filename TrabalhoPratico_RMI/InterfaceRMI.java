import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface InterfaceRMI extends Remote {

	public int getFatorial(int numeroFatorial) throws RemoteException;

	public String dataAtual() throws RemoteException;

	public String horaAtual() throws RemoteException;

	public String boasVindas(String nomeUsuario) throws RemoteException;

	public List<Integer> ordenarLista(List<Integer> listaInteiros) throws RemoteException;

	public boolean validarCPF(String cpf) throws RemoteException;
}