
import java.net.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TCPServer {

    public static void main(String args[]) {
        try {
            int serverPort = 7896;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while (true) {
                Socket clientSocket = listenSocket.accept();
                Connection c = new Connection(clientSocket);
            }
        } catch (IOException e) {
            System.out.println("Listen :" + e.getMessage());
        }
    }
}

class Connection extends Thread {

    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;
    String clientIP;
    String clientPort;

    public Connection(Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());

            clientIP = String.valueOf(clientSocket.getInetAddress());
            clientPort = String.valueOf(clientSocket.getPort());
            this.start();
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
    }

    public void run() {
        try { // an echo server
            String data = in.readUTF();

            String requestFullValue = data.toUpperCase().trim();
            String requestType = data.toUpperCase().trim().split(" ")[0];
            String meuRetorno = "";

            switch (requestType) {
                case "DATA":
                    meuRetorno = retornarDataAtualServidor();

                    out.writeUTF(meuRetorno);
                    break;
                case "MULTIPLIQUE":
                    Float valorRecebido = Float.parseFloat(requestFullValue.split(" ")[1]);
                    Float valorRecebido2 = Float.parseFloat(requestFullValue.split(" ")[2]);
                    Float resultado = multiplicar(valorRecebido, valorRecebido2);

                    meuRetorno = resultado.toString();

                    out.writeUTF(meuRetorno);
                    break;
                case "FATORIAL":
                    int valorRecebidoFatorial = Integer.parseInt(requestFullValue.split(" ")[1]);
                    long resultadoFatorial = fatorialDoNumero(valorRecebidoFatorial);

                    meuRetorno = String.valueOf(resultadoFatorial);

                    out.writeUTF(meuRetorno);
                    break;
                case "RAIZ2":
                    int valorRecebidoRaiz = Integer.parseInt(requestFullValue.split(" ")[1]);
                    double resultadoRaizQuadrada = raizquadrada(valorRecebidoRaiz);

                    meuRetorno = String.valueOf(resultadoRaizQuadrada);

                    out.writeUTF(meuRetorno);
                    break;
                case "DISCO":
                    long resultadoEspacoEmDisco = retornarEspacoEmDisco();
                    String espacoEmDiscoFormatado = "Espaco dispon�vel : " + resultadoEspacoEmDisco / (1024 * 1024)
                            + " MB";

                    meuRetorno = espacoEmDiscoFormatado;

                    out.writeUTF(meuRetorno);
                    break;
                case "IP":
                    out.writeUTF(clientIP);
                    break;

                case "PORTA":
                    out.writeUTF(clientPort);
                    break;
                default:
                    meuRetorno = "FUNCAO NAO ENCONTRADA";
                    out.writeUTF(meuRetorno);
                    break;
            }

        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {/* close failed */
            }
        }
    }

    private static String retornarDataAtualServidor() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    private static float multiplicar(float valor1, float valor2) {
        return valor1 * valor2;
    }

    private static long fatorialDoNumero(int num) {
        int anterior = num - 1;
        long fatorialDesteNumero = 0;

        if (num <= 1) {
            return 1;
        } else {
            fatorialDesteNumero = num * fatorialDoNumero(anterior);
        }

        return fatorialDesteNumero;
    }

    private static double raizquadrada(int num) {
        return Math.sqrt(num);
    }

    private static long retornarEspacoEmDisco() {
        File diskPartition = new File("C:");
        long espacoLivre = diskPartition.getFreeSpace();
        return espacoLivre;
    }
}
