import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.Naming;

public class ClienteRMI {
  static public void main(String rmi[]) {
    try {
      String localizacao = "127.0.0.1";
			InterfaceRMI objeto = (InterfaceRMI) Naming.lookup(localizacao);
      System.out.println("BOMBOu");
    } catch (Exception exc) {
      System.err.println(exc.toString());
    }
  }
}
