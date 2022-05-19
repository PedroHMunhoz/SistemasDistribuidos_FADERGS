import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceRMI extends Remote {
    //Interface:

public int getFatorial (int fatorial) throws RemoteException;
public String dataAtual () throws RemoteException;
public String horaAtual () throws RemoteException;
public String boasVindas(String nomeUsuario) throws RemoteException;
public int ordenarLista(int[] listaOrdenada)  throws RemoteException; 
public boolean validarCPF(String cpf)    throws RemoteException;       


int result = 0;

public int getFatorial (int fatorial) {
	
	while(fatorial < 1) {
		
		result+=fatorial*(fatorial-1);
		fatorial--;
	}
}
//
public String dataAtual () {
	
	SimpleDateFormat form = new SimpleDateFormat("dd/mm/yyyy");
	String data = new Date().toString();

return form.format(data);

}
//
public String horaAtual () {
	
	SimpleDateFormat form = new SimpleDateFormat("hh:mm:ss");
	String hora = new Date().toString();

return form.format(hora);
	
}
public String boasVindas (String nomeUsuario) {
	
	// System.out.println(objeto.boasVindas(nomeDigitado));
}

public int ordenarLista(int[] listaOrdenada)    {

return int[]listaOrdenada;
}


public boolean validarCPF(String cpf)  {


return boolean; 

}

                         
}