import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Naming;
import java.util.Date;

public class ServidorRMIString extends UnicastRemoteObject implements InterfaceRMIString {
	public ServidorRMIString() throws RemoteException {
		super();
	}

	static public void main(String rmi[]) {
		try {
			ServidorRMIString objetoServidor = new ServidorRMIString();
			String localizacao = "127.0.0.1";
			Naming.rebind(localizacao, objetoServidor);
		} catch (Exception exc) {
			System.err.println(exc.toString());
		}
	}

	public String getData() {
		String data = new Date().toString();
		return data;
	}

	public String getNome() {
		String nome = "Pastel";
		return nome;
	}

	public String bemVindo(String nome) {
		String frase = "Bem vindo " + nome;
		return frase;
	}
}
