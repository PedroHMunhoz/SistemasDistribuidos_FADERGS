
import java.net.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UDPServer {

    public static void main(String args[]) {
        DatagramSocket aSocket = null;
        try {
            aSocket = new DatagramSocket(6789);
            byte[] buffer = new byte[1000];
            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
                DatagramPacket reply = new DatagramPacket(request.getData(),
                        request.getLength(), request.getAddress(), request.getPort());

                String requestFullValue = new String(request.getData()).toUpperCase().trim();
                String requestType = new String(request.getData()).toUpperCase().trim().split(" ")[0];
                String meuRetorno = "";
                int tamanho = 0;

                switch (requestType) {
                    case "DATA":
                        meuRetorno = retornarDataAtualServidor();
                        tamanho = meuRetorno.length();

                        reply = new DatagramPacket(meuRetorno.getBytes(),
                                tamanho, request.getAddress(), request.getPort());
                        break;
                    case "MULTIPLIQUE":
                        Float valorRecebido = Float.parseFloat(requestFullValue.split(" ")[1]);
                        Float valorRecebido2 = Float.parseFloat(requestFullValue.split(" ")[2]);
                        Float resultado = multiplicar(valorRecebido, valorRecebido2);

                        meuRetorno = resultado.toString();
                        tamanho = meuRetorno.length();

                        reply = new DatagramPacket(meuRetorno.getBytes(),
                                tamanho, request.getAddress(), request.getPort());
                        break;
                    case "FATORIAL":
                        int valorRecebidoFatorial = Integer.parseInt(requestFullValue.split(" ")[1]);
                        long resultadoFatorial = fatorialDoNumero(valorRecebidoFatorial);

                        meuRetorno = String.valueOf(resultadoFatorial);
                        tamanho = meuRetorno.length();

                        reply = new DatagramPacket(meuRetorno.getBytes(),
                                tamanho, request.getAddress(), request.getPort());
                        break;
                    case "RAIZ2":
                        int valorRecebidoRaiz = Integer.parseInt(requestFullValue.split(" ")[1]);
                        double resultadoRaizQuadrada = raizquadrada(valorRecebidoRaiz);

                        meuRetorno = String.valueOf(resultadoRaizQuadrada);
                        tamanho = meuRetorno.length();

                        reply = new DatagramPacket(meuRetorno.getBytes(),
                                tamanho, request.getAddress(), request.getPort());
                        break;
                    case "DISCO":
                        long resultadoEspacoEmDisco = retornarEspacoEmDisco();
                        String espacoEmDiscoFormatado = "Espaço disponível : " + resultadoEspacoEmDisco / (1024 * 1024) + " MB";

                        meuRetorno = espacoEmDiscoFormatado;
                        tamanho = meuRetorno.length();

                        reply = new DatagramPacket(meuRetorno.getBytes(),
                                tamanho, request.getAddress(), request.getPort());
                        break;
                    case "IP":
                        meuRetorno = request.getAddress().toString();
                        tamanho = meuRetorno.length();

                        reply = new DatagramPacket(meuRetorno.getBytes(),
                                tamanho, request.getAddress(), request.getPort());
                        break;

                    case "PORTA":
                        meuRetorno = String.valueOf(request.getPort());
                        tamanho = meuRetorno.length();

                        reply = new DatagramPacket(meuRetorno.getBytes(),
                                tamanho, request.getAddress(), request.getPort());
                        break;
                    default:
                        meuRetorno = "FUNCAO NAO ENCONTRADA";
                        tamanho = meuRetorno.length();
                        reply = new DatagramPacket(meuRetorno.getBytes(),
                                tamanho, request.getAddress(), request.getPort());
                        break;
                }

                aSocket.send(reply);
            }
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (aSocket != null) {
                aSocket.close();
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
