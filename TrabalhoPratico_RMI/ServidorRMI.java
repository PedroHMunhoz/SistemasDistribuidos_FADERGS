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

    String mensagemBoasVindas = "Seja bem vindo(a) " + nomeUsuario;
    return mensagemBoasVindas;
  }

  public List<Integer> ordenarLista(List<Integer> listaInteiros) {

    Collections.sort(listaInteiros);

    return listaInteiros;
  }

  public boolean validarCPF(String cpf) {
    int i, soma, resto1, digito1, resto2, digito2; // Variáveis de apoio

    // Inicialização de variáveis para evitar lixo de memória
    soma = 0;
    resto1 = 0;
    resto2 = 0;
    digito1 = 0;
    digito2 = 0;

    if (cpf.length() < 11 || cpf.length() > 11) {
      return false;
    } else {

      char[] strArray = cpf.toCharArray();

      for (i = 0; i < 9; i++) {
        soma += Integer.parseInt(String.valueOf(strArray[i])) * (10 - i);
      }

      // Calcula o primeiro resto, fazendo a soma MOD 11
      resto1 = soma % 11;

      // Se por acaso o resto do MOD 11 for 0 ou 1, então o dígito deve ser 0
      if ((resto1 == 0) || (resto1 == 1)) {
        digito1 = 0;
      } else // Caso contrário, o dígito será 11 menos o valor do resto
      {
        digito1 = 11 - resto1;
      }

      soma = 0;

      // Calculando o segundo digito
      for (i = 0; i < 10; i++) {
        soma += Integer.parseInt(String.valueOf(strArray[i])) * (11 - i);
      }

      // Calcula o segundo resto, fazendo a soma MOD 11
      resto2 = soma % 11;

      // A regra aqui é a mesma do primeiro dígito, se o resto do MOD 11 for 0 ou 1, o
      // dígito deve ser 0
      if ((resto2 == 0) || (resto2 == 1)) {
        digito2 = 0;
      } else // Caso contrário, o dígito será 11 menos o valor do resto
      {
        digito2 = 11 - resto2;
      }

      if ((digito1 == Integer.parseInt(String.valueOf(strArray[9]))
          && (digito2 == Integer.parseInt(String.valueOf(strArray[10]))))) {
        return true;
      } else {
        return false;
      }
    }

  }
}