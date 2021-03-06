import java.rmi.Naming;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class ClienteRMI {
  static public void main(String rmi[]) {
    try {
      String localizacao = "127.0.0.1";
      InterfaceRMI objeto = (InterfaceRMI) Naming.lookup(localizacao);

      // Variável para a leitura do usuário
      Scanner leitura = new Scanner(System.in);

      // Variável para a opção selecionada no menu de opções
      int opcaoMenu = 0;

      // Variável para o nome digitado para o método Boas Vindas
      String nomeDigitado = "";

      // Variável para o fatorial que o usuário deseja obter
      int fatorialDesejado = 0;

      // Variável para ler os numeros digitados pelo usuário
      String numerosListaDigitados = "";

      // Variável para a lista de inteiros a ser ordenada
      List<Integer> listaInteiros = new ArrayList<Integer>();

      // Variável para o CPF que for digitado
      String cpfDigitado = "";

      while (opcaoMenu != 7) {
        System.out.println("\n########## MENU DE OPCOES RMI ##########");
        System.out.println("1. Fatorial");
        System.out.println("2. Data Atual");
        System.out.println("3. Hora Atual");
        System.out.println("4. Mensagem de Boas Vindas");
        System.out.println("5. Ordenar Lista");
        System.out.println("6. Validar CPF");
        System.out.println("7. Sair\n");

        // Atribiu o valor digitado pelo usuário
        opcaoMenu = leitura.nextInt();

        // O Switch/Case define qual opção será acessada baseada no
        // valor informado pelo usuário
        switch (opcaoMenu) {
          case 1:
            // Pede que o usuário digite o valor para calcular o fatorial
            System.out.println("Digite o valor que deseja obter o fatorial:");

            // Lê o valor digitado pelo usuário
            fatorialDesejado = leitura.nextInt();

            // Executa o método no servidor remoto e imprime o valor retornado
            System.out.println("O fatorial de " + fatorialDesejado + " e': " +
                objeto.getFatorial(fatorialDesejado));
            break;
          case 2:
            // Executa o método no servidor remoto e imprime o valor
            System.out.println("A Data Atual do sistema e': " + objeto.dataAtual());
            break;
          case 3:
            // Executa o método no servidor remoto e imprime o valor
            System.out.println("A Hora Atual do sistema e': " + objeto.horaAtual());
            break;
          case 4:
            // Pede que o usuário digite o nome
            System.out.println("Digite um nome para receber a mensagem:");

            // Lê o valor digitado pelo usuário
            nomeDigitado = leitura.next();

            // Limpa buffer de teclado
            leitura.nextLine();

            // Executa o método no servidor remoto e imprime o valor
            System.out.println(objeto.boasVindas(nomeDigitado));
            break;
          case 5:
            // Pede que o usuário digite os números da lista
            System.out.println("Digite numeros inteiros separados por virgula:");

            // Lê o valor digitado pelo usuário
            numerosListaDigitados = leitura.next();

            // Limpa buffer de teclado
            leitura.nextLine();

            // Adiciona os valores lidos do usuário na lista de inteiros
            for (String numero : numerosListaDigitados.split(",")) {
              listaInteiros.add(Integer.parseInt(numero));
            }

            // Executa o método no servidor remoto e imprime o valor
            System.out.println("Sua lista digitada ordenada:");
            System.out.println(objeto.ordenarLista(listaInteiros));

            break;
          case 6:
            // Pede que o usuário digite os números da lista
            System.out.println("Digite o CPF a ser validado, sem espaços nem caracteres especiais:");

            // Lê o valor digitado pelo usuário
            cpfDigitado = leitura.next();

            // Limpa buffer de teclado
            leitura.nextLine();

            // Executa o método no servidor remoto e imprime o valor
            boolean cpfValido = objeto.validarCPF(cpfDigitado);

            // Mostra a mensagem conforme o retorno do método
            if (cpfValido) {
              System.out.println("O CPF digitado e' valido!");
            } else {
              System.out.println("O CPF digitado nao e' valido!");
            }

            break;
          case 7:
            System.out.println("Saindo do sistema...");
            break;
          default:
            // Se for digitada alguma opção inválida, cai nesse case
            System.out.println("Opção invalida!");
            break;
        }
      }
    } catch (Exception exc) {
      System.err.println(exc.toString());
    }
  }
}
