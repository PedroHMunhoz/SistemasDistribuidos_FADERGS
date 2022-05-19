import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
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

  public int getFatorial(int numeroFatorial) {

    if (numeroFatorial <= 1) {
      return 1;
    } else {
      return numeroFatorial * getFatorial(numeroFatorial - 1);
    }
  }

  public String dataAtual() {

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

    Date data = new Date();

    return formato.format(data);

  }

  public String horaAtual() {

    SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");

    Date hora = new Date();

    return formato.format(hora);

  }

  public String boasVindas(String nomeUsuario) {

    // System.out.println(objeto.boasVindas(nomeDigitado));
    return "";
  }

  public List<Integer> ordenarLista(List<Integer> listaInteiros) {

    Collections.sort(listaInteiros);

    return listaInteiros;
  }

  public boolean validarCPF(String cpf) {

    return true;

  }

}