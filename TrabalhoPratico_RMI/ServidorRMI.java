import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Naming;

public class ServidorRMI extends UnicastRemoteObject implements InterfaceRMI {
  public ServidorRMI() throws RemoteException {
    super();
  }

  static public void main(String rmi[]) {

    try {
      ServidorRMI objetoServidor = new ServidorRMI();
      String localizacao = "127.0.0.1";
      Naming.rebind(localizacao, objetoServidor);
    } catch (

    Exception exc) {
      System.err.println(exc.toString());
    }
  }
}