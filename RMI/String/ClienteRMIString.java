import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.Naming;

public class ClienteRMIString {
	static public void main(String rmi[]) {
		try {
			String localizacao = "127.0.0.1";
			InterfaceRMIString objeto = (InterfaceRMIString) Naming.lookup(localizacao);

			System.out.println(objeto.bemVindo("Pedro"));
		} catch (Exception exc) {
			System.err.println(exc.toString());
		}
	}
}
